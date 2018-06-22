package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.query.QcAssetsByImplementIdQuery;
import com.aek.ebey.qc.query.QcAssetsQuery;
import com.aek.ebey.qc.query.QcAssetsQuery2;
import com.aek.ebey.qc.request.QcAssetsResponse;
import com.aek.ebey.qc.request.QcAssetsResponse2;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssets3;
import com.aek.ebey.qc.request.QcImAssetsResponse;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public interface QcAssetsService extends BaseService<QcAssets> {

	List<QcAssetsResponse> searchQcAssets(Page<QcAssetsResponse> pageQcAssets, QcAssetsQuery query);

	List<QcAssetsResponse> selectByimplementId(Long id);

	List<QcAssetsResponse> searchContentByImplementId(Page<QcAssetsResponse> pageQcAssets,
			QcAssetsByImplementIdQuery query);

	List<QcAssetsResponse2> searchQcAssets2(Page<QcAssetsResponse2> pageQcAssets, QcAssetsQuery2 query);

	int check(QcImAssets request);

	void submitQcImAssets(QcImAssets3 request);

	
}
