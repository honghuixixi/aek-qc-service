package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.PmPlanImplement;
import com.aek.ebey.qc.query.PmImplementQuery;
import com.aek.ebey.qc.request.PmImplementResponse;
import com.aek.ebey.qc.request.PmMounth;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.TimeQuery;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmPlanImplementService extends BaseService<PmPlanImplement> {

	LiuHuiPage<PmImplementResponse> search(PmImplementQuery query);

	List<PmOverView> countPM(TimeQuery query);

	List<PmMounth> countPMAssets(TimeQuery query);
	
}
