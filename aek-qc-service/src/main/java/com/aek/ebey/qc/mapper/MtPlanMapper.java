package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.vo.MtPlanResponseVO;
import com.aek.ebey.qc.query.MtPlanQuery;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;

/**
 * Mapper接口
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtPlanMapper extends BaseMapper<MtPlan> {

	/**
	 * 保养计划分页查询
	 * @param page
	 * @param query
	 * @param user
	 * @return
	 */
	List<MtPlanResponseVO> getPlanByPage(@Param("page")Page<MtPlanResponseVO> page,@Param("query")MtPlanQuery query,@Param("user")AuthUser user);
}