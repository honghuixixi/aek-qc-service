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
import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.query.PmTemplateQuery;
import com.aek.ebey.qc.request.PmTemplateDetail;
import com.aek.ebey.qc.request.PmTemplateResponse;
import com.aek.ebey.qc.request.QcTemplateRequest;
import com.aek.ebey.qc.service.PmProjectService;
import com.aek.ebey.qc.service.PmTemplateService;
import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * PM模版  前端控制器
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
@RestController
@RequestMapping("/pm/pmTemplate")
@Api(value = "PmTemplateController", description = "PM模版")
public class PmTemplateController extends BaseController {
private static final Logger logger = LoggerFactory.getLogger(PmTemplateController.class);
	
	@Autowired
	private PmTemplateService pmTemplateService;
	
	@Autowired
	private PmProjectService pmProjectService;
	
	/**
	 * 新建PM模版
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_NEW')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建PM模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@Validated(value = Add.class)@RequestBody QcTemplateRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		PmTemplate pmTemplate = BeanMapper.map(request, PmTemplate.class);
		this.pmTemplateService.save(pmTemplate);
		return response();
	}
	
	/**
	 * 编辑PM模版
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_EDIT')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑PM模版", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@Validated(value = Edit.class) @RequestBody QcTemplateRequest request) {
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		PmTemplate pmTemplate = BeanMapper.map(request, PmTemplate.class);
		pmTemplateService.edit(pmTemplate);
		return response();
	}
	
	/**
	 * 停用PM模版
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_DISABLE_ENABLE')")
	@GetMapping(value = "/disable/{id}")
	@ApiOperation(value = "停用PM模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> disable(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmTemplate  pmTemplate =pmTemplateService.selectById(id);
		if(pmTemplate!=null&&pmTemplate.getStatus().intValue()==StatusConstants.ONE){
			pmTemplate.setStatus(StatusConstants.TWO);
			pmTemplateService.updateById(pmTemplate);
			return response();
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	/**
	 * 启用PM模版
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_DISABLE_ENABLE')")
	@GetMapping(value = "/enable/{id}")
	@ApiOperation(value = "启用巡检模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> enable(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmTemplate  pmTemplate =pmTemplateService.selectById(id);
		if(pmTemplate!=null&&pmTemplate.getStatus().intValue()==StatusConstants.TWO){
			pmTemplate.setStatus(StatusConstants.ONE);
			pmTemplateService.updateById(pmTemplate);
			return response();
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	/**
	 * 删除PM模版
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_DELETE')")
	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "删除PM模版")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmTemplate  pmTemplate =pmTemplateService.selectById(id);
		if(pmTemplate!=null){
			pmTemplateService.deleteById(id);
			return response();
		}else{
			throw ExceptionFactory.create("Q_003");
		}
	}
	
	/**
	 * 获取PM模板详情
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_VIEW')")
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "获取PM模板详情")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmTemplateDetail> get(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmTemplate  pmTemplate =pmTemplateService.selectById(id);
		if(pmTemplate!=null){
			return response(pmTemplateService.getAllById(id));
		}else{
			throw ExceptionFactory.create("Q_012");
		}
	}
	
	/**
	 * 选择获取PM模板详情(无权限)
	 */
	@GetMapping(value = "/change/{id}")
	@ApiOperation(value = "选择获取PM模板详情(无权限)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<PmTemplateDetail> change(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		PmTemplate  pmTemplate =pmTemplateService.selectById(id);
		if(pmTemplate!=null){
			return response(pmTemplateService.getAllById2(id));
		}else{
			throw ExceptionFactory.create("Q_012");
		}
	}
	
	/**
	 * 获取PM模板列表
	 */
	@PreAuthorize("hasAuthority('PM_TEMPLATE_VIEW')")
	@GetMapping(value = "/search")
	@ApiOperation(value = "获取PM模板列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<PmTemplateResponse>> search(PmTemplateQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<PmTemplateResponse> page = pmTemplateService.search(query);
		return response(page);
	}
	
	
	/**
	 * 选择PM模板列表
	 */
	@GetMapping(value = "/changeSearch")
	@ApiOperation(value = "选择PM模板列表")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<List<PmTemplateResponse>> changeSearch(String keyword) {
 		logger.debug("<---------------------------------"+JSON.toJSONString(keyword));
		List<PmTemplateResponse> list = pmTemplateService.changeSearch(keyword);
		return response(list);
	}
	
}
