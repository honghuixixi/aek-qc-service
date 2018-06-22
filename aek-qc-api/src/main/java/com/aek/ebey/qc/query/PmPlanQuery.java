package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.PmPlanResponse;

public class PmPlanQuery extends LiuHuiFrontPage<PmPlanResponse>{

	/**
	 * 设备名称/设备编号
	 */
	private String keyword;
	
	/**
	 * 巡检状态    状态 1:启用、2:停用
	 */
	private int status;
	
	/**
	 * 设备部门id
	 */
	private Long departmentId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
}
