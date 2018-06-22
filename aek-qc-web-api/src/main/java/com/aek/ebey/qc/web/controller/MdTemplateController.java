package com.aek.ebey.qc.web.controller;

import java.util.List;

import com.aek.ebey.qc.enums.MdQueryTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.core.BeanMapper;
import com.aek.ebey.qc.model.MdTemplate;
import com.aek.ebey.qc.model.vo.MdTemplateDetailVO;
import com.aek.ebey.qc.query.MdTemplateQuery;
import com.aek.ebey.qc.request.MdTemplateRequest;
import com.aek.ebey.qc.service.MdTemplateService;
import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 质控检测系统模板表  前端控制器
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@RestController
@RequestMapping("/qc/mdTemplate")
@Api(value = "MdTemplateController", description = "质控模板")
public class MdTemplateController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MdTemplateController.class);

    @Autowired
    private MdTemplateService mdTemplateService;

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @PostMapping(value = "/save")
    @ApiOperation(value = "新建质控模板", httpMethod = "POST", produces = "application/json")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> save(@Validated(value = Add.class) @RequestBody MdTemplateRequest request) {
        logger.debug("===============新增质控模板=============");
        MdTemplate mdTemplate = BeanMapper.map(request, MdTemplate.class);
        mdTemplateService.save(mdTemplate);
        return response();
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑质控模板", httpMethod = "POST", produces = "application/json")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> edit(@Validated(value = Edit.class) @RequestBody MdTemplateRequest request) {
        logger.debug("===============编辑质控模板=============");
        MdTemplate mdTemplate = BeanMapper.map(request, MdTemplate.class);
        mdTemplateService.edit(mdTemplate);
        return response();
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @GetMapping(value = "/page")
    @ApiOperation(value = "查询质控模版分页", httpMethod = "GET", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "keyword", value = "输入关键字（模版名称）", paramType = "query"),
            @ApiImplicitParam(name = "releaseFlag", value = "发布状态(0=待发布,1=已发布)", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "查询类型(0=爱怡康,1=普通机构)", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少数", paramType = "query", required = true)})
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Page<MdTemplate>> getMdTemplatePage(MdTemplateQuery query) {
        logger.debug("===============查询质控模板分页=============");
        if (MdQueryTypeEnum.AEK.getNumber().equals(query.getType())) {
            return response(mdTemplateService.getMdTemplatePage(query));
        } else if (MdQueryTypeEnum.COMMON.getNumber().equals(query.getType())) {
            query.setEnable(true);
            query.setReleaseFlag(true);
            return response(mdTemplateService.getMdTemplatePage(query));
        }
        return response(null);
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询质控模版列表(质控填报调用)", httpMethod = "GET", produces = "application/json")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<List<MdTemplate>> getMdTemplateList() {
        logger.debug("===============查询质控模板列表=============");
        return response(mdTemplateService.getMdTemplateList());
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @GetMapping(value = "/detail")
    @ApiOperation(value = "查询质控模板详情", httpMethod = "GET", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "质控模版ID", required = true)
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<MdTemplateDetailVO> getMdTemplateDetail(@RequestParam("id") Long id) {
        logger.debug("===============查询质控模板详情=============");
        return response(mdTemplateService.getMdTemplateDetail(id));
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除质控模版", httpMethod = "DELETE", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "模版ID", required = true)
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> delete(@RequestParam("id") Long id) {
        logger.debug("===============删除质控模板=============");
        mdTemplateService.delete(id);
        return response();
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @PutMapping(value = "/enableOrDisable")
    @ApiOperation(value = "启/停用质控模版", httpMethod = "PUT", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "模版ID", paramType = "query", required = true),
            @ApiImplicitParam(name = "enable", value = "状态:0停用,1启用", paramType = "query", required = true)})
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> disable(@RequestParam("id") Long id, @RequestParam("enable") Boolean enable) {
        logger.debug("===============启/停用质控模板=============");
        mdTemplateService.enableOrDisable(id, enable);
        return response();
    }

    @PreAuthorize("hasAuthority('MD_TEMPLATE_MANAGE')")
    @PutMapping(value = "/release")
    @ApiOperation(value = "发布质控模版", httpMethod = "PUT", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "模版ID", paramType = "query", required = true)
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Object> release(@RequestParam("id") Long id) {
        logger.debug("===============发布质控模板=============");
        mdTemplateService.release(id);
        return response();
    }

}
