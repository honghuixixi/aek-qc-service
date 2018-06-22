package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.CheckStatus;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.QcImplementMapper;
import com.aek.ebey.qc.mapper.QcPlanMapper;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.QcDept;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.model.QcPlanCheck;
import com.aek.ebey.qc.query.QcPlanRecordQuery;
import com.aek.ebey.qc.query.QcReportQuery;
import com.aek.ebey.qc.request.QcAssetsRequest;
import com.aek.ebey.qc.request.QcBeginImplementRequest;
import com.aek.ebey.qc.request.QcChargeRequest;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImplementRequest;
import com.aek.ebey.qc.request.QcImplementSubmitRequest;
import com.aek.ebey.qc.request.QcImplementSubmitRequestx;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;
import com.aek.ebey.qc.request.QcPlanRecordResponse;
import com.aek.ebey.qc.request.QcReportResponse;
import com.aek.ebey.qc.request.TimeQuery;
import com.aek.ebey.qc.service.QcAssetsService;
import com.aek.ebey.qc.service.QcDeptService;
import com.aek.ebey.qc.service.QcImplementService;
import com.aek.ebey.qc.service.QcPlanCheckService;
import com.aek.ebey.qc.service.QcPlanService;
import com.aek.ebey.qc.service.feign.AssetsClientService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.aek.ebey.qc.service.feign.RepairClientService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@Service
@Transactional
public class QcImplementServiceImpl extends BaseServiceImpl<QcImplementMapper, QcImplement> implements QcImplementService {
	
	private static final int DEFAULT_THREAD_POOL_SIZE = 100;
	
	private ExecutorService executorService;
	
	@Autowired
	private AssetsClientService assetsClientService;

	@Autowired
	private QcImplementService qcImplementService;
	
	@Autowired
	private QcImplementMapper qcImplementMapper;
	
	@Autowired
	private QcPlanMapper qcPlanMapper;
	
	@Autowired
	private QcPlanService qcPlanService;
	
	@Autowired
	private QcDeptService qcDeptService;
	
	
	@Autowired
	private QcAssetsService qcAssetsService;
	
	@Autowired
	private  RepairClientService repairClientService;
	
	@Autowired
	private  QcPlanCheckService qcPlanCheckService;
	
	@Autowired
	private  DeptClientService deptClientService;
	
	
	
	public QcImplementServiceImpl() {
		this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
	}

