package com.aek.ebey.qc.request;

import java.util.Date;

public class QcOverView {
	
	/**
	 * 机构ID
	 */
	private Long tenantId;
	
	/**
	 * 统计日期（年月日）
	 */
	private Date countDate;
	
	/**
	 * 年度巡检总数(台/次)（累加值）
	 */
	private  Long inspectionTotalYear=0L;
	
	/**
	 * 年度巡检计划总数（累加值）
	 */
	private  Long inspectionPlanTotalYear=0L;
	
	/**
	 * 年度巡检执行率（百分比）
	 */
	private  Double inspectionRateYear=100D;
	
	/**
	 * 统计时间
	 */
	private Date countTime;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCountDate() {
		return countDate;
	}

	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}

	public Long getInspectionTotalYear() {
		return inspectionTotalYear;
	}

	public void setInspectionTotalYear(Long inspectionTotalYear) {
		this.inspectionTotalYear = inspectionTotalYear;
	}

	public Long getInspectionPlanTotalYear() {
		return inspectionPlanTotalYear;
	}

	public void setInspectionPlanTotalYear(Long inspectionPlanTotalYear) {
		this.inspectionPlanTotalYear = inspectionPlanTotalYear;
	}

	public Double getInspectionRateYear() {
		return inspectionRateYear;
	}

	public void setInspectionRateYear(Double inspectionRateYear) {
		this.inspectionRateYear = inspectionRateYear;
	}

	public Date getCountTime() {
		return countTime;
	}

	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}

	
}
