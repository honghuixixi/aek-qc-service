package com.aek.ebey.qc.request;

public class QcPlanTotal {

	/**
	 * 机构ID
	 */
	private Long tenantId;
	
	
	
	/**
	 * 年度PM计划总数（累加值）
	 */
	private  Long total;



	public Long getTenantId() {
		return tenantId;
	}



	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}



	public Long getTotal() {
		return total;
	}



	public void setTotal(Long total) {
		this.total = total;
	}
	
}
