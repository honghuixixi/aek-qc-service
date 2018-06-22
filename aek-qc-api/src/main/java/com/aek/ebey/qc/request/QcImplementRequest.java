package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public class QcImplementRequest {
	/**
	 * 关联巡检计划id
	 */
	private Long id;
	/**
	 * 实际开始日期
	 */
	private Date actualStartDate;
	/**
	 * 时间结束时间
	 */
	private Date actualEndDate;
	
	/**
	 * 设备列表
	 */
	private List<QcAssetsRequest> records;
	/**
	 * 使用情况
	 */
	private String condition;
	/**
	 * 问题及分析
	 */
	private String analysis;
	/**
	 * 建议
	 */
	private String suggestion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public List<QcAssetsRequest> getRecords() {
		return records;
	}
	public void setRecords(List<QcAssetsRequest> records) {
		this.records = records;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	
}
