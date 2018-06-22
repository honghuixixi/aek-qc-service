package com.aek.ebey.qc.model.bo;

import java.util.Date;


import io.swagger.annotations.ApiModelProperty;

public class MdSysTemplateItemBO {

	/**
	 * 系统项目ID
	 */
	 @ApiModelProperty(value="系统项目ID")
	private Long sourceItemId;
	/**
	 * 质控模板ID
	 */
	 @ApiModelProperty(value="质控模板ID")
	private Long reportTemplateId;
	/**
	 * 父项目ID
	 */
    @ApiModelProperty(value="父项目ID")
	private Long parentId;
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
	 * 创建人ID
	 */
    @ApiModelProperty(value="创建人ID")
	private Long createBy;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value="创建时间")
	private Date createTime;
	/**
	 * 更新人ID
	 */
    @ApiModelProperty(value="更新人ID")
	private Long updateBy;
	/**
	 * 更新时间
	 */
    @ApiModelProperty(value="更新时间")
	private Date updateTime;
	public Long getSourceItemId() {
		return sourceItemId;
	}
	public void setSourceItemId(Long sourceItemId) {
		this.sourceItemId = sourceItemId;
	}
	public Long getReportTemplateId() {
		return reportTemplateId;
	}
	public void setReportTemplateId(Long reportTemplateId) {
		this.reportTemplateId = reportTemplateId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
