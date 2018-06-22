package com.aek.ebey.qc.request;

import com.aek.ebey.qc.model.MsAssets;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "MsAssetsResponse", description = "MsAssetsResponse")
public class MsAssetsResponse extends MsAssets{
	
	@ApiModelProperty(value="提示语")
	private String attention;

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

}
