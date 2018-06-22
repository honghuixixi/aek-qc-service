package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MsAssets;
import com.aek.ebey.qc.query.MsAssetsQuery;
import com.aek.ebey.qc.request.EditMsAssets;
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
public interface MsAssetsService extends BaseService<MsAssets> {

	LiuHuiPage<MsAssetsResponse> search(MsAssetsQuery query);

	void updateMsAssets(EditMsAssets request);

	void insertMsMsAssetsAndCheck(MsAssets request);

	void saveOrUpdate(MsAssets request);

	//LiuHuiPage<MsAssetsResponse> searchCheck(MsAssetsQuery query);
	
}
