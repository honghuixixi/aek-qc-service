package com.aek.ebey.qc.request;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class EditMsAssets {
	/**
	 * ID
	 */
    @ApiModelProperty(value="ID")
	private Long id;
	/**
	 * 管理类别（1，非强制性计量设备 2，强制性计量设备）
	 */
    @ApiModelProperty(value="管理类别（1，非强制性计量设备 2，强制性计量设备）")
	private Integer measureManageType;
    
    /**
	 * 计量类别（1，A类 2，B类 3，C类）
	 */
    @ApiModelProperty(value="计量类别（1，A类 2，B类 3，C类）")
	private Integer measureType;
    
	/**
	 * 计量分类
	 */
    @ApiModelProperty(value="计量分类")
	private Integer measureCategory;
	/**
	 * 计量周期(单位月)
	 */
    @ApiModelProperty(value="计量周期(单位月)")
	private Integer measureCycle;
	/**
	 * 下次检定日期
	 */
    @ApiModelProperty(value="下次检定日期")
	private Date nextCheckTime;
	/**
	 * 检定方式（1，院内 2，外包）
	 */
    @ApiModelProperty(value="检定方式（1，院内 2，外包）")
	private Integer checkMode;
	/**
	 * 负责人ID
	 */
    @ApiModelProperty(value="负责人ID")
	private Long chargeUserId;
	/**
	 * 负责人姓名
	 */
    @ApiModelProperty(value="负责人姓名")
	private String chargeUserName;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String remarks;
	/**
	 * 状态（1，待完善 2，已完善）
	 */
    @ApiModelProperty(value="状态（1，待完善 2，已完善）")
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getMeasureManageType() {
		return measureManageType;
	}
	public void setMeasureManageType(Integer measureManageType) {
		this.measureManageType = measureManageType;
	}
	public Integer getMeasureType() {
		return measureType;
	}
	public void setMeasureType(Integer measureType) {
		this.measureType = measureType;
	}
	public Integer getMeasureCategory() {
		return measureCategory;
	}
	public void setMeasureCategory(Integer measureCategory) {
		this.measureCategory = measureCategory;
	}
	public Integer getMeasureCycle() {
		return measureCycle;
	}
	public void setMeasureCycle(Integer measureCycle) {
		this.measureCycle = measureCycle;
	}
	public Date getNextCheckTime() {
		return nextCheckTime;
	}
	public void setNextCheckTime(Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}
	public Integer getCheckMode() {
		return checkMode;
	}
	public void setCheckMode(Integer checkMode) {
		this.checkMode = checkMode;
	}
	public Long getChargeUserId() {
		return chargeUserId;
	}
	public void setChargeUserId(Long chargeUserId) {
		this.chargeUserId = chargeUserId;
	}
	public String getChargeUserName() {
		return chargeUserName;
	}
	public void setChargeUserName(String chargeUserName) {
		this.chargeUserName = chargeUserName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

    
}
