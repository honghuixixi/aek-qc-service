package com.aek.ebey.qc.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.LiuHuiPage2;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.query.QcAssetsByImplementIdQuery;
import com.aek.ebey.qc.query.QcAssetsQuery;
import com.aek.ebey.qc.query.QcAssetsQuery2;
import com.aek.ebey.qc.query.QcImplementQuery;
import com.aek.ebey.qc.query.QcReportQuery;
import com.aek.ebey.qc.request.QcAssetsRequest;
import com.aek.ebey.qc.request.QcAssetsResponse;
import com.aek.ebey.qc.request.QcAssetsResponse2;
import com.aek.ebey.qc.request.QcBeginImplementRequest;
import com.aek.ebey.qc.request.QcDetail;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssets3;
import com.aek.ebey.qc.request.QcImAssetsResponse;
import com.aek.ebey.qc.request.QcImplementContentResponse;
import com.aek.ebey.qc.request.QcImplementDetail;
import com.aek.ebey.qc.request.QcImplementReport;
import com.aek.ebey.qc.request.QcImplementRequest;
import com.aek.ebey.qc.request.QcImplementResponse;
import com.aek.ebey.qc.request.QcImplementSheet;
import com.aek.ebey.qc.request.QcImplementSubmitRequest;
import com.aek.ebey.qc.request.QcImplementSubmitRequestx;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;
import com.aek.ebey.qc.request.QcProjectRequest4;
import com.aek.ebey.qc.request.QcReportResponse;
import com.aek.ebey.qc.service.QcAssetsService;
import com.aek.ebey.qc.service.QcImplementService;
import com.aek.ebey.qc.service.QcPlanService;
import com.aek.ebey.qc.service.feign.DataClientService;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@RestController
@RequestMapping("/qc/qcImplement")
@Api(value = "QcImplementController", description = "巡检实施")
public class QcImplementController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(QcImplementController.class);
	
	@Autowired
	private QcPlanService qcPlanService;
	
	@Autowired
	private QcImplementService qcImplementService;
	
	@Autowired
	private QcAssetsService qcAssetsService;
	
	/**
	 * 查询巡检实施列表
	 */
	@GetMapping(value = "/search")
	@ApiOperation(value = "查询巡检实施列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcImplementResponse>> search(QcImplementQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcImplementResponse> page = qcPlanService.searchImplement(query);
		return response(page);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "查询巡检实施详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcImplementDetail> get(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcPlan  qcPlan =qcPlanService.selectById(id);
		if(qcPlan!=null){
			return response(qcPlanService.getQcImplementDetailByQcPlan(qcPlan));
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 * 查询巡检实施详情(小程序)
	 */
	@GetMapping(value = "/getByid/{id}")
	@ApiOperation(value = "查询巡检实施详情(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcDetail> getByid(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcPlan  qcPlan =qcPlanService.selectById(id);
		if(qcPlan!=null){
			return response(qcPlanService.getQcDetailByQcPlan(qcPlan));
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	@GetMapping(value = "/getByImplement/{id}")
	@ApiOperation(value = "查询巡检实施详情ByImplementId")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcImplementDetail> getByImplementId(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		
		return response(qcPlanService.getQcImplementDetailByImplementId(id));
		
	}
	
	@PreAuthorize("hasAuthority('QC_PLAN_IMPLEMENT')")
	@PostMapping(value = "/begin")
	@ApiOperation(value = "开始巡检")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> begin(@RequestBody QcBeginImplementRequest request) {
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		qcImplementService.save(request);
		return response();
	}
	
	/**
	 * 查询巡检实施分页
	 */
	@GetMapping(value = "/search_content")
	@ApiOperation(value = "查询巡检实施分页")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcAssetsResponse>> searchContent(QcAssetsQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcAssetsResponse> page = qcPlanService.searchQcAssets(query);
		return response(page);
	}
	
	/**
	 * 查询巡检实施内容(小程序)
	 */
	@GetMapping(value = "/search_content2")
	@ApiOperation(value = "查询巡检实施内容(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage2<QcAssetsResponse2>> searchContent2(QcAssetsQuery2 query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage2<QcAssetsResponse2> page = qcPlanService.searchQcAssets2(query);
		return response(page);
	}
	
	/**
	 * 
	 *查询巡检实施设备状态(小程序)
	 */
	@PostMapping(value = "/check")
	@ApiOperation(value = "查询巡检实施设备状态(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Integer> check(@RequestBody QcImAssets request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		int i=this.qcImplementService.check(request);
		return response(i);
	}
	
	
	/**
	 * 
	 *查询巡检实施是否已提交(小程序)
	 */
	@GetMapping(value = "/isCommit/{id}")
	@ApiOperation(value = "查询巡检实施是否已提交(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Boolean> isCommit(@PathVariable Long id){
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		Boolean b=this.qcImplementService.isCommit(id);
		return response(b);
	}
	/**
	 * 
	 *查询巡检实施设备详情(小程序)
	 */
	@PostMapping(value = "/getQcImAssets")
	@ApiOperation(value = "查询巡检实施设备详情(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcImAssetsResponse> getQcImAssets(@RequestBody QcImAssets request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		QcImAssetsResponse qc=this.qcPlanService.getQcImAssets(request);
		return response(qc);
	}
	
	/**
	 * 
	 *提交巡检实施设备(小程序)
	 */
	@PostMapping(value = "/submitQcImAssets")
	@ApiOperation(value = "提交巡检实施设备(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> submitQcImAssets(@RequestBody QcImAssets3 request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.qcAssetsService.submitQcImAssets(request);
		return response();
	}
	
	
	/**
	 * 查询巡检实施分页ImplementId
	 */
	@GetMapping(value = "/search_content_ImplementId")
	@ApiOperation(value = "查询巡检实施分页ImplementId")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcAssetsResponse>> searchContentByImplementId(QcAssetsByImplementIdQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcAssetsResponse> page = qcPlanService.searchContentByImplementId(query);
		return response(page);
	}
	
	/**
	 * 查询巡检实施内容
	 */
	@GetMapping(value = "/getImplementContent/{id}")
	@ApiOperation(value = "查询巡检实施内容")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcImplementContentResponse> getImplementContent(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcImplementContentResponse implementContentResponse=qcPlanService.getImplementContentById(id);
		return response(implementContentResponse);
		
	}
	
	/**
	 * 查询巡检实施内容(计划ID)
	 */
	@GetMapping(value = "/getTemplete/{id}")
	@ApiOperation(value = "查询巡检实施内容(计划ID)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<List<QcProjectRequest4>> getTemplete(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		List<QcProjectRequest4> list=qcPlanService.getTempleteById(id);
		return response(list);
		
	}
	
	
	
	/**
	 * 
	 *暂存巡检内容
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "暂存巡检内容")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@RequestBody QcImplementRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.qcImplementService.add(request);
		return response();
	}
	
	/**
	 * 
	 *批量巡检
	 */
	@PostMapping(value = "/batchSave")
	@ApiOperation(value = "批量巡检(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> batchSave(@RequestBody QcAssetsRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.qcImplementService.batchSave(request);
		return response();
	}
	
	/**
	 * 
	 *提交巡检
	 */
	@PreAuthorize("hasAuthority('QC_PLAN_IMPLEMENT')")
	@PostMapping(value = "/submitImplement")
	@ApiOperation(value = "提交巡检")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> submitImplement(@RequestBody QcImplementSubmitRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		Long implementId=this.qcImplementService.submitImplement(request);
		return response(implementId);
	}
	
	/**
	 * 
	 *提交巡检
	 */
	@PreAuthorize("hasAuthority('QC_PLAN_IMPLEMENT')")
	@PostMapping(value = "/submitImplementx")
	@ApiOperation(value = "提交巡检(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> submitImplementx(@RequestBody QcImplementSubmitRequestx request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		Long implementId=this.qcImplementService.submitImplementx(request);
		return response(implementId);
	}
	
	/**
	 * 打印巡检实施单
	 */
	@GetMapping(value = "/printImplementSheet/{id}")
	@ApiOperation(value = "打印巡检实施单")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcImplementSheet> printImplementSheet(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcImplementSheet qcImplementSheet=qcPlanService.getImplementSheetById(id);
		return response(qcImplementSheet);
		
	}
	
	/**
	 * 查询巡检报告
	 */
	@GetMapping(value = "/getImplementReport/{id}")
	@ApiOperation(value = "查询巡检报告")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcImplementReport> getImplementReport(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcImplementReport qcImplementReport=qcPlanService.getImplementReportById(id);
		return response(qcImplementReport);
		
	}
	
	/**
	 * 巡检报告查询
	 */
	@PreAuthorize("hasAnyAuthority('QC_REPORT_VIEW')")
	@GetMapping(value = "/search_report")
	@ApiOperation(value = "巡检报告查询")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcReportResponse>> searchReport(QcReportQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcReportResponse> page = qcImplementService.searchReport(query);
		return response(page);
	}
	
	/**
	 * 
	 *根据qcimplementId得到计划id
	 */
	@GetMapping(value = "/getPlanId/{id}")
	@ApiOperation(value = "根据qcimplementId得到计划id")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Long> getPlanId(@PathVariable Long id){
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcImplement  qcImplement =qcImplementService.selectById(id);
		if(qcImplement!=null){
			return response(qcImplement.getPalnId());
		}
		return response(null);
	}
	
	
}
