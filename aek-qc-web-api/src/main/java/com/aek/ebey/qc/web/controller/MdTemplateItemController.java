package com.aek.ebey.qc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.model.vo.MdTemplateDetailVO;
import com.aek.ebey.qc.model.vo.MdTemplateItemVO;
import com.aek.ebey.qc.request.MdTemplateItemRequest;
import com.aek.ebey.qc.service.MdTemplateItemService;
import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 质控检测系统模板项目表  前端控制器
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@RestController
@RequestMapping("/qc/mdTemplateItem")
@Api(value = "MdTemplateItemController", description = "质控模板项目")
public class MdTemplateItemController extends BaseController {
	
    private static final Logger logger = LoggerFactory.getLogger(MdTemplateItemController.class);
    
    @Autowired
    private MdTemplateItemService mdTemplateItemService;
    
    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @PostMapping(value = "/save")
    @ApiOperation(value = "新建质控模板项目", httpMethod = "POST", produces = "application/json")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> save(@Validated(value=Add.class) @RequestBody MdTemplateItemRequest request) {
        logger.debug("===============新建质控模板项目=============");
        mdTemplateItemService.save(request);
        return response();  
    }
    
    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑质控模板项目", httpMethod = "POST", produces = "application/json")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> edit(@Validated(value=Edit.class) @RequestBody MdTemplateItemRequest request) {
        logger.debug("===============编辑质控模板项目=============");
        mdTemplateItemService.edit(request);
        return response();  
    }
    
    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除质控模版项目", httpMethod = "DELETE", produces = "application/json")
    @ApiImplicitParam(name = "id",value = "项目ID",required = true)
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> delete(@RequestParam("id") Long id) {
        logger.debug("===============删除质控模版项目=============");
        mdTemplateItemService.delete(id);
        return response();  
    }
    
    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @GetMapping(value = "/detail")
    @ApiOperation(value = "查询质控模板项目详情", httpMethod = "GET", produces = "application/json")
    @ApiImplicitParam(name = "id",value = "质控模版项目ID",required = true)
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<MdTemplateItemVO> getMdTemplateDetail(@RequestParam("id") Long id) {
        logger.debug("===============查询质控模板项目详情=============");
        return response(mdTemplateItemService.getMdTemplateItemDetail(id));   
    }
}
