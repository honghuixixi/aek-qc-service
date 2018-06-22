package com.aek.ebey.qc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.model.MtAssets;
import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.vo.MtPlanDetailVO;
import com.aek.ebey.qc.model.vo.MtPlanResponseVO;
import com.aek.ebey.qc.query.MtPlanQuery;
import com.aek.ebey.qc.request.MtPlanEditRequest;
import com.aek.ebey.qc.request.MtPlanRequest;
import com.aek.ebey.qc.service.MtPlanService;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 保养计划  前端控制器
 *
 * @author Honghui
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/qc/mtPlan")
@Api(value = "MtPlanController", description = "保养计划")
public class MtPlanController extends BaseController {
	
	@Autowired
	private MtPlanService mtPlanService;
	
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@PostMapping(value = "/addPlan")
	@ApiOperation(value = "新建保养计划", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> addPlan(@RequestBody MtPlanRequest request) {
		if(request!=null&&request.getMtAssets().size()>0){
			MtPlan plan = BeanMapper.map(request, MtPlan.class);
			List<MtAssets> mtAssetList = BeanMapper.mapList(request.getMtAssets(), MtAssets.class);
			mtPlanService.addPlan(plan, mtAssetList);
		}		
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@GetMapping(value = "/checkTemplate/{templateId}")
	@ApiOperation(value = "校验模板是否有项目", httpMethod = "GET", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Boolean> checkTemplate(@PathVariable(value="templateId")Long templateId) {
		return response(mtPlanService.checkTemplate(templateId));	
	}
	
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@PostMapping(value = "/editPlan")
	@ApiOperation(value = "编辑保养计划", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> editPlan(@RequestBody MtPlanEditRequest request) {
		mtPlanService.editPlan(request);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@GetMapping(value = "/disablePlan/{id}")
	@ApiOperation(value = "停用保养计划", httpMethod = "GET", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> disablePlan(@PathVariable(value="id")Long id) {
		mtPlanService.disablePlan(id);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@GetMapping(value = "/getPlanDetail/{id}")
	@ApiOperation(value = "查保养计划详情", httpMethod = "GET", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MtPlanDetailVO> getPlanDetail(@PathVariable(value="id")Long id) {
		return response(mtPlanService.getPlanDetail(id));	
	}
	
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@GetMapping(value = "/getPlanByPage")
	@ApiOperation(value = "保养计划分页", httpMethod = "GET", produces = "application/json")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "keyword",value = "输入关键字（设备名称或者设备编号）",paramType = "query" ,required = true),
			@ApiImplicitParam(name = "enable",value = "停启用(0=停用,1=启用,查全部=不用传)",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageNo",value = "当前页",paramType = "query",required = true),
			@ApiImplicitParam(name = "deptId",value = "部门id",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageSize",value = "每页显示多少数",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Page<MtPlanResponseVO>> getByPage(MtPlanQuery query) {
		return response(mtPlanService.getPlanByPage(query));	
	}
}
