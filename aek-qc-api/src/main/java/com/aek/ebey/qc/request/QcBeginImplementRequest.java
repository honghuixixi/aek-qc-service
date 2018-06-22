package com.aek.ebey.qc.request;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public class QcBeginImplementRequest {
	/**
	 * 关联巡检计划id
	 */
	private Long id;
	/**
	 * 答案结果
	 */
	private Integer resultStatus;
	
	/**
	 * 答案
	 */
	private String answers;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
}
