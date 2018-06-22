package com.aek.ebey.qc.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.query.QcPlanCheckQuery;
import com.aek.ebey.qc.request.QcPlanCheckResponse;
import com.aek.ebey.qc.service.QcPlanCheckService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * qc验收人员表  前端控制器
 * </p>
 *
 * @author Liuhui
 * @since 2018-05-16
 */
@RestController
@RequestMapping("/qc/qcPlanCheck")
@Api(value = "QcPlanCheckController", description = "巡检验收")
public class QcPlanCheckController extends BaseController {
private static final Logger logger = LoggerFactory.getLogger(QcPlanCheckController.class);
	
	@Autowired
	private QcPlanCheckService qcPlanCheckService;
	

	/**
	 * 查询验收列表
	 */
	@GetMapping(value = "/search")
	@ApiOperation(value = "查询验收列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "起始页 [默认1]", paramType = "query", required = false),
			@ApiImplicitParam(name = "pageSize", value = "分页大小[默认10]", paramType = "query", required = false),
			@ApiImplicitParam(name = "status", value = "验收状态（1，待验收 2，已验收）", paramType = "query", required = false),
			@ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", required = false),
			@ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", required = false),
			@ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", required = false),
			@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false) })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcPlanCheckResponse>> search(QcPlanCheckQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcPlanCheckResponse> page = qcPlanCheckService.search(query);
		return response(page);
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
		qcPlanCheckService.check(id);
		return response();
	}

	/**
	 * 查询验收待办
	 */
	@GetMapping(value = "/getQcPlanCheckWaitToDo")
	@ApiOperation(value = "查询验收待办")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Integer> getQcPlanCheckWaitToDo(Long id) {
		logger.debug("<---------------------getQcPlanCheckWaitToDo------------");
		Integer  i =qcPlanCheckService.getQcPlanCheckWaitToDo(id);
		return response(i);
		
	}
}
