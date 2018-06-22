package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 质控报告模板子项目表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@TableName("md_report_template_item")
public class MdReportTemplateItem extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * copyID
	 */
	@TableField(exist=false)
	private Long copy;
	/**
	 * 系统项目ID
	 */
	@TableField(value="source_item_id")
	private Long sourceItemId;
	/**
	 * 质控模板ID
	 */
	@TableField(value="report_template_id")
	private Long reportTemplateId;
	/**
	 * 父项目ID
	 */
	@TableField(value="parent_id")
	private Long parentId;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 项目备注
	 */
	private String remarks;
	/**
	 * 输入类型(1=文本，2=选择)
	 */
	@TableField(value="input_type")
	private Integer inputType;
	/**
	 * 列数(type=子项目,input_type=文本)
	 */
	private Integer columns;
	/**
	 * 跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)
	 */
	@TableField(value="cross_row")
	private Boolean crossRow;
	/**
	 * 项目类型(1=子项目,2=子项目备注)
	 */
	private Integer type;
	/**
	 * 选择类型(1=单选，2=多选)(input_type=选择)
	 */
	@TableField(value="select_type")
	private Integer selectType;
	/**
	 * 创建人ID
	 */
	@TableField(value="create_by")
	private Long createBy;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 更新人ID
	 */
	@TableField(value="update_by")
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@TableField(value="update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Long getCopy() {
		return copy;
	}

	public void setCopy(Long copy) {
		this.copy = copy;
	}

}
