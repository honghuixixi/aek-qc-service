package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@ApiModel(value = "QcImplementSubmitRequestx")
public class QcImplementSubmitRequestx {
	/**
	 * 关联巡检计划id
	 */
	private Long id;
	/**
	 * 下次巡检日期（时间戳）
	 */
	private Date nexDate;
	
	/**
	 * 时间结束时间
	 */
	private Date actualEndDate;
	
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
	 * 下次实施人
	 */
	@ApiModelProperty(value = "下次实施人")
	private QcChargeRequest nextChargeMan;
	
	/**
	 * 计划验收人
	 */
	@ApiModelProperty(value = "计划验收人")
	private List<QcChargeRequest> checkMan;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getNexDate() {
		return nexDate;
	}
	public void setNexDate(Date nexDate) {
		this.nexDate = nexDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
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
	public QcChargeRequest getNextChargeMan() {
		return nextChargeMan;
	}
	public void setNextChargeMan(QcChargeRequest nextChargeMan) {
		this.nextChargeMan = nextChargeMan;
	}
	public List<QcChargeRequest> getCheckMan() {
		return checkMan;
	}
	public void setCheckMan(List<QcChargeRequest> checkMan) {
		this.checkMan = checkMan;
	}
	
	
}
