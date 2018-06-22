package com.aek.ebey.qc.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.query.PmPlanQuery;
import com.aek.ebey.qc.request.CheckDetail;
import com.aek.ebey.qc.request.PmEnable;
import com.aek.ebey.qc.request.PmPlanDetail;
import com.aek.ebey.qc.request.PmPlanEditDetail;
import com.aek.ebey.qc.request.PmPlanEditRequest;
import com.aek.ebey.qc.request.PmPlanRequest;
import com.aek.ebey.qc.request.PmPlanResponse;
import com.aek.ebey.qc.service.PmPlanService;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * PM巡检计划  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@RestController
@RequestMapping("/pm/pmPlan")
@Api(value = "PmPlanController", description = "PM计划")
public class PmPlanController extends BaseController {
	
private static final Logger logger = LoggerFactory.getLogger(PmPlanController.class);
	/*
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private  DeptClientService deptClientService;*/
	
	@Autowired
	private PmPlanService pmPlanService;
	
	/**
	 * 新建PM计划
	 */
	@PreAuthorize("hasAnyAuthority('PM_PLAN_NEW_EDIT,PM_PLAN_VIEW')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建PM计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@RequestBody PmPlanRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.pmPlanService.save(request);
		return response();
	}
	
	/**
	 * 编辑PM计划
	 */
	@PreAuthorize("hasAnyAuthority('PM_PLAN_NEW_EDIT,PM_PLAN_VIEW')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑PM计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@RequestBody PmPlanEditRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.pmPlanService.edit(request);
		return response();
	}
	
	/**
	 * 查询PM计划详情
	 */
	@PreAuthorize("hasAnyAuthority('PM_PLAN_VIEW')")
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "查询PM计划详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmPlanDetail> get(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmPlanDetail pmPlanDetail=pmPlanService.getAllByPmPlanId(id);
		return response(pmPlanDetail);
		
	}
	
	/**
	 * 停用PM计划
	 */
	@PreAuthorize("hasAnyAuthority('PM_PLAN_DISABLE_ENABLE,PM_PLAN_VIEW')")
	@GetMapping(value = "/disable/{id}")
	@ApiOperation(value = "停用PM计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> disable(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		pmPlanService.updatePmPlan(id);
		return response();
		
	}
	
	/**
	 * 停用PM计划(实施id)
	 */
	//@PreAuthorize("hasAnyAuthority('QC_PLAN_DISABLE,QC_PLAN_VIEW')")
	@GetMapping(value = "/disableImplementId/{id}")
	@ApiOperation(value = "停用PM计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> disableImplementId(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		pmPlanService.updatePmPlan2(id);
		return response();
		
	}
	
	/**
	 * 启用PM计划
	 */
	@PreAuthorize("hasAnyAuthority('PM_PLAN_DISABLE_ENABLE,PM_PLAN_VIEW')")
	@PostMapping(value = "/enable")
	@ApiOperation(value = "启用PM计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<CheckDetail> enable(@RequestBody PmEnable pmEnable) {
		logger.debug("<---------------------------------"+JSON.toJSONString(pmEnable));
		CheckDetail checkDetail=pmPlanService.enableById(pmEnable);
		return response(checkDetail);
	}
	
	/**
	 * 查询PM计划列表
	 */
	@PreAuthorize("hasAnyAuthority('PM_PLAN_VIEW')")
	@GetMapping(value = "/search")
	@ApiOperation(value = "查询PM计划列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<PmPlanResponse>> search(PmPlanQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<PmPlanResponse> page = pmPlanService.search(query);
		return response(page);
	}
	
	/**
	 * 查询PM计划详情(编辑)
	 */
	//@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/edit/{id}")
	@ApiOperation(value = "查询PM计划详情(编辑)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmPlanEditDetail> get2(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmPlanEditDetail pmPlanEditDetail=pmPlanService.getAllByPmPlanId2(id);
		return response(pmPlanEditDetail);
		
	}
	
}
