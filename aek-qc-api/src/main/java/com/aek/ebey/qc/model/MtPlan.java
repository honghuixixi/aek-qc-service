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
 * 保养计划
 *
 * @author Honghui
 * @since 2018-03-21
 */
@ApiModel(value="保养计划")
@TableName("mt_plan")
public class MtPlan extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 机构ID
	 */
	@ApiModelProperty(value="机构ID")
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 机构名称
	 */
	@ApiModelProperty(value="机构名称")
	@TableField(value="tenant_name")
	private String tenantName;
	/**
	 * 计划模板ID
	 */
	@ApiModelProperty(value="计划模板ID")
	@TableField(value="plan_template_id")
	private Long planTemplateId;
	/**
	 * 模板库模板ID
	 */
	@ApiModelProperty(value="模板库模板ID")
	@TableField(value="template_id")
	private Long templateId;
	/**
	 * 保养频率(1=每天，2=每周)
	 */
	@ApiModelProperty(value="保养频率(1=每天，2=每周)")
	private Integer rate;
	/**
	 * 专管人
	 */
	@ApiModelProperty(value="专管人")
	private String administrator;
	/**
	 * 上次实施时间
	 */
	@ApiModelProperty(value="上次实施时间")
	@TableField(value="last_implement_time")
	private Date lastImplementTime;
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	@TableField(value="next_implement_time")
	private Date nextImplementTime;
	/**
	 * 启用状态(0=停用，1=启用)
	 */
	@ApiModelProperty(value="启用状态(0=停用，1=启用)")
	private Boolean enable;
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public Long getPlanTemplateId() {
		return planTemplateId;
	}

	public void setPlanTemplateId(Long planTemplateId) {
		this.planTemplateId = planTemplateId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public Date getLastImplementTime() {
		return lastImplementTime;
	}

	public void setLastImplementTime(Date lastImplementTime) {
		this.lastImplementTime = lastImplementTime;
	}

	public Date getNextImplementTime() {
		return nextImplementTime;
	}

	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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
