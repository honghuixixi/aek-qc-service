package com.aek.ebey.qc.request;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public class QcAssetsRequest {
	/**
	 * 设备id
	 */
	private Long id;
	
	/**
	 * 答案
	 */
	private String answers;
	
	/**
	 * 答案结果(1，不全 ， 2 全）
	 */
	private Integer status;
	
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

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	

}
