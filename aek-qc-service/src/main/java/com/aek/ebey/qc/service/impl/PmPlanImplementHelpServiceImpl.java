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
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.PmPlanImplementHelpMapper;
import com.aek.ebey.qc.model.PmAnswers;
import com.aek.ebey.qc.model.PmAnswersOption;
import com.aek.ebey.qc.model.PmImplement;
import com.aek.ebey.qc.model.PmImplementFile;
import com.aek.ebey.qc.model.PmOptionHelp;
import com.aek.ebey.qc.model.PmPlan;
import com.aek.ebey.qc.model.PmPlanImplement;
import com.aek.ebey.qc.model.PmPlanImplementHelp;
import com.aek.ebey.qc.model.PmProjectHelp;
import com.aek.ebey.qc.query.PmPlanImplementQuery;
import com.aek.ebey.qc.query.PmQAssets;
import com.aek.ebey.qc.request.CheckDetail;
import com.aek.ebey.qc.request.PmAnswerRequest;
import com.aek.ebey.qc.request.PmImplementDetail;
import com.aek.ebey.qc.request.PmImplementFileRequest;
import com.aek.ebey.qc.request.PmImplementRequest;
import com.aek.ebey.qc.request.PmImplementSheet;
import com.aek.ebey.qc.request.PmOptionAnswerRequest;
import com.aek.ebey.qc.request.PmPlanImplementResponse;
import com.aek.ebey.qc.request.PmProjectDetail;
import com.aek.ebey.qc.request.PmProjectDetail2;
import com.aek.ebey.qc.request.QcChargeRequest;
import com.aek.ebey.qc.request.SendMessage;
import com.aek.ebey.qc.service.PmAnswersOptionService;
import com.aek.ebey.qc.service.PmAnswersService;
import com.aek.ebey.qc.service.PmImplementFileService;
import com.aek.ebey.qc.service.PmImplementService;
import com.aek.ebey.qc.service.PmOptionHelpService;
import com.aek.ebey.qc.service.PmPlanImplementHelpService;
import com.aek.ebey.qc.service.PmPlanImplementService;
import com.aek.ebey.qc.service.PmPlanService;
import com.aek.ebey.qc.service.PmProjectHelpService;
import com.aek.ebey.qc.service.feign.AssetsClientService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.aek.ebey.qc.service.feign.RepairClientService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * PM计划实施 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@Service
@Transactional
public class PmPlanImplementHelpServiceImpl extends BaseServiceImpl<PmPlanImplementHelpMapper, PmPlanImplementHelp> implements PmPlanImplementHelpService {
	@Autowired
	private PmPlanImplementHelpMapper pmPlanImplementHelpMapper;
	
	@Autowired
	private PmPlanService pmPlanService;
	
	@Autowired
	private PmImplementService pmImplementService;
	
	@Autowired
	private PmPlanImplementService pmPlanImplementService;
	
	@Autowired
	private PmProjectHelpService pmProjectHelpService;
	
	@Autowired
	private PmOptionHelpService pmOptionHelpService;
	
	
	@Autowired
	private PmImplementFileService pmImplementFileService;
	
	@Autowired
	private PmAnswersService pmAnswersService;
	
	@Autowired
	private PmAnswersOptionService pmAnswersOptionService;
	
	@Autowired
	private AssetsClientService assetsClientService;
	
	@Autowired
	private  RepairClientService repairClientService;
	
