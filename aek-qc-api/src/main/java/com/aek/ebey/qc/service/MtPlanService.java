package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.MtAssets;
import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.vo.MtPlanDetailVO;
import com.aek.ebey.qc.model.vo.MtPlanResponseVO;
import com.aek.ebey.qc.query.MtPlanQuery;
import com.aek.ebey.qc.request.MtPlanEditRequest;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * 保养计划服务类
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtPlanService extends BaseService<MtPlan> {
	
	/**
	 * 新建保养计划
	 * @param plan
	 * @param mtAssetList
	 */
	public void addPlan(MtPlan plan,List<MtAssets> mtAssetList);
	
	public void editPlan(MtPlanEditRequest request);
	
	public void disablePlan(Long id);
	
	public Boolean checkTemplate(Long id);
	
	public MtPlanDetailVO getPlanDetail(Long id);
	
	public Page<MtPlanResponseVO> getPlanByPage(MtPlanQuery query);
}
