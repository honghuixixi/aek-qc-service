package com.aek.ebey.qc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="新建保养计划资产封装类")
public class MtAssets {

	@ApiModelProperty(value="资产id")
	private Long id;
	@ApiModelProperty(value="资产名称")
	private String assetsName;
	@ApiModelProperty(value="设备编号")
	private String assetsNum;
	@ApiModelProperty(value="院内编码")
	private String serialNum;	
	@ApiModelProperty(value="资产规格型号")
	private String assetsSpec;
	@ApiModelProperty(value="出厂编号")
	private String factoryNum;
	@ApiModelProperty(value="所在部门id")
	private Long deptId;
	@ApiModelProperty(value="所在部门名称")
	private String deptName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getAssetsNum() {
		return assetsNum;
	}
	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getAssetsSpec() {
		return assetsSpec;
	}
	public String getFactoryNum() {
		return factoryNum;
	}
	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
	}
	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
