package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.PmPlanImplement;
import com.aek.ebey.qc.query.PmImplementQuery;
import com.aek.ebey.qc.request.PmImplementResponse;
import com.aek.ebey.qc.request.PmMounth;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.TimeQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmPlanImplementMapper extends BaseMapper<PmPlanImplement> {

	List<PmImplementResponse> search(Page<PmImplementResponse> pagePmImplement,@Param("q") PmImplementQuery query,
			@Param("user")AuthUser authUser);

	List<PmOverView> selectByCondtion(@Param("q") TimeQuery query);
	
	//List<PmOverView> selectByCondtion2(@Param("q") TimeQuery query);

	List<PmMounth> selectCountPMAssets(@Param("q") TimeQuery query);

}