	@Override
	public void save(QcBeginImplementRequest request) {
		QcPlan  qcPlan =qcPlanService.selectById(request.getId());
		
		if(qcPlan!=null&&qcPlan.getStatus().intValue()==StatusConstants.ONE.intValue()){
			Long tenantId=qcPlan.getTenantId();
			Wrapper<QcImplement> wrapperQcImplement=new EntityWrapper<>();
			wrapperQcImplement.eq("status", 1).eq("paln_id", request.getId());
			List<QcImplement> list=qcImplementService.selectList(wrapperQcImplement);
			if(list!=null&&list.size()>0){
				throw ExceptionFactory.create("Q_006");
			}
			QcImplement qcimplement=new QcImplement();
			qcimplement.setPalnId(request.getId());
			qcimplement.setStatus(1);
			qcimplement.setPlanDate(qcPlan.getNextTime()==null?qcPlan.getFirstTime():qcPlan.getNextTime());
			Date date=new Date();
			qcimplement.setCreateDate(date);
			qcimplement.setActualStartDate(date);
			qcimplement.setTenantId(tenantId);
			qcImplementService.insert(qcimplement);
			AuthUser authUser = WebSecurityUtils.getCurrentUser();
			Wrapper<QcDept> wrapper=new EntityWrapper<>();
			wrapper.eq("plan_id", request.getId());
			List<QcDept> listQcDept=qcDeptService.selectList(wrapper);
			List<Long>  listdeptIds=new ArrayList<>();
			Long[] deptIds=null;
			Result<Page<QcAssets>> result=null;
			int current=1;
			int size=1000;
			int totalPage=0;
			int total2Page=0;
			int total = 0;
			Page page = new Page();
			page.setSize(size);
			page.setCurrent(current);
			if(listQcDept!=null&&listQcDept.size()>0){
				for(QcDept qcDept:listQcDept){
					listdeptIds.add(qcDept.getDeptId());
				}
				deptIds = listdeptIds.toArray(new Long[listdeptIds.size()]);
			}
			List<QcAssets> listDept=new ArrayList<>();
			if(authUser!=null){
				result = assetsClientService.getQcAssetsPage(page,authUser.getTenantId(), WebSecurityUtils.getCurrentToken(), deptIds);
				if(null == result){
					throw ExceptionFactory.create("Q_005");     //服务器异常
				}else{
					if(null != result.getData()){
						if(null != result.getData().getRecords()){
							if(result.getData().getRecords().size() == 0){
								throw ExceptionFactory.create("Q_007"); //巡检范围内无设备，请添加后再开始巡检
							}else{
								for (QcAssets qcAssets : result.getData().getRecords()) {
									qcAssets.setImplementId(qcimplement.getId());
									qcAssets.setResult(request.getAnswers());
									qcAssets.setResultStatus(request.getResultStatus());
									qcAssets.setImStatus(1);
									qcAssets.setTenantId(tenantId);
									listDept.add(qcAssets);
								}
								totalPage=result.getData().getPages();
								total = result.getData().getTotal();
								
								while(current<=totalPage){
									current++;
									total2Page++;
									page.setCurrent(current);
									result = assetsClientService.getQcAssetsPage(page,authUser.getTenantId(), WebSecurityUtils.getCurrentToken(), deptIds);
									if(null != result && result.getData() != null){
										for (QcAssets qcAssets : result.getData().getRecords()) {
											qcAssets.setImplementId(qcimplement.getId());
											qcAssets.setResult(request.getAnswers());
											qcAssets.setResultStatus(request.getResultStatus());
											qcAssets.setImStatus(1);
											qcAssets.setTenantId(tenantId);
											listDept.add(qcAssets);
										}
									}
								}
							}
						}
						
					}
				}
				if(listDept.size() < total){
					throw ExceptionFactory.create("Q_005"); //设备信息不全
				}
				
				/*while(result != null&&result.getData()!=null&&result.getData().getRecords()!=null&&result.getData().getRecords().size()>0){
					if(result.getData() != null&&current<=result.getData().getPages()){
						total=result.getData().getPages();
						for (QcAssets qcAssets : result.getData().getRecords()) {
							qcAssets.setImplementId(qcimplement.getId());
							qcAssets.setResult(request.getAnswers());
							qcAssets.setResultStatus(request.getResultStatus());
							listDept.add(qcAssets);
						}
						current++;
						total2++;
						page.setCurrent(current);
						result = assetsClientService.getQcAssetsPage(page,authUser.getTenantId(), WebSecurityUtils.getCurrentToken(), deptIds);
					}
					
				}
				if(totalPage!=total2Page||total==0){
					listDept.clear();
					throw ExceptionFactory.create("Q_005");
				}
				*/
			}
			//设备处理
			if(listDept.size()>0){
				qcAssetsService.insertBatch(listDept);
			}
			if(1==qcPlan.getCheckStatus().intValue()){
				qcPlan.setCheckStatus(2);
				qcPlanService.updateById(qcPlan);
			}else{
				throw ExceptionFactory.create("Q_010");
			}
		}else{
			throw ExceptionFactory.create("Q_010");
		}
		
	}

	@Override
	public void add(QcImplementRequest request) {
		if(request!=null&&request.getId()!=null){
			
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", request.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement qcImplement=list.get(0);
				qcImplement.setActualStartDate(request.getActualStartDate());
				qcImplement.setActualEndDate(request.getActualEndDate());
				qcImplement.setCondition(request.getCondition());
				qcImplement.setAnalysis(request.getAnalysis());
				qcImplement.setSuggestion(request.getSuggestion());
				qcImplementService.updateById(qcImplement);
				List<QcAssetsRequest>  listQcAssetsRequest=request.getRecords();
				if(listQcAssetsRequest!=null&&listQcAssetsRequest.size()>0){
					List<QcAssets>  listQcAssets=new  ArrayList<>();
					for(QcAssetsRequest qcAssetsRequest:listQcAssetsRequest){
						QcAssets assets=new QcAssets();
						assets.setResult(qcAssetsRequest.getAnswers());
						assets.setAssetsId(qcAssetsRequest.getId());
						assets.setResultStatus(qcAssetsRequest.getStatus());
						assets.setRemarks(qcAssetsRequest.getRemarks());
						assets.setImplementId(qcImplement.getId());
						listQcAssets.add(assets);
					}
					if(listQcAssets.size()>0){
						qcImplementService.batchUpdate(listQcAssets);
					}
					
				}
			}else{
				throw ExceptionFactory.create("Q_003");
			}	
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		
	}

