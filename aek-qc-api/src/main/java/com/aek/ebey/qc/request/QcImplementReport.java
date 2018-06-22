package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "QcImplementReport")
public class QcImplementReport extends QcImplementSheet{
	

	/**
	 * 报告编号
	 */
	private String reportNo;
	
	/**
	 * 开始日期
	 */
	private Date firstDate;
	
	
	/**
	 * 结束日期
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
	private List<String> checkMan;
	
	
	/**
	 * 已验收人
	 */
	@ApiModelProperty(value = "已验收人")
	private List<String> checkedMan;
	
	/**
	 * 状态(1，待验收 )
	 */
	@ApiModelProperty(value = "状态(1，待验收 )")
	private Integer status;
	
	/**
	 * 巡检名称
	 */
	@ApiModelProperty(value = "巡检名称")
	private String name;
	/**
	 * 巡检类型(1，按科室巡检）
	 */
	@ApiModelProperty(value = "巡检类型(1，按科室巡检）")
	private Integer planType;
	
	/**
	 * 巡检周期
	 */
	@ApiModelProperty(value = "巡检周期")
	private Integer planCycle;
	/**
	 * 巡检周期类型(1，月 2，天)
	 */
	@ApiModelProperty(value = "巡检周期类型(1，月 2，天)")
	private Integer cycleType;
	
	/**
	 * 提交日期
	 */
	@ApiModelProperty(value="提交日期")
	private Date endDate;

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

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

	public List<String> getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(List<String> checkMan) {
		this.checkMan = checkMan;
	}

	public List<String> getCheckedMan() {
		return checkedMan;
	}

	public void setCheckedMan(List<String> checkedMan) {
		this.checkedMan = checkedMan;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public Integer getPlanCycle() {
		return planCycle;
	}

	public void setPlanCycle(Integer planCycle) {
		this.planCycle = planCycle;
	}

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
