package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MsCheckResultInfo;
import com.aek.ebey.qc.query.MsAssetsRecordQuery;
import com.aek.ebey.qc.request.MsAssetsRecordResponse;
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
public interface MsCheckResultInfoMapper extends BaseMapper<MsCheckResultInfo> {

	List<MsAssetsRecordResponse> search(Page<MsAssetsRecordResponse> pageMsAssetsRecordResponse,
			 @Param("q") MsAssetsRecordQuery query,  @Param("user") AuthUser authUser);

}