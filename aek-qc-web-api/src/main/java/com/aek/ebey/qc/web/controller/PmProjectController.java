package com.aek.ebey.qc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.model.PmProject;
import com.aek.ebey.qc.request.PmProjectRequest2;
import com.aek.ebey.qc.service.PmProjectService;
import com.aek.ebey.qc.service.QcTemplateService;
import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * PM项目  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
@RestController
@RequestMapping("/pm/pmProject")
@Api(value = "PmProjectController", description = "PM模版项目")
public class PmProjectController extends BaseController {
	
private static final Logger logger = LoggerFactory.getLogger(PmProjectController.class);
	
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private PmProjectService pmProjectService;
	
	/**
	 * 添加PM模版项目
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "添加PM模版项目")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> save(@Validated(value = Add.class)@RequestBody PmProjectRequest2 pmProjectRequest){
		logger.debug("<---------------------------------"+JSON.toJSONString(pmProjectRequest));
		this.pmProjectService.save(pmProjectRequest);
		return response();
	}
	
	/**
	 * 编辑PM模版项目
	 */
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑PM模版项目", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@Validated(value = Edit.class) @RequestBody PmProjectRequest2 request) {
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		pmProjectService.edit(request);
		return response();
	}
	
	/**
	 * 删除PM模板项目
	 */
	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "删除巡PM板项目", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmProject  pmProject =pmProjectService.selectById(id);
		pmProjectService.deleteProject(pmProject);
		return response();
		
	}
}
