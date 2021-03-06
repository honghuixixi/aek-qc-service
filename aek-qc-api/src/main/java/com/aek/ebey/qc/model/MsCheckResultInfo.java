package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 计量设备检测结果信息表（已检测）
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@TableName("ms_check_result_info")
public class MsCheckResultInfo extends BaseModel {

    private static final long serialVersionUID = 1L;
	/**
	 * 报告编号
	 */
	@TableField(value="check_report_no")
	private String checkReportNo;
	/**
	 * 检测ID
	 */
	@TableField(value="check_history_id")
	private Long checkHistoryId;
	/**
	 * 实际检定方式（1，院内 2，外包）
	 */
	@TableField(value="check_mode")
	private Integer checkMode;
	/**
	 * 证书编号
	 */
	@TableField(value="certificate_num")
	private String certificateNum;
	/**
	 * 本次检测日期
	 */
	@TableField(value="current_check_time")
	private Date currentCheckTime;
	/**
	 * 下次检测日期
	 */
	@TableField(value="next_check_time")
	private Date nextCheckTime;
	/**
	 * 检定形式（1，首次 2，随后 3，使用中检测 4，周期 5，仲裁）
	 */
	@TableField(value="check_form")
	private Integer checkForm;
	/**
	 * 检测结果（1，合格 2，准用 3，限用 4，禁用）
	 */
	@TableField(value="check_result_status")
	private Integer checkResultStatus;
	/**
	 * 检定机构
	 */
	@TableField(value="check_organization")
	private String checkOrganization;
	/**
	 * 检定费用
	 */
	@TableField(value="check_fee")
	private Double checkFee;
	/**
	 * 检定人
	 */
	@TableField(value="check_user_name")
	private String checkUserName;
	/**
	 * 审核人
	 */
	private String auditor;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 附件 以逗号分割
	 */
	private String files;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 提交时间
	 */
	@TableField(value="submit_time")
	private Date submitTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckReportNo() {
		return checkReportNo;
	}

	public void setCheckReportNo(String checkReportNo) {
		this.checkReportNo = checkReportNo;
	}

	public Long getCheckHistoryId() {
		return checkHistoryId;
	}

	public void setCheckHistoryId(Long checkHistoryId) {
		this.checkHistoryId = checkHistoryId;
	}

	public Integer getCheckMode() {
		return checkMode;
	}

	public void setCheckMode(Integer checkMode) {
		this.checkMode = checkMode;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Date getCurrentCheckTime() {
		return currentCheckTime;
	}

	public void setCurrentCheckTime(Date currentCheckTime) {
		this.currentCheckTime = currentCheckTime;
	}


	public Date getNextCheckTime() {
		return nextCheckTime;
	}

	public void setNextCheckTime(Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}

	public Integer getCheckForm() {
		return checkForm;
	}

	public void setCheckForm(Integer checkForm) {
		this.checkForm = checkForm;
	}

	public Integer getCheckResultStatus() {
		return checkResultStatus;
	}

	public void setCheckResultStatus(Integer checkResultStatus) {
		this.checkResultStatus = checkResultStatus;
	}

	public String getCheckOrganization() {
		return checkOrganization;
	}

	public void setCheckOrganization(String checkOrganization) {
		this.checkOrganization = checkOrganization;
	}

	public Double getCheckFee() {
		return checkFee;
	}

	public void setCheckFee(Double checkFee) {
		this.checkFee = checkFee;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

}
