package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 质控检测单表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-17
 */
@TableName("md_report")
public class MdReport extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 质控检测主键
	 */
	private Long id;
	/**
	 * 机构id
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 质控检测单号
	 */
	@TableField(value="md_num")
	private String mdNum;
	/**
	 * 质控检测档案号
	 */
	@TableField(value="md_archive_num")
	private String mdArchiveNum;
	/**
	 * 质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)
	 */
	private Integer status;
	/**
	 * 联系电话
	 */
	@TableField(value="contact_number")
	private String contactNumber;
	/**
	 * 检测依据
	 */
	@TableField(value="check_according")
	private String checkAccording;
	/**
	 * 环境条件
	 */
	@TableField(value="environment_condition")
	private String environmentCondition;
	/**
	 * 偏离情况记录
	 */
	@TableField(value="deviation_record")
	private String deviationRecord;
	/**
	 * 检测仪器(标准器)名称
	 */
	@TableField(value="check_instrument_name")
	private String checkInstrumentName;
	/**
	 * 检测仪器规格型号
	 */
	@TableField(value="check_instrument_spec")
	private String checkInstrumentSpec;
	/**
	 * 检测仪器生产商
	 */
	@TableField(value="check_instrument_producer")
	private String checkInstrumentProducer;
	/**
	 * 质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)
	 */
	@TableField(value="check_type")
	private Integer checkType;
	/**
	 * 检测结论(0=不合格、1=合格)
	 */
	@TableField(value="check_result")
	private Integer checkResult;
	/**
	 * 检测日期
	 */
	@TableField(value="check_time")
	private Date checkTime;
	/**
	 * 检测人id
	 */
	@TableField(value="check_by")
	private Long checkBy;
	/**
	 * 检测人姓名
	 */
	@TableField(value="check_name")
	private String checkName;
	/**
	 * 审核结果(0=审核未通过、1=审核通过)
	 */
	@TableField(value="verify_result")
	private Integer verifyResult;
	/**
	 * 审核日期
	 */
	@TableField(value="verify_time")
	private Date verifyTime;
	/**
	 * 审核人id
	 */
	@TableField(value="verify_by")
	private Long verifyBy;
	/**
	 * 审核人姓名
	 */
	@TableField(value="verify_name")
	private String verifyName;
	/**
	 * 审核备注
	 */
	@TableField(value="verify_remark")
	private String verifyRemark;
	/**
	 * 外观及工作正常性检查
	 */
	@TableField(value="appearance_work_check")
	private String appearanceWorkCheck;
	/**
	 * 最大允差
	 */
	@TableField(value="maximum_allowable_error")
	private String maximumAllowableError;
	/**
	 * 创建人
	 */
	@TableField(value="create_by")
	private Long createBy;
	/**
	 * 创建日期
	 */
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 更新人
	 */
	@TableField(value="update_by")
	private Long updateBy;
	/**
	 * 更新日期
	 */
	@TableField(value="update_time")
	private Date updateTime;
	/**
	 * 作废标识(0=未作废、1=作废)
	 */
	@TableField(value="del_flag")
	private Boolean delFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getMdNum() {
		return mdNum;
	}

	public void setMdNum(String mdNum) {
		this.mdNum = mdNum;
	}

	public String getMdArchiveNum() {
		return mdArchiveNum;
	}

	public void setMdArchiveNum(String mdArchiveNum) {
		this.mdArchiveNum = mdArchiveNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCheckAccording() {
		return checkAccording;
	}

	public void setCheckAccording(String checkAccording) {
		this.checkAccording = checkAccording;
	}

	public String getEnvironmentCondition() {
		return environmentCondition;
	}

	public void setEnvironmentCondition(String environmentCondition) {
		this.environmentCondition = environmentCondition;
	}

	public String getDeviationRecord() {
		return deviationRecord;
	}

	public void setDeviationRecord(String deviationRecord) {
		this.deviationRecord = deviationRecord;
	}

	public String getCheckInstrumentName() {
		return checkInstrumentName;
	}

	public void setCheckInstrumentName(String checkInstrumentName) {
		this.checkInstrumentName = checkInstrumentName;
	}

	public String getCheckInstrumentSpec() {
		return checkInstrumentSpec;
	}

	public void setCheckInstrumentSpec(String checkInstrumentSpec) {
		this.checkInstrumentSpec = checkInstrumentSpec;
	}

	public String getCheckInstrumentProducer() {
		return checkInstrumentProducer;
	}

	public void setCheckInstrumentProducer(String checkInstrumentProducer) {
		this.checkInstrumentProducer = checkInstrumentProducer;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Long getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(Long checkBy) {
		this.checkBy = checkBy;
	}

	public Integer getVerifyResult() {
		return verifyResult;
	}

	public void setVerifyResult(Integer verifyResult) {
		this.verifyResult = verifyResult;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Long getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(Long verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public String getAppearanceWorkCheck() {
		return appearanceWorkCheck;
	}

	public void setAppearanceWorkCheck(String appearanceWorkCheck) {
		this.appearanceWorkCheck = appearanceWorkCheck;
	}

	public String getMaximumAllowableError() {
		return maximumAllowableError;
	}

	public void setMaximumAllowableError(String maximumAllowableError) {
		this.maximumAllowableError = maximumAllowableError;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	

}
