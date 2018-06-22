package com.aek.ebey.qc.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实施列表返回实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtPlanImplementResponse", description = "MtPlanImplementResponse")
public class MtPlanImplementResponse {

	/**
	 * 实施ID
	 */
	@ApiModelProperty(value="实施ID")
	private Long id;
	
	/**
	 * 资产ID
	 */
	@ApiModelProperty(value="资产ID")
	private Long assetsId;
	/**
	 * 资产编号
	 */
	@ApiModelProperty(value="资产编号")
	private String assetsNum;
	/**
	 * 资产院内编码
	 */
	@ApiModelProperty(value="资产院内编码")
	private String hospitalAssetsNum;
	/**
	 * 资产名称
	 */
	@ApiModelProperty(value="资产名称")
	private String assetsName;
	/**
	 * 设备型号
	 */
	@ApiModelProperty(value="设备型号")
	private String assetsSpec;
	/**
	 * 设备所在科室ID
	 */
	@ApiModelProperty(value="设备所在科室ID")
	private Long assetsDeptId;
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value="设备所在科室名称")
	private String assetsDeptName;
	
	/**
	 * 保养频率(1=每天，2=每周)
	 */
	@ApiModelProperty(value="保养频率(1=每天，2=每周)")
	private Integer rate;
	
	/**
	 * 专管人
	 */
	@ApiModelProperty(value="专管人")
	private String administrator;
	
	/**
	 * 上次实施时间
	 */
	@ApiModelProperty(value="上次实施时间")
	private Date lastImplementTime;
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	private Date nextImplementTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}
	public String getAssetsNum() {
		return assetsNum;
	}
	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}
	public String getHospitalAssetsNum() {
		return hospitalAssetsNum;
	}
	public void setHospitalAssetsNum(String hospitalAssetsNum) {
		this.hospitalAssetsNum = hospitalAssetsNum;
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
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	public Date getLastImplementTime() {
		return lastImplementTime;
	}
	public void setLastImplementTime(Date lastImplementTime) {
		this.lastImplementTime = lastImplementTime;
	}
	public Date getNextImplementTime() {
		return nextImplementTime;
	}
	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}
	
}
