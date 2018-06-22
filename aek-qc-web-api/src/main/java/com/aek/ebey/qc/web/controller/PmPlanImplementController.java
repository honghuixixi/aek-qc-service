package com.aek.ebey.qc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.query.PmImplementQuery;
import com.aek.ebey.qc.request.PmImplementResponse;
import com.aek.ebey.qc.service.PmPlanImplementService;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * PM计划实施help  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/pm/pmPlanImplement")
@Api(value = "PmPlanImplementController", description = "PM计划实施")
public class PmPlanImplementController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PmPlanImplementController.class);
	
	 @InitBinder
	 protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
	       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       dateFormat.setLenient(false);
	       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	   }
	
	@Autowired
	private PmPlanImplementService pmPlanImplementService;
	
	/**
	 * 报告查询
	 */
	//@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/searchReport")
	@ApiOperation(value = "报告查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "起始页 [默认1]", paramType = "query", required = false),
		@ApiImplicitParam(name = "pageSize", value = "分页大小[默认10]", paramType = "query", required = false),
		@ApiImplicitParam(name = "status", value = "验收状态（1，待验收 2，已验收）", paramType = "query", required = false),
		@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", required = false),
		@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", required = false),
		@ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", required = false),
		@ApiImplicitParam(name = "departmentId", value = "设备部门id", paramType = "query", required = false),
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false) })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<PmImplementResponse>> search(PmImplementQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		query.setOrderStatus(1);
		LiuHuiPage<PmImplementResponse> page = pmPlanImplementService.search(query);
		return response(page);
	}
	
	/**
	 * 报告查询
	 */
	//@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/search_Report")
	@ApiOperation(value = "报告查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "起始页 [默认1]", paramType = "query", required = false),
		@ApiImplicitParam(name = "pageSize", value = "分页大小[默认10]", paramType = "query", required = false),
		@ApiImplicitParam(name = "status", value = "验收状态（1，待验收 2，已验收）", paramType = "query", required = false),
		@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", required = false),
		@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", required = false),
		@ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", required = false),
		@ApiImplicitParam(name = "departmentId", value = "设备部门id", paramType = "query", required = false),
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false) })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<PmImplementResponse>> search_Report(PmImplementQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		query.setOrderStatus(2);
		LiuHuiPage<PmImplementResponse> page = pmPlanImplementService.search(query);
		return response(page);
	}

	
}
