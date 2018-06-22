package com.aek.ebey.qc.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模版项目返回实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value="质控模版项目返回实体类")
public class MdReportTemplateItemVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;
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
     * 选择类型(1=单选，2=多选)(input_type=选择)
     */
    @ApiModelProperty(value="选择类型(1=单选，2=多选)(input_type=选择)")
    private Integer selectType;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer sort;
    
    /**
     * 子文本项目集合
     */
    @ApiModelProperty(value="子文本项目集合")
    private List<MdReportTemplateChildItemVO> childItems;
    
    /**
     * 项目选项集合
     */
    @ApiModelProperty(value="项目选项集合")
    private List<MdReportTemplateItemOptionVO> childItemOptions;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public List<MdReportTemplateChildItemVO> getChildItems() {
		return childItems;
	}

	public void setChildItems(List<MdReportTemplateChildItemVO> childItems) {
		this.childItems = childItems;
	}

	public List<MdReportTemplateItemOptionVO> getChildItemOptions() {
		return childItemOptions;
	}

	public void setChildItemOptions(List<MdReportTemplateItemOptionVO> childItemOptions) {
		this.childItemOptions = childItemOptions;
	}

    
}