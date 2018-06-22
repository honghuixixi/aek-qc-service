package com.aek.ebey.qc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.constant.MdConstant;
import com.aek.ebey.qc.model.MdReportTemplateItemResult;
import com.aek.ebey.qc.model.bo.MdAssetsBO;
import com.aek.ebey.qc.model.bo.MdMaximumAllowableErrorDetailBO;
import com.aek.ebey.qc.model.bo.MdReportBO;
import com.aek.ebey.qc.model.vo.MdAssetsVO;
import com.aek.ebey.qc.model.vo.MdReportDetailVO;
import com.aek.ebey.qc.model.vo.MdReportPageVO;
import com.aek.ebey.qc.query.MdReportQuery;
import com.aek.ebey.qc.query.MdVerifyReportQuery;
import com.aek.ebey.qc.service.MdReportService;
import com.aek.ebey.qc.web.request.MdTempSaveRequest;
import com.aek.ebey.qc.web.request.MdVerifyRequest;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 质控检测单表  前端控制器
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@RestController
@RequestMapping("/qc/mdReport")
public class MdReportController extends BaseController {
	
	@Autowired
	private MdReportService mdReportService;
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "获取质控管理所需常量", httpMethod = "GET")
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/getMdConstantMap", method = RequestMethod.GET)
	public Result<Map> getMdConstantMap() {
		Map<String, Map> map = new HashMap<>();
		map.put("STATUS", MdConstant.STATUS_MAP);
		map.put("CHECK_TYPE", MdConstant.CHECK_TYPE_MAP);
		map.put("CHECK_RESULT", MdConstant.CHECK_RESULT_MAP);
		map.put("VERIFY_RESULT", MdConstant.VERIFY_RESULT_MAP);
		return response(map);
	}

	@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "质控填报，暂存、待审核设备id集合", httpMethod = "GET")
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/getMdAssetsExist", method = RequestMethod.GET)
	public Result<List<Long>> getMdAssetsExist() {
		return response(mdReportService.getMdAssetsExist());
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "新增填报暂存", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "质控检测单Id", paramType = "query"),
		@ApiImplicitParam(name = "reportTemplateId", value = "质控报告模板Id", paramType = "query"),
		@ApiImplicitParam(name = "mdNum", value = "质控检测单号", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)", paramType = "query"),
		@ApiImplicitParam(name = "contactNumber", value = "联系电话", paramType = "query", required = true),
		@ApiImplicitParam(name = "checkAccording", value = "检测依据", paramType = "query", required = true),
		@ApiImplicitParam(name = "environmentCondition", value = "环境条件", paramType = "query"),
		@ApiImplicitParam(name = "deviationRecord", value = "偏离情况记录", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentName", value = "检测仪器(标准器)名称", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentSpec", value = "检测仪器(标准器)规格", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentProducer", value = "检测仪器(标准器)生产商", paramType = "query"),
		@ApiImplicitParam(name = "checkType", value = "质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)", paramType = "query"),
		@ApiImplicitParam(name = "checkResult", value = "检测结论(0=不合格、1=合格)", paramType = "query"),
		@ApiImplicitParam(name = "appearanceWorkCheck", value = "外观及工作正常性检查", paramType = "query"),
		@ApiImplicitParam(name = "maximumAllowableErrorList", value = "最大允差集合", paramType = "query"),
		@ApiImplicitParam(name = "mdAsset", value = "质控设备", paramType = "query"),
		@ApiImplicitParam(name = "mdItemResultList", value = "答案接收集合", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/tempSave", method = RequestMethod.POST)
	public Result<Long> tempSaveOrSave(@RequestBody MdTempSaveRequest request) {
		MdReportBO mdReportBO = BeanMapper.map(request, MdReportBO.class);
		List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList = request.getMaximumAllowableErrorList();
		MdAssetsBO mdAsset = request.getMdAsset();
		List<MdReportTemplateItemResult> mdItemResultList = request.getMdItemResultList();
		Long id = mdReportService.tempSave(mdReportBO,mdAsset, maximumAllowableErrorList , mdItemResultList);
		return response(id);
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "新增填报提交", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "质控检测单Id", paramType = "query"),
		@ApiImplicitParam(name = "reportTemplateId", value = "质控报告模板Id", paramType = "query"),
		@ApiImplicitParam(name = "mdNum", value = "质控检测单号", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)", paramType = "query"),
		@ApiImplicitParam(name = "contactNumber", value = "联系电话", paramType = "query", required = true),
		@ApiImplicitParam(name = "checkAccording", value = "检测依据", paramType = "query", required = true),
		@ApiImplicitParam(name = "environmentCondition", value = "环境条件", paramType = "query"),
		@ApiImplicitParam(name = "deviationRecord", value = "偏离情况记录", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentName", value = "检测仪器(标准器)名称", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentSpec", value = "检测仪器(标准器)规格", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentProducer", value = "检测仪器(标准器)生产商", paramType = "query"),
		@ApiImplicitParam(name = "checkType", value = "质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)", paramType = "query"),
		@ApiImplicitParam(name = "checkResult", value = "检测结论(0=不合格、1=合格)", paramType = "query"),
		@ApiImplicitParam(name = "appearanceWorkCheck", value = "外观及工作正常性检查", paramType = "query"),
		@ApiImplicitParam(name = "maximumAllowableErrorList", value = "最大允差集合", paramType = "query"),
		@ApiImplicitParam(name = "mdAsset", value = "质控设备", paramType = "query"),
		@ApiImplicitParam(name = "mdItemResultList", value = "答案接收集合", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Object> save(@RequestBody MdTempSaveRequest request) {
		MdReportBO mdReportBO = BeanMapper.map(request, MdReportBO.class);
		List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList = request.getMaximumAllowableErrorList();
		MdAssetsBO mdAsset = request.getMdAsset();
		List<MdReportTemplateItemResult> mdItemResultList = request.getMdItemResultList();
		mdReportService.save(mdReportBO,mdAsset, maximumAllowableErrorList , mdItemResultList);
		return response();
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "质控检测单再次编辑暂存", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "质控检测单Id", paramType = "query"),
		@ApiImplicitParam(name = "reportTemplateId", value = "质控报告模板Id", paramType = "query"),
		@ApiImplicitParam(name = "mdNum", value = "质控检测单号", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)", paramType = "query"),
		@ApiImplicitParam(name = "contactNumber", value = "联系电话", paramType = "query", required = true),
		@ApiImplicitParam(name = "checkAccording", value = "检测依据", paramType = "query", required = true),
		@ApiImplicitParam(name = "environmentCondition", value = "环境条件", paramType = "query"),
		@ApiImplicitParam(name = "deviationRecord", value = "偏离情况记录", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentName", value = "检测仪器(标准器)名称", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentSpec", value = "检测仪器(标准器)规格", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentProducer", value = "检测仪器(标准器)生产商", paramType = "query"),
		@ApiImplicitParam(name = "checkType", value = "质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)", paramType = "query"),
		@ApiImplicitParam(name = "checkResult", value = "检测结论(0=不合格、1=合格)", paramType = "query"),
		@ApiImplicitParam(name = "appearanceWorkCheck", value = "外观及工作正常性检查", paramType = "query"),
		@ApiImplicitParam(name = "maximumAllowableErrorList", value = "最大允差集合", paramType = "query"),
		@ApiImplicitParam(name = "mdAsset", value = "质控设备", paramType = "query"),
		@ApiImplicitParam(name = "mdItemResultList", value = "答案接收集合", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/againTempSave", method = RequestMethod.POST)
	public Result<Long> againTempSave(@RequestBody MdTempSaveRequest request) {
		MdReportBO mdReportBO = BeanMapper.map(request, MdReportBO.class);
		List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList = request.getMaximumAllowableErrorList();
		MdAssetsBO mdAsset = request.getMdAsset();
		List<MdReportTemplateItemResult> mdItemResultList = request.getMdItemResultList();
		Long id = mdReportService.againTempSave(mdReportBO,mdAsset, maximumAllowableErrorList , mdItemResultList);
		return response(id);
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "质控检测单再次编辑保存", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "质控检测单Id", paramType = "query"),
		@ApiImplicitParam(name = "reportTemplateId", value = "质控报告模板Id", paramType = "query"),
		@ApiImplicitParam(name = "mdNum", value = "质控检测单号", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)", paramType = "query"),
		@ApiImplicitParam(name = "contactNumber", value = "联系电话", paramType = "query", required = true),
		@ApiImplicitParam(name = "checkAccording", value = "检测依据", paramType = "query", required = true),
		@ApiImplicitParam(name = "environmentCondition", value = "环境条件", paramType = "query"),
		@ApiImplicitParam(name = "deviationRecord", value = "偏离情况记录", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentName", value = "检测仪器(标准器)名称", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentSpec", value = "检测仪器(标准器)规格", paramType = "query"),
		@ApiImplicitParam(name = "checkInstrumentProducer", value = "检测仪器(标准器)生产商", paramType = "query"),
		@ApiImplicitParam(name = "checkType", value = "质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)", paramType = "query"),
		@ApiImplicitParam(name = "checkResult", value = "检测结论(0=不合格、1=合格)", paramType = "query"),
		@ApiImplicitParam(name = "appearanceWorkCheck", value = "外观及工作正常性检查", paramType = "query"),
		@ApiImplicitParam(name = "maximumAllowableErrorList", value = "最大允差集合", paramType = "query"),
		@ApiImplicitParam(name = "mdAsset", value = "质控设备", paramType = "query"),
		@ApiImplicitParam(name = "mdItemResultList", value = "答案接收集合", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/againSave", method = RequestMethod.POST)
	public Result<Object> againSave(@RequestBody MdTempSaveRequest request) {
		MdReportBO mdReportBO = BeanMapper.map(request, MdReportBO.class);
		List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList = request.getMaximumAllowableErrorList();
		MdAssetsBO mdAsset = request.getMdAsset();
		List<MdReportTemplateItemResult> mdItemResultList = request.getMdItemResultList();
		mdReportService.againSave(mdReportBO,mdAsset, maximumAllowableErrorList , mdItemResultList);
		return response();
	}
		
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "质控填报列表查询", httpMethod = "GET")
	@ApiImplicitParams({@ApiImplicitParam(name = "status", value = "质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)", paramType = "query"),
		@ApiImplicitParam(name = "deptId", value = "科室id", paramType = "query"),
		@ApiImplicitParam(name = "checkTimeStart", value = "填报时间开始", paramType = "query"),
		@ApiImplicitParam(name = "checkTimeEnd", value = "填报时间结束", paramType = "query"),
		@ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", required = true),
		@ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true),
		@ApiImplicitParam(name = "keyword", value = "质控单号/设备编号/设备名称", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/getApplyMdReportPage", method = RequestMethod.GET)
	public Result<Page<MdReportPageVO>> getApplyMdReportPage(MdReportQuery query) {
		return response(mdReportService.getApplyMdReportPage(query));
	}
	
	@PreAuthorize("hasAuthority('MD_CHECK_MANAGE')")
	@ApiOperation(value = "质控审核列表查询", httpMethod = "GET")
	@ApiImplicitParams({@ApiImplicitParam(name = "status", value = "质控审核列表查询选择状态(不传值=全部、2=待审核、3=审核通过、4=审核未通过)", paramType = "query"),
		@ApiImplicitParam(name = "deptId", value = "科室id", paramType = "query"),
		@ApiImplicitParam(name = "checkTimeStart", value = "填报时间开始", paramType = "query"),
		@ApiImplicitParam(name = "checkTimeEnd", value = "填报时间结束", paramType = "query"),
		@ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", required = true),
		@ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true),
		@ApiImplicitParam(name = "keyword", value = "质控单号/设备编号/设备名称", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/getVerifyMdReportPage", method = RequestMethod.GET)
	public Result<Page<MdReportPageVO>> getVerifyMdReportPage(MdVerifyReportQuery query) {
		return response(mdReportService.getVerifyMdReportPage(query));
	}
	
	@PreAuthorize("hasAuthority('MD_ARCHIVE_MANAGE')")
	@ApiOperation(value = "质控档案列表查询", httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptId", value = "科室id", paramType = "query"),
		@ApiImplicitParam(name = "checkTimeStart", value = "填报时间开始", paramType = "query"),
		@ApiImplicitParam(name = "checkTimeEnd", value = "填报时间结束", paramType = "query"),
		@ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", required = true),
		@ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true),
		@ApiImplicitParam(name = "keyword", value = "质控单号/设备编号/设备名称", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/getArchiveMdReportPage", method = RequestMethod.GET)
	public Result<Page<MdReportPageVO>> getArchiveMdReportPage(MdReportQuery query) {
		return response(mdReportService.getArchiveMdReportPage(query));
	}
	
	@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "质控检测单明细查询", httpMethod = "GET")	
	@ApiImplicitParam(name = "id", value = "质控检测单id", paramType = "query")
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/getMdReportDetail", method = RequestMethod.GET)
	public Result<MdReportDetailVO> getMdReportDetail(@RequestParam("id")Long id) {
		return response(mdReportService.getMdReportDetail(id));
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "质控检测单撤回", httpMethod = "GET")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "质控检测单id", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "质控单号/设备编号/设备名称", paramType = "query")})	
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/recallMdReport", method = RequestMethod.GET)
	public Result<Object> recallMdReport(@RequestParam("id")Long id) {
		mdReportService.recallMdReport(id);
		return response();
	}
	
	@PreAuthorize("hasAuthority('MD_CHECK_MANAGE')")
	@ApiOperation(value = "质控检测单审核", httpMethod = "POST")	
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "质控单id", paramType = "query"),
		@ApiImplicitParam(name = "verifyResult", value = "审核结果(0=审核未通过、1=审核通过)", paramType = "query"),
		@ApiImplicitParam(name = "verifyRemark", value = "审核备注", paramType = "query")})
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/verifyMdReport", method = RequestMethod.POST)
	public Result<Object> verifyMdReport(@RequestBody MdVerifyRequest request) {
		Long id = request.getId();
		String verifyRemark = request.getVerifyRemark();
		Integer verifyResult = request.getVerifyResult();
		mdReportService.verifyMdReport(id, verifyRemark, verifyResult);
		return response();
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "质控检测单作废", httpMethod = "GET")	
	@ApiImplicitParam(name = "id", value = "质控检测单id", paramType = "query")
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/deleteMdReport", method = RequestMethod.GET)
	public Result<Object> deleteMdReport(@RequestParam("id")Long id) {
		mdReportService.deleteMdReport(id);
		return response();
	}
	
	@PreAuthorize("hasAuthority('MD_APPLY_MANAGE')")
	@ApiOperation(value = "统计某人审核质控单数目", httpMethod = "GET")	
	@ApiImplicitParam(name = "tenantId", value = "机构id", paramType = "query")
	@ApiResponse(code = 0, message = "OK", response = Result.class)
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public Result<Integer> count(@RequestParam("tenantId")Long tenantId) {
		Integer num = mdReportService.count(tenantId);
		return response(num);
	}
}
