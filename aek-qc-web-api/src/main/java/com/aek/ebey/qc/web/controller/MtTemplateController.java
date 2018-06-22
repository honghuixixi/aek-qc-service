package com.aek.ebey.qc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.ebey.qc.model.MtTemplate;
import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.model.MtTemplateItemCommon;
import com.aek.ebey.qc.model.vo.MtTemplateVO;
import com.aek.ebey.qc.query.MtTemplateQuery;
import com.aek.ebey.qc.request.MtItemRequest;
import com.aek.ebey.qc.request.MtTemplateItemRequest;
import com.aek.ebey.qc.request.MtTemplateRequest;
import com.aek.ebey.qc.service.MtTemplateService;
import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 保养模板表  前端控制器
 *
 * @author Honghui
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/qc/mtTemplate")
@Api(value = "MtTemplateController", description = "保养模板")
public class MtTemplateController extends BaseController {
	
	@Autowired
	private MtTemplateService mtTemplateService;
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建保养模版", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@Validated(value = Add.class) @RequestBody MtTemplateRequest request) {
		MtTemplate mtp = BeanMapper.map(request, MtTemplate.class);
		mtTemplateService.add(mtp);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑保养模版", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@Validated(value = Edit.class) @RequestBody MtTemplateRequest request) {
		MtTemplate mtp = BeanMapper.map(request, MtTemplate.class);
		mtTemplateService.edit(mtp);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@GetMapping(value = "/getDetail")
	@ApiOperation(value = "保养模版详情", httpMethod = "GET", produces = "application/json")
	@ApiImplicitParam(name = "id",value = "模版ID",required = true)
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MtTemplateVO> getDetail(@RequestParam("id")Long id) {
		return response(mtTemplateService.getDetail(id));	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@DeleteMapping(value = "/delete")
	@ApiOperation(value = "(逻辑)删除保养模版及其关联的项目", httpMethod = "DELETE", produces = "application/json")
	@ApiImplicitParam(name = "id",value = "模版ID",required = true)
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@RequestParam("id")Long id) {
		mtTemplateService.delete(id);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@PutMapping(value = "/enableOnOff")
	@ApiOperation(value = "停/启用保养模版", httpMethod = "PUT", produces = "application/json")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "id",value = "模版ID",paramType = "query" ,required = true),
			@ApiImplicitParam(name = "enable",value = "状态:0停用,1启用",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> enableOnOff(@RequestParam("id")Long id,@RequestParam("enable")Boolean enable) {
		mtTemplateService.enableOnOff(id,enable);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@GetMapping(value = "/getByPage")
	@ApiOperation(value = "保养模版分页", httpMethod = "GET", produces = "application/json")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "keyword",value = "输入关键字（模版名称）",paramType = "query" ,required = true),
			@ApiImplicitParam(name = "enable",value = "停启用(0=停用,1=启用,查全部=不用传)",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageNo",value = "当前页",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageSize",value = "每页显示多少数",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Page<MtTemplate>> getByPage(MtTemplateQuery query) {
		return response(mtTemplateService.getByPage(query));	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@GetMapping(value = "/getList")
	@ApiOperation(value = "新建保养计划查保养模版集合", httpMethod = "GET", produces = "application/json")
	@ApiImplicitParam(name = "keyword",value = "输入关键字（模版名称）",paramType = "query" ,required = false)
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<List<MtTemplateVO>> getList(@RequestParam(value="keyword",required=false)String keyword) {
		return response(mtTemplateService.getList(keyword));	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@PostMapping(value = "/addItem")
	@ApiOperation(value = "添加项目", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> addItem(@Validated(value = Add.class)@RequestBody MtTemplateItemRequest request) {
		Long templateId = request.getTemplateId();
		List<MtTemplateItem> items = request.getItems();
		if(items!=null&&items.size()==0)throw ExceptionFactory.create("M_009");//请新增项目
		mtTemplateService.addItem(templateId,items);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@PutMapping(value = "/editItem")
	@ApiOperation(value = "编辑项目", httpMethod = "PUT", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> editItem(@Validated(value = Edit.class)@RequestBody MtItemRequest request) {
		MtTemplateItem item = BeanMapper.map(request, MtTemplateItem.class);
		mtTemplateService.editItem(item);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@DeleteMapping(value = "/deleteItem")
	@ApiOperation(value = "(逻辑)删除项目", httpMethod = "DELETE", produces = "application/json")
	@ApiImplicitParam(name = "itemId",value = "项目ID",paramType = "query" ,required = true)
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> deleteItem(@RequestParam("itemId")Long itemId) {
		mtTemplateService.deleteItem(itemId);
		return response();	
	}
	
	@PreAuthorize("hasAuthority('MT_TEMPLATE_MANAGE')")
	@GetMapping(value = "/getSysItems")
	@ApiOperation(value = "查系统项目", httpMethod = "GET", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<List<MtTemplateItemCommon>> getSysItems() {
		return response(mtTemplateService.getSysItems());	
	}
}
