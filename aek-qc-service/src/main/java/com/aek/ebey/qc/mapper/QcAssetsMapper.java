package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.query.QcAssetsByImplementIdQuery;
import com.aek.ebey.qc.query.QcAssetsQuery;
import com.aek.ebey.qc.query.QcAssetsQuery2;
import com.aek.ebey.qc.request.QcAssetsResponse;
import com.aek.ebey.qc.request.QcAssetsResponse2;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssets3;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public interface QcAssetsMapper extends BaseMapper<QcAssets> {

	void insertBatch(List<QcAssets> list);

	List<QcAssetsResponse> searchQcAssets(Page<QcAssetsResponse> pageQcAssets, @Param("q") QcAssetsQuery query);

	List<QcAssetsResponse> selectByimplementId(Long id);

	List<QcAssetsResponse> searchContentByImplementId(Page<QcAssetsResponse> pageQcAssets,
			@Param("q")QcAssetsByImplementIdQuery query);

	List<QcAssetsResponse2> searchQcAssets2(Page<QcAssetsResponse2> pageQcAssets,@Param("q") QcAssetsQuery2 query);

	int check(@Param("q") QcImAssets request);

	void submitQcImAssets(@Param("q")QcImAssets3 request);

	//QcImAssetsResponse getQcImAssets(@Param("q") QcImAssets request);

	//List<QcPlanRecordResponse> getRecord(Page<QcPlanRecordResponse> pageQcPlanRecordResponse,@Param("q") QcPlanRecordQuery query);

}