	@Autowired
	private  DeptClientService deptClientService;
	
	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<PmPlanImplementResponse> search(PmPlanImplementQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<PmPlanImplementResponse> pmPlanImplement = query.getPagePlus();
		List<PmPlanImplementResponse> list=pmPlanImplementHelpMapper.search(pmPlanImplement,query,authUser);
		LiuHuiPage<PmPlanImplementResponse> page=new LiuHuiPage<>(pmPlanImplement);
		if(list!=null&&list.size()>0){
			for(PmPlanImplementResponse pmPlanImplementResponse:list){
				if(pmPlanImplementResponse.getNextDate()!=null){
					pmPlanImplementResponse.setAttention(QcUtils.getAttention(new Date(),pmPlanImplementResponse.getNextDate()));
				}
			}
		}
		page.setRecords(list);
		return page;
	}
	@Override
	public CheckDetail checkById(Long id) {
		PmPlanImplementHelp pmPlanImplementHelp=pmPlanImplementHelpMapper.selectById(id);
		CheckDetail checkDetail=new CheckDetail();
		checkDetail.setStatus(1);
		checkDetail.setMsg("OK");
		if(pmPlanImplementHelp!=null){
			//实施中
			if(pmPlanImplementHelp.getCheckStatus().intValue()==2){
				return checkDetail;
			}else{
				//调用接口查看是否可以实施
				PmPlan pmPlan=pmPlanService.selectById(pmPlanImplementHelp.getPlanId());
				Integer assetsStatus=pmPlanImplementHelp.getAssetsStatus();
				String assetsStatusText=pmPlanImplementHelp.getAssetsStatusText();
				Result<PmQAssets> re=assetsClientService.getAssetsStatus(WebSecurityUtils.getCurrentToken(), pmPlanImplementHelp.getAssetsId());
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
					pmPlanService.updateById(pmPlan);
				}
				pmPlanImplementHelp.setAssetsStatus(assetsStatus);
				pmPlanImplementHelp.setAssetsStatusText(assetsStatusText);
				Integer[] intstatus={4,5,6};
				List<Integer> list=new ArrayList<>(Arrays.asList(intstatus));
				if(list.contains(pmPlanImplementHelp.getAssetsStatus().intValue())){
					//不能实施
					checkDetail.setStatus(2);
					checkDetail.setMsg(assetsStatusText);
					pmPlanImplementHelpMapper.updateById(pmPlanImplementHelp);
					return checkDetail;
				}
				//开始实施
				PmImplement pmImplement=new PmImplement();
				pmImplement.setImplementId(pmPlanImplementHelp.getId());
				pmImplement.setPalnId(pmPlan.getId());
				pmImplement.setPlanDate(pmPlan.getNextTime());
				pmImplement.setCreateDate(new Date());
				pmImplement.setStatus(1);
				pmImplementService.insert(pmImplement);
				
				Wrapper<PmProjectHelp> wrapper=new EntityWrapper<>();
				wrapper.eq("template_id", pmPlanImplementHelp.getTemplateId());
				List<PmProjectHelp> listPmProject=pmProjectHelpService.selectList(wrapper);
				if(listPmProject!=null&&listPmProject.size()>0){
					for(PmProjectHelp pmProjectHelp:listPmProject){
						Long pmProjectId=pmProjectHelp.getId();
						PmAnswers pmAnswers=new PmAnswers();
						pmAnswers.setImplementId(pmPlanImplementHelp.getId());
						pmAnswers.setProjectId(pmProjectId);
						pmAnswers.setProjectName(pmProjectHelp.getName());
						pmAnswersService.insert(pmAnswers);
						Wrapper<PmOptionHelp> wrapperPmOptionHelp=new EntityWrapper<>();
						wrapperPmOptionHelp.eq("project_id", pmProjectId);
						List<PmOptionHelp> listPmOptionHelp=pmOptionHelpService.selectList(wrapperPmOptionHelp);
						if(listPmOptionHelp!=null&&listPmOptionHelp.size()>0){
							for(PmOptionHelp pmOptionHelp:listPmOptionHelp){
								PmAnswersOption answersOption=new PmAnswersOption();
								answersOption.setAnswerId(pmAnswers.getId());
								answersOption.setImplementId(id);
								answersOption.setOptionId(pmOptionHelp.getId());
								answersOption.setOptionName(pmOptionHelp.getName());
								answersOption.setAnswer(1);
								pmAnswersOptionService.insert(answersOption);
							}
						}
					}
				}
				pmPlanImplementHelp.setCheckStatus(2);
				pmPlanImplementHelpMapper.updateById(pmPlanImplementHelp);
			}
		}else{
			throw ExceptionFactory.create("P_005"); 
		}
		return checkDetail;
	}
	@Override
	public void save(PmImplementRequest request) {
		Long id = request.getId();
		PmImplement pmImplement = null;
		PmPlanImplementHelp pmPlanImplementHelp = pmPlanImplementHelpMapper.selectById(id);
		if (pmPlanImplementHelp != null) {
			String checkName=null;
			Long checkId=null;
			pmPlanImplementHelp.setNextTime(request.getNextDate());
			pmPlanImplementHelp.setPreTime(request.getActualEndDate());
			QcChargeRequest check=request.getCheckMan();
			if(check!=null){
				checkName=check.getName();
				checkId=check.getId();
			}
			pmPlanImplementHelp.setCheckId(checkId);
			pmPlanImplementHelp.setCheckName(checkName);
			Wrapper<PmImplement> wrapper = new EntityWrapper<>();
			wrapper.eq("implement_id", id);
			List<PmImplement> list = pmImplementService.selectList(wrapper);
			if (list != null && list.size() > 0) {
				pmImplement = list.get(0);
				pmImplement.setLive(request.getLive());
				pmImplement.setWorkTime(request.getWorkTime());
				pmImplement.setRemarks(request.getRemarks());
			}
			List<PmAnswerRequest> listPmAnswer = request.getTemplate();
			if (listPmAnswer != null) {
				for (PmAnswerRequest pmAnswerRequest : listPmAnswer) {
					List<PmOptionAnswerRequest> listOptionAnswer = pmAnswerRequest.getOptions();
					if (listOptionAnswer != null && listOptionAnswer.size() > 0) {
						for (PmOptionAnswerRequest pmOptionAnswerRequest : listOptionAnswer) {
							Long pmOptionId = pmOptionAnswerRequest.getId();
							Wrapper<PmAnswersOption> wrapperPmAnswersOption = new EntityWrapper<>();
							wrapperPmAnswersOption.eq("implement_id", id).eq("option_id", pmOptionId);
							List<PmAnswersOption> listPmAnswersOption = pmAnswersOptionService
									.selectList(wrapperPmAnswersOption);
							if (listPmAnswersOption != null && listPmAnswersOption.size() > 0) {
								PmAnswersOption answersOption = listPmAnswersOption.get(0);
								answersOption.setAnswer(pmOptionAnswerRequest.getAnswer());
								answersOption.setRemarks(pmOptionAnswerRequest.getRemarks());
								answersOption.setMeasure(pmOptionAnswerRequest.getMeasure());
								answersOption.setSetnum(pmOptionAnswerRequest.getSetnum());
								pmAnswersOptionService.updateById(answersOption);
							}

						}
					}
				}
			}
			List<PmImplementFileRequest> listFiles = request.getFiles();
			Wrapper<PmImplementFile> wrapperPmImplementFile = new EntityWrapper<>();
			wrapperPmImplementFile.eq("implement_id", id);
			//删除文件
			pmImplementFileService.delete(wrapperPmImplementFile);
			List<PmImplementFile> listPmfile = new ArrayList<>();
			if (listFiles != null && listFiles.size() > 0) {
				for (PmImplementFileRequest fileRequest : listFiles) {
					PmImplementFile pmImplemmentFile = new PmImplementFile();
					pmImplemmentFile.setImplementId(id);
					pmImplemmentFile.setName(fileRequest.getName());
					pmImplemmentFile.setUrl(fileRequest.getUrl());
					listPmfile.add(pmImplemmentFile);
				}
			}
			if (listPmfile.size() > 0) {
				pmImplementFileService.insertBatch(listPmfile);
			}
			if (request.getType().intValue() == 2) {
				Boolean bol = pmAnswersService.selectAnswerByImplementId(id);
				if (bol) {
					throw ExceptionFactory.create("P_001");
				}
				PmPlanImplement pmPlanImplement = BeanMapper.map(pmPlanImplementHelp, PmPlanImplement.class);
				pmPlanImplement.setTemplateId(pmPlanImplementHelp.getId());
				pmPlanImplement.setImplementId(id);
				pmPlanImplement.setId(null);
				if(checkId!=null){
					Result<Boolean> ret = deptClientService.checkUserPermission(checkId, "PM_CHECK_MANAGE",
							WebSecurityUtils.getCurrentToken());
					if (ret != null) {
						if (!ret.getData()) {
							throw ExceptionFactory.create("Q_020");
						}
					} else {
						throw ExceptionFactory.create("Q_005");
					}
					//待验收
					pmImplement.setIsCheck(CheckStatus.ONE.getCode());
					//pmImplement.setNeedCheck(CheckStatus.ONE.getCode());
				}else{
					//已验收
					pmImplement.setIsCheck(CheckStatus.TWO.getCode());
					//pmImplement.setNeedCheck(CheckStatus.TWO.getCode());
				}
				pmPlanImplementService.insert(pmPlanImplement);
				// pmPlanImplementHelp
				Long planId = pmPlanImplementHelp.getPlanId();
				PmPlan pmPlan = pmPlanService.selectById(planId);
				if (pmPlan != null && pmPlan.getStatus().intValue() == StatusConstants.ONE.intValue()) {
					pmPlan.setCheckName(checkName);
					pmPlan.setCheckId(checkId);
					pmPlan.setNextTime(request.getNextDate());
					pmPlan.setPreTime(request.getActualEndDate());
					PmPlanImplementHelp pmPlanIHelp = BeanMapper.map(pmPlan, PmPlanImplementHelp.class);
					pmPlanIHelp.setPlanId(pmPlan.getId());
					pmPlanIHelp.setId(null);
					pmPlanIHelp.setMsgStatus(1);
					pmPlanImplementHelpMapper.insert(pmPlanIHelp);
					pmPlanService.updateAllColumnById(pmPlan);
				}
				pmPlanImplementHelpMapper.deleteById(pmPlanImplementHelp.getId());
				pmImplement.setStatus(2);
				pmImplement.setReportNo(QcUtils.pMreportNo());
				//发送消息 
				/*SendMessage  message=new SendMessage();
				message.setModuleId(pmImplement.getId());
				message.setRemarks(pmPlan.getAssetsName());
				message.setMessageContent("PM实施已完成，查看详情");
				message.setStatus(5);
				message.setUserId(pmPlan.getChargeId());
				repairClientService.send(message);*/
			}
			pmImplement.setEndDate(new Date());
			pmImplement.setActualStartDate(request.getActualStartDate());
			pmImplement.setActualEndDate(request.getActualEndDate());
			pmImplementService.updateById(pmImplement);
		}else{
			throw ExceptionFactory.create("P_006");
		}
	}
	@Override
	@Transactional(readOnly=true)
	public PmImplementDetail getPmImplementDetail(Long id) {
		
		PmImplementDetail pmImplementDetail=new PmImplementDetail();
		PmPlanImplementHelp  pmPlanImplementHelp =pmPlanImplementHelpMapper.selectById(id);
		Wrapper<PmImplement> wrapperPmImplement=new EntityWrapper<>();
		wrapperPmImplement.eq("implement_id", id);
		List<PmImplement> liPmImplement=pmImplementService.selectList(wrapperPmImplement);
		Wrapper<PmAnswers> wrapperPmAnswers =new EntityWrapper<>();
		wrapperPmAnswers.eq("implement_id", id);
		List<PmAnswers> listPmAnswers=pmAnswersService.selectList(wrapperPmAnswers);
		List<PmAnswerRequest> items=null;
		if(listPmAnswers!=null&&listPmAnswers.size()>0){
			items=new ArrayList<>();
			for(PmAnswers pmAnswers:listPmAnswers){
				PmAnswerRequest pmAnswerRequest=new PmAnswerRequest();
				pmAnswerRequest.setId(pmAnswers.getProjectId());
				pmAnswerRequest.setName(pmAnswers.getProjectName());
				Wrapper<PmAnswersOption> wrapperPmAnswersOption = new EntityWrapper<>();
				wrapperPmAnswersOption.eq("implement_id", id).eq("answer_id", pmAnswers.getId());
				List<PmAnswersOption> listPmAnswersOption = pmAnswersOptionService.selectList(wrapperPmAnswersOption);
				if(listPmAnswersOption!=null&&listPmAnswersOption.size()>0){
					List<PmOptionAnswerRequest> options=new ArrayList<>();
					for(PmAnswersOption pmAnswersOption:listPmAnswersOption){
						PmOptionAnswerRequest pmOptionAnswerRequest=new PmOptionAnswerRequest();
						pmOptionAnswerRequest.setId(pmAnswersOption.getOptionId());
						pmOptionAnswerRequest.setName(pmAnswersOption.getOptionName());
						pmOptionAnswerRequest.setAnswer(pmAnswersOption.getAnswer());
						pmOptionAnswerRequest.setRemarks(pmAnswersOption.getRemarks());
						pmOptionAnswerRequest.setMeasure(pmAnswersOption.getMeasure());
						pmOptionAnswerRequest.setSetnum(pmAnswersOption.getSetnum());
						options.add(pmOptionAnswerRequest);
					}
					pmAnswerRequest.setOptions(options);
				}
				items.add(pmAnswerRequest);
			}
		}
		Wrapper<PmImplementFile> wrapperfiles=new EntityWrapper<>();
		wrapperfiles.eq("implement_id", id);
		List<PmImplementFileRequest> files=null;
		List<PmImplementFile> listFiles=pmImplementFileService.selectList(wrapperfiles);
		if(listFiles!=null&&listFiles.size()>0){
			files=new ArrayList<>();
			for(PmImplementFile pmImplementFile:listFiles){
				PmImplementFileRequest pmImplementFileRequest = new PmImplementFileRequest();
				pmImplementFileRequest.setName(pmImplementFile.getName());
				pmImplementFileRequest.setUrl(pmImplementFile.getUrl());
				files.add(pmImplementFileRequest);
			}
		}
		PmImplement pmImplement=null;
		if(liPmImplement!=null&&liPmImplement.size()>0){
			pmImplement=liPmImplement.get(0);
		}
		if(pmPlanImplementHelp!=null){
			pmImplementDetail.setNo(pmPlanImplementHelp.getAssetsNum());
			pmImplementDetail.setName(pmPlanImplementHelp.getAssetsName());
			pmImplementDetail.setModel(pmPlanImplementHelp.getAssetsSpec());
			pmImplementDetail.setDepartmentName(pmPlanImplementHelp.getAssetsDeptName());
			pmImplementDetail.setCycle(pmPlanImplementHelp.getCycle());
			pmImplementDetail.setLevel(pmPlanImplementHelp.getLevel());
			pmImplementDetail.setDirectorName(pmPlanImplementHelp.getChargeName());
			pmImplementDetail.setCreateTime(pmPlanImplementHelp.getCreateTime());
			pmImplementDetail.setPrevDate(pmPlanImplementHelp.getPreTime());
			pmImplementDetail.setNextDate(pmPlanImplementHelp.getNextTime());
			pmImplementDetail.setEquipmentStatus(pmPlanImplementHelp.getAssetsStatusText());
			pmImplementDetail.setAttention(QcUtils.getAttention(new Date(),pmPlanImplementHelp.getNextTime()));
			pmImplementDetail.setCheckName(pmPlanImplementHelp.getCheckName());
			pmImplementDetail.setCheckId(pmPlanImplementHelp.getCheckId());
		}else{
			Wrapper<PmPlanImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("implement_id", id);
			List<PmPlanImplement> list=pmPlanImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				PmPlanImplement pmPlanImplement=list.get(0);
				pmImplementDetail.setNo(pmPlanImplement.getAssetsNum());
				pmImplementDetail.setName(pmPlanImplement.getAssetsName());
				pmImplementDetail.setModel(pmPlanImplement.getAssetsSpec());
				pmImplementDetail.setDepartmentName(pmPlanImplement.getAssetsDeptName());
				pmImplementDetail.setCycle(pmPlanImplement.getCycle());
				pmImplementDetail.setLevel(pmPlanImplement.getLevel());
				pmImplementDetail.setDirectorName(pmPlanImplement.getChargeName());
				pmImplementDetail.setCreateTime(pmPlanImplement.getCreateTime());
				pmImplementDetail.setPrevDate(pmPlanImplement.getPreTime());
				pmImplementDetail.setNextDate(pmPlanImplement.getNextTime());
				pmImplementDetail.setEquipmentStatus(pmPlanImplement.getAssetsStatusText());
				pmImplementDetail.setAttention(QcUtils.getAttention(new Date(),pmPlanImplement.getNextTime()));
				pmImplementDetail.setCheckName(pmPlanImplement.getCheckName());
				pmImplementDetail.setCheckId(pmPlanImplement.getCheckId());
			}
		}
		if(pmImplement!=null){
			pmImplementDetail.setActualStartDate(pmImplement.getActualStartDate()==null?new Date():pmImplement.getActualStartDate());
			pmImplementDetail.setActualEndDate(pmImplement.getActualEndDate());
			pmImplementDetail.setReportNo(pmImplement.getReportNo());
			pmImplementDetail.setItems(items);
			pmImplementDetail.setLive(pmImplement.getLive());
			pmImplementDetail.setWorkTime(pmImplement.getWorkTime());
			pmImplementDetail.setFiles(files);
			pmImplementDetail.setRemarks(pmImplement.getRemarks());
		}
		return pmImplementDetail;
	}
	@Override
	@Transactional(readOnly=true)
	public PmImplementSheet getImplementSheetById(Long id) {
		PmPlanImplementHelp  pmPlanImplementHelp =pmPlanImplementHelpMapper.selectById(id);
		List<PmProjectDetail> items = getItems(id);
		
		PmImplementSheet pmImplementSheet=new PmImplementSheet();
		if(pmPlanImplementHelp!=null){
			pmImplementSheet.setHospital(pmPlanImplementHelp.getTenantName());
			pmImplementSheet.setNo(pmPlanImplementHelp.getAssetsNum());
			pmImplementSheet.setName(pmPlanImplementHelp.getAssetsName());
			pmImplementSheet.setModel(pmPlanImplementHelp.getAssetsSpec());
			pmImplementSheet.setDepartmentName(pmPlanImplementHelp.getAssetsDeptName());
			pmImplementSheet.setDirectorName(pmPlanImplementHelp.getChargeName());
		}
		pmImplementSheet.setItems(items);
		return pmImplementSheet;
	}
	@Override
	@Transactional(readOnly=true)
	public List<PmProjectDetail> getItems(Long id) {
		List<PmProjectDetail> items=null;
		Wrapper<PmAnswers> wrapperPmAnswers =new EntityWrapper<>();
		wrapperPmAnswers.eq("implement_id", id);
		List<PmAnswers> listPmAnswers=pmAnswersService.selectList(wrapperPmAnswers);
		if(listPmAnswers!=null&&listPmAnswers.size()>0){
			items=new ArrayList<>();
			for(PmAnswers pmAnswers:listPmAnswers){
				PmProjectDetail pmProjectDetail=new PmProjectDetail();
				pmProjectDetail.setName(pmAnswers.getProjectName());
				Wrapper<PmAnswersOption> wrapperPmAnswersOption = new EntityWrapper<>();
				wrapperPmAnswersOption.eq("answer_id", pmAnswers.getId());
				List<PmAnswersOption> listPmAnswersOption = pmAnswersOptionService.selectList(wrapperPmAnswersOption);
				if(listPmAnswersOption!=null&&listPmAnswersOption.size()>0){
					String[] options=new String[listPmAnswersOption.size()];
					for(int i=0;i<listPmAnswersOption.size();i++){
						options[i]=listPmAnswersOption.get(i).getOptionName();
					}
					pmProjectDetail.setOptions(options);
				}
				items.add(pmProjectDetail);
			}
		}
		return items;
	}
	@Override
	@Transactional(readOnly=true)
	public List<PmProjectDetail2> getItems2(Long id) {
		List<PmProjectDetail2> items=null;
		Wrapper<PmAnswers> wrapperPmAnswers =new EntityWrapper<>();
		wrapperPmAnswers.eq("implement_id", id);
		List<PmAnswers> listPmAnswers=pmAnswersService.selectList(wrapperPmAnswers);
		if(listPmAnswers!=null&&listPmAnswers.size()>0){
			items=new ArrayList<>();
			for(PmAnswers pmAnswers:listPmAnswers){
				PmProjectDetail2 pmProjectDetail2=new PmProjectDetail2();
				pmProjectDetail2.setName(pmAnswers.getProjectName());
				Wrapper<PmAnswersOption> wrapperPmAnswersOption = new EntityWrapper<>();
				wrapperPmAnswersOption.eq("answer_id", pmAnswers.getId());
				List<PmAnswersOption> listPmAnswersOption = pmAnswersOptionService.selectList(wrapperPmAnswersOption);
				if(listPmAnswersOption!=null&&listPmAnswersOption.size()>0){
					 List<PmOptionAnswerRequest> options=new ArrayList<>();
				
					for(int i=0;i<listPmAnswersOption.size();i++){
						PmOptionAnswerRequest pmOptionAnswerRequest=new PmOptionAnswerRequest();
						pmOptionAnswerRequest.setName(listPmAnswersOption.get(i).getOptionName());
						pmOptionAnswerRequest.setAnswer(listPmAnswersOption.get(i).getAnswer());
						pmOptionAnswerRequest.setRemarks(listPmAnswersOption.get(i).getRemarks());
						pmOptionAnswerRequest.setMeasure(listPmAnswersOption.get(i).getMeasure());
						pmOptionAnswerRequest.setSetnum(listPmAnswersOption.get(i).getSetnum());
						options.add(pmOptionAnswerRequest);
					}
					pmProjectDetail2.setOptions(options);
				}
				items.add(pmProjectDetail2);
			}
		}
		return items;
	}
	@Override
	@Transactional(readOnly=true)
	public CheckDetail scan(Long id) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		CheckDetail checkDetail=new CheckDetail();
		//int ret = 1;
		/**
		 * 实施状态(1，不在范围中 2，范围中)
		 */
		Wrapper<PmPlanImplementHelp> wrapper = new EntityWrapper<>();
		wrapper.eq("assets_id", id);
		List<PmPlanImplementHelp> list = pmPlanImplementHelpMapper.selectList(wrapper);
		if (list != null && list.size() > 0) {
			//ret = 2;
			PmPlanImplementHelp pmPlanImplementHelp=list.get(0);
			if(pmPlanImplementHelp!=null){
				if(authUser.getId().longValue()!=pmPlanImplementHelp.getChargeId().longValue()){
					checkDetail.setStatus(1);
				}else{
					checkDetail.setStatus(2);
					checkDetail.setMsg(pmPlanImplementHelp.getId()+"");
				}
				return checkDetail;
			}
			
		} else {
			checkDetail.setStatus(1);
			return checkDetail;
		}
		return checkDetail;
	}
	@Override
	public Integer getPmImplementWaitToDo(Long id) {
		int i=0;
		Wrapper<PmPlanImplementHelp> wrapper=new EntityWrapper<>();
		wrapper.eq("charge_id", id).eq("status", 1).eq("check_status",1).eq("del_flag", false);
		List<PmPlanImplementHelp> list=pmPlanImplementHelpMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
				i+=list.size();
			}
		return i;
	}
	@Override
	public void autoSend() {
		Wrapper<PmPlanImplementHelp> wrapper=new EntityWrapper<>();
		wrapper.eq("status", 1).eq("check_status",1).eq("msg_status",1).eq("del_flag", false);
		List<PmPlanImplementHelp> list=pmPlanImplementHelpMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			for(PmPlanImplementHelp pmPlanImplementHelp:list){
				int  flag=QcUtils.getFlag(new Date(), pmPlanImplementHelp.getNextTime());
				if(1==flag){
					//发送消息 5天待办
					SendMessage  message=new SendMessage();
					message.setModuleId(pmPlanImplementHelp.getId());
					message.setRemarks("巡检日期"+QcUtils.getMsgDate(pmPlanImplementHelp.getNextTime()));
					long  days=QcUtils.getNumDay(new Date(), pmPlanImplementHelp.getNextTime());
					message.setMessageContent("PM实施"+days+"天后将过期，查看详情");
					message.setStatus(6);
					message.setUserId(pmPlanImplementHelp.getChargeId());
					Result<Object> rusult=repairClientService.send(message);
					if(rusult!=null){
						if("200".equals(rusult.getCode())){
							pmPlanImplementHelp.setMsgStatus(2);
							pmPlanImplementHelpMapper.updateById(pmPlanImplementHelp);
						}
					}
					
				}else if(2==flag){//发送消息 5天待办
					SendMessage  message=new SendMessage();
					message.setModuleId(pmPlanImplementHelp.getId());
					message.setRemarks("巡检日期"+QcUtils.getMsgDate(pmPlanImplementHelp.getNextTime()));
					message.setMessageContent("PM实施已过期，查看详情");
					message.setStatus(6);
					message.setUserId(pmPlanImplementHelp.getChargeId());
					Result<Object> rusult=repairClientService.send(message);
					if(rusult!=null){
						if("200".equals(rusult.getCode())){
							pmPlanImplementHelp.setMsgStatus(2);
							pmPlanImplementHelpMapper.updateById(pmPlanImplementHelp);
						}
					}}
			
			}
		}
	}
	
}
