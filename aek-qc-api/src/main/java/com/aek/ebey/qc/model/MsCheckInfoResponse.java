package com.aek.ebey.qc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MsCheckInfoResponse", description = "MsCheckInfoResponse")
public class MsCheckInfoResponse {
	
	@ApiModelProperty(value="提示语")
	private String attention;
	
	private  MsCheck msCheck;
	
	private  MsCheckStorage msCheckStorage;

	public MsCheck getMsCheck() {
		return msCheck;
	}

	public void setMsCheck(MsCheck msCheck) {
		this.msCheck = msCheck;
	}

	public MsCheckStorage getMsCheckStorage() {
		return msCheckStorage;
	}

	public void setMsCheckStorage(MsCheckStorage msCheckStorage) {
		this.msCheckStorage = msCheckStorage;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}
	
	
	
	

}
