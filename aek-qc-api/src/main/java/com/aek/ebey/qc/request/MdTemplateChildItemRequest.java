package com.aek.ebey.qc.request;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 质控检测系统模板子项目表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@ApiModel(value="质控模版子项目实体类")
public class MdTemplateChildItemRequest extends BaseModel {

    private static final long serialVersionUID = 1L;

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
	 * 项目名称
	 */
    @ApiModelProperty(value="项目名称")
	private String name;
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
	 * 选择类型(1=单选，2=多选)(input_type=选择)
	 */
    @ApiModelProperty(value="选择类型(1=单选，2=多选)(input_type=选择)")
	private Integer selectType;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    
}
