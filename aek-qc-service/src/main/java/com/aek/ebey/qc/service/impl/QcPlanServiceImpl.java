package com.aek.ebey.qc.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.BusinessException;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.CheckStatus;
import com.aek.ebey.qc.inter.Constants;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.LiuHuiPage2;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.QcPlanMapper;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.QcDept;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.model.QcOptionHelp;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.model.QcPlanCheck;
import com.aek.ebey.qc.model.QcProjectHelp;
import com.aek.ebey.qc.model.QcTemplate;
import com.aek.ebey.qc.model.QcTemplateHelp;
import com.aek.ebey.qc.query.QcAssetsByImplementIdQuery;
import com.aek.ebey.qc.query.QcAssetsQuery;
import com.aek.ebey.qc.query.QcAssetsQuery2;
import com.aek.ebey.qc.query.QcImplementQuery;
import com.aek.ebey.qc.query.QcPlanQuery;
import com.aek.ebey.qc.query.QcPlanRecordQuery;
import com.aek.ebey.qc.query.QcUserDept;
import com.aek.ebey.qc.query.Query;
import com.aek.ebey.qc.query.ReturnMsg;
import com.aek.ebey.qc.request.Dept;
import com.aek.ebey.qc.request.QcAssetsResponse;
import com.aek.ebey.qc.request.QcAssetsResponse2;
import com.aek.ebey.qc.request.QcChargeRequest;
import com.aek.ebey.qc.request.QcDeptRequest;
import com.aek.ebey.qc.request.QcDetail;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssets2;
import com.aek.ebey.qc.request.QcImAssetsResponse;
import com.aek.ebey.qc.request.QcImplementContentResponse;
import com.aek.ebey.qc.request.QcImplementDetail;
import com.aek.ebey.qc.request.QcImplementReport;
import com.aek.ebey.qc.request.QcImplementResponse;
import com.aek.ebey.qc.request.QcImplementSheet;
import com.aek.ebey.qc.request.QcOptionRequest2;
import com.aek.ebey.qc.request.QcOptionRequest3;
import com.aek.ebey.qc.request.QcPlanDetail;
import com.aek.ebey.qc.request.QcPlanRecordResponse;
import com.aek.ebey.qc.request.QcPlanRequest;
import com.aek.ebey.qc.request.QcPlanResponse;
import com.aek.ebey.qc.request.QcProjectRequest3;
import com.aek.ebey.qc.request.QcProjectRequest4;
import com.aek.ebey.qc.request.QcTemplateDetail;
import com.aek.ebey.qc.request.SendMessage;
import com.aek.ebey.qc.service.QcAssetsService;
import com.aek.ebey.qc.service.QcDeptService;
import com.aek.ebey.qc.service.QcFlowingService;
import com.aek.ebey.qc.service.QcImplementService;
import com.aek.ebey.qc.service.QcOptionHelpService;
import com.aek.ebey.qc.service.QcPlanCheckService;
import com.aek.ebey.qc.service.QcPlanService;
import com.aek.ebey.qc.service.QcProjectHelpService;
import com.aek.ebey.qc.service.QcTemplateHelpService;
import com.aek.ebey.qc.service.QcTemplateService;
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
 * @since 2017-11-03
 */
@Service
@Transactional
public class QcPlanServiceImpl extends BaseServiceImpl<QcPlanMapper, QcPlan> implements QcPlanService {
	
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private QcOptionHelpService qcOptionHelpService;
	
	@Autowired
	private QcProjectHelpService qcProjectHelpService;
	
	@Autowired
	private QcTemplateHelpService qcTemplateHelpService;
	
	@Autowired
	private QcPlanMapper qcPlanMapper;
	
	@Autowired
	private QcImplementService qcImplementService;
	
	@Autowired
	private QcDeptService qcDeptService;
	
	@Autowired
	private QcFlowingService qcFlowingService;
	
	@Autowired
	private QcAssetsService qcAssetsService;
	
	@Autowired
	private  DeptClientService deptClientService;
	
	@Autowired
	private  RepairClientService repairClientService;
	
	
	@Autowired
	private  QcPlanCheckService qcPlanCheckService;
	
	
	
	

