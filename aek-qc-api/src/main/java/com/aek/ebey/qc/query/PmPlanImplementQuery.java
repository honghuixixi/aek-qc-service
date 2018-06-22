package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.PmPlanImplementResponse;

public class PmPlanImplementQuery extends LiuHuiFrontPage<PmPlanImplementResponse>{

	/**
	 * 设备名称/设备编号
	 */
	private String keyword;
	
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
}
