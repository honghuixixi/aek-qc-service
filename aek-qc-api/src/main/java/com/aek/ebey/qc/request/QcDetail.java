package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class QcDetail {
	/**
	 * 下次巡检时间
	 */
	private Date nextDate;
	
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
	

	/**
	 * 验收人
	 */
	@ApiModelProperty(value = "验收人")
	private List<QcChargeRequest> checkMan;

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
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

	public List<QcChargeRequest> getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(List<QcChargeRequest> checkMan) {
		this.checkMan = checkMan;
	}
	
	

}
