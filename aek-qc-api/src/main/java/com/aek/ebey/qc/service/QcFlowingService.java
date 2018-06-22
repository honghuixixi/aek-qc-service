package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.QcFlowing;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public interface QcFlowingService extends BaseService<QcFlowing> {

	Long savePlanNo(String stringDate);
	
}
