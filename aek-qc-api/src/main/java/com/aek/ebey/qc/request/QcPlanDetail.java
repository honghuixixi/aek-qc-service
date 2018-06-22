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
@ApiModel(value = "QcPlanDetail")
public class QcPlanDetail{
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
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
	 * 巡检周期
	 */
	private String cycle;
	
	/**
	 * 首次巡检开始时间
	 */
	private Date firstDate;
	/**
	 * 下次巡检时间
	 */
	private Date nextDate;
	
	/**
	 * 上次次巡检时间
	 */
	private Date prevDate;
	
	
	/**
	 * 负责人姓名
	 */
	private String director;
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;
	
	/**
	 * 巡检范围
	 */
	private String scope;
	
	/**
	 * 巡检模板
	 */
	private QcTemplateDetail template;
	

	/**
	 * 验收人
	 */
	@ApiModelProperty(value = "验收人")
	private List<QcChargeRequest> checkMan;

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

	public Date getPrevDate() {
		return prevDate;
	}

	public void setPrevDate(Date prevDate) {
		this.prevDate = prevDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
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

	public QcTemplateDetail getTemplate() {
		return template;
	}

	public void setTemplate(QcTemplateDetail template) {
		this.template = template;
	}

	public List<QcChargeRequest> getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(List<QcChargeRequest> checkMan) {
		this.checkMan = checkMan;
	}
	

	
}
