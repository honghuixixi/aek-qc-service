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
 * @since 2017-11-03
 */
@ApiModel(value = "QcImplementDetail")
public class QcImplementDetail{
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 首次巡检开始时间
	 */
	private Date firstTime;
	
	/**
	 * 计划编号
	 */
	private String planNo;
	
	/**
	 * 巡检名称
	 */
	private String name;
	
	/**
	 * 巡检类型
	 */
	private String type;
	
	/**
	 * 负责人
	 */
	private String director;
	
	
	/**
	 * 巡检周期
	 */
	private String cycle;
	
	/**
	 * 上次巡检开始时间
	 */
	private Date preDate;
	/**
	 * 下次巡检时间
	 */
	private Date nextDate;
	
	/**
	 * 实际开始巡检时间
	 */
	private Date actualStartDate;
	
	/**
	 * 实际结束巡检时间
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
	 * 状态 1:启用、2:停用
	 */
	private Integer status;
	
	/**
	 * 巡检范围
	 */
	private String scope;
	
	/**
	 * 巡检提示语
	 */
	private String attention;
	
	
	/**
	 * 验收人
	 */
	@ApiModelProperty(value = "验收人")
	private List<QcChargeRequest> checkMan;
	
	private List<QcProjectRequest4> template;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Date getPreDate() {
		return preDate;
	}

	public void setPreDate(Date preDate) {
		this.preDate = preDate;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public List<QcProjectRequest4> getTemplate() {
		return template;
	}

	public void setTemplate(List<QcProjectRequest4> template) {
		this.template = template;
	}

	public List<QcChargeRequest> getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(List<QcChargeRequest> checkMan) {
		this.checkMan = checkMan;
	}


	
}
