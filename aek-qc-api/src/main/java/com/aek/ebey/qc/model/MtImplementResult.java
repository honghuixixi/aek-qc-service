package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 保养实施结果表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@TableName("mt_implement_result")
public class MtImplementResult extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 保养计划实施ID
	 */
	@TableField(value="plan_implement_id")
	private Long planImplementId;
	/**
	 * 保养计划ID
	 */
	@TableField(value="plan_id")
	private Long planId;
	
	/**
	 * 保养报告编号
	 */
	@TableField(value="report_no")
	private String reportNo;
	/**
	 * 保养实施实际结束时间
	 */
	@TableField(value="actual_end_date")
	private Date actualEndDate;
	/**
	 * 实施结束时间
	 */
	@TableField(value="end_date")
	private Date endDate;
	/**
	 * 设备现状(1=正常工作,2=小问题不影响使用,3=有故障需要维修,4=不能使用)
	 */
	@TableField(value="assets_status")
	private Integer assetsStatus;
	/**
	 * 备注
	 */
	@TableField(value="remarks")
	private String remarks;
	/**
	 * 状态(1=实施中，2=已实施)
	 */
	@TableField(value="status")
	private Integer status;
	
	/**
	 * 实施人ID
	 */
	@TableField(value="implement_user_id")
	private Long implementUserId;
	
	/**
	 * 实施人姓名
	 */
	@TableField(value="implement_user_name")
	private String implementUserName;
	
	/**
	 * 专管人
	 */
	@TableField(value="administrator")
	private String administrator;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanImplementId() {
		return planImplementId;
	}

	public void setPlanImplementId(Long planImplementId) {
		this.planImplementId = planImplementId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
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

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public Long getImplementUserId() {
		return implementUserId;
	}

	public void setImplementUserId(Long implementUserId) {
		this.implementUserId = implementUserId;
	}

	public String getImplementUserName() {
		return implementUserName;
	}

	public void setImplementUserName(String implementUserName) {
		this.implementUserName = implementUserName;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

}
