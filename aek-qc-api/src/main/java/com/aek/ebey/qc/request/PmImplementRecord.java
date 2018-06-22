package com.aek.ebey.qc.request;

import java.util.Date;

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
@ApiModel(value = "PmImplementRecord", description = "PmImplementRecord")
public class PmImplementRecord{

	@ApiModelProperty(value="实施id")
	private Long id;
	
	
	
	/**
	 * 计划日期
	 */
	@ApiModelProperty(value="提交日期")
	private Date planDate;
	
	/**
	 * 实际开始日期
	 */
	@ApiModelProperty(value="提交日期")
	private Date actualStartDate;
	
	/**
	 * 实际结束日期
	 */
	@ApiModelProperty(value="提交日期")
	private Date actualEndDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
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

	
}
