package com.aek.ebey.qc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.query.PmImplementQuery;
import com.aek.ebey.qc.query.PmImplementRecordQuery;
import com.aek.ebey.qc.request.PmImplementRecord;
import com.aek.ebey.qc.request.PmImplementReport;
import com.aek.ebey.qc.request.PmImplementResponse;
import com.aek.ebey.qc.request.QcImplementReport;
import com.aek.ebey.qc.service.PmImplementService;
import com.aek.ebey.qc.service.PmPlanImplementService;
import com.aek.ebey.qc.service.PmPlanService;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * PM实施  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/pm/pmImplement")
@Api(value = "PmImplementController", description = "PM实施")
public class PmImplementController extends BaseController {
	
private static final Logger logger = LoggerFactory.getLogger(PmImplementController.class);
	
	
	@Autowired
	private PmImplementService pmImplementService;
	
	@Autowired
	private PmPlanService pmPlanService;
	
	/**
	 * PM详情记录查询
	 */
	//@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/getRecord")
	@ApiOperation(value = "PM详情记录查询")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<PmImplementRecord>> getRecord(PmImplementRecordQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<PmImplementRecord> page = pmImplementService.getRecord(query);
		return response(page);
	}
	
	/**
	 * 查看PM实施报告单
	 */
	//@PreAuthorize("hasAuthority('PM_REPORT_VIEW')")
	@GetMapping(value = "/getImplementReport/{id}")
	@ApiOperation(value = "查看PM实施报告单")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmImplementReport> getImplementReport(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmImplementReport pmImplementReport=pmPlanService.getImplementReportById(id);
		return response(pmImplementReport);
		
	}
	
	/**
	 *验收
	 */
	@GetMapping(value = "/check/{id}")
	@ApiOperation(value = "验收")
	@ApiImplicitParam(name = "id", value = "实施ID", required = true, paramType = "query")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> check(@PathVariable Long id) {
		logger.debug("<---------------------------------" + JSON.toJSONString(id));
		pmImplementService.check(id);
		return response();
	}
	
	
	/**
	 * 查询验收待办
	 */
	@GetMapping(value = "/getPmCheckWaitToDo")
	@ApiOperation(value = "查询验收待办")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Integer> getPmCheckWaitToDo(Long id) {
		logger.debug("<---------------------getPmCheckWaitToDo------------");
		Integer  i =pmImplementService.getPmCheckWaitToDo(id);
		return response(i);
		
	}
}
