package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.LiuHuiPage2;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.query.QcAssetsByImplementIdQuery;
import com.aek.ebey.qc.query.QcAssetsQuery;
import com.aek.ebey.qc.query.QcAssetsQuery2;
import com.aek.ebey.qc.query.QcImplementQuery;
import com.aek.ebey.qc.query.QcPlanQuery;
import com.aek.ebey.qc.query.QcPlanRecordQuery;
import com.aek.ebey.qc.request.Dept;
import com.aek.ebey.qc.request.QcAssetsResponse;
import com.aek.ebey.qc.request.QcAssetsResponse2;
import com.aek.ebey.qc.request.QcDetail;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssetsResponse;
import com.aek.ebey.qc.request.QcImplementContentResponse;
import com.aek.ebey.qc.request.QcImplementDetail;
import com.aek.ebey.qc.request.QcImplementReport;
import com.aek.ebey.qc.request.QcImplementResponse;
import com.aek.ebey.qc.request.QcImplementSheet;
import com.aek.ebey.qc.request.QcPlanDetail;
import com.aek.ebey.qc.request.QcPlanRecordResponse;
import com.aek.ebey.qc.request.QcPlanRequest;
import com.aek.ebey.qc.request.QcPlanResponse;
import com.aek.ebey.qc.request.QcProjectRequest4;

import java.util.List;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public interface QcPlanService extends BaseService<QcPlan> {

	void save(QcPlanRequest request);

	LiuHuiPage<QcPlanResponse> search(QcPlanQuery query);

	QcPlanDetail getAllByQcPlan(QcPlan qcPlan);

	LiuHuiPage<QcImplementResponse> searchImplement(QcImplementQuery query);

	QcImplementDetail getQcImplementDetailByQcPlan(QcPlan qcPlan);

	LiuHuiPage<QcAssetsResponse> searchQcAssets(QcAssetsQuery query);

	QcImplementContentResponse getImplementContentById(Long id);

	QcImplementSheet getImplementSheetById(Long id);

	//com.aek.ebey.qc.request.QcPlanRecordResponse getQcPlanRecordResponseById(Long id);

	LiuHuiPage<QcPlanRecordResponse> getRecord(QcPlanRecordQuery query);

	QcImplementReport getImplementReportById(Long id);

	List<Dept> getDepts(String keyword);

	LiuHuiPage<QcAssetsResponse> searchContentByImplementId(QcAssetsByImplementIdQuery query);

	QcImplementDetail getQcImplementDetailByImplementId(Long id);

	void updateByQcPlan(QcPlan qcPlan);

	void updateQcPlan(Long id);

	LiuHuiPage2<QcAssetsResponse2> searchQcAssets2(QcAssetsQuery2 query);

	QcImAssetsResponse getQcImAssets(QcImAssets request);

	List<QcProjectRequest4> getTempleteById(Long id);

	QcDetail getQcDetailByQcPlan(QcPlan qcPlan);

	Integer getQcImplementWaitToDo(Long id);

	void autoSend();

	
}
