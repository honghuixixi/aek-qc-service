package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.query.QcImplementQuery;
import com.aek.ebey.qc.query.QcPlanQuery;
import com.aek.ebey.qc.query.Query;
import com.aek.ebey.qc.request.QcImplementResponse;
import com.aek.ebey.qc.request.QcOverView;
import com.aek.ebey.qc.request.QcPlanResponse;
import com.aek.ebey.qc.request.QcPlanTotal;
import com.aek.ebey.qc.request.TimeQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public interface QcPlanMapper extends BaseMapper<QcPlan> {

	List<QcPlanResponse> search(Page<QcPlanResponse> pageQcPlan, @Param("q") QcPlanQuery query, @Param("user") AuthUser authUser);

	List<QcImplementResponse> searchImplement(Page<QcImplementResponse> pageQcPlan,@Param("q") QcImplementQuery query,@Param("user") AuthUser authUser);

	void updateByQcPlan(@Param("q") QcPlan qcPlan);

	List<QcPlan> selectList2(@Param("q") Query query);

	List<QcOverView> selcetByCondtion(@Param("q") TimeQuery query);
	

}