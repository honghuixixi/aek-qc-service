package com.aek.ebey.qc.service;

import java.util.List;

import com.aek.common.core.base.BaseService;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.query.QcPlanRecordQuery;
import com.aek.ebey.qc.query.QcReportQuery;
import com.aek.ebey.qc.request.QcAssetsRequest;
import com.aek.ebey.qc.request.QcBeginImplementRequest;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssetsResponse;
import com.aek.ebey.qc.request.QcImplementRequest;
import com.aek.ebey.qc.request.QcImplementSubmitRequest;
import com.aek.ebey.qc.request.QcImplementSubmitRequestx;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;
import com.aek.ebey.qc.request.QcPlanRecordResponse;
import com.aek.ebey.qc.request.QcReportResponse;
import com.aek.ebey.qc.request.TimeQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public interface QcImplementService extends BaseService<QcImplement> {

	void save(QcBeginImplementRequest request);

	void add(QcImplementRequest request);

	Long submitImplement(QcImplementSubmitRequest request);

	void batchUpdate(List<QcAssets> listQcAssets);

	List<QcPlanRecordResponse> getRecord(Page<QcPlanRecordResponse> pageQcPlanRecordResponse, QcPlanRecordQuery query);

	int check(QcImAssets request);

	Long submitImplementx(QcImplementSubmitRequestx request);

	LiuHuiPage<QcReportResponse> searchReport(QcReportQuery query);

	void batchSave(QcAssetsRequest request);

	void batchUpdate2(List<QcAssets> listQcAssets);

	Boolean isCommit(Long id);

	List<QcOverView> countQc(TimeQuery query);

	List<QcMounth> countQcAssets(TimeQuery query);

	//List<QcOverView> countHistoryQc(TimeQuery query);

	//QcImAssetsResponse getQcImAssets(QcImAssets request);


	//QcImplementReport getImplementReportById(Long id);
	
}
