package com.aek.ebey.qc.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("质控单分页输出类")
public class MdReportPageVO {

	@ApiModelProperty("质控单据id")
	private Long id;
	@ApiModelProperty("质控检测单号")
	private String mdNum;
	@ApiModelProperty("质控检测档案号")
	private String mdArchiveNum;
	@ApiModelProperty("设备编号")
	private String assetsNum;
	@ApiModelProperty("设备名称")
	private String assetsName;
	@ApiModelProperty("设备规格型号")
	private String assetsSpec;
	@ApiModelProperty("设备所在部门id")
	private Long deptId;
	@ApiModelProperty("设备所在部门名称")
	private String deptName;
	@ApiModelProperty("检测人id")
	private Long checkBy;
	@ApiModelProperty("检测人")
	private String checkName;
	@ApiModelProperty("检测日期")
	private Date checkTime;
	@ApiModelProperty("审核人id")
	private Long verifyBy;
	@ApiModelProperty("审核人")
	private String verifyName;
	@ApiModelProperty("审核日期")
	private Date verifyTime;
	@ApiModelProperty("质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)")
	private Integer status;
	@ApiModelProperty("质检单状态显示")
	private String statusStr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMdNum() {
		return mdNum;
	}
	public void setMdNum(String mdNum) {
		this.mdNum = mdNum;
	}
	public String getAssetsNum() {
		return assetsNum;
	}
	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
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
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public Long getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(Long checkBy) {
		this.checkBy = checkBy;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public Long getVerifyBy() {
		return verifyBy;
	}
	public void setVerifyBy(Long verifyBy) {
		this.verifyBy = verifyBy;
	}
	public String getVerifyName() {
		return verifyName;
	}
	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}
	public String getMdArchiveNum() {
		return mdArchiveNum;
	}
	public void setMdArchiveNum(String mdArchiveNum) {
		this.mdArchiveNum = mdArchiveNum;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	
}
