package com.aek.ebey.qc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MsChecRecordResponse", description = "MsChecRecordResponse")
public class MsChecRecordResponse {
	
	@ApiModelProperty(value="MsCheckHistory")
	private  MsCheckHistory msCheckHistory;
	
	@ApiModelProperty(value="MsCheckResultInfo")
	private  MsCheckResultInfo msCheckResultInfo;
	
	private String tenantName;

	public MsCheckHistory getMsCheckHistory() {
		return msCheckHistory;
	}

	public void setMsCheckHistory(MsCheckHistory msCheckHistory) {
		this.msCheckHistory = msCheckHistory;
	}

	public MsCheckResultInfo getMsCheckResultInfo() {
		return msCheckResultInfo;
	}

	public void setMsCheckResultInfo(MsCheckResultInfo msCheckResultInfo) {
		this.msCheckResultInfo = msCheckResultInfo;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
	

	

}
