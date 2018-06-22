package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.query.QcPlanRecordQuery;
import com.aek.ebey.qc.query.QcReportQuery;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;
import com.aek.ebey.qc.request.QcPlanRecordResponse;
import com.aek.ebey.qc.request.QcReportResponse;
import com.aek.ebey.qc.request.TimeQuery;
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
 * @since 2017-11-06
 */
public interface QcImplementMapper extends BaseMapper<QcImplement> {

	void batchUpdate(@Param("list")List<QcAssets> listQcAssets);

	List<QcPlanRecordResponse> getRecord(Page<QcPlanRecordResponse> pageQcPlanRecordResponse,@Param("q") QcPlanRecordQuery query);

	int check( @Param("q") QcImAssets request);

	List<QcReportResponse> searchReport(Page<QcReportResponse> pageQcReportResponse,@Param("q") QcReportQuery query,@Param("user")AuthUser authUser);

	void batchUpdate2(@Param("list")List<QcAssets> listQcAssets);

	/**
	 * 年度巡检执行率
	 * @param query
	 * @return
	 */
	List<QcOverView> selectByCondtion(@Param("q") TimeQuery query);

	/**
	 * 月度巡检设备总数
	 * @param query
	 * @return
	 */
	List<QcMounth> selectCountQcAssets(@Param("q")TimeQuery query);

	/**
	 * 统计年度巡检设备总数
	 * @param query
	 * @return
	 */
	List<QcOverView> selectCountQcAssetsYear(@Param("q") TimeQuery query);

}