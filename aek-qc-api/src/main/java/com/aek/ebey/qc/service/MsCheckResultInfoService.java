package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MsCheckResultInfo;
import com.aek.ebey.qc.query.MsAssetsRecordQuery;
import com.aek.ebey.qc.request.MsAssetsRecordResponse;
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
public interface MsCheckResultInfoService extends BaseService<MsCheckResultInfo> {

	MsCheckResultInfo saved(MsCheckStorageRequest request);

	LiuHuiPage<MsAssetsRecordResponse> search(MsAssetsRecordQuery query);
	
}
