package com.aek.ebey.qc.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.query.PmPlanImplementQuery;
import com.aek.ebey.qc.request.CheckDetail;
import com.aek.ebey.qc.request.PmImplementDetail;
import com.aek.ebey.qc.request.PmImplementRequest;
import com.aek.ebey.qc.request.PmImplementSheet;
import com.aek.ebey.qc.request.PmPlanImplementResponse;
import com.aek.ebey.qc.service.PmPlanImplementHelpService;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * PM计划实施  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@RestController
@RequestMapping("/pm/pmPlanImplementHelp")
@Api(value = "PmPlanImplementHelpController", description = "PM计划实施Help")
public class PmPlanImplementHelpController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PmPlanImplementHelpController.class);
	/*
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private  DeptClientService deptClientService;*/
	
	@Autowired
	private PmPlanImplementHelpService pmPlanImplementHelpService;
	
	/**
	 * PM实施列表
	 */
	//@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/search")
	@ApiOperation(value = "PM实施列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<PmPlanImplementResponse>> search(PmPlanImplementQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<PmPlanImplementResponse> page = pmPlanImplementHelpService.search(query);
		return response(page);
	}
	
	/**
	 * 查询待巡检待办
	 */
	@GetMapping(value = "/getPmImplementWaitToDo")
	@ApiOperation(value = "查询PM待巡检待办")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Integer> getPmImplementWaitToDo(Long id) {
		logger.debug("<---------------------getPmImplementWaitToDo------------");
		Integer  i =pmPlanImplementHelpService.getPmImplementWaitToDo(id);
		return response(i);
		
	}
	
	/**
	 * PM实施检测
	 */
	//@PreAuthorize("hasAuthority('QC_PLAN_VIEW')")
	@GetMapping(value = "/check/{id}")
	@ApiOperation(value = "PM实施检测")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<CheckDetail> check(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		CheckDetail checkDetail=pmPlanImplementHelpService.checkById(id);
		return response(checkDetail);
		
	}
	
	/**
	 * 打印PM实施单
	 */
	//@PreAuthorize("hasAuthority('PM_REPORT_VIEW')")
	@GetMapping(value = "/printImplementSheet/{id}")
	@ApiOperation(value = "打印PM实施单")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmImplementSheet> printImplementSheet(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmImplementSheet pmImplementSheet=pmPlanImplementHelpService.getImplementSheetById(id);
		return response(pmImplementSheet);
		
	}
	
	/**
	 * 
	 *扫码PM实施(小程序)
	 */
	@GetMapping(value = "/scan/{id}")
	@ApiOperation(value = "扫码PM实施(小程序)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<CheckDetail> scan(@PathVariable Long id){
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		CheckDetail checkDetail=this.pmPlanImplementHelpService.scan(id);
		return response(checkDetail);
	}
	
	/**
	 * PM实施详情
	 */
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "PM实施详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmImplementDetail> getPmImplementDetail(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmImplementDetail pmImplementDetail=pmPlanImplementHelpService.getPmImplementDetail(id);
		return response(pmImplementDetail);
	}
	
	/**
	 * 
	 *暂存PM实施or提交PM
	 */
	@PostMapping(value = "/saveOrSubmit")
	@ApiOperation(value = "暂存PM实施or提交PM")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> save(@RequestBody PmImplementRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.pmPlanImplementHelpService.save(request);
		return response();
	}
	
	 /**
     * 定时发送消息
     */
	@Scheduled(cron="0/50 * *  * * ? ")  
    public void autoSend() throws Exception
    {
		pmPlanImplementHelpService.autoSend();
		
    }
	
}
