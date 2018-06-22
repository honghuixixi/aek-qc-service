package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 保养模板项目表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@ApiModel(value="保养模板项目表")
@TableName("mt_template_item")
public class MtTemplateItem extends BaseModel {

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
	 * 项目名称
	 */
    @ApiModelProperty(value="项目名称")
	@TableField(value="item_name")
	private String itemName;
	/**
	 * 删除标记(0=未删除，1=已删除)
	 */
    @ApiModelProperty(value="删除标记(0=未删除，1=已删除)")
	@TableField(value="del_flag")
	private Boolean delFlag;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value="创建时间")
	@TableField(value="create_time")
	private Date createTime;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
