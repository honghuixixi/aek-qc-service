package com.aek.ebey.qc.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MtImplementResult;
import com.aek.ebey.qc.model.MtImplementResultFile;
import com.aek.ebey.qc.model.MtImplementResultItem;
import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.MtPlanAssets;
import com.aek.ebey.qc.model.vo.MtRecordBatchReponseVO;
import com.aek.ebey.qc.query.MtImplementResultQuery;
import com.aek.ebey.qc.query.MtPlanImplementQuery;
import com.aek.ebey.qc.query.MtRecordBatchQuery;
import com.aek.ebey.qc.query.MtRecordQuery;
import com.aek.ebey.qc.request.MtImplementResultPageResponse;
import com.aek.ebey.qc.request.MtImplementResultResponse;
import com.aek.ebey.qc.request.MtPlanImplementResponse;
import com.aek.ebey.qc.service.MtImplementResultFileService;
import com.aek.ebey.qc.service.MtImplementResultItemService;
import com.aek.ebey.qc.service.MtImplementResultService;
import com.aek.ebey.qc.service.MtPlanAssetsService;
import com.aek.ebey.qc.service.MtPlanImplementService;
import com.aek.ebey.qc.service.MtPlanService;
import com.aek.ebey.qc.service.MtPlanTemplateItemService;
import com.aek.ebey.qc.service.MtPlanTemplateService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 保养实施结果表  前端控制器
 *
 * @author Honghui
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/qc/mtImplementResult")
@Api(value = "MtImplementResultController", description = "保养实施结果")
public class MtImplementResultController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MtImplementResultController.class);
	
	@Autowired
	private MtPlanService mtPlanService;
	@Autowired
	private MtImplementResultService mtImplementResultService;
	@Autowired
	private MtPlanAssetsService mtPlanAssetsService;
	@Autowired
	private MtImplementResultFileService mtImplementResultFileService;
	@Autowired
	private MtImplementResultItemService mtImplementResultItemService;

	
	/**
	 * 实施后返回实施结果
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_PLAN_IMPLEMENT_MANAGE')")
	@GetMapping(value = "/getResult")
	@ApiOperation(value = "获取实施结果， 打印实施报告")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MtImplementResultResponse> getMtPlanImplementDetail(@RequestParam("resultId") Long resultId) {
		if (null == resultId) {
			throw ExceptionFactory.create("MT_007");
		}
		//实施结果
 		MtImplementResult mtImplementResult = mtImplementResultService.selectById(resultId);
 		MtImplementResultResponse mtImplementResultResponse = BeanMapper.map(mtImplementResult, MtImplementResultResponse.class);
 		//保养计划及保养设备
		MtPlan mtPlan = mtPlanService.selectById(mtImplementResult.getPlanId());
		Wrapper<MtPlanAssets> mtPlanAssetsWrapper = new EntityWrapper<MtPlanAssets>();
		mtPlanAssetsWrapper.eq("plan_id", mtPlan.getId());
		MtPlanAssets mtPlanAssets = mtPlanAssetsService.selectOne(mtPlanAssetsWrapper);
		BeanUtils.copyProperties(mtPlanAssets, mtImplementResultResponse);
		BeanUtils.copyProperties(mtPlan, mtImplementResultResponse);
		mtImplementResultResponse.setAdministrator(mtImplementResult.getAdministrator());
		//所属机构
		mtImplementResultResponse.setTenantName(mtPlan.getTenantName());
		//实施附件
		Wrapper<MtImplementResultFile> mtImplementResultFileWrapper = new EntityWrapper<MtImplementResultFile>();
		mtImplementResultFileWrapper.eq("result_id", resultId);
		List<MtImplementResultFile> files = mtImplementResultFileService.selectList(mtImplementResultFileWrapper);
		mtImplementResultResponse.setFiles(files);
		//实施项目结果
		Wrapper<MtImplementResultItem> mtImplementResultItemWrapper = new EntityWrapper<MtImplementResultItem>();
		mtImplementResultItemWrapper.eq("result_id", resultId);
		List<MtImplementResultItem> items = mtImplementResultItemService.selectList(mtImplementResultItemWrapper);
		mtImplementResultResponse.setItems(items);
		return response(mtImplementResultResponse);
	}
	
	/**
	 * 实施报告分页列表
	 * @param query
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_REPORT_VIEW')")
	@GetMapping(value = "/list")
	@ApiOperation(value = "实施报告分页列表")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "keyword",value = "设备名称/设备编号",paramType = "query" ,required = false),
			@ApiImplicitParam(name = "startImplementDate",value = "实施开始时间",paramType = "query",required = false),
			@ApiImplicitParam(name = "endImplementDate",value = "实施结束时间",paramType = "query",required = false),
			@ApiImplicitParam(name = "deptId",value = "部门ID",paramType = "query",required = false),
			@ApiImplicitParam(name = "pageNo",value = "当前页",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageSize",value = "每页显示多少数",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<MtImplementResultPageResponse>> getMtImplementResultPage(MtImplementResultQuery query) {
		logger.debug("查询参数="+JSON.toJSONString(query));
		AuthUser user = WebSecurityUtils.getCurrentUser();
		LiuHuiPage<MtImplementResultPageResponse> data = mtImplementResultService.getMtImplementResultPage(query.getPagePlus(),user,query);
		return response(data);
	}
	
	/**
	 * 保养记录分页列表
	 * @param query
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_PLAN_MANAGE')")
	@GetMapping(value = "/getMtRecordPage")
	@ApiOperation(value = "保养记录分页列表")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "planId",value = "计划id",paramType = "query",required = true),
			@ApiImplicitParam(name = "startDate",value = "开始时间",paramType = "query",required = false),
			@ApiImplicitParam(name = "endDate",value = "结束时间",paramType = "query",required = false),
			@ApiImplicitParam(name = "pageNo",value = "当前页",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageSize",value = "每页显示多少数",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Page<MtImplementResult>> getMtRecordPage(MtRecordQuery query) {
		return response(mtImplementResultService.getMtRecordPage(query));
	}
	
	/**
	 * 批量打印保养实施报告
	 * @param query
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_REPORT_VIEW')")
	@GetMapping(value = "/getMtRecordBatchPrint")
	@ApiOperation(value = "批量打印保养实施报告")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "startDate",value = "开始时间",paramType = "query",required = false),
			@ApiImplicitParam(name = "endDate",value = "结束时间",paramType = "query",required = false),
			@ApiImplicitParam(name = "planId",value = "计划id",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MtRecordBatchReponseVO> getMtRecordBatchPrint(MtRecordBatchQuery query) {
		return response(mtImplementResultService.getMtRecordBatchPrint(query));
	}
}
