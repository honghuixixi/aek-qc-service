package com.aek.ebey.qc.request;

public class CheckDetail {
	/**
	 * 1：可以实施；2：不可以实施
	 */
	private Integer status;
	
	/**
	 * 实施信息
	 */
	private String msg;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
 
}
