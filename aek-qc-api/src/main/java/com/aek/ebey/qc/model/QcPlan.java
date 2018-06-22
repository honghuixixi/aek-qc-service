package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 巡检计划
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@ApiModel(value = "QcPlan")
@TableName("qc_plan")
public class QcPlan extends BaseModel {

    private static final long serialVersionUID = 1L;
	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	@ApiModelProperty(value = "机构ID")
	private Long tenantId;
	/**
	 * 机构名称
	 */
	@TableField(value="tenant_name")
	@ApiModelProperty(value = "机构名称")
	private String tenantName;
	/**
	 * 巡检名称
	 */
	@ApiModelProperty(value = "巡检名称")
	private String name;
	/**
	 * 巡检类型(1，按科室巡检）
	 */
	@TableField(value="plan_type")
	@ApiModelProperty(value = "巡检类型(1，按科室巡检）")
	private Integer planType;
	/**
	 * 计划编号
	 */
	@TableField(value="plan_no")
	@ApiModelProperty(value = "计划编号")
	private String planNo;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 关联模版id
	 */
	@TableField(value="template_id")
	@ApiModelProperty(value = "关联模版id")
	private Long templateId;
	/**
	 * 巡检周期
	 */
	@TableField(value="plan_cycle")
	@ApiModelProperty(value = "巡检周期")
	private Integer planCycle;
	/**
	 * 巡检周期类型(1，月 2，天)
	 */
	@TableField(value="cycle_type")
	@ApiModelProperty(value = "巡检周期类型(1，月 2，天)")
	private Integer cycleType;
	/**
	 * 首次巡检开始时间
	 */
	@TableField(value="first_time")
	@ApiModelProperty(value = "首次巡检开始时间")
	private Date firstTime;
	/**
	 * 上次巡检时间
	 */
	@TableField(value="pre_time")
	@ApiModelProperty(value = "上次巡检时间")
	private Date preTime;
	/**
	 * 下次巡检时间
	 */
	@TableField(value="next_time")
	@ApiModelProperty(value = "下次巡检时间")
	private Date nextTime;
	/**
	 * 负责人ID
	 */
	@TableField(value="charge_id")
	@ApiModelProperty(value = "负责人ID")
	private Long chargeId;
	/**
	 * 负责人姓名
	 */
	@TableField(value="charge_name")
	@ApiModelProperty(value = "负责人姓名")
	private String chargeName;
	/**
	 * 状态 1:启用、2:停用
	 */
	@ApiModelProperty(value = "状态 1:启用、2:停用")
	private Integer status;
	/**
	 * 巡检状态 1:待巡检、2:巡检中
	 */
	@TableField(value="check_status")
	@ApiModelProperty(value = "巡检状态 1:待巡检、2:巡检中")
	private Integer checkStatus;
	/**
	 * 作废标记，0：启用，1：删除
	 */
	@TableField(value="del_flag")
	@ApiModelProperty(value = "作废标记，0：启用，1：删除")
	private Boolean delFlag;
	/**
	 * 是否可以发送消息（1，是 2，不）
	 */
	@TableField(value="msg_status")
	@ApiModelProperty(value = "是否可以发送消息（1，是 2，不）")
	private Integer msgStatus;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Integer getPlanCycle() {
		return planCycle;
	}

	public void setPlanCycle(Integer planCycle) {
		this.planCycle = planCycle;
	}

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getPreTime() {
		return preTime;
	}

	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}


	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}

}
