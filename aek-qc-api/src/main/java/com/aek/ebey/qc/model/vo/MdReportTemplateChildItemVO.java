package com.aek.ebey.qc.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模版子文本项目返回实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value="质控模版子文本项目返回实体类")
public class MdReportTemplateChildItemVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 父项目ID
     */
    @ApiModelProperty(value="父项目ID")
    private Long parentId;
    /**
     * 模板ID
     */
    @ApiModelProperty(value="模板ID")
    private Long templateId;
    /**
     * 项目名称
     */
    @ApiModelProperty(value="项目名称")
    private String name;
    /**
     * 项目备注
     */
    @ApiModelProperty(value="项目备注")
    private String remarks;
    
    /**
     * 输入类型(1=文本，2=选择)
     */
    @ApiModelProperty(value="输入类型(1=文本，2=选择)")
    private Integer inputType;
    
    /**
     * 列数(type=子项目,input_type=文本)
     */
    @ApiModelProperty(value="列数(type=子项目,input_type=文本)")
    private Integer columns;
    /**
     * 跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)
     */
    @ApiModelProperty(value="跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)")
    private Boolean crossRow;
    /**
     * 项目类型(1=子项目,2=子项目备注)
     */
    @ApiModelProperty(value="项目类型(1=子项目,2=子项目备注)")
    private Integer type;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer sort;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Long getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Integer getInputType() {
        return inputType;
    }
    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }
    public Integer getColumns() {
        return columns;
    }
    public void setColumns(Integer columns) {
        this.columns = columns;
    }
    public Boolean getCrossRow() {
        return crossRow;
    }
    public void setCrossRow(Boolean crossRow) {
        this.crossRow = crossRow;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
}
