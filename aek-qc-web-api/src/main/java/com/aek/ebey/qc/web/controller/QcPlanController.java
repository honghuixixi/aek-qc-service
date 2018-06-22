package com.aek.ebey.qc.web.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.query.QcPlanQuery;
import com.aek.ebey.qc.query.QcPlanRecordQuery;
import com.aek.ebey.qc.request.Dept;
import com.aek.ebey.qc.request.QcPlanDetail;
import com.aek.ebey.qc.request.QcPlanRecordResponse;
import com.aek.ebey.qc.request.QcPlanRequest;
import com.aek.ebey.qc.request.QcPlanResponse;
import com.aek.ebey.qc.service.QcPlanService;
import com.aek.ebey.qc.service.QcTemplateService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 *   巡检计划 前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@RestController
@RequestMapping("/qc/qcPlan")
@Api(value = "QcPlanController", description = "巡检计划")
public class QcPlanController extends BaseController {
	
private static final Logger logger = LoggerFactory.getLogger(QcPlanController.class);
	
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private  DeptClientService deptClientService;
	
	@Autowired
	private QcPlanService qcPlanService;
	
	@PreAuthorize("hasAnyAuthority('QC_PLAN_NEW,QC_PLAN_VIEW')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建巡检计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@Validated   @RequestBody QcPlanRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		//QcPlan qcPlan = BeanMapper.map(request, QcPlan.class);
		this.qcPlanService.save(request);
		return response();
	}
	

	/**
	 * 查询巡检计划列表
	 */
	@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/search")
	@ApiOperation(value = "查询巡检计划列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcPlanResponse>> search(QcPlanQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcPlanResponse> page = qcPlanService.search(query);
		return response(page);
	}
	
	/**
	 * 查询巡检计划详情
	 */
	@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "查询巡检计划详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcPlanDetail> get(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcPlan  qcPlan =qcPlanService.selectById(id);
		if(qcPlan!=null){
			return response(qcPlanService.getAllByQcPlan(qcPlan));
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	/**
	 * 查询QC待巡检待办
	 */
	@GetMapping(value = "/getQcImplementWaitToDo")
	@ApiOperation(value = "查询QC待巡检待办")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Integer> getQcImplementWaitToDo(Long id) {
		logger.debug("<---------------------getQcImplementWaitToDo------------");
		Integer  i =qcPlanService.getQcImplementWaitToDo(id);
		return response(i);
		
	}
	
	/**
	 * 停用巡检计划
	 */
	@PreAuthorize("hasAnyAuthority('QC_PLAN_DISABLE,QC_PLAN_VIEW')")
	@GetMapping(value = "/disable/{id}")
	@ApiOperation(value = "停用巡检计划")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> disable(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		qcPlanService.updateQcPlan(id);
		return response();
		
	}
	
	/**
	 * 查询巡检记录
	 */
	@GetMapping(value = "/getRecord")
	@ApiOperation(value = "查询巡检记录")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcPlanRecordResponse>> getRecord(QcPlanRecordQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcPlanRecordResponse> page = qcPlanService.getRecord(query);
		return response(page);
	}
	
	/**
	 * 查询巡检科室
	 */
	@GetMapping(value = "/getDepts")
	@ApiOperation(value = "查询巡检科室")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<List<Dept>> getDepts(String keyword) {
		logger.debug("<---------------------------------"+JSON.toJSONString(keyword));
		List<Dept> list = qcPlanService.getDepts(keyword);
		//调用接口得到List<Dept>
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Result<List<Dept>> result=null;
		if(authUser!=null){
			result=deptClientService.getQcDeptList(authUser.getTenantId(), WebSecurityUtils.getCurrentToken(), keyword);
			logger.debug("<---------------------------------deptClientService.getQcDeptList="+JSON.toJSONString(result));
			
		}
		List<Dept> listDept=new ArrayList<>();
		if (result != null && result.getData() != null && result.getData().size() != 0) {
			for (Dept dept : result.getData()) {
				listDept.add(dept);
			}
		}
		if(listDept.size()>0){
			listDept.removeAll(list);
		}
		return response(listDept);
	}
	
	
	 /**
     * 定时发送消息
     */
	@Scheduled(cron="0/50 * *  * * ? ")  
    public void autoSend() throws Exception
    {
		qcPlanService.autoSend();
		
    }
	
	
}
