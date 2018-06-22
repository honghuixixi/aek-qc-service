package com.aek.ebey.qc.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.model.QcTemplate;
import com.aek.ebey.qc.query.QcTemplateQuery;
import com.aek.ebey.qc.request.QcTemplateDetail;
import com.aek.ebey.qc.request.QcTemplateRequest;
import com.aek.ebey.qc.request.QcTemplateResponse;
import com.aek.ebey.qc.service.QcOptionService;
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
 * 巡检模版  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
@RestController
@RequestMapping("/qc/qcTemplate")
@Api(value = "QcTemplateController", description = "巡检模版")
public class QcTemplateController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(QcTemplateController.class);
	
	@Autowired
	private QcTemplateService qcTemplateService;
	
	@Autowired
	private QcProjectService qcProjectService;
	
	@Autowired
	private QcOptionService qcOptionService;
	
	@PreAuthorize("hasAuthority('QC_TEMPLATE_NEW')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建巡检模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@Validated(value = Add.class)@RequestBody QcTemplateRequest QcTemplateRequest){
		logger.debug("<---------------------------------"+JSON.toJSONString(QcTemplateRequest));
		QcTemplate qcTemplate = BeanMapper.map(QcTemplateRequest, QcTemplate.class);
		this.qcTemplateService.save(qcTemplate);
		return response();
	}
	
	@PreAuthorize("hasAuthority('QC_TEMPLATE_EDIT')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑模版", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@Validated(value = Edit.class) @RequestBody QcTemplateRequest request) {
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		QcTemplate qcTemplate = BeanMapper.map(request, QcTemplate.class);
		qcTemplateService.edit(qcTemplate);
		return response();
	}
	@PreAuthorize("hasAuthority('QC_TEMPLATE_DISABLE_ENABLE')")
	@GetMapping(value = "/disable/{id}")
	@ApiOperation(value = "停用巡检模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> disable(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcTemplate  qcTemplate =qcTemplateService.selectById(id);
		if(qcTemplate!=null){
			qcTemplate.setStatus(StatusConstants.TWO);
			qcTemplateService.updateById(qcTemplate);
			return response();
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	@PreAuthorize("hasAuthority('QC_TEMPLATE_DISABLE_ENABLE')")
	@GetMapping(value = "/enable/{id}")
	@ApiOperation(value = "启用巡检模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> enable(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcTemplate  qcTemplate =qcTemplateService.selectById(id);
		if(qcTemplate!=null){
			qcTemplate.setStatus(StatusConstants.ONE);
			qcTemplateService.updateById(qcTemplate);
			return response();
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	@PreAuthorize("hasAuthority('QC_TEMPLATE_DELETE')")
	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "删除巡检模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcTemplate  qcTemplate =qcTemplateService.selectById(id);
		if(qcTemplate!=null){
			qcTemplate.setDelFlag(true);
			qcTemplateService.updateById(qcTemplate);
			return response();
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	@PreAuthorize("hasAuthority('QC_TEMPLATE_VIEW')")
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "获取巡检模板详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcTemplateDetail> get(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcTemplate  qcTemplate =qcTemplateService.selectById(id);
		if(qcTemplate!=null){
			return response(qcTemplateService.getAllById(id));
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	
	@GetMapping(value = "/change/{id}")
	@ApiOperation(value = "选择获取巡检模板详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<QcTemplateDetail> change(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		QcTemplate  qcTemplate =qcTemplateService.selectById(id);
		if(qcTemplate!=null){
			return response(qcTemplateService.getAllById(id));
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}

	
	/**
	 * 获取巡检模板列表
	 */
	@GetMapping(value = "/search")
	@ApiOperation(value = "获取巡检模板列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<QcTemplateResponse>> search(QcTemplateQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<QcTemplateResponse> page = qcTemplateService.search(query);
		return response(page);
	}
	
	/**
	 * 选择巡检模板列表
	 */
	@GetMapping(value = "/changeSearch")
	@ApiOperation(value = "选择巡检模板列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<List<QcTemplateResponse>> changeSearch(String keyword) {
		logger.debug("<---------------------------------"+JSON.toJSONString(keyword));
		List<QcTemplateResponse> list = qcTemplateService.changeSearch(keyword);
		return response(list);
	}
	
}
