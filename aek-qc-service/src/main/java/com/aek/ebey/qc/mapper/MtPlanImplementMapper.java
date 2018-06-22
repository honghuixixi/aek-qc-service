package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.MtPlanImplement;
import com.aek.ebey.qc.query.MtPlanImplementQuery;
import com.aek.ebey.qc.request.MtPlanImplementResponse;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * Mapper接口
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtPlanImplementMapper extends BaseMapper<MtPlanImplement> {

	/**
	 * 分页查询保养实施列表
	 * @param page
	 * @param query
	 * @return
	 */
	List<MtPlanImplementResponse> getMtPlanImplementPage(Page<MtPlanImplementResponse> page, @Param("user") AuthUser user , @Param("q") MtPlanImplementQuery query);
	
}