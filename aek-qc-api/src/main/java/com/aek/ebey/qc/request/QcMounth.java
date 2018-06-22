package com.aek.ebey.qc.request;

import java.util.Date;

public class QcMounth {
	
	/**
	 * 机构ID
	 */
	private Long tenantId;
	
	/**
	 * 统计日期（年月）
	 */
	private String countMonth;
	
	/**
	 * PM执行设备总数（台/次）（累加值）
	 */
	private  Long inspectionAssetsTotal=0L;
	
	/**
	 * 统计时间
	 */
	private  Date countTime;
	
	/**
	 * 更新时间
	 */
	private  Date updateTime;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getCountMonth() {
		return countMonth;
	}

	public void setCountMonth(String countMonth) {
		this.countMonth = countMonth;
	}

	public Long getInspectionAssetsTotal() {
		return inspectionAssetsTotal;
	}

	public void setInspectionAssetsTotal(Long inspectionAssetsTotal) {
		this.inspectionAssetsTotal = inspectionAssetsTotal;
	}

	public Date getCountTime() {
		return countTime;
	}

	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
}
