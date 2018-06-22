package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 质控检测系统模板子项目表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@ApiModel(value="质控模版项目实体类")
@TableName("md_template_item")
public class MdTemplateItem extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
    @ApiModelProperty(value="主键ID")
	private Long id;
	/**
	 * 模板ID
	 */
    @ApiModelProperty(value="模板ID")
	@TableField(value="template_id")
	private Long templateId;
	/**
	 * 父项目ID
	 */
    @ApiModelProperty(value="父项目ID")
	@TableField(value="parent_id")
	private Long parentId;
	/**
	 * 项目名称
	 */
    @ApiModelProperty(value="项目名称")
	@TableField(value="name")
	private String name;
	/**
	 * 项目备注
	 */
    @ApiModelProperty(value="项目备注")
    @TableField(value="remarks")
	private String remarks;
	/**
	 * 输入类型(1=文本，2=选择)
	 */
    @ApiModelProperty(value="输入类型(1=文本，2=选择)")
	@TableField(value="input_type")
	private Integer inputType;
	/**
	 * 列数(type=子项目,input_type=文本)
	 */
    @ApiModelProperty(value="列数(type=子项目,input_type=文本)")
    @TableField(value="columns")
	private Integer columns;
	/**
	 * 跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)
	 */
    @ApiModelProperty(value="跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)")
	@TableField(value="cross_row")
	private Boolean crossRow;
	/**
	 * 项目类型(1=子项目,2=子项目备注)
	 */
    @ApiModelProperty(value="项目类型(1=子项目,2=子项目备注)")
    @TableField(value="type")
	private Integer type;
	/**
	 * 选择类型(1=单选，2=多选)(input_type=选择)
	 */
    @ApiModelProperty(value="选择类型(1=单选，2=多选)(input_type=选择)")
	@TableField(value="select_type")
	private Integer selectType;
	/**
	 * 排序
	 */
    @ApiModelProperty(value="排序")
    @TableField(value="sort")
	private Integer sort;
	/**
	 * 创建人ID
	 */
    @ApiModelProperty(value="创建人ID")
	@TableField(value="create_by")
	private Long createBy;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value="创建时间")
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 更新人ID
	 */
    @ApiModelProperty(value="更新人ID")
	@TableField(value="update_by")
	private Long updateBy;
	/**
	 * 更新时间
	 */
    @ApiModelProperty(value="更新时间")
	@TableField(value="update_time")
	private Date updateTime;


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
