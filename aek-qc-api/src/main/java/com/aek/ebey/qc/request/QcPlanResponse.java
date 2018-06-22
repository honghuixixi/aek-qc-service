package com.aek.ebey.qc.request;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class QcPlanResponse{

	private Long id;
	
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
	 * 巡检类型(1，按科室巡检）
	 */
	private Integer planType;
	
	/**
	 * 巡检周期
	 */
	private String cycle;
	
	/**
	 * 巡检周期
	 */
	private Integer planCycle;
	/**
	 * 巡检周期类型(1，月 2，天)
	 */
	private Integer cycleType;
	
	/**
	 * 首次巡检开始时间
	 */
	private Date firstDate;
	
	
	/**
	 * 上次巡检开始时间
	 */
	private Date preDate;
	/**
	 * 下次巡检时间
	 */
	private Date nextDate;
	
	
	/**
	 * 负责人
	 */
	private String director;
	
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getPreDate() {
		return preDate;
	}

	public void setPreDate(Date preDate) {
		this.preDate = preDate;
	}


}