	@Override
	public Long submitImplement(QcImplementSubmitRequest request) {
		AuthUser authUser=WebSecurityUtils.getCurrentUser();
		if(request!=null&&request.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", request.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement qcImplement=list.get(0);
				Wrapper<QcAssets> wrapperQcAssets=new EntityWrapper<>();
				wrapperQcAssets.eq("implement_id", qcImplement.getId()).eq("result_status", 1);
				List<QcAssets> listQcAssets=qcAssetsService.selectList(wrapperQcAssets);
				if(listQcAssets!=null&&listQcAssets.size()>0){
					throw ExceptionFactory.create("Q_004");
				}
				Wrapper<QcAssets> wrapperQcAssets2=new EntityWrapper<>();
				wrapperQcAssets2.eq("implement_id", qcImplement.getId());
				List<QcAssets> listQcAssets2=qcAssetsService.selectList(wrapperQcAssets2);
				if(listQcAssets2!=null&&listQcAssets2.size()>0){
					for(QcAssets qcAssets:listQcAssets2){
						qcAssets.setCreateTime(new Date());
					}
				}
				qcAssetsService.updateBatchById(listQcAssets2);
				qcImplement.setStatus(2);
				qcImplement.setActualEndDate(request.getActualEndDate());
				qcImplement.setReportNo(QcUtils.reportNo());
				QcPlan qcPlan=qcPlanService.selectById(request.getId());
				if(qcPlan!=null&&qcPlan.getCheckStatus().intValue()==2){
					qcPlan.setNextTime(request.getNexDate());
					qcImplement.setNextTime(request.getNexDate());
					qcImplement.setCreateDate(new Date());
					qcImplement.setChargeId(qcPlan.getChargeId());
					qcImplement.setChargeName(qcPlan.getChargeName());
					qcPlan.setPreTime(request.getActualEndDate());
					qcPlan.setCheckStatus(1);
					qcPlan.setMsgStatus(1);
					QcChargeRequest nextChargeMan=request.getNextChargeMan();
					if(nextChargeMan!=null){
						qcPlan.setChargeId(nextChargeMan.getId());
						qcPlan.setChargeName(nextChargeMan.getName());
						Result<Boolean> ret=deptClientService.checkUserPermission(nextChargeMan.getId(), "QC_PLAN_IMPLEMENT",WebSecurityUtils.getCurrentToken());
						if(ret!=null){
							if(!ret.getData()){
								throw ExceptionFactory.create("Q_022");
							}
						}else{
							throw ExceptionFactory.create("Q_005");
						}
					}
					//qcPlanService.updateById(qcPlan);
				}
				Wrapper<QcPlanCheck> wrappercheck=new EntityWrapper<>();
				wrappercheck.eq("paln_id", qcPlan.getId()).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNull("implement_id");
				List<QcPlanCheck> listCheck = qcPlanCheckService.selectList(wrappercheck);
				if (listCheck != null && listCheck.size() > 0) {
					for (QcPlanCheck qcPlanCheck : listCheck) {
						qcPlanCheck.setDelFlag(true);
					}
					qcPlanCheckService.updateBatchById(listCheck);
				}
				List<QcChargeRequest> listMan = request.getCheckMan();
				if (listMan != null && listMan.size() > 0) {
					List<QcPlanCheck> listqcCheck = new ArrayList<>();
					List<QcPlanCheck> listqcCheck2 = new ArrayList<>();
					List<Long> ids=new ArrayList<>();
					//需要验收
					//qcImplement.setNeedCheck(NeedStatus.TWO.getCode());
					qcImplement.setIsCheck(CheckStatus.ONE.getCode());
					for (QcChargeRequest man : listMan) {
						QcPlanCheck qcCheck = new QcPlanCheck();
						QcPlanCheck qcCheck2 = new QcPlanCheck();
						qcCheck.setPalnId(qcPlan.getId());
						qcCheck2.setPalnId(qcPlan.getId());
						qcCheck.setTenantId(qcPlan.getTenantId());
						qcCheck2.setTenantId(qcPlan.getTenantId());
						qcCheck.setImplementId(qcImplement.getId());
						qcCheck.setUserId(man.getId());
						qcCheck2.setUserId(man.getId());
						qcCheck.setUserName(man.getName());
						qcCheck2.setUserName(man.getName());
						qcCheck.setCreateBy(authUser.getId());
						qcCheck2.setCreateBy(authUser.getId());
						listqcCheck.add(qcCheck);
						listqcCheck2.add(qcCheck2);
						ids.add(man.getId());
					}
					Result<Boolean> ret=deptClientService.checkUserPermissions(ids, "QC_CHECK_MANAGE",authUser.getTenantId(), WebSecurityUtils.getCurrentToken());
					if(ret!=null){
						if(!ret.getData()){
							throw ExceptionFactory.create("Q_020");
						}
					}else{
						throw ExceptionFactory.create("Q_005");
					}
					qcPlanCheckService.insertBatch(listqcCheck);
					qcPlanCheckService.insertBatch(listqcCheck2);
				}else{
					//不需要验收
					//qcImplement.setNeedCheck(NeedStatus.ONE.getCode());
					qcImplement.setIsCheck(CheckStatus.TWO.getCode());
				}
				qcPlanService.updateById(qcPlan);
				qcImplementService.updateById(qcImplement);
				Long qcImplementId=qcImplement.getId();
				//发送消息 
				/*SendMessage  message=new SendMessage();
				message.setModuleId(qcImplementId);
				message.setRemarks(qcPlan.getName());
				message.setMessageContent("巡检实施已完成，查看详情");
				message.setStatus(4);
				message.setUserId(qcPlan.getChargeId());
				repairClientService.send(message);*/
				return qcImplementId;
			}else{
				throw ExceptionFactory.create("Q_016");
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}

	@Override
	public void batchUpdate(List<QcAssets> listQcAssets) {
		qcImplementMapper.batchUpdate(listQcAssets);
		
	}

	@Override
	public List<QcPlanRecordResponse> getRecord(Page<QcPlanRecordResponse> pageQcPlanRecordResponse,
			QcPlanRecordQuery query) {
		return qcImplementMapper.getRecord(pageQcPlanRecordResponse, query);
	}

	@Override
	public int check(QcImAssets request) {
		int ret=1;
		/**
		 * 实施状态(1，实施中 2，已完成）
		 */
		if(request!=null&&request.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", request.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement QcImplement=list.get(0);
				request.setId(QcImplement.getId());
			}else{
				return ret;
			}
		}else{
			return ret;
		}
		Wrapper<QcAssets> wrapper=new EntityWrapper<>();
		wrapper.eq("implement_id", request.getId()).eq("assets_id", request.getAssetId());
		int size=qcAssetsService.selectCount(wrapper);
		if(size>0){
			int st=	qcAssetsService.check(request);
			if(st==1){
				ret=2;
			}else if(st==2){
				ret=3;
			}
		}
		return ret;
	}

