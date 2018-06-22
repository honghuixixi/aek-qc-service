package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * qc验收人员表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@ApiModel(value = "QcPlanCheck")
@TableName("qc_plan_check")
public class QcPlanCheck extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	@ApiModelProperty(value = "机构ID")
	private Long tenantId;
	/**
	 * 关联巡检计划id
	 */
	@TableField(value="paln_id")
	@ApiModelProperty(value = "关联巡检计划id")
	private Long palnId;
	/**
	 * 关联实施ID
	 */
	@TableField(value="implement_id")
	@ApiModelProperty(value = "关联实施ID")
	private Long implementId;
	/**
	 * 验收人ID
	 */
	@TableField(value="user_id")
	@ApiModelProperty(value = "验收人ID")
	private Long userId;
	/**
	 * 验收人姓名
	 */
	@TableField(value="user_name")
	@ApiModelProperty(value = "验收人姓名")
	private String userName;
	/**
	 * 验收状态（1，待验收 2，已验收）
	 */
	@ApiModelProperty(value = "验收状态（1，待验收 2，已验收）")
	private Integer status=1;
	/**
	 * 创建人id
	 */
	@TableField(value="create_by")
	@ApiModelProperty(value = "创建人id")
	private Long createBy;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime=new Date();
	/**
	 * 更新人ID
	 */
	@TableField(value="update_by")
	@ApiModelProperty(value = "更新人ID")
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@TableField(value="update_time")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**
	 * 作废标记，0：启用，1：删除
	 */
	@TableField(value="del_flag")
	@ApiModelProperty(value = "作废标记，0：启用，1：删除")
	private Boolean delFlag;


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

	public Long getPalnId() {
		return palnId;
	}

	public void setPalnId(Long palnId) {
		this.palnId = palnId;
	}

	public Long getImplementId() {
		return implementId;
	}

	public void setImplementId(Long implementId) {
		this.implementId = implementId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}


}
