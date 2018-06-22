package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.PmPlanImplementHelp;
import com.aek.ebey.qc.query.PmPlanImplementQuery;
import com.aek.ebey.qc.request.PmPlanImplementResponse;
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
 * @author aek
 * @since 2017-11-30
 */
public interface PmPlanImplementHelpMapper extends BaseMapper<PmPlanImplementHelp> {

	List<PmPlanImplementResponse> search(Page<PmPlanImplementResponse> pmPlanImplement, @Param("q") PmPlanImplementQuery query,
			@Param("user") AuthUser authUser);

}