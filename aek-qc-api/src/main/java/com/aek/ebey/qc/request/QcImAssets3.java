package com.aek.ebey.qc.request;

public class QcImAssets3 {
	/**
	 * 计划id
	 */
	private Long id;
	
	/**
	 * 设备id
	 */
	private Long assetId;
	
	
	
	/**
	 * 答案完整状态
	 */
	private Integer status;
	
	
	/**
	 * 答案
	 */
	private String answers;
	
	
	private Integer imAtatus;
	
	/**
	 * 备注
	 */
	private String remarks;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getAssetId() {
		return assetId;
	}


	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getAnswers() {
		return answers;
	}


	public void setAnswers(String answers) {
		this.answers = answers;
	}


	public Integer getImAtatus() {
		return imAtatus;
	}


	public void setImAtatus(Integer imAtatus) {
		this.imAtatus = imAtatus;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
