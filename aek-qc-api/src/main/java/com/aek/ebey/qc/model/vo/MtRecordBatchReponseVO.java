package com.aek.ebey.qc.model.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class MtRecordBatchReponseVO { 

	@ApiModelProperty(value="计划所在机构名称")
	private String tenantName;
	@ApiModelProperty(value="资产名称")
	private String assetsName;
	@ApiModelProperty(value="设备编号")
	private String assetsNum;
	@ApiModelProperty(value="资产规格型号")
	private String assetsSpec;
	@ApiModelProperty(value="所在部门名称")
	private String deptName;
	@ApiModelProperty(value="保养频率")
	private String rate;
	@ApiModelProperty(value="开始时间")
	private Date startDate;
	@ApiModelProperty(value="结束时间")
	private Date endDate;
	@ApiModelProperty(value="保养实施明细")
	private List<MtImplementResultVO> mtImplementResultVos;

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<MtImplementResultVO> getMtImplementResultVos() {
		return mtImplementResultVos;
	}

	public void setMtImplementResultVos(List<MtImplementResultVO> mtImplementResultVos) {
		this.mtImplementResultVos = mtImplementResultVos;
	}
	
}
