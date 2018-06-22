package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 计量设备检测信息暂存表(正在检测)
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@TableName("ms_check_storage")
@ApiModel(value = "MsCheckStorage", description = "MsCheckStorage")
public class MsCheckStorage extends BaseModel {

    private static final long serialVersionUID = 1L;
	/**
	 * 检测ID
	 */
    @ApiModelProperty(value="检测ID")
	@TableField(value="ms_check_id")
	private Long msCheckId;
	/**
	 * 实际检定方式（1，院内 2，外包）
	 */
    @ApiModelProperty(value="实际检定方式（1，院内 2，外包）")
	@TableField(value="check_mode")
	private Integer checkMode;
	/**
	 * 证书编号
	 */
    @ApiModelProperty(value="证书编号")
	@TableField(value="certificate_num")
	private String certificateNum;
	/**
	 * 本次检测日期
	 */
    @ApiModelProperty(value="本次检测日期")
	@TableField(value="current_check_time")
	private Date currentCheckTime;
	/**
	 * 下次检测日期
	 */
    @ApiModelProperty(value="下次检测日期")
	@TableField(value="next_check_time")
	private Date nextCheckTime;
	/**
	 * 检定形式（1，首次 2，随后 3，使用中检测 4，周期 5，仲裁）
	 */
    @ApiModelProperty(value="检定形式（1，首次 2，随后 3，使用中检测 4，周期 5，仲裁）")
	@TableField(value="check_form")
	private Integer checkForm;
	/**
	 * 检测结果（1，合格 2，准用 3，限用 4，禁用）
	 */
    @ApiModelProperty(value="检测结果（1，合格 2，准用 3，限用 4，禁用）")
	@TableField(value="check_result_status")
	private Integer checkResultStatus;
	/**
	 * 检定机构
	 */
    @ApiModelProperty(value="检定机构")
	@TableField(value="check_organization")
	private String checkOrganization;
	/**
	 * 检定费用
	 */
    @ApiModelProperty(value="检定费用")
	@TableField(value="check_fee")
	private Double checkFee;
	/**
	 * 检定人
	 */
    @ApiModelProperty(value="检定人")
	@TableField(value="check_user_name")
	private String checkUserName;
	/**
	 * 审核人
	 */
    @ApiModelProperty(value="审核人")
	private String auditor;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String remarks;
	/**
	 * 附件 以逗号分割
	 */
    @ApiModelProperty(value="附件 以逗号分割")
	private String files;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value="创建时间")
	@TableField(value="create_time")
	private Date createTime=new Date();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMsCheckId() {
		return msCheckId;
	}

	public void setMsCheckId(Long msCheckId) {
		this.msCheckId = msCheckId;
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

}
