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
 * @since 2018-05-16
 */
@ApiModel(value = "QcPlanCheckResponse")
public class QcPlanCheckResponse{
	
	/**
	 * 实施id
	 */
	@ApiModelProperty(value = "实施id")
	private Long id;
	
	/**
	 *  报告编号
	 */
	@ApiModelProperty(value = "报告编号")
	private String reportNo;
	
	/**
	 * 巡检计划名称
	 */
	@ApiModelProperty(value = "巡检计划名称")
	private String planName;
	
	/**
	 * 巡检类型(1，按科室巡检）
	 */
	@ApiModelProperty(value = "巡检类型(1，按科室巡检)")
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
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	private Date actualEndDate;
	
	/**
	 * 负责人
	 */
	@ApiModelProperty(value = "负责人")
	private String chargeName;
	
	/**
	 * 验收状态（1，待验收 2，已验收）
	 */
	@ApiModelProperty(value = "验收状态（1，待验收 2，已验收）")
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
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

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
