package com.aek.ebey.qc.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="保养计划分页查询返回对象")
public class MtPlanResponseVO {
 
	@ApiModelProperty(value="计划id")
	private Long id;
	@ApiModelProperty(value="保养频率(1=每天，2=每周)显示")
	private String rate;
	@ApiModelProperty(value="下次实施时间")
	private Date nextImplementTime;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	@ApiModelProperty(value="专管人")
	private String administrator;
	@ApiModelProperty(value="停启用")
	private String enable;
	@ApiModelProperty(value="设备名称")
	private String assetsName;
	@ApiModelProperty(value="设备编号")
	private String assetsNum;
	@ApiModelProperty(value="设备规格")
	private String assetsSpec;
	@ApiModelProperty(value="设备出厂编号")
	private String factoryNum;
	@ApiModelProperty(value="设备所在部门id")
	private Long assetsDeptId;
	@ApiModelProperty(value="设备所在部门名称")
	private String assetsDeptName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Date getNextImplementTime() {
		return nextImplementTime;
	}
	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
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
	public String getAssetsSpec() {
		return assetsSpec;
	}
	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}
	public String getFactoryNum() {
		return factoryNum;
	}
	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
	}
	
	public Long getAssetsDeptId() {
		return assetsDeptId;
	}
	public void setAssetsDeptId(Long assetsDeptId) {
		this.assetsDeptId = assetsDeptId;
	}
	public String getAssetsDeptName() {
		return assetsDeptName;
	}
	public void setAssetsDeptName(String assetsDeptName) {
		this.assetsDeptName = assetsDeptName;
	}
	
}
