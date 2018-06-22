package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.PmPlan;
import com.aek.ebey.qc.query.PmPlanQuery;
import com.aek.ebey.qc.request.CheckDetail;
import com.aek.ebey.qc.request.PmEnable;
import com.aek.ebey.qc.request.PmImplementReport;
import com.aek.ebey.qc.request.PmPlanDetail;
import com.aek.ebey.qc.request.PmPlanEditDetail;
import com.aek.ebey.qc.request.PmPlanEditRequest;
import com.aek.ebey.qc.request.PmPlanRequest;
import com.aek.ebey.qc.request.PmPlanResponse;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
public interface PmPlanService extends BaseService<PmPlan> {

	void save(PmPlanRequest request);

	void edit(PmPlanEditRequest request);

	PmPlanDetail getAllByPmPlanId(Long id);

	void updatePmPlan(Long id);

	LiuHuiPage<PmPlanResponse> search(PmPlanQuery query);

	PmPlanEditDetail getAllByPmPlanId2(Long id);

	CheckDetail enableById(PmEnable pmEnable);

	PmImplementReport getImplementReportById(Long id);

	void updatePmPlan2(Long id);
	
}
