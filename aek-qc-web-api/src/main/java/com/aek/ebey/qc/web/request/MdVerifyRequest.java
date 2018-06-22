package com.aek.ebey.qc.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("质控单审核请求类")
public class MdVerifyRequest {

	@ApiModelProperty("质控单id")
	private Long id;
	@ApiModelProperty("审核结果(0=审核未通过、1=审核通过)")
	private Integer verifyResult;
	@ApiModelProperty("审核备注")
	private String verifyRemark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVerifyResult() {
		return verifyResult;
	}
	public void setVerifyResult(Integer verifyResult) {
		this.verifyResult = verifyResult;
	}
	public String getVerifyRemark() {
		return verifyRemark;
	}
	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
	
}
