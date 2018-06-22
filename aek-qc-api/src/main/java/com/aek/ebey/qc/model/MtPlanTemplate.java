package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 保养计划模板表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@TableName("mt_plan_template")
public class MtPlanTemplate extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 保养计划ID
	 */
	@TableField(value="plan_id")
	private Long planId;
	/**
	 * 模板库中模板ID
	 */
	@TableField(value="template_id")
	private Long templateId;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 模板类型(1=系统模板，2=自定义模板)
	 */
	private Integer type;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 启用标记(0=停用，1=启用)
	 */
	private Boolean enable;
	/**
	 * 删除标记(0=未删除，1=已删除)
	 */
	@TableField(value="del_flag")
	private Boolean delFlag;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

}
