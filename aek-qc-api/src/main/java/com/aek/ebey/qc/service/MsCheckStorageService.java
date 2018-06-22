package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.MsCheckStorage;
import com.aek.ebey.qc.request.MsCheckStorageRequest;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
public interface MsCheckStorageService extends BaseService<MsCheckStorage> {

	void saving(MsCheckStorageRequest request);
	
}
