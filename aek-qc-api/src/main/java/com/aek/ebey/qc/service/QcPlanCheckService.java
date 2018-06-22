package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.QcPlanCheck;
import com.aek.ebey.qc.query.QcPlanCheckQuery;
import com.aek.ebey.qc.request.QcPlanCheckResponse;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface QcPlanCheckService extends BaseService<QcPlanCheck> {

	LiuHuiPage<QcPlanCheckResponse> search(QcPlanCheckQuery query);

	void check(Long id);

	Integer getQcPlanCheckWaitToDo(Long id);
	
}
