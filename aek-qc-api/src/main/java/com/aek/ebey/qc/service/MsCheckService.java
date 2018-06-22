package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MsCheck;
import com.aek.ebey.qc.query.MsAssetsQuery;
import com.aek.ebey.qc.request.MsAssetsResponse;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
public interface MsCheckService extends BaseService<MsCheck> {

	LiuHuiPage<MsAssetsResponse> search(MsAssetsQuery query);
	
}