	@Override
	public Long submitImplementx(QcImplementSubmitRequestx request) {
		AuthUser authUser=WebSecurityUtils.getCurrentUser();
		if(request!=null&&request.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", request.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement qcImplement=list.get(0);
				Wrapper<QcAssets> wrapperQcAssets=new EntityWrapper<>();
				wrapperQcAssets.eq("implement_id", qcImplement.getId()).eq("result_status", 1);
				List<QcAssets> listQcAssets=qcAssetsService.selectList(wrapperQcAssets);
				if(listQcAssets!=null&&listQcAssets.size()>0){
					throw ExceptionFactory.create("Q_004");
				}
				Wrapper<QcAssets> wrapperQcAssets2=new EntityWrapper<>();
				wrapperQcAssets2.eq("implement_id", qcImplement.getId());
				List<QcAssets> listQcAssets2=qcAssetsService.selectList(wrapperQcAssets2);
				if(listQcAssets2!=null&&listQcAssets2.size()>0){
					for(QcAssets qcAssets:listQcAssets2){
						qcAssets.setCreateTime(new Date());
					}
				}
				qcAssetsService.updateBatchById(listQcAssets2);
				qcImplement.setStatus(2);
				qcImplement.setActualEndDate(request.getActualEndDate());
				qcImplement.setReportNo(QcUtils.reportNo());
				qcImplement.setCondition(request.getCondition());
				qcImplement.setAnalysis(request.getAnalysis());
				qcImplement.setSuggestion(request.getSuggestion());
				QcPlan qcPlan=qcPlanService.selectById(request.getId());
				if(qcPlan!=null&&qcPlan.getCheckStatus().intValue()==2){
					qcPlan.setNextTime(request.getNexDate());
					qcImplement.setNextTime(request.getNexDate());
					qcImplement.setCreateDate(new Date());
					qcImplement.setChargeId(qcPlan.getChargeId());
					qcImplement.setChargeName(qcPlan.getChargeName());
					qcPlan.setPreTime(request.getActualEndDate());
					qcPlan.setCheckStatus(1);
					qcPlan.setMsgStatus(1);
					QcChargeRequest nextChargeMan=request.getNextChargeMan();
					if(nextChargeMan!=null){
						qcPlan.setChargeId(nextChargeMan.getId());
						qcPlan.setChargeName(nextChargeMan.getName());
						Result<Boolean> ret=deptClientService.checkUserPermission(nextChargeMan.getId(), "QC_PLAN_IMPLEMENT",WebSecurityUtils.getCurrentToken());
						if(ret!=null){
							if(!ret.getData()){
								throw ExceptionFactory.create("Q_022");
							}
						}else{
							throw ExceptionFactory.create("Q_005");
						}
					}
				}
				Wrapper<QcPlanCheck> wrappercheck=new EntityWrapper<>();
				wrappercheck.eq("paln_id", qcPlan.getId()).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNull("implement_id");
				List<QcPlanCheck> listCheck = qcPlanCheckService.selectList(wrappercheck);
				if (listCheck != null && listCheck.size() > 0) {
					for (QcPlanCheck qcPlanCheck : listCheck) {
						qcPlanCheck.setDelFlag(true);
					}
					qcPlanCheckService.updateBatchById(listCheck);
				}
				List<QcChargeRequest> listMan = request.getCheckMan();
				if (listMan != null && listMan.size() > 0) {
					List<QcPlanCheck> listqcCheck = new ArrayList<>();
					List<QcPlanCheck> listqcCheck2 = new ArrayList<>();
					List<Long> ids=new ArrayList<>();
					//需要验收
					//qcImplement.setNeedCheck(NeedStatus.TWO.getCode());
					qcImplement.setIsCheck(CheckStatus.ONE.getCode());
					for (QcChargeRequest man : listMan) {
						QcPlanCheck qcCheck = new QcPlanCheck();
						QcPlanCheck qcCheck2 = new QcPlanCheck();
						qcCheck.setPalnId(qcPlan.getId());
						qcCheck2.setPalnId(qcPlan.getId());
						qcCheck.setTenantId(qcPlan.getTenantId());
						qcCheck2.setTenantId(qcPlan.getTenantId());
						qcCheck.setImplementId(qcImplement.getId());
						qcCheck.setUserId(man.getId());
						qcCheck2.setUserId(man.getId());
						qcCheck.setUserName(man.getName());
						qcCheck2.setUserName(man.getName());
						qcCheck.setCreateBy(authUser.getId());
						qcCheck2.setCreateBy(authUser.getId());
						listqcCheck.add(qcCheck);
						listqcCheck2.add(qcCheck2);
						ids.add(man.getId());
					}
					Result<Boolean> ret=deptClientService.checkUserPermissions(ids, "QC_CHECK_MANAGE",authUser.getTenantId(), WebSecurityUtils.getCurrentToken());
					if(ret!=null){
						if(!ret.getData()){
							throw ExceptionFactory.create("Q_020");
						}
					}else{
						throw ExceptionFactory.create("Q_005");
					}
					qcPlanCheckService.insertBatch(listqcCheck);
					qcPlanCheckService.insertBatch(listqcCheck2);
				}else{
					//不需要验收
					//qcImplement.setNeedCheck(NeedStatus.ONE.getCode());
					qcImplement.setIsCheck(CheckStatus.TWO.getCode());
				}
				qcPlanService.updateById(qcPlan);
				qcImplementService.updateById(qcImplement);
				qcImplementService.updateById(qcImplement);
				Long qcImplementId=qcImplement.getId();
				//发送消息 
				/*SendMessage  message=new SendMessage();
				message.setModuleId(qcImplementId);
				message.setRemarks(qcPlan.getName());
				message.setMessageContent("巡检实施已完成，查看详情");
				message.setStatus(4);
				message.setUserId(qcPlan.getChargeId());
				repairClientService.send(message);*/
				return qcImplementId;
			}else{
				throw ExceptionFactory.create("Q_016");
			}
		}else{
			throw ExceptionFactory.create("Q_017");
		}
	}

	@Override
	public LiuHuiPage<QcReportResponse> searchReport(QcReportQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<QcReportResponse> pageQcReportResponse = query.getPagePlus();
		List<QcReportResponse> list=qcImplementMapper.searchReport(pageQcReportResponse,query,authUser);
		LiuHuiPage<QcReportResponse> page=new LiuHuiPage<>(pageQcReportResponse);
		page.setRecords(list);
		return page;
	}

	@Override
	public void batchSave(QcAssetsRequest request) {
		// 计划id
		Long id = request.getId();
		Wrapper<QcImplement> wrapper = new EntityWrapper<>();
		wrapper.eq("paln_id", id).eq("status", 1);
		List<QcImplement> listQcImplement = qcImplementService.selectList(wrapper);
		if (listQcImplement != null && listQcImplement.size() > 0) {
			QcImplement qcImplement = listQcImplement.get(0);
			if (qcImplement != null) {
				Wrapper<QcAssets> wrapperQcAssets = new EntityWrapper<>();
				wrapperQcAssets.eq("implement_id", qcImplement.getId()).eq("im_status", 1);
				List<QcAssets> list = qcAssetsService.selectList(wrapperQcAssets);
				if (list != null && list.size() > 0) {
					List<QcAssets> listQcAssets = new ArrayList<>();
					// 答案完整状态
					Integer status = request.getStatus();
					for (QcAssets assets : list) {
						assets.setResult(request.getAnswers());
						assets.setImStatus(status);
						assets.setResultStatus(status);
						assets.setRemarks(request.getRemarks());
						listQcAssets.add(assets);
					}
					if (listQcAssets.size() > 0) {
						qcImplementService.batchUpdate2(listQcAssets);
					}

				}else {
					throw ExceptionFactory.create("Q_018");
				}
			} else {
				throw ExceptionFactory.create("Q_003");
			}
		}else {
			throw ExceptionFactory.create("Q_017");
		}
	}

	@Override
	public void batchUpdate2(List<QcAssets> listQcAssets) {
		qcImplementMapper.batchUpdate2(listQcAssets);
		
	}

	@Override
	public Boolean isCommit(Long id) {
		Boolean b=true;
		Wrapper<QcImplement> wrapper = new EntityWrapper<>();
		wrapper.eq("paln_id", id).eq("status", 1);
		List<QcImplement> listQcImplement = qcImplementService.selectList(wrapper);
		if (listQcImplement != null && listQcImplement.size() > 0) {
			b=false;
		}
		return b;
	}

	@Override
	public List<QcOverView> countQc(TimeQuery query) {
	    //年度巡检计划总数
        List<QcOverView> inspectionPlanTotalYearList = qcPlanMapper.selcetByCondtion(query);
	    //年度巡检执行率（百分比）
		List<QcOverView> inspectionRateYearList = qcImplementMapper.selectByCondtion(query);
		//年度巡检设备总数
		List<QcOverView> inspectionTotalYearList = qcImplementMapper.selectCountQcAssetsYear(query);
		//组装数据
		for (QcOverView inspectionPlanTotalYear : inspectionPlanTotalYearList) {
		    //设置年度巡检执行率
            for (QcOverView inspectionRateYear : inspectionRateYearList) {
                if (inspectionPlanTotalYear.getTenantId().longValue() == inspectionRateYear.getTenantId().longValue()) {
                    inspectionPlanTotalYear.setInspectionRateYear(inspectionRateYear.getInspectionRateYear());
                }
            }
            //设置年度巡检设备总数
            for (QcOverView inspectionTotalYear : inspectionTotalYearList) {
                if (inspectionPlanTotalYear.getTenantId().longValue() == inspectionTotalYear.getTenantId().longValue()) {
                    inspectionPlanTotalYear.setInspectionTotalYear(inspectionTotalYear.getInspectionTotalYear());
                }
            }
        }
		return inspectionPlanTotalYearList;
	}

	@Override
	public List<QcMounth> countQcAssets(TimeQuery query) {
	    //巡检设备总数
		List<QcMounth> list = qcImplementMapper.selectCountQcAssets(query);
		if(query != null && query.getStartDate() != null){
			if(list != null && list.size() > 0){
				for(QcMounth qcMounth:list){
					qcMounth.setCountMonth(query.getStartDate().substring(0, query.getStartDate().length()-3));
				}
			}
		}
		return list;
	}

}