	@Override
	public void save(QcPlanRequest request) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null&&request!=null){
			QcTemplateHelp templateHelp=null;
			if(request.getTemplateId()!=null){
				QcTemplateDetail QcTemplateDetail=qcTemplateService.getAllById(request.getTemplateId());
				QcTemplate  qcTemplate =qcTemplateService.selectById(request.getTemplateId());
				if(qcTemplate!=null){
					if(qcTemplate.getDelFlag()){
						throw ExceptionFactory.create("Q_012");
					}
					if(qcTemplate.getStatus().intValue()==StatusConstants.TWO.intValue()){
						throw ExceptionFactory.create("Q_013");
					}
					if(!qcTemplate.getHasProject()){
						throw ExceptionFactory.create("Q_014");
					}
				}
				QcUserDept	qcUserDept=new QcUserDept();
				qcUserDept.setUserId(request.getDirector().getId());
				qcUserDept.setUserName(request.getDirector().getName());
				qcUserDept.setQcDepts(request.getScope());
				
				Result<ReturnMsg> result=deptClientService.verifyCreateQcPlan(qcUserDept,  WebSecurityUtils.getCurrentToken());
				
				if (result != null) {
					ReturnMsg returnMsg=result.getData();
					if(returnMsg!=null){
						if(!returnMsg.getStatus()){
							throw new BusinessException("Q_012",returnMsg.getTips());
						}
					}else{
						throw ExceptionFactory.create("Q_005");
					}
				}else{
					throw ExceptionFactory.create("Q_005");
				}
				if(QcTemplateDetail!=null){
					templateHelp=new QcTemplateHelp();
					templateHelp.setName(QcTemplateDetail.getName());
					templateHelp.setRemarks(QcTemplateDetail.getRemarks());
					templateHelp.setTemplateType(Constants.CUSTOM_TYPE);
					templateHelp.setStatus(StatusConstants.ONE);
					qcTemplateHelpService.insert(templateHelp);
					
					List<QcProjectRequest3> listProjectHelp=QcTemplateDetail.getProjects();
					if(listProjectHelp!=null&&listProjectHelp.size()>0){
						for(QcProjectRequest3 qcProjectRequest3:listProjectHelp){
							QcProjectHelp projectHelp=new QcProjectHelp();
							projectHelp.setName(qcProjectRequest3.getName());
							projectHelp.setTemplateId(templateHelp.getId());
							projectHelp.setStatus(StatusConstants.ONE);
							qcProjectHelpService.insert(projectHelp);
							List<QcOptionRequest2> listQcOptionRequest=qcProjectRequest3.getResults();
							if(listQcOptionRequest!=null&&listQcOptionRequest.size()>0){
								for(QcOptionRequest2 qcOptionRequest2:listQcOptionRequest){
									QcOptionHelp optionHelp=new QcOptionHelp();
									optionHelp.setHaveDefault(qcOptionRequest2.getIsDefault());
									optionHelp.setName(qcOptionRequest2.getName());
									optionHelp.setProjectId(projectHelp.getId());
									optionHelp.setStatus(StatusConstants.ONE);
									qcOptionHelpService.insert(optionHelp);
								}
							}
						}	
					}
					
				}
			}
			QcPlan qcPlan = BeanMapper.map(request, QcPlan.class);
			qcPlan.setTenantId(authUser.getTenantId());
			qcPlan.setTenantName(authUser.getTenantName());
			qcPlan.setTemplateId(templateHelp.getId());
			//qcPlan.setName(request.getName());
			qcPlan.setPlanType(request.getType());
			qcPlan.setPlanNo(QcUtils.getPlanNo(qcFlowingService.savePlanNo(QcUtils.getStringDate())));
			qcPlan.setCreateTime(new Date());
			//qcPlan.setTemplateId(request.getTemplateId());
			qcPlan.setPlanCycle(request.getCycle());
			//qcPlan.setCycleType(request.getCycleType());
			qcPlan.setFirstTime(request.getDate());
			qcPlan.setNextTime(request.getDate());
			qcPlan.setChargeId(request.getDirector().getId());
			qcPlan.setChargeName(request.getDirector().getName());
			qcPlan.setStatus(StatusConstants.ONE);
			qcPlan.setCheckStatus(1);
			qcPlan.setMsgStatus(1);
			List<QcChargeRequest> listMan=request.getCheckMan();
			/*if(listMan!=null&&listMan.size()>0){
				//需要验收
				qcPlan.setNeedCheck(2);
				qcPlan.setIsCheck(1);
			}else{
				//不需要验收
				qcPlan.setNeedCheck(1);
				qcPlan.setIsCheck(2);
			}*/
			/*Wrapper<QcPlan> wrapper=new EntityWrapper<>();
			wrapper.eq("tenant_id", qcPlan.getTenantId()).eq("name", qcPlan.getName()).eq("del_flag", false);*/
			Query query=new Query();
			query.setTenantId(qcPlan.getTenantId());
			query.setName(qcPlan.getName());
			query.setDelFlag(false);
			List<QcPlan> listQcPlan=qcPlanMapper.selectList2(query);
			if(listQcPlan!=null&&listQcPlan.size()>0){
				throw ExceptionFactory.create("Q_009");
			}
			this.qcPlanMapper.insert(qcPlan);
			//插入验收信息
			if(listMan!=null&&listMan.size()>0){
				List<QcPlanCheck> list=new ArrayList<>();
				List<Long> ids=new ArrayList<>();
				for(QcChargeRequest man:listMan){
					QcPlanCheck qcPlanCheck=new QcPlanCheck();
					qcPlanCheck.setPalnId(qcPlan.getId());
					qcPlanCheck.setTenantId(qcPlan.getTenantId());
					qcPlanCheck.setUserId(man.getId());
					qcPlanCheck.setUserName(man.getName());
					qcPlanCheck.setCreateBy(authUser.getId());
					list.add(qcPlanCheck);
					ids.add(man.getId());
				}
				//qcPlanCheckService.insertBatch(list);
				Result<Boolean> ret=deptClientService.checkUserPermissions(ids, "QC_CHECK_MANAGE",authUser.getTenantId(), WebSecurityUtils.getCurrentToken());
				if(ret!=null){
					if(ret.getData()){
						qcPlanCheckService.insertBatch(list);
					}else{
						throw ExceptionFactory.create("Q_020");
					}
				}else{
					throw ExceptionFactory.create("Q_005");
				}
			}
			List<QcDeptRequest> list=request.getScope();
			if(list!=null&&list.size()>0){
				List<QcDept> listqcDept=new ArrayList<>();
				for(QcDeptRequest qcDeptRequest:list){
					Wrapper<QcDept> qcDeptwrapper=new EntityWrapper<>();
					qcDeptwrapper.eq("tenant_id", qcPlan.getTenantId()).eq("dept_id", qcDeptRequest.getId()).eq("status", StatusConstants.ONE.intValue());
					List<QcDept> listQcDept=qcDeptService.selectList(qcDeptwrapper);
					if(listQcDept!=null&&listQcDept.size()>0){
						throw ExceptionFactory.create("Q_015");
					}else{
						QcDept qcDept=new QcDept();
						qcDept.setTenantId(qcPlan.getTenantId());
						qcDept.setDeptId(qcDeptRequest.getId());
						qcDept.setDeptName(qcDeptRequest.getName());
						qcDept.setPlanId(qcPlan.getId());
						qcDept.setStatus(StatusConstants.ONE);
						listqcDept.add(qcDept);
					}
				}
				qcDeptService.insertBatch(listqcDept);
				/*if(listqcDept.size()>0){
					for(QcDept qcDept:listqcDept){
						qcDeptService.insert(qcDept);
					}
				}*/
			}
			
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<QcPlanResponse> search(QcPlanQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<QcPlanResponse> pageQcPlan = query.getPagePlus();
		List<QcPlanResponse> list=qcPlanMapper.search(pageQcPlan,query,authUser);
		LiuHuiPage<QcPlanResponse> page=new LiuHuiPage<>(pageQcPlan);
		if(list!=null&&list.size()>0){
			for(QcPlanResponse qcPlanResponse:list){
				if(1==qcPlanResponse.getPlanType().intValue()){
					qcPlanResponse.setType("按科室巡检");
				}
				if(Constants.CYCLE_MOUNTH.intValue()==qcPlanResponse.getCycleType().intValue()){
					qcPlanResponse.setCycle(qcPlanResponse.getPlanCycle()+"个月");
				}
				if(Constants.CYCLE_DAY.intValue()==qcPlanResponse.getCycleType().intValue()){
					qcPlanResponse.setCycle(qcPlanResponse.getPlanCycle()+"天");
				}
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public QcPlanDetail getAllByQcPlan(QcPlan qcPlan) {

		QcPlanDetail qcPlanDetail=new QcPlanDetail();
		qcPlanDetail.setCreateTime(qcPlan.getCreateTime());
		qcPlanDetail.setPlanNo(qcPlan.getPlanNo());
		qcPlanDetail.setName(qcPlan.getName());
		qcPlanDetail.setType(qcPlan.getPlanType().intValue()==1?"按科室巡检":"");
		qcPlanDetail.setCycle(Constants.CYCLE_MOUNTH.intValue()==qcPlan.getCycleType().intValue()?qcPlan.getPlanCycle()+"个月":qcPlan.getPlanCycle()+"天");
		qcPlanDetail.setFirstDate(qcPlan.getFirstTime());
		qcPlanDetail.setNextDate(qcPlan.getNextTime()==null?qcPlan.getFirstTime():qcPlan.getNextTime());
		qcPlanDetail.setDirector(qcPlan.getChargeName());
		qcPlanDetail.setStatus(qcPlan.getStatus());
		//巡检范围
		StringBuilder builder = getScope(qcPlan);
		if(builder.length()>0){
			qcPlanDetail.setScope(builder.substring(0, builder.length()-1));
		}
		QcTemplateDetail qcTemplateDetail=qcTemplateService.getAllById2(qcPlan.getTemplateId());
		qcPlanDetail.setTemplate(qcTemplateDetail);
		Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
		wrapper.eq("paln_id", qcPlan.getId()).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNull("implement_id");
		List<QcPlanCheck> list=qcPlanCheckService.selectList(wrapper);
		if(list!=null&&list.size()>0){
			List<QcChargeRequest> listCheckMan=new ArrayList<>();
			for(QcPlanCheck qcPlanCheck:list){
				QcChargeRequest qcChargeRequest=new QcChargeRequest();
				qcChargeRequest.setId(qcPlanCheck.getUserId());
				qcChargeRequest.setName(qcPlanCheck.getUserName());
				listCheckMan.add(qcChargeRequest);
			}
			qcPlanDetail.setCheckMan(listCheckMan);
		}
		return qcPlanDetail;
	
	}

	/**
	 * 得到巡检范围
	 */
	private StringBuilder getScope(QcPlan qcPlan) {
		Wrapper<QcDept> wrapper=new EntityWrapper<>();
		wrapper.eq("plan_id", qcPlan.getId());
		List<QcDept> list=qcDeptService.selectList(wrapper);
		StringBuilder builder=new StringBuilder();
		if(list!=null&&list.size()>0){
			for(QcDept qcDept:list){
				builder.append(qcDept.getDeptName());
				builder.append("；");
			}
		}
		return builder;
	}

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<QcImplementResponse> searchImplement(QcImplementQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<QcImplementResponse> pageQcPlan = query.getPagePlus();
		List<QcImplementResponse> list=qcPlanMapper.searchImplement(pageQcPlan,query,authUser);
		LiuHuiPage<QcImplementResponse> page=new LiuHuiPage<>(pageQcPlan);
		if(list!=null&&list.size()>0){
			for(QcImplementResponse qcPlanResponse:list){
				if(1==qcPlanResponse.getPlanType().intValue()){
					qcPlanResponse.setType("按科室巡检");
				}
				if(Constants.CYCLE_MOUNTH.intValue()==qcPlanResponse.getCycleType().intValue()){
					qcPlanResponse.setCycle(qcPlanResponse.getPlanCycle()+"个月");
				}
				if(Constants.CYCLE_DAY.intValue()==qcPlanResponse.getCycleType().intValue()){
					qcPlanResponse.setCycle(qcPlanResponse.getPlanCycle()+"天");
				}
				if(qcPlanResponse.getNextDate()!=null){
					qcPlanResponse.setAttention(QcUtils.getAttention(new Date(),qcPlanResponse.getNextDate()));
				}else{
					qcPlanResponse.setAttention(QcUtils.getAttention(new Date(),qcPlanResponse.getFirstDate()));
				}
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public QcImplementDetail getQcImplementDetailByQcPlan(QcPlan qcPlan) {
		

		QcImplementDetail qcImplementDetail=new QcImplementDetail();
		qcImplementDetail.setCreateTime(qcPlan.getCreateTime());
		qcImplementDetail.setFirstTime(qcPlan.getFirstTime());
		qcImplementDetail.setPlanNo(qcPlan.getPlanNo());
		qcImplementDetail.setDirector(qcPlan.getChargeName());
		qcImplementDetail.setName(qcPlan.getName());
		qcImplementDetail.setType(qcPlan.getPlanType().intValue()==1?"按科室巡检":"");
		qcImplementDetail.setCycle(Constants.CYCLE_MOUNTH.intValue()==qcPlan.getCycleType().intValue()?qcPlan.getPlanCycle()+"个月":qcPlan.getPlanCycle()+"天");
		qcImplementDetail.setPreDate(qcPlan.getPreTime());
		qcImplementDetail.setNextDate(qcPlan.getNextTime()==null?qcPlan.getFirstTime():qcPlan.getNextTime());
		if(qcPlan.getNextTime()!=null){
			qcImplementDetail.setAttention(QcUtils.getAttention(new Date(),qcPlan.getNextTime()));
		}
		Wrapper<QcImplement> wrapperQcImplement=new EntityWrapper<>();
		wrapperQcImplement.eq("paln_id", qcPlan.getId()).eq("status", 1);
		List<QcImplement> listQcImplement=qcImplementService.selectList(wrapperQcImplement);
		QcImplement QcImplement=null;
		if(listQcImplement!=null&&listQcImplement.size()>0){
			QcImplement=listQcImplement.get(0);
		}
		if(QcImplement!=null){
			qcImplementDetail.setActualStartDate(QcImplement.getActualStartDate()==null?QcImplement.getCreateDate():QcImplement.getActualStartDate());
			qcImplementDetail.setActualEndDate(QcImplement.getActualEndDate());
			qcImplementDetail.setCondition(QcImplement.getCondition());
			qcImplementDetail.setAnalysis(QcImplement.getAnalysis());
			qcImplementDetail.setSuggestion(QcImplement.getSuggestion());
		}
		qcImplementDetail.setStatus(qcPlan.getCheckStatus());
		StringBuilder builder = getScope(qcPlan);
		if(builder.length()>0){
			qcImplementDetail.setScope(builder.substring(0, builder.length()-1));
		}
		List<QcProjectRequest4> listQcProjectRequest=getListQcproject(qcPlan);
		qcImplementDetail.setTemplate(listQcProjectRequest);
		Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
		wrapper.eq("paln_id", qcPlan.getId()).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNull("implement_id");
		List<QcPlanCheck> list=qcPlanCheckService.selectList(wrapper);
		if(list!=null&&list.size()>0){
			List<QcChargeRequest> listCheckMan=new ArrayList<>();
			for(QcPlanCheck qcPlanCheck:list){
				QcChargeRequest qcChargeRequest=new QcChargeRequest();
				qcChargeRequest.setId(qcPlanCheck.getUserId());
				qcChargeRequest.setName(qcPlanCheck.getUserName());
				listCheckMan.add(qcChargeRequest);
			}
			qcImplementDetail.setCheckMan(listCheckMan);
		}
		return qcImplementDetail;
	}

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<QcAssetsResponse> searchQcAssets(QcAssetsQuery query) {
		Page<QcAssetsResponse> pageQcAssets = query.getPagePlus();
		if(query!=null&&query.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", query.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement QcImplement=list.get(0);
				query.setId(QcImplement.getId());
			}else{
			LiuHuiPage<QcAssetsResponse> page=new LiuHuiPage<>(pageQcAssets);
			page.setRecords(null);
			return page;
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		List<QcAssetsResponse> list=qcAssetsService.searchQcAssets(pageQcAssets,query);
		LiuHuiPage<QcAssetsResponse> page=new LiuHuiPage<>(pageQcAssets);
		page.setRecords(list);
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public QcImplementContentResponse getImplementContentById(Long id) {
		QcPlan  qcPlan =qcPlanMapper.selectById(id);
		if(qcPlan!=null){
			QcImplementContentResponse qcImplementContentResponse=new QcImplementContentResponse();
			List<QcProjectRequest4> listQcProjectRequest=getListQcproject(qcPlan);
			qcImplementContentResponse.setTemplate(listQcProjectRequest);
			return qcImplementContentResponse;
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		
		
	}

	@Override
	@Transactional(readOnly=true)
	public QcImplementSheet getImplementSheetById(Long id) {
		QcPlan  qcPlan =qcPlanMapper.selectById(id);
		if(qcPlan!=null){
			QcImplementSheet qcImplementSheet=new QcImplementSheet();
			qcImplementSheet.setHospital(qcPlan.getTenantName());
			qcImplementSheet.setPlanNo(qcPlan.getPlanNo());
			qcImplementSheet.setDirector(qcPlan.getChargeName());
			//巡检范围
			StringBuilder builder = getScope(qcPlan);
			if(builder.length()>0){
				qcImplementSheet.setScope(builder.substring(0, builder.length()-1));
			}
			
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", id).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement qcImplement=list.get(0);
				Wrapper<QcAssets> wrapperQcAssets=new EntityWrapper<>();
				wrapperQcAssets.eq("implement_id", qcImplement.getId());
				List<QcAssets> listQcAssets=qcAssetsService.selectList(wrapperQcAssets);
				if(listQcAssets!=null&&listQcAssets.size()>0){
					List<QcAssetsResponse> listQcAssetsResponse=new  ArrayList<>();
					for(QcAssets qcAssets:listQcAssets){
						QcAssetsResponse qcAssetsResponse=new QcAssetsResponse();
						qcAssetsResponse.setRemarks(qcAssets.getRemarks());
						qcAssetsResponse.setName(qcAssets.getAssetsName());
						qcAssetsResponse.setDepartment(qcAssets.getAssetsDeptName());
						qcAssetsResponse.setModel(qcAssets.getAssetsSpec());
						qcAssetsResponse.setEquipmentNo(qcAssets.getAssetsNum());
						qcAssetsResponse.setId(qcAssets.getAssetsId());
						listQcAssetsResponse.add(qcAssetsResponse);
					}
					qcImplementSheet.setRecords(listQcAssetsResponse);
				}
				List<QcProjectRequest4> listQcProjectRequest=getListQcproject(qcPlan);
				qcImplementSheet.setTemplate(listQcProjectRequest);
			}
			return qcImplementSheet;
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		
	}

	/**
	 * 得到巡检模版
	 */
	@Transactional(readOnly=true)
	private List<QcProjectRequest4> getListQcproject(QcPlan qcPlan) {
		QcTemplateHelp qcTemplateHelp=qcTemplateHelpService.selectById(qcPlan.getTemplateId());
		List<QcProjectHelp> listQcProjectHelp=null;
		if(qcTemplateHelp!=null){
			Wrapper<QcProjectHelp> wrapperQcProjectHelp=new EntityWrapper<>();
			wrapperQcProjectHelp.eq("template_id", qcTemplateHelp.getId());
			listQcProjectHelp=qcProjectHelpService.selectList(wrapperQcProjectHelp);
		}
		if(listQcProjectHelp!=null&&listQcProjectHelp.size()>0){
			List<QcProjectRequest4> listQcProjectRequest=new ArrayList<>();
			for(QcProjectHelp qcProjectHelp:listQcProjectHelp){
				QcProjectRequest4 qcProjectRequest4=new QcProjectRequest4();
				qcProjectRequest4.setId(qcProjectHelp.getId());
				qcProjectRequest4.setName(qcProjectHelp.getName());
				Wrapper<QcOptionHelp> wrapperQcOptionHelp=new EntityWrapper<>();
				wrapperQcOptionHelp.eq("project_id", qcProjectHelp.getId());
				List<QcOptionHelp> listQcOptionHelp=qcOptionHelpService.selectList(wrapperQcOptionHelp);
				if(listQcOptionHelp!=null&&listQcOptionHelp.size()>0){
					List<QcOptionRequest3> listQcOptionRequest=new ArrayList<>();
					for(QcOptionHelp qcOptionHelp:listQcOptionHelp){
						QcOptionRequest3 optionRequest3=new QcOptionRequest3();
						optionRequest3.setId(qcOptionHelp.getId());
						optionRequest3.setName(qcOptionHelp.getName());
						optionRequest3.setIsDefault(qcOptionHelp.getHaveDefault());
						listQcOptionRequest.add(optionRequest3);
					}
					qcProjectRequest4.setProjects(listQcOptionRequest);
				}
				listQcProjectRequest.add(qcProjectRequest4);
			}
			return listQcProjectRequest;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<QcPlanRecordResponse> getRecord(QcPlanRecordQuery query) {
		Page<QcPlanRecordResponse> pageQcPlanRecordResponse = query.getPagePlus();
		List<QcPlanRecordResponse> list=qcImplementService.getRecord(pageQcPlanRecordResponse,query);
		if(list!=null&&list.size()>0){
			for(QcPlanRecordResponse qcPlanRecordResponse:list){
				Wrapper<QcAssets> wrapper=new EntityWrapper<>();
				wrapper.eq("implement_id", qcPlanRecordResponse.getId());
				int i=qcAssetsService.selectCount(wrapper);
				qcPlanRecordResponse.setEquipments(i);
			}
		}
		LiuHuiPage<QcPlanRecordResponse> page=new LiuHuiPage<>(pageQcPlanRecordResponse);
		page.setRecords(list);
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public QcImplementReport getImplementReportById(Long id) {
		QcImplement  qcImplement =qcImplementService.selectById(id);
		AuthUser user=WebSecurityUtils.getCurrentUser();
		if(qcImplement!=null){
			QcImplementReport qcImplementReport=new QcImplementReport();
			QcPlan  qcPlan =qcPlanMapper.selectById(qcImplement.getPalnId());
			if(qcPlan!=null){
				qcImplementReport.setHospital(qcPlan.getTenantName());
				qcImplementReport.setPlanNo(qcPlan.getPlanNo());
				qcImplementReport.setDirector(qcPlan.getChargeName());
				//巡检范围
				StringBuilder builder = getScope(qcPlan);
				if(builder.length()>0){
					qcImplementReport.setScope(builder.substring(0, builder.length()-1));
				}
				List<QcProjectRequest4> listQcProjectRequest=getListQcproject(qcPlan);
				qcImplementReport.setTemplate(listQcProjectRequest);
				qcImplementReport.setName(qcPlan.getName());
				qcImplementReport.setPlanType(qcPlan.getPlanType());
				qcImplementReport.setPlanCycle(qcPlan.getPlanCycle());
				qcImplementReport.setCycleType(qcPlan.getCycleType());
			}
			List<QcAssetsResponse> listQcAssets=qcAssetsService.selectByimplementId(qcImplement.getId());
			qcImplementReport.setRecords(listQcAssets);
			qcImplementReport.setReportNo(qcImplement.getReportNo());
			qcImplementReport.setFirstDate(qcImplement.getActualStartDate());
			qcImplementReport.setNextDate(qcImplement.getActualEndDate());
			qcImplementReport.setAnalysis(qcImplement.getAnalysis());
			qcImplementReport.setCondition(qcImplement.getCondition());
			qcImplementReport.setSuggestion(qcImplement.getSuggestion());
			qcImplementReport.setEndDate(qcImplement.getActualEndDate());
			Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
			wrapper.eq("implement_id", id).eq("del_flag", false);
			List<QcPlanCheck> listCheck=qcPlanCheckService.selectList(wrapper);
			if(listCheck!=null&&listCheck.size()>0){
				List<String> checkMan=new ArrayList<>();
				List<String> checkedMan=new ArrayList<>();
				for(QcPlanCheck qcPlanCheck:listCheck){
					checkMan.add(qcPlanCheck.getUserName());
					if(qcPlanCheck.getStatus().intValue()==CheckStatus.TWO.getCode()){
						checkedMan.add(qcPlanCheck.getUserName());
					}
					if(qcPlanCheck.getUserId().longValue()==user.getId().longValue()){
						if(qcPlanCheck.getStatus().intValue()==CheckStatus.ONE.getCode()){
							qcImplementReport.setStatus(CheckStatus.ONE.getCode());
						}
					}
				}
				qcImplementReport.setCheckMan(checkMan);
				qcImplementReport.setCheckedMan(checkedMan);
			}
			return qcImplementReport;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Dept> getDepts(String keyword) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null){
			Wrapper<QcPlan> wrapper=new EntityWrapper<>();
			wrapper.eq("tenant_id", authUser.getTenantId()).eq("status", 1).eq("del_flag", false);
			List<QcPlan> list=qcPlanMapper.selectList(wrapper);
			List<Dept> listDept =new ArrayList<>();
			if(list!=null&&list.size()>0){
				for(QcPlan qcPlan:list){
					Wrapper<QcDept> wrapperQcDept=new EntityWrapper<>();
					wrapperQcDept.eq("plan_id", qcPlan.getId());
					List<QcDept> listQcDept=qcDeptService.selectList(wrapperQcDept);
					if(listQcDept!=null&&listQcDept.size()>0){
						for(QcDept qcDept:listQcDept){
							Dept dept=new Dept();
							dept.setId(qcDept.getDeptId());
							dept.setName(qcDept.getDeptName());
							listDept.add(dept);
						}
					}
				}
			}
			return listDept;
		}
		return null;
	}

	@Override
	public LiuHuiPage<QcAssetsResponse> searchContentByImplementId(QcAssetsByImplementIdQuery query) {
		Page<QcAssetsResponse> pageQcAssets = query.getPagePlus();
		List<QcAssetsResponse> list=qcAssetsService.searchContentByImplementId(pageQcAssets,query);
		LiuHuiPage<QcAssetsResponse> page=new LiuHuiPage<>(pageQcAssets);
		page.setRecords(list);
		return page;
	}

	@Override
	public QcImplementDetail getQcImplementDetailByImplementId(Long id) {
		QcImplement  qcImplement =qcImplementService.selectById(id);
		if(qcImplement!=null){
			QcPlan qcPlan=qcPlanMapper.selectById(qcImplement.getPalnId());
			if(qcPlan!=null){
				QcImplementDetail qcImplementDetail=new QcImplementDetail();
				qcImplementDetail.setCreateTime(qcPlan.getCreateTime());
				qcImplementDetail.setPlanNo(qcPlan.getPlanNo());
				qcImplementDetail.setDirector(qcPlan.getChargeName());
				qcImplementDetail.setName(qcPlan.getName());
				qcImplementDetail.setType(qcPlan.getPlanType().intValue()==1?"按科室巡检":"");
				qcImplementDetail.setCycle(Constants.CYCLE_MOUNTH.intValue()==qcPlan.getCycleType().intValue()?qcPlan.getPlanCycle()+"个月":qcPlan.getPlanCycle()+"天");
				qcImplementDetail.setPreDate(qcPlan.getPreTime());
				qcImplementDetail.setNextDate(qcPlan.getNextTime()==null?qcPlan.getFirstTime():qcPlan.getNextTime());
				if(qcPlan.getNextTime()!=null){
					qcImplementDetail.setAttention(QcUtils.getAttention(new Date(),qcPlan.getNextTime()));
				}
				qcImplementDetail.setActualStartDate(qcImplement.getActualStartDate());
				qcImplementDetail.setActualEndDate(qcImplement.getActualEndDate());
				qcImplementDetail.setCondition(qcImplement.getCondition());
				qcImplementDetail.setAnalysis(qcImplement.getAnalysis());
				qcImplementDetail.setSuggestion(qcImplement.getSuggestion());
				qcImplementDetail.setStatus(qcPlan.getCheckStatus());
				StringBuilder builder = getScope(qcPlan);
				if(builder.length()>0){
					qcImplementDetail.setScope(builder.substring(0, builder.length()-1));
				}
				Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
				wrapper.eq("paln_id", qcPlan.getId()).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNull("implement_id");
				List<QcPlanCheck> list=qcPlanCheckService.selectList(wrapper);
				if(list!=null&&list.size()>0){
					List<QcChargeRequest> listCheckMan=new ArrayList<>();
					for(QcPlanCheck qcPlanCheck:list){
						QcChargeRequest qcChargeRequest=new QcChargeRequest();
						qcChargeRequest.setId(qcPlanCheck.getUserId());
						qcChargeRequest.setName(qcPlanCheck.getUserName());
						listCheckMan.add(qcChargeRequest);
					}
					qcImplementDetail.setCheckMan(listCheckMan);
				}
				return qcImplementDetail;
			}else{
				return null;
			}
			
		}else{
			return null;
		}
	}

	@Override
	public void updateByQcPlan(QcPlan qcPlan) {
		qcPlanMapper.updateByQcPlan(qcPlan);
		
	}

	@Override
	public void updateQcPlan(Long id) {
		QcPlan  qcPlan =qcPlanMapper.selectById(id);
		if(qcPlan!=null){
			qcPlan.setStatus(StatusConstants.TWO);
			qcPlanMapper.updateById(qcPlan);
			Wrapper<QcDept> wrapper=new EntityWrapper<>();
			wrapper.eq("plan_id", qcPlan.getId());
			List<QcDept> list=qcDeptService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				for(QcDept qcDept:list){
					qcDept.setStatus(StatusConstants.TWO);
					qcDeptService.updateById(qcDept);
				}
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		
	}

	@Override
	public LiuHuiPage2<QcAssetsResponse2> searchQcAssets2(QcAssetsQuery2 query) {
		/**
		 * Page<QcAssetsResponse> pageQcAssets = query.getPagePlus();
		if(query!=null&&query.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", query.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement QcImplement=list.get(0);
				query.setId(QcImplement.getId());
			}else{
			LiuHuiPage<QcAssetsResponse> page=new LiuHuiPage<>(pageQcAssets);
			page.setRecords(null);
			return page;
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		List<QcAssetsResponse> list=qcAssetsService.searchQcAssets(pageQcAssets,query);
		LiuHuiPage<QcAssetsResponse> page=new LiuHuiPage<>(pageQcAssets);
		page.setRecords(list);
		return page;
		 */
		QcPlan  qcPlan =qcPlanMapper.selectById(query.getId());
		Page<QcAssetsResponse2> pageQcAssets = query.getPagePlus();
		if(query!=null&&query.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", query.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement QcImplement=list.get(0);
				query.setId(QcImplement.getId());
			}else{
			LiuHuiPage2<QcAssetsResponse2> page=new LiuHuiPage2<>(pageQcAssets);
			page.setRecords(null);
			return page;
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		List<QcAssetsResponse2> list=qcAssetsService.searchQcAssets2(pageQcAssets,query);
		LiuHuiPage2<QcAssetsResponse2> page=new LiuHuiPage2<>(pageQcAssets);
		Wrapper<QcAssets> wrapper=new EntityWrapper<>();
		wrapper.eq("implement_id", query.getId()).eq("im_status", 1);
		int inspectingNum=qcAssetsService.selectCount(wrapper);
		Wrapper<QcAssets> wrapper2=new EntityWrapper<>();
		wrapper2.eq("implement_id", query.getId()).eq("im_status", 2);
		int inspectedNum=qcAssetsService.selectCount(wrapper2);
		page.setInspectingNum(inspectingNum);
		page.setInspectedNum(inspectedNum);
		page.setNextDate(QcUtils.getNextTime2(qcPlan));
		page.setRecords(list);
		return page;
		/*List<QcAssetsResponse2> list=qcAssetsService.searchQcAssets2(pageQcAssets,query);
		LiuHuiPage2<QcAssetsResponse2> page=new LiuHuiPage2<>(pageQcAssets);
		Wrapper<QcAssets> wrapper=new EntityWrapper<>();
		wrapper.eq("implement_id", query.getId()).eq("im_status", 1);
		int inspectingNum=qcAssetsService.selectCount(wrapper);
		Wrapper<QcAssets> wrapper2=new EntityWrapper<>();
		wrapper2.eq("implement_id", query.getId()).eq("im_status", 2);
		int inspectedNum=qcAssetsService.selectCount(wrapper2);
		page.setRecords(list);
		page.setInspectingNum(inspectingNum);
		page.setInspectedNum(inspectedNum);
		return page;*/
	}

	@Override
	public QcImAssetsResponse getQcImAssets(QcImAssets request) {
		if(request!=null&&request.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", request.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement QcImplement=list.get(0);
				request.setId(QcImplement.getId());
			}else{
				throw ExceptionFactory.create("Q_017");
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		QcImAssetsResponse qcImAssetsResponse = new QcImAssetsResponse();
		Wrapper<QcAssets> wrapperQcAssets = new EntityWrapper<>();
		wrapperQcAssets.eq("implement_id", request.getId()).eq("assets_id", request.getAssetId());
		List<QcAssets> listQcAssets = qcAssetsService.selectList(wrapperQcAssets);
		QcAssets qcAssets = null;
		if (listQcAssets != null && listQcAssets.size() > 0) {
			qcAssets = listQcAssets.get(0);
			QcImAssets2 assetInfo = new QcImAssets2();
			qcImAssetsResponse.setAnswers(qcAssets.getResult());
			qcImAssetsResponse.setStatus(qcAssets.getResultStatus());
			qcImAssetsResponse.setRemarks(qcAssets.getRemarks());
			assetInfo.setName(qcAssets.getAssetsName());
			assetInfo.setAssetNo(qcAssets.getAssetsNum());
			assetInfo.setDeptName(qcAssets.getAssetsDeptName());
			qcImAssetsResponse.setAssetInfo(assetInfo);
		}
		QcImplement qcImplement=qcImplementService.selectById(request.getId());
		if(qcImplement!=null){
			QcPlan  qcPlan =qcPlanMapper.selectById(qcImplement.getPalnId());
			if(qcPlan!=null){
				List<QcProjectRequest4> listQcProjectRequest=getListQcproject(qcPlan);
				qcImAssetsResponse.setModel(listQcProjectRequest);
			}
		}
		return qcImAssetsResponse;
	}

	@Override
	public List<QcProjectRequest4> getTempleteById(Long id) {
		Wrapper<QcImplement> wrapper = new EntityWrapper<>();
		wrapper.eq("paln_id", id).eq("status", 1);
		List<QcImplement> listQcImplement = qcImplementService.selectList(wrapper);
		if (listQcImplement != null && listQcImplement.size() > 0) {
			QcImplement qcImplement = listQcImplement.get(0);
			if (qcImplement != null) {
				QcPlan qcPlan = qcPlanMapper.selectById(qcImplement.getPalnId());
				if (qcPlan != null) {
					List<QcProjectRequest4> list = getListQcproject(qcPlan);
					return list;
				}
			} 
		}
		return null;
	}

	@Override
	public QcDetail getQcDetailByQcPlan(QcPlan qcPlan) {
		QcDetail qcDetail=new QcDetail();
		qcDetail.setNextDate(qcPlan.getNextTime()==null?qcPlan.getFirstTime():qcPlan.getNextTime());
		Wrapper<QcImplement> wrapperQcImplement=new EntityWrapper<>();
		wrapperQcImplement.eq("paln_id", qcPlan.getId()).eq("status", 1);
		List<QcImplement> listQcImplement=qcImplementService.selectList(wrapperQcImplement);
		QcImplement QcImplement=null;
		if(listQcImplement!=null&&listQcImplement.size()>0){
			QcImplement=listQcImplement.get(0);
		}
		if(QcImplement!=null){
			qcDetail.setCondition(QcImplement.getCondition());
			qcDetail.setAnalysis(QcImplement.getAnalysis());
			qcDetail.setSuggestion(QcImplement.getSuggestion());
		}
		Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
		wrapper.eq("paln_id", qcPlan.getId()).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNull("implement_id");
		List<QcPlanCheck> list=qcPlanCheckService.selectList(wrapper);
		if(list!=null&&list.size()>0){
			List<QcChargeRequest> listCheckMan=new ArrayList<>();
			for(QcPlanCheck qcPlanCheck:list){
				QcChargeRequest qcChargeRequest=new QcChargeRequest();
				qcChargeRequest.setId(qcPlanCheck.getUserId());
				qcChargeRequest.setName(qcPlanCheck.getUserName());
				listCheckMan.add(qcChargeRequest);
			}
			qcDetail.setCheckMan(listCheckMan);
		}
		return qcDetail;
	}

	@Override
	public Integer getQcImplementWaitToDo(Long id) {
		int i=0;
		Wrapper<QcPlan> wrapper=new EntityWrapper<>();
		wrapper.eq("charge_id", id).eq("status", 1).eq("check_status",1).eq("del_flag", false);
		List<QcPlan> list=qcPlanMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			i+=list.size();
		}
		return i;
	}

	@Override
	public void autoSend() {
		Wrapper<QcPlan> wrapper=new EntityWrapper<>();
		wrapper.eq("status", 1).eq("check_status",1).eq("msg_status",1).eq("del_flag", false);
		List<QcPlan> list=qcPlanMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			for(QcPlan qcPlan:list){
				int  flag=QcUtils.getFlag(new Date(), qcPlan.getNextTime());
				if(1==flag){
					//发送消息 5天待办
					SendMessage  message=new SendMessage();
					message.setModuleId(qcPlan.getId());
					message.setRemarks("巡检日期"+QcUtils.getMsgDate(qcPlan.getNextTime()));
					long  days=QcUtils.getNumDay(new Date(), qcPlan.getNextTime());
					message.setMessageContent("巡检计划"+days+"天后将过期，查看详情");
					message.setStatus(3);
					message.setUserId(qcPlan.getChargeId());
					Result<Object> rusult=repairClientService.send(message);
					if(rusult!=null){
						if("200".equals(rusult.getCode())){
							qcPlan.setMsgStatus(2);
							qcPlanMapper.updateById(qcPlan);
						}
					}
					
				}else if(2==flag){
					//发送消息 过期待办
					SendMessage  message=new SendMessage();
					message.setModuleId(qcPlan.getId());
					message.setRemarks("巡检日期"+QcUtils.getMsgDate(qcPlan.getNextTime()));
					message.setMessageContent("巡检已过期，查看详情");
					message.setStatus(3);
					message.setUserId(qcPlan.getChargeId());
					Result<Object> rusult=repairClientService.send(message);
					if(rusult!=null){
						if("200".equals(rusult.getCode())){
							qcPlan.setMsgStatus(2);
							qcPlanMapper.updateById(qcPlan);
						}
					}
				}
			}
		}
		
	}
	
}
