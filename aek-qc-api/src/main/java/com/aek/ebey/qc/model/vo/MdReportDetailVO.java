package com.aek.ebey.qc.model.vo;

import java.util.Date;
import java.util.List;

import com.aek.ebey.qc.model.MdReportAssets;
import com.aek.ebey.qc.model.bo.MdMaximumAllowableErrorDetailBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("质控单详情输出类")
public class MdReportDetailVO {

	@ApiModelProperty("质控检测单Id")
	private Long id;
	@ApiModelProperty("机构名称")
	private String tenantName;
	@ApiModelProperty("质控检测单号")
	private String mdNum;
	@ApiModelProperty("质控检测档案号")
	private String mdArchiveNum;
	@ApiModelProperty("质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)")
	private Integer status;
	@ApiModelProperty("联系电话")
	private String contactNumber;
	@ApiModelProperty("检测依据")
	private String checkAccording;
	@ApiModelProperty("环境条件")
	private String environmentCondition;
	@ApiModelProperty("偏离情况记录")
	private String deviationRecord;
	@ApiModelProperty("检测仪器(标准器)名称")
	private String checkInstrumentName;
	@ApiModelProperty("检测仪器(标准器)规格")
	private String checkInstrumentSpec;
	@ApiModelProperty("检测仪器(标准器)生产商")
	private String checkInstrumentProducer;
	@ApiModelProperty("质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)")
	private String checkType;
	@ApiModelProperty("检测日期")
	private Date checkTime;
	@ApiModelProperty("检测人id")
	private Long checkBy;
	@ApiModelProperty("检测人姓名")
	private String checkName;
	@ApiModelProperty("检测结论(0=不合格、1=合格)")
	private Integer checkResult;
	@ApiModelProperty("审核结果(0=审核未通过、1=审核通过)")
	private Integer verifyResult;
	@ApiModelProperty("审核人id")
	private Long verifyBy;
	@ApiModelProperty("审核人姓名")
	private String verifyName;
	@ApiModelProperty("审核日期")
	private Date verifyTime;
	@ApiModelProperty("审核备注")
	private String verifyRemark;
	@ApiModelProperty("外观及工作正常性检查")
	private String appearanceWorkCheck;
	
	@ApiModelProperty("最大允差json")
	private String maximumAllowableError;
	@ApiModelProperty("最大允差集合")
	private List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList;
	
	@ApiModelProperty("质控设备")
	private MdReportAssets mdAsset;
	
	@ApiModelProperty("质控模版详情")
	private MdReportTemplateDetailVO templateDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
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

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
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

	public Integer getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	public Integer getVerifyResult() {
		return verifyResult;
	}

	public void setVerifyResult(Integer verifyResult) {
		this.verifyResult = verifyResult;
	}

	public Long getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(Long verifyBy) {
		this.verifyBy = verifyBy;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
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

	public List<MdMaximumAllowableErrorDetailBO> getMaximumAllowableErrorList() {
		return maximumAllowableErrorList;
	}

	public void setMaximumAllowableErrorList(List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList) {
		this.maximumAllowableErrorList = maximumAllowableErrorList;
	}

	public MdReportAssets getMdAsset() {
		return mdAsset;
	}

	public void setMdAsset(MdReportAssets mdAsset) {
		this.mdAsset = mdAsset;
	}

	public MdReportTemplateDetailVO getTemplateDetail() {
		return templateDetail;
	}

	public void setTemplateDetail(MdReportTemplateDetailVO templateDetail) {
		this.templateDetail = templateDetail;
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
