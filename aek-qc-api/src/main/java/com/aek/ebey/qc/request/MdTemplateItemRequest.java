package com.aek.ebey.qc.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模板项目请求实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value = "MdTemplateItemRequest", description = "质控模板项目数据")
public class MdTemplateItemRequest {

    @ApiModelProperty(value="项目ID")
    @NotNull(groups = Edit.class, message = "MD_007")
    private Long id;
    
    @ApiModelProperty(value="项目名称")
    @NotNull(groups = {Add.class,Edit.class}, message = "MD_005")
    @Length(max = 40, groups = {Add.class,Edit.class}, message = "MD_006")
    private String name;
    
    @ApiModelProperty(value="项目备注")
    @Length(max = 12, groups = {Add.class,Edit.class}, message = "MD_008")
    private String remarks;
    
    @ApiModelProperty(value="模板ID")
    @NotNull(groups = {Add.class,Edit.class}, message = "MD_003")
    private Long templateId;
    
    @ApiModelProperty(value="项目类型(1=文本，2=选择)")
    private Integer inputType;
    
    @ApiModelProperty(value="列数")
    private Integer columns;
    
    @ApiModelProperty(value="选择类型(1=单选，2=多选)(input_type=选择)")
    private Integer selectType;
    
    @ApiModelProperty(value="子项目集合")
    private List<MdTemplateChildItemRequest> childItems;
    
    @ApiModelProperty(value="子项目备注集合")
    private List<MdTemplateChildItemRequest> childItemRemarks;
    
    @ApiModelProperty(value="选项集合")
    private List<MdTemplateItemOptionRequest> childItemOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public List<MdTemplateChildItemRequest> getChildItems() {
        return childItems;
    }

    public void setChildItems(List<MdTemplateChildItemRequest> childItems) {
        this.childItems = childItems;
    }

    public List<MdTemplateChildItemRequest> getChildItemRemarks() {
        return childItemRemarks;
    }

    public void setChildItemRemarks(List<MdTemplateChildItemRequest> childItemRemarks) {
        this.childItemRemarks = childItemRemarks;
    }

    public List<MdTemplateItemOptionRequest> getChildItemOptions() {
        return childItemOptions;
    }

    public void setChildItemOptions(List<MdTemplateItemOptionRequest> childItemOptions) {
        this.childItemOptions = childItemOptions;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
    
}
