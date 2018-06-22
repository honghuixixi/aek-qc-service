package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.PmImplementResponse;

import io.swagger.annotations.ApiModelProperty;

public class PmImplementQuery extends LiuHuiFrontPage<PmImplementResponse>{
	
	/**
	 * 开始日期
	 */
	private String startDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	
	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 设备名称/设备编号
	 */
	private String keyword;
	
	/**
	 * 设备部门id
	 */
	private Long departmentId;
	
	/**
	 * 
	 * 验收状态（1，待验收 2，已验收）
	 */
	private Integer status;
	
	/**
	 * 
	 * 排序字段（1，2）
	 */
	private Integer orderStatus;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	
}
