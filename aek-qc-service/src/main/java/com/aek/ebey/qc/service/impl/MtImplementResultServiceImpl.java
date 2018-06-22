package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.mapper.MtImplementResultItemMapper;
import com.aek.ebey.qc.mapper.MtImplementResultMapper;
import com.aek.ebey.qc.mapper.MtPlanAssetsMapper;
import com.aek.ebey.qc.model.MtImplementResult;
import com.aek.ebey.qc.model.MtImplementResultFile;
import com.aek.ebey.qc.model.MtImplementResultItem;
import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.MtPlanAssets;
import com.aek.ebey.qc.model.MtPlanImplement;
import com.aek.ebey.qc.model.vo.MtImplementResultItemsVO;
import com.aek.ebey.qc.model.vo.MtImplementResultVO;
import com.aek.ebey.qc.model.vo.MtRecordBatchReponseVO;
import com.aek.ebey.qc.query.MtImplementResultQuery;
import com.aek.ebey.qc.query.MtRecordBatchQuery;
import com.aek.ebey.qc.query.MtRecordQuery;
import com.aek.ebey.qc.request.MtImplementResultFileRequest;
import com.aek.ebey.qc.request.MtImplementResultItemRequest;
import com.aek.ebey.qc.request.MtImplementResultPageResponse;
import com.aek.ebey.qc.request.MtImplementResultRequest;
import com.aek.ebey.qc.service.MtImplementResultFileService;
import com.aek.ebey.qc.service.MtImplementResultItemService;
import com.aek.ebey.qc.service.MtImplementResultService;
import com.aek.ebey.qc.service.MtPlanImplementService;
import com.aek.ebey.qc.service.MtPlanService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 保养实施结果表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtImplementResultServiceImpl extends BaseServiceImpl<MtImplementResultMapper, MtImplementResult> implements MtImplementResultService {
	
	@Autowired
	private MtImplementResultMapper mtImplementResultMapper;
	@Autowired
	private MtImplementResultFileService mtImplementResultFileService;
	@Autowired
	private MtImplementResultItemService mtImplementResultItemService;
	@Autowired
	private MtPlanImplementService mtPlanImplementService;
	@Autowired
	private MtPlanService mtPlanService;
	@Autowired
	private MtPlanAssetsMapper mtPlanAssetsMapper;
	@Autowired
	private MtImplementResultItemMapper mtImplementResultItemMapper;

	
	/**
	 * 提交实施报告内容
	 * @param request
	 */
	@Override
	public MtImplementResult submitImplementResult(MtImplementResultRequest request){
		AuthUser user = WebSecurityUtils.getCurrentUser();
		MtPlan mtPlan = mtPlanService.selectById(request.getPlanId());
		MtImplementResult mtImplementResult = BeanMapper.map(request, MtImplementResult.class);
		mtImplementResult.setReportNo(QcUtils.mtReportNo());
		//实施人
		mtImplementResult.setImplementUserId(user.getId());
		mtImplementResult.setImplementUserName(user.getRealName());
		//实施状态“已实施”
		mtImplementResult.setStatus(2);
		//实施实际结束时间
		mtImplementResult.setActualEndDate(new Date());
		//专管人
		mtImplementResult.setAdministrator(mtPlan.getAdministrator());
		//保存MtImplementResut
		mtImplementResultMapper.insert(mtImplementResult);
		//保存MtImplementResultFile
		List<MtImplementResultFile> files = new ArrayList<MtImplementResultFile>();
		for (MtImplementResultFileRequest mtImplementResultFileRequest : request.getFiles()) {
			MtImplementResultFile mtImplementResultFile = BeanMapper.map(mtImplementResultFileRequest, MtImplementResultFile.class);
			mtImplementResultFile.setResultId(mtImplementResult.getId());
			files.add(mtImplementResultFile);
		}
		if (files.size() > 0 ) {
			mtImplementResultFileService.insertBatch(files);
		}
		//保存MtImplementResultItem
		List<MtImplementResultItem> items = new ArrayList<MtImplementResultItem>();
		for (MtImplementResultItemRequest mtImplementResultItemRequest : request.getTemplateItemResults()) {
			MtImplementResultItem mtImplementResultItem = BeanMapper.map(mtImplementResultItemRequest, MtImplementResultItem.class);
			mtImplementResultItem.setResultId(mtImplementResult.getId());
			items.add(mtImplementResultItem);
		}
		mtImplementResultItemService.insertBatch(items);
		MtPlanImplement mtPlanImplement = mtPlanImplementService.selectById(request.getPlanImplementId());
		//新增MtPlanImplement
		MtPlanImplement nextMtPlanImplement = new MtPlanImplement();
		nextMtPlanImplement.setId(null);
		nextMtPlanImplement.setLastImplementTime(request.getEndDate());
		nextMtPlanImplement.setNextImplementTime(request.getNextImplementTime());
		nextMtPlanImplement.setCreateTime(new Date());
		nextMtPlanImplement.setAdministrator(mtPlan.getAdministrator());
		nextMtPlanImplement.setRate(mtPlan.getRate());
		nextMtPlanImplement.setPlanId(mtPlan.getId());
		mtPlanImplementService.insert(nextMtPlanImplement);
		//删除原来MtPlanImplement
		mtPlanImplementService.deleteById(mtPlanImplement.getId());
		//更新计划中上、下次实施日期
		
		mtPlan.setLastImplementTime(mtPlanImplement.getNextImplementTime());
		mtPlan.setNextImplementTime(request.getNextImplementTime());
		mtPlanService.insertOrUpdate(mtPlan);
		return mtImplementResult;
	}

	/**
	 * 分页查询实施报告
	 */
	@Override
	public LiuHuiPage<MtImplementResultPageResponse> getMtImplementResultPage(Page<MtImplementResultPageResponse> page,
			AuthUser user, MtImplementResultQuery query) {
		List<MtImplementResultPageResponse> list = mtImplementResultMapper.getMtImplementResultPage(page,user,query);
		LiuHuiPage<MtImplementResultPageResponse> responsePage = new LiuHuiPage<>(page);
		responsePage.setRecords(list);
		return responsePage;
	}

	@Override
	public Page<MtImplementResult> getMtRecordPage(MtRecordQuery query) {
		Page<MtImplementResult> page = query.getPage();
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		List<MtImplementResult> list = mtImplementResultMapper.getMtRecordPage(page, currentUser, query);
		page.setRecords(list);
		return page;
	}

	@Override
	public MtRecordBatchReponseVO getMtRecordBatchPrint(MtRecordBatchQuery query) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		Long planId = query.getPlanId();
		MtPlan plan = mtPlanService.selectById(planId);
		if(plan==null)return null;
		Wrapper<MtPlanAssets> wrapper1 = new EntityWrapper<MtPlanAssets>();
		wrapper1.eq("plan_id", planId);
		List<MtPlanAssets> mtPlanAssetList = mtPlanAssetsMapper.selectList(wrapper1);
		if(mtPlanAssetList!=null&&mtPlanAssetList.size()>0){
			MtPlanAssets mtPlanAsset = mtPlanAssetList.get(0);	
			MtRecordBatchReponseVO mtRecordBatchReponseVo = BeanMapper.map(mtPlanAsset, MtRecordBatchReponseVO.class);
			mtRecordBatchReponseVo.setDeptName(mtPlanAsset.getAssetsDeptName());
			mtRecordBatchReponseVo.setTenantName(plan.getTenantName());
			if(plan.getRate()==1)mtRecordBatchReponseVo.setRate("每天");
			if(plan.getRate()==2)mtRecordBatchReponseVo.setRate("每周");
			
			List<MtImplementResult> mplementResultList = mtImplementResultMapper.getMplementResultList(user, query);
			List<MtImplementResultVO> MtImplementResultVoList = BeanMapper.mapList(mplementResultList, MtImplementResultVO.class);
			if(MtImplementResultVoList!=null&&MtImplementResultVoList.size()>0){
				for (MtImplementResultVO mtImplementResultVo : MtImplementResultVoList) {
					Wrapper<MtImplementResultItem> wrapper2 = new EntityWrapper<MtImplementResultItem>();
					wrapper2.eq("result_id", mtImplementResultVo.getId());
					List<MtImplementResultItem> mtImplementResultItemList = mtImplementResultItemMapper.selectList(wrapper2);
					List<MtImplementResultItemsVO> mtImplementResultItemsVoList = BeanMapper.mapList(mtImplementResultItemList, MtImplementResultItemsVO.class);
					mtImplementResultVo.setMtImplementResultItemsVo(mtImplementResultItemsVoList);
				}
			}	
			mtRecordBatchReponseVo.setMtImplementResultVos(MtImplementResultVoList);	
			return mtRecordBatchReponseVo;
		}
		return null;	
	}
	
	
}
