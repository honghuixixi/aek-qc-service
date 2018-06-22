package com.aek.ebey.qc.request;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 质控模板项目选项表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@ApiModel(value="质控模版项目选项")
@TableName("md_template_item_option")
public class MdTemplateItemOptionRequest extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
    @ApiModelProperty(value="主键ID")
	private Long id;
	/**
	 * 项目ID
	 */
    @ApiModelProperty(value="项目ID")
	private Long itemId;
	/**
	 * 项目名称
	 */
    @ApiModelProperty(value="项目名称")
	private String name;
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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
