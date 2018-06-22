package com.aek.ebey.qc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MsAssetsRecordResponse", description = "MsAssetsRecordResponse")
public class MsAssetsRecordResponse {
	/**
	 * 检测信息ID
	 */
	@ApiModelProperty(value = "检测信息ID")
	private Long infoId;
	/**
	 * 计量编号
	 */
	@ApiModelProperty(value = "计量编号")
	private String measureNum;

	/**
	 * 设备名称
	 */
	@ApiModelProperty(value = "设备名称")
	private String assetsName;
	
	/**
	 * 设备型号
	 */
	@ApiModelProperty(value = "设备型号")
	private String assetsSpec;
	
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value = "设备所在科室名称")
	private String assetsDeptName;
	/**
	 * 管理类别（1，非强制性计量设备 2，强制性计量设备）
	 */
	@ApiModelProperty(value = "管理类别（1，非强制性计量设备 2，强制性计量设备）")
	private Integer measureManageType;
	
	/**
	 * 报告编号
	 */
	@ApiModelProperty(value = "报告编号")
	private String checkReportNo;

	/**
	 * 负责人姓名
	 */
	@ApiModelProperty(value = "负责人姓名")
	private String chargeUserName;

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public String getMeasureNum() {
		return measureNum;
	}

	public void setMeasureNum(String measureNum) {
		this.measureNum = measureNum;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getAssetsSpec() {
		return assetsSpec;
	}

	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}

	public String getAssetsDeptName() {
		return assetsDeptName;
	}

	public void setAssetsDeptName(String assetsDeptName) {
		this.assetsDeptName = assetsDeptName;
	}

	public Integer getMeasureManageType() {
		return measureManageType;
	}

	public void setMeasureManageType(Integer measureManageType) {
		this.measureManageType = measureManageType;
	}

	public String getCheckReportNo() {
		return checkReportNo;
	}

	public void setCheckReportNo(String checkReportNo) {
		this.checkReportNo = checkReportNo;
	}

	public String getChargeUserName() {
		return chargeUserName;
	}

	public void setChargeUserName(String chargeUserName) {
		this.chargeUserName = chargeUserName;
	}
	

}
