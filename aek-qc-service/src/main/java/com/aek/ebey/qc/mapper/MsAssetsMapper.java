package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MsAssets;
import com.aek.ebey.qc.query.MsAssetsQuery;
import com.aek.ebey.qc.request.EditMsAssets;
import com.aek.ebey.qc.request.MsAssetsResponse;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
public interface MsAssetsMapper extends BaseMapper<MsAssets> {

	List<MsAssetsResponse> search(Page<MsAssetsResponse> pageMsAssetsResponse,  @Param("q") MsAssetsQuery query, @Param("user") AuthUser authUser);

	void updateMsAssets(@Param("r") EditMsAssets request);

}