package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 保养计划实施表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@TableName("mt_plan_implement")
public class MtPlanImplement extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 保养计划ID
	 */
	@TableField(value="plan_id")
	private Long planId;
	/**
	 * 专管人
	 */
	private String administrator;
	/**
	 * 保养频率(1=每天，2=每周)
	 */
	private Integer rate;
	/**
	 * 上次实施时间
	 */
	@TableField(value="last_implement_time")
	private Date lastImplementTime;
	/**
	 * 下次实施时间
	 */
	@TableField(value="next_implement_time")
	private Date nextImplementTime;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Date getLastImplementTime() {
		return lastImplementTime;
	}

	public void setLastImplementTime(Date lastImplementTime) {
		this.lastImplementTime = lastImplementTime;
	}

	public Date getNextImplementTime() {
		return nextImplementTime;
	}

	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
