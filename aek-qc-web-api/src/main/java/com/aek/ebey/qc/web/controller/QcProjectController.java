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
import com.aek.ebey.qc.model.QcProject;
import com.aek.ebey.qc.request.QcProjectRequest;
import com.aek.ebey.qc.request.QcProjectRequest2;
import com.aek.ebey.qc.service.QcProjectService;
import com.aek.ebey.qc.service.QcTemplateService;
import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 项目  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
@RestController
@RequestMapping("/qc/qcProject")
@Api(value = "QcProjectController", description = "模版项目")
public class QcProjectController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(QcProjectController.class);
	
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private QcProjectService qcProjectService;
	
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建巡检模版项目")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> save(@Validated(value = Add.class)@RequestBody QcProjectRequest QcProjectRequest){
		logger.debug("<---------------------------------"+JSON.toJSONString(QcProjectRequest));
		this.qcProjectService.save(QcProjectRequest);
		return response();
	}
	
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑模版项目", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@Validated(value = Edit.class) @RequestBody QcProjectRequest2 request) {
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		qcProjectService.edit(request);
		return response();
	}
	
	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "删除巡检模板项目", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcProject  qcProject =qcProjectService.selectById(id);
		qcProjectService.deleteProject(qcProject);
		return response();
		
	}
	
}
