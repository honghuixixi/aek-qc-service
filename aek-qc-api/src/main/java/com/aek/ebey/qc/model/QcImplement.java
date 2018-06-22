package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@TableName("qc_implement")
public class QcImplement extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 关联巡检计划id
	 */
	@TableField(value="paln_id")
	private Long palnId;
	
	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 下次巡检时间
	 */
	@TableField(value="next_time")
	private Date nextTime;
	
	/**
	 * 报告编号
	 */
	@TableField(value="report_no")
	private String reportNo;
	
	
	
	/**
	 * 实际开始日期
	 */
	@TableField(value="actual_start_date")
	private Date actualStartDate;
	/**
	 * 时间结束时间
	 */
	@TableField(value="actual_end_date")
	private Date actualEndDate;
	
	/**
	 * 实施开始时间
	 */
	@TableField(value="create_date")
	private Date createDate;
	
	/**
	 * 计划巡检时间
	 */
	@TableField(value="plan_date")
	private Date planDate;
	
	
	/**
	 * 使用情况
	 */
	private String condition;
	/**
	 * 问题及分析
	 */
	private String analysis;
	/**
	 * 建议
	 */
	private String suggestion;

	/**
	 * 实施状态(1，实施中 2，已完成）
	 */
	private Integer status;
	
	/**
	 * 是否验收（1，待验收 2，已验收）
	 */
	@TableField(value="is_check")
	@ApiModelProperty(value = "是否验收（1，待验收 2，已验收）")
	private Integer isCheck;
	
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
	public Long getPalnId() {
		return palnId;
	}

	public void setPalnId(Long palnId) {
		this.palnId = palnId;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}


	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
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

}
