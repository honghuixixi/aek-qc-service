package com.aek.ebey.qc.request;

import java.util.Date;

public class PmOverView {
	
	/**
	 * 机构ID
	 */
	private Long tenantId;
	
	/**
	 * 统计日期（年月日）
	 */
	private Date countDate;
	
	/**
	 * 年度PM实施总数（累加值）
	 */
	private  Long pmImplementTotalYear=0L;
	
	/**
	 * 年度PM计划总数（累加值）
	 */
	private  Long pmPlanTotalYear=0L;
	
	/**
	 * 年度PM执行率(百分比)
	 */
	private  Double pmRateYear=100D;
	
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

	public Long getPmImplementTotalYear() {
		return pmImplementTotalYear;
	}

	public void setPmImplementTotalYear(Long pmImplementTotalYear) {
		this.pmImplementTotalYear = pmImplementTotalYear;
	}

	public Long getPmPlanTotalYear() {
		return pmPlanTotalYear;
	}

	public void setPmPlanTotalYear(Long pmPlanTotalYear) {
		this.pmPlanTotalYear = pmPlanTotalYear;
	}

	public Double getPmRateYear() {
		return pmRateYear;
	}

	public void setPmRateYear(Double pmRateYear) {
		this.pmRateYear = pmRateYear;
	}

	public Date getCountTime() {
		return countTime;
	}

	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}
	
	

}
