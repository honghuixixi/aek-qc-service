package com.aek.ebey.qc.model;

import java.math.BigDecimal;
import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * PM实施
 * </p>
 *
 * @author Liuhui
 * @since 2018-05-21
 */
@ApiModel(value = "PmImplement")
@TableName("pm_implement")
public class PmImplement extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 关联实施id
	 */
	@TableField(value="implement_id")
	@ApiModelProperty(value = "关联实施id")
	private Long implementId;
	/**
	 * 报告编号
	 */
	@TableField(value="report_no")
	@ApiModelProperty(value = "报告编号")
	private String reportNo;
	/**
	 * 关联巡检计划id
	 */
	@TableField(value="paln_id")
	@ApiModelProperty(value = "关联巡检计划id")
	private Long palnId;
	/**
	 * 实际开始日期
	 */
	@TableField(value="actual_start_date")
	@ApiModelProperty(value = "实际开始日期")
	private Date actualStartDate;
	/**
	 * 时间结束时间
	 */
	@TableField(value="actual_end_date")
	@ApiModelProperty(value = "时间结束时间")
	private Date actualEndDate;
	/**
	 * 计划巡检日期
	 */
	@TableField(value="plan_date")
	@ApiModelProperty(value = "计划巡检日期")
	private Date planDate;
	/**
	 * 实施开始时间
	 */
	@TableField(value="create_date")
	@ApiModelProperty(value = "实施开始时间")
	private Date createDate;
	/**
	 * 实施结束时间
	 */
	@TableField(value="end_date")
	@ApiModelProperty(value = "实施结束时间")
	private Date endDate;
	/**
	 * 设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用
	 */
	private Integer live;
	/**
	 * 工时
	 */
	@TableField(value="work_time")
	@ApiModelProperty(value = "工时")
	private BigDecimal workTime;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 实施状态(1，实施中 2，已完成）
	 */
	private Integer status;
	/**
	 * 是否验收（1，待验收 2，已验收）
	 */
	@TableField(value="is_check")
	@ApiModelProperty(value = "是否验收（1，待验收 2，已验收）")
	private Integer isCheck;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImplementId() {
		return implementId;
	}

	public void setImplementId(Long implementId) {
		this.implementId = implementId;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public Long getPalnId() {
		return palnId;
	}

	public void setPalnId(Long palnId) {
		this.palnId = palnId;
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

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getLive() {
		return live;
	}

	public void setLive(Integer live) {
		this.live = live;
	}

	public BigDecimal getWorkTime() {
		return workTime;
	}

	public void setWorkTime(BigDecimal workTime) {
		this.workTime = workTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

}
