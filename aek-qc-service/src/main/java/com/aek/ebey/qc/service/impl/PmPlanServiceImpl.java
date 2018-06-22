package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.CheckStatus;
import com.aek.ebey.qc.inter.Constants;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.PmPlanMapper;
import com.aek.ebey.qc.model.PmImplement;
import com.aek.ebey.qc.model.PmImplementFile;
import com.aek.ebey.qc.model.PmOptionHelp;
import com.aek.ebey.qc.model.PmPlan;
import com.aek.ebey.qc.model.PmPlanImplement;
import com.aek.ebey.qc.model.PmPlanImplementHelp;
import com.aek.ebey.qc.model.PmProjectHelp;
import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.model.PmTemplateHelp;
import com.aek.ebey.qc.query.PmPlanQuery;
import com.aek.ebey.qc.query.PmQAssets;
import com.aek.ebey.qc.request.CheckDetail;
import com.aek.ebey.qc.request.PmAssets;
import com.aek.ebey.qc.request.PmEnable;
import com.aek.ebey.qc.request.PmFile;
import com.aek.ebey.qc.request.PmImplementReport;
import com.aek.ebey.qc.request.PmOptionRequest;
import com.aek.ebey.qc.request.PmPlanDetail;
import com.aek.ebey.qc.request.PmPlanEditDetail;
import com.aek.ebey.qc.request.PmPlanEditRequest;
import com.aek.ebey.qc.request.PmPlanRequest;
import com.aek.ebey.qc.request.PmPlanResponse;
import com.aek.ebey.qc.request.PmProjectDetail;
import com.aek.ebey.qc.request.PmProjectDetail2;
import com.aek.ebey.qc.request.PmProjectRequest;
import com.aek.ebey.qc.request.PmTemplateDetail;
import com.aek.ebey.qc.request.PmTemplateEditResponse;
import com.aek.ebey.qc.request.QcChargeRequest;
import com.aek.ebey.qc.service.PmImplementFileService;
import com.aek.ebey.qc.service.PmImplementService;
import com.aek.ebey.qc.service.PmOptionHelpService;
import com.aek.ebey.qc.service.PmPlanImplementHelpService;
import com.aek.ebey.qc.service.PmPlanImplementService;
import com.aek.ebey.qc.service.PmPlanService;
import com.aek.ebey.qc.service.PmProjectHelpService;
import com.aek.ebey.qc.service.PmTemplateHelpService;
import com.aek.ebey.qc.service.PmTemplateService;
import com.aek.ebey.qc.service.feign.AssetsClientService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * PM巡检计划 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@Service
@Transactional
public class PmPlanServiceImpl extends BaseServiceImpl<PmPlanMapper, PmPlan> implements PmPlanService {

	@Autowired
	private PmPlanMapper pmPlanMapper;
	
	
	@Autowired
	private PmPlanImplementHelpService pmPlanImplementHelpService;
	
	@Autowired
	private PmPlanImplementService pmPlanImplementService;
	
	@Autowired
	private PmTemplateService pmTemplateService;
	
	@Autowired
	private PmTemplateHelpService pmTemplateHelpService;
	
	@Autowired
	private PmProjectHelpService pmProjectHelpService;
	
	@Autowired
	private PmOptionHelpService pmOptionHelpService;
	
	@Autowired
	private PmImplementService pmImplementService;
	
	@Autowired
	private PmImplementFileService pmImplementFileService;
	
	@Autowired
	private AssetsClientService assetsClientService;
	
