package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.QcPlanCheck;
import com.aek.ebey.qc.query.QcPlanCheckQuery;
import com.aek.ebey.qc.request.QcPlanCheckResponse;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface QcPlanCheckMapper extends BaseMapper<QcPlanCheck> {

	List<QcPlanCheckResponse> search(Page<QcPlanCheckResponse> pageResponse, @Param("q") QcPlanCheckQuery query, @Param("user")AuthUser authUser);

}