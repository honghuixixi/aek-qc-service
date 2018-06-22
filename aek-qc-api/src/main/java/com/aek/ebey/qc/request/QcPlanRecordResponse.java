package com.aek.ebey.qc.request;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class QcPlanRecordResponse{
	/**
	 * id
	 */
	
	private Long id;
	
	
	
	/**
	 * 计划巡检日期（时间戳）
	 */
	private Date planDate;
	
	/**
	 * 实际开始日期（时间戳）
	 */
	private Date actualStartDate;
	
	/**
	 * 实际结束日期（时间戳）
	 */
	private Date actualEndDate;
	
	
	/**
	 * 设备数
	 */
	private Integer equipments;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getPlanDate() {
		return planDate;
	}


	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
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


	public Integer getEquipments() {
		return equipments;
	}


	public void setEquipments(Integer equipments) {
		this.equipments = equipments;
	}
	
	
	
}