	@Autowired
	private DeptClientService deptClientService;
	
	
	
	
	@Override
	public void save(PmPlanRequest request) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if (request != null) {
			Long checkId = request.getCheckId();
			if (checkId != null) {
				Result<Boolean> ret = deptClientService.checkUserPermission(checkId, "PM_CHECK_MANAGE",
						WebSecurityUtils.getCurrentToken());
				if (ret != null) {
					if (ret.getData()) {
						savePmPlan(request, authUser);
					} else {
						throw ExceptionFactory.create("Q_020");
					}
				} else {
					throw ExceptionFactory.create("Q_005");
				}
			} else {
				savePmPlan(request, authUser);
			}

		}
	}

	private void savePmPlan(PmPlanRequest request, AuthUser authUser) {
		PmTemplateHelp templateHelp = null;
		Long templateId = request.getTemplateId();
		if (templateId != null) {

			checkPmTemplete(templateId);

			templateHelp = addPmTemplateHelp(templateHelp, templateId);
			List<PmAssets> list = request.getEquipmentIds();
			if (list != null && list.size() > 0) {
				List<Long> assetsIds = new ArrayList<Long>();
				for (PmAssets pmAssets : list) {
					PmPlan pmPlan = BeanMapper.map(request, PmPlan.class);
					pmPlan.setAssetsId(pmAssets.getId());
					pmPlan.setAssetsNum(pmAssets.getNo());
					pmPlan.setAssetsName(pmAssets.getName());
					pmPlan.setAssetsSpec(pmAssets.getModel());
					pmPlan.setAssetsDeptId(pmAssets.getDepartmentId());
					pmPlan.setAssetsDeptName(pmAssets.getDepartmentName());
					pmPlan.setAssetsStatus(pmAssets.getEquipmentStatusType());
					pmPlan.setAssetsStatusText(pmAssets.getEquipmentStatusText());
					pmPlan.setTenantId(authUser.getTenantId());
					pmPlan.setTenantName(authUser.getTenantName());
					pmPlan.setTemplateId(templateHelp.getId());
					pmPlan.setCreateTime(new Date());
					// pmPlan.setCycle(request.getCycle());
					// pmPlan.setLevel(request.getLevel());
					pmPlan.setFirstTime(request.getStartDate());
					pmPlan.setNextTime(request.getStartDate());
					pmPlan.setChargeId(request.getDirector().getId());
					pmPlan.setChargeName(request.getDirector().getName());
					pmPlan.setPreTemplateId(request.getTemplateId());
					pmPlan.setStatus(StatusConstants.ONE);
					pmPlan.setCheckStatus(1);
					Wrapper<PmPlan> wrapper = new EntityWrapper<>();
					wrapper.eq("tenant_id", pmPlan.getTenantId()).eq("assets_id", pmAssets.getId())
							.eq("del_flag", false);
					List<PmPlan> listPmPlan = pmPlanMapper.selectList(wrapper);
					if (listPmPlan != null && listPmPlan.size() > 0) {
						throw ExceptionFactory.create("P_002");
					}
					this.pmPlanMapper.insert(pmPlan);
					insertPlanImplement(pmPlan, null);
					assetsIds.add(pmAssets.getId());
				}
				Result<Object> re = assetsClientService
						.updateAssetsPmPlanStatusByIds(WebSecurityUtils.getCurrentToken(), assetsIds);
				if (re != null && !"200".equals(re.getCode())) {
					throw ExceptionFactory.create("Q_003");
				}
			}

		}
	}
		
			

	private void insertPlanImplement(PmPlan pmPlan,PmEnable pmEnable) {
		if(pmEnable!=null){
			Wrapper<PmPlanImplementHelp> wrapper=new EntityWrapper<>();
			wrapper.eq("plan_id", pmEnable.getId());
			List<PmPlanImplementHelp> listpmPlanImplement=pmPlanImplementHelpService.selectList(wrapper);
			if(listpmPlanImplement!=null&&listpmPlanImplement.size()>0){
				return ;
			}
		}
		PmPlanImplementHelp pmPlanImplement =BeanMapper.map(pmPlan, PmPlanImplementHelp.class);
		pmPlanImplement.setPlanId(pmPlan.getId());
		pmPlanImplement.setId(null);
		pmPlanImplement.setNextTime(pmPlan.getNextTime());
		pmPlanImplement.setMsgStatus(1);
		if(pmEnable!=null){
			pmPlanImplement.setNextTime(pmEnable.getNextDate());
		}
		pmPlanImplementHelpService.insert(pmPlanImplement);
	}

	/**
	 * 插入模板
	 */
	private PmTemplateHelp addPmTemplateHelp(PmTemplateHelp templateHelp, Long templateId) {
		PmTemplateDetail pmTemplateDetail=pmTemplateService.getAllById(templateId);
		if(pmTemplateDetail!=null){
			templateHelp=new PmTemplateHelp();
			templateHelp.setName(pmTemplateDetail.getName());
			templateHelp.setRemarks(pmTemplateDetail.getRemarks());
			templateHelp.setTemplateType(Constants.CUSTOM_TYPE);
			templateHelp.setStatus(StatusConstants.ONE);
			pmTemplateHelpService.insert(templateHelp);
			
			List<PmProjectRequest> listProjectHelp=pmTemplateDetail.getItems();
			if(listProjectHelp!=null&&listProjectHelp.size()>0){
				for(PmProjectRequest pmProjectRequest:listProjectHelp){
					PmProjectHelp projectHelp=new PmProjectHelp();
					projectHelp.setName(pmProjectRequest.getName());
					projectHelp.setTemplateId(templateHelp.getId());
					projectHelp.setStatus(StatusConstants.ONE);
					pmProjectHelpService.insert(projectHelp);
					List<PmOptionRequest> listpmOptionRequest=pmProjectRequest.getOptions();
					if(listpmOptionRequest!=null&&listpmOptionRequest.size()>0){
						for(PmOptionRequest pmOptionRequest:listpmOptionRequest){
							PmOptionHelp pmOptionHelp=new PmOptionHelp();
							pmOptionHelp.setName(pmOptionRequest.getName());
							pmOptionHelp.setProjectId(projectHelp.getId());
							pmOptionHelp.setStatus(StatusConstants.ONE);
							pmOptionHelpService.insert(pmOptionHelp);
						}
					}
				}	
			}
			return templateHelp;
		}
		return null;
	}

	/**
	 * 验证模板是否有效
	 */
	@Transactional(readOnly=true)
	private void checkPmTemplete(Long templateId) {
		PmTemplate  pmTemplate =pmTemplateService.selectById(templateId);
		if(pmTemplate!=null){
			if(pmTemplate.isDelFlag()){
				throw ExceptionFactory.create("Q_012");
			}
			if(pmTemplate.getStatus().intValue()==StatusConstants.TWO.intValue()){
				throw ExceptionFactory.create("Q_013");
			}
			if(!pmTemplate.isHasProject()){
				throw ExceptionFactory.create("Q_014");
			}
		}else{
			throw ExceptionFactory.create("Q_012");
		}
	}

	@Override
	public void edit(PmPlanEditRequest request) {
		PmPlan pmPlan=pmPlanMapper.selectById(request.getId());
		if(pmPlan!=null&&pmPlan.getStatus().intValue()==1){
			PmTemplateHelp templateHelp=null;
			Long templateId=request.getTemplateId();
			checkPmTemplete(templateId);
			templateHelp= addPmTemplateHelp(templateHelp, templateId);
			if(templateHelp!=null){
				pmPlan.setTemplateId(templateHelp.getId());
			}else{
				throw ExceptionFactory.create("G_006");
			}
			pmPlan.setPreTemplateId(templateId);
			pmPlan.setCycle(request.getCycle());
			pmPlan.setLevel(request.getLevel());
			pmPlan.setNextTime(pmPlan.getFirstTime());
			if(pmPlan.getFirstTime()==null){
				pmPlan.setFirstTime(request.getNextDate());
			}
			pmPlan.setChargeId(request.getDirector().getId());
			pmPlan.setChargeName(request.getDirector().getName());
			QcChargeRequest check=request.getCheck();
			if(check!=null){
				Long checkId=check.getId();
				String name=check.getName();
				pmPlan.setCheckId(checkId);
				pmPlan.setCheckName(name);
				Result<Boolean> ret = deptClientService.checkUserPermission(checkId, "PM_CHECK_MANAGE",
						WebSecurityUtils.getCurrentToken());
				if (ret != null) {
					if (!ret.getData()) {
						throw ExceptionFactory.create("Q_020");
					}
				} else {
					throw ExceptionFactory.create("Q_005");
				}
				
			}else{
				pmPlan.setCheckId(null);
				pmPlan.setCheckName(null);
			}
			pmPlanMapper.updateAllColumnById(pmPlan);
			Wrapper<PmPlanImplementHelp> wrapper=new EntityWrapper<>();
			wrapper.eq("plan_id", pmPlan.getId());
			List<PmPlanImplementHelp> list=	pmPlanImplementHelpService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				PmPlanImplementHelp pmPlanImplementHelp=list.get(0);
				//待实施
				if(pmPlanImplementHelp.getCheckStatus().intValue()==1){
					pmPlanImplementHelpService.deleteById(pmPlanImplementHelp);
					insertPlanImplement(pmPlan,null);
				}
			}
		}else{
			throw ExceptionFactory.create("G_006");
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public PmPlanDetail getAllByPmPlanId(Long id) {
		PmPlan pmPlan=pmPlanMapper.selectById(id);
		if(pmPlan!=null){
			PmPlanDetail pmPlanDetail=new PmPlanDetail();
			pmPlanDetail.setNo(pmPlan.getAssetsNum());
			pmPlanDetail.setName(pmPlan.getAssetsName());
			pmPlanDetail.setModel(pmPlan.getAssetsSpec());
			pmPlanDetail.setDepartmentName(pmPlan.getAssetsDeptName());
			pmPlanDetail.setCycle(pmPlan.getCycle());
			pmPlanDetail.setLevel(pmPlan.getLevel());
			pmPlanDetail.setDirectorName(pmPlan.getChargeName());
			pmPlanDetail.setCreateTime(pmPlan.getCreateTime());
			pmPlanDetail.setPrevDate(pmPlan.getPreTime());
			pmPlanDetail.setNextDate(pmPlan.getNextTime());
			pmPlanDetail.setStatus(pmPlan.getStatus());
			pmPlanDetail.setCheckName(pmPlan.getCheckName());
			//巡检范围
			List<PmProjectDetail> list=pmTemplateHelpService.getAllById(pmPlan.getTemplateId());
			pmPlanDetail.setTemplate(list);
			return pmPlanDetail;
		}
		return null;
		
	}

	@Override
	public void updatePmPlan(Long id) {
		PmPlan  pmPlan =pmPlanMapper.selectById(id);
		if(pmPlan!=null&&pmPlan.getStatus().intValue()==StatusConstants.ONE.intValue()){
			pmPlan.setStatus(StatusConstants.TWO);
			pmPlanMapper.updateById(pmPlan);
		}else{
			throw ExceptionFactory.create("P_003");
		}
		Wrapper<PmPlanImplementHelp> wrapper=new EntityWrapper<>();
		wrapper.eq("plan_id", id).eq("del_flag", false);
		List<PmPlanImplementHelp> listPmPlanImplement=pmPlanImplementHelpService.selectList(wrapper);
		if(listPmPlanImplement!=null&&listPmPlanImplement.size()>0){
			PmPlanImplementHelp pmPlanImplementHelp=listPmPlanImplement.get(0);
			pmPlanImplementHelp.setStatus(StatusConstants.TWO);
			if(pmPlanImplementHelp.getCheckStatus().intValue()==1){
				pmPlanImplementHelpService.deleteById(pmPlanImplementHelp.getId());	
			}else{
				pmPlanImplementHelpService.updateById(pmPlanImplementHelp);
			}
		}
		
		
		
	}

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<PmPlanResponse> search(PmPlanQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<PmPlanResponse> pagePmPlan = query.getPagePlus();
		List<PmPlanResponse> list=pmPlanMapper.search(pagePmPlan,query,authUser);
		LiuHuiPage<PmPlanResponse> page=new LiuHuiPage<>(pagePmPlan);
		page.setRecords(list);
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public PmPlanEditDetail getAllByPmPlanId2(Long id) {
		PmPlan pmPlan=pmPlanMapper.selectById(id);
		if(pmPlan!=null){
			PmPlanEditDetail pmPlanEditDetail=new PmPlanEditDetail();
			pmPlanEditDetail.setNo(pmPlan.getAssetsNum());
			pmPlanEditDetail.setName(pmPlan.getAssetsName());
			pmPlanEditDetail.setModel(pmPlan.getAssetsSpec());
			pmPlanEditDetail.setDepartmentName(pmPlan.getAssetsDeptName());
			pmPlanEditDetail.setCycle(pmPlan.getCycle());
			pmPlanEditDetail.setLevel(pmPlan.getLevel());
			QcChargeRequest qcChargeRequest=new QcChargeRequest();
			qcChargeRequest.setId(pmPlan.getChargeId());
			qcChargeRequest.setName(pmPlan.getChargeName());
			QcChargeRequest qcCheck=new QcChargeRequest();
			qcCheck.setId(pmPlan.getCheckId());
			qcCheck.setName(pmPlan.getCheckName());
			pmPlanEditDetail.setDirectorName(qcChargeRequest);
			pmPlanEditDetail.setCheck(qcCheck);
			pmPlanEditDetail.setCreateTime(pmPlan.getCreateTime());
			pmPlanEditDetail.setPrevDate(pmPlan.getPreTime());
			pmPlanEditDetail.setNextDate(pmPlan.getNextTime());
			pmPlanEditDetail.setStatus(pmPlan.getStatus());
			PmTemplateEditResponse PmTemplateEditResponse=new PmTemplateEditResponse();
			Long templateId=pmPlan.getTemplateId();
			Long preTemplateId=pmPlan.getPreTemplateId();
			if(templateId!=null){
				PmTemplateHelp pmTemplateHelp=pmTemplateHelpService.selectById(templateId);
				PmTemplateEditResponse.setId(preTemplateId);
				PmTemplateEditResponse.setName(pmTemplateHelp.getName());
			}
			pmPlanEditDetail.setTemplate(PmTemplateEditResponse);
			return pmPlanEditDetail;
		}
		return null;
	}

	@Override
	public CheckDetail enableById(PmEnable pmEnable) {
		PmPlan pmPlan=pmPlanMapper.selectById(pmEnable.getId());
		Result<PmQAssets> re=assetsClientService.getAssetsStatus(WebSecurityUtils.getCurrentToken(), pmPlan.getAssetsId());
		Integer assetsStatus=pmPlan.getAssetsStatus();
		String assetsStatusText=pmPlan.getAssetsStatusText();
		CheckDetail checkDetail=new CheckDetail();
		if(re != null &&"200".equals(re.getCode())){
			PmQAssets pmQAssets=re.getData();
			if(pmQAssets!=null){
				assetsStatus=pmQAssets.getStatus();
				assetsStatusText=pmQAssets.getStatusName();
			}
		}else{
			throw ExceptionFactory.create("Q_005"); 
		}
		if(pmPlan!=null){
			pmPlan.setAssetsStatus(assetsStatus);
			pmPlan.setAssetsStatusText(assetsStatusText);
			pmPlanMapper.updateById(pmPlan);
		}
		Integer[] intstatus={4,5,6};
		List<Integer> list=new ArrayList<>(Arrays.asList(intstatus));
		if(list.contains(pmPlan.getAssetsStatus().intValue())){
			checkDetail.setStatus(2);
			checkDetail.setMsg(assetsStatusText);
			return checkDetail;
		}
		if(pmPlan!=null&&pmPlan.getStatus().intValue()==StatusConstants.TWO.intValue()){
			pmPlan.setStatus(StatusConstants.ONE);
			pmPlan.setNextTime(pmEnable.getNextDate());
			pmPlanMapper.updateById(pmPlan);
			insertPlanImplement(pmPlan,pmEnable);
			checkDetail.setStatus(1);
			checkDetail.setMsg("OK");
			return checkDetail;
		}else{
			throw ExceptionFactory.create("P_004");
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public PmImplementReport getImplementReportById(Long id) {
		AuthUser user=WebSecurityUtils.getCurrentUser();
		Wrapper<PmImplement> wrapper = new EntityWrapper<>();
		wrapper.eq("implement_id", id);
		List<PmImplement> list = pmImplementService.selectList(wrapper);
		if (list != null && list.size() > 0) {
			PmImplement pmImplement = list.get(0);
			if (pmImplement != null) {
				PmImplementReport pmImplementReport = new PmImplementReport();
				PmPlan pmPlan = pmPlanMapper.selectById(pmImplement.getPalnId());
				if (pmPlan != null) {
					Long implementId=pmImplement.getImplementId();
					Wrapper<PmPlanImplement> wrapperPmPlanImplement = new EntityWrapper<>();
					wrapperPmPlanImplement.eq("implement_id", implementId);
					List<PmPlanImplement>  listPmPlanImplement=pmPlanImplementService.selectList(wrapperPmPlanImplement);
					if(listPmPlanImplement!=null&&listPmPlanImplement.size()>0){
						PmPlanImplement PmPlanImplement=listPmPlanImplement.get(0);
						pmImplementReport.setDirectorName(PmPlanImplement.getChargeName());
						pmImplementReport.setHospital(PmPlanImplement.getTenantName());
						pmImplementReport.setNo(PmPlanImplement.getAssetsNum());
						pmImplementReport.setName(PmPlanImplement.getAssetsName());
						pmImplementReport.setModel(PmPlanImplement.getAssetsSpec());
						pmImplementReport.setCycle(PmPlanImplement.getCycle());
						pmImplementReport.setDepartmentName(PmPlanImplement.getAssetsDeptName());
						pmImplementReport.setCheckMan(PmPlanImplement.getCheckName());
						if(pmImplement.getIsCheck().intValue()==CheckStatus.ONE.getCode()){
							Long checkId=PmPlanImplement.getCheckId();
							if(checkId!=null&&checkId.longValue()==user.getId().longValue()){
								pmImplementReport.setStatus(CheckStatus.ONE.getCode());
							}
						}else{
							pmImplementReport.setCheckedMan(PmPlanImplement.getCheckName());
						}
					}
					// 巡检范围
					List<PmProjectDetail2> items = pmPlanImplementHelpService.getItems2(id);
					pmImplementReport.setItems(items);
					pmImplementReport.setLive(pmImplement.getLive());
					pmImplementReport.setWorkTime(pmImplement.getWorkTime());
					Wrapper<PmImplementFile> wrapperPmImplementFile = new EntityWrapper<>();
					wrapperPmImplementFile.eq("implement_id", id);
					List<PmImplementFile> listFile = pmImplementFileService.selectList(wrapperPmImplementFile);
					if (listFile != null && listFile.size() > 0) {
						List<PmFile> file=new ArrayList<>();
						for(int i=0;i<listFile.size();i++){
							PmFile f=new PmFile();
							f.setName(listFile.get(i).getName());
							f.setUrl(listFile.get(i).getUrl());
							file.add(f);
						}
						pmImplementReport.setFiles(file);
					}
					pmImplementReport.setRemarks(pmImplement.getRemarks());
					pmImplementReport.setReportNo(pmImplement.getReportNo());
					pmImplementReport.setStartDate(pmImplement.getActualStartDate());
					pmImplementReport.setEndDate(pmImplement.getActualEndDate());

				}
				
				return pmImplementReport;
			}
		}
		return null;

	}

	@Override
	public void updatePmPlan2(Long id) {
		PmPlanImplementHelp  pmPlanImplementHelp =pmPlanImplementHelpService.selectById(id);
		if(pmPlanImplementHelp!=null){
			Long pmPlanId=pmPlanImplementHelp.getPlanId();
			PmPlan  pmPlan =pmPlanMapper.selectById(pmPlanId);
			if(pmPlan!=null&&pmPlan.getStatus().intValue()==StatusConstants.ONE.intValue()){
				pmPlan.setStatus(StatusConstants.TWO);
				pmPlanMapper.updateById(pmPlan);
			}else{
				throw ExceptionFactory.create("P_003");
			}
			Wrapper<PmPlanImplementHelp> wrapper=new EntityWrapper<>();
			wrapper.eq("plan_id", pmPlanId).eq("del_flag", false);
			List<PmPlanImplementHelp> listPmPlanImplement=pmPlanImplementHelpService.selectList(wrapper);
			if(listPmPlanImplement!=null&&listPmPlanImplement.size()>0){
				PmPlanImplementHelp pmPlanImplementHelp2=listPmPlanImplement.get(0);
				pmPlanImplementHelp2.setStatus(StatusConstants.TWO);
				if(pmPlanImplementHelp2.getCheckStatus().intValue()==1){
					pmPlanImplementHelpService.deleteById(pmPlanImplementHelp2.getId());	
				}else{
					pmPlanImplementHelpService.updateById(pmPlanImplementHelp2);
				}
			}
		}
		
		
	}
	
}
