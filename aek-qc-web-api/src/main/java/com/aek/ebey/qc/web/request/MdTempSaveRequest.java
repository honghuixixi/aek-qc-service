package com.aek.ebey.qc.web.request;

import java.util.List;

import com.aek.ebey.qc.model.MdReportTemplateItemResult;
import com.aek.ebey.qc.model.bo.MdAssetsBO;
import com.aek.ebey.qc.model.bo.MdMaximumAllowableErrorDetailBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("质控检测暂存请求类")
public class MdTempSaveRequest {

	@ApiModelProperty("质控检测单Id")
	private Long id;
	@ApiModelProperty("质控报告模板Id")
	private Long  reportTemplateId;
	@ApiModelProperty("质控检测单号")
	private String mdNum;
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
	@ApiModelProperty("检测结论(0=不合格、1=合格)")
	private Integer checkResult;
	@ApiModelProperty("外观及工作正常性检查")
	private String appearanceWorkCheck;
	
	@ApiModelProperty("最大允差集合")
	private List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList;
	
	@ApiModelProperty("质控设备")
	private MdAssetsBO mdAsset;
	
	@ApiModelProperty("答案接收集合")
	private List<MdReportTemplateItemResult> mdItemResultList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReportTemplateId() {
		return reportTemplateId;
	}

	public void setReportTemplateId(Long reportTemplateId) {
		this.reportTemplateId = reportTemplateId;
	}

	public String getMdNum() {
		return mdNum;
	}

	public void setMdNum(String mdNum) {
		this.mdNum = mdNum;
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

	public Integer getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	public String getAppearanceWorkCheck() {
		return appearanceWorkCheck;
	}

	public void setAppearanceWorkCheck(String appearanceWorkCheck) {
		this.appearanceWorkCheck = appearanceWorkCheck;
	}

	public List<MdMaximumAllowableErrorDetailBO> getMaximumAllowableErrorList() {
		return maximumAllowableErrorList;
	}

	public void setMaximumAllowableErrorList(List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList) {
		this.maximumAllowableErrorList = maximumAllowableErrorList;
	}
	
	public MdAssetsBO getMdAsset() {
		return mdAsset;
	}

	public void setMdAsset(MdAssetsBO mdAsset) {
		this.mdAsset = mdAsset;
	}

	public List<MdReportTemplateItemResult> getMdItemResultList() {
		return mdItemResultList;
	}

	public void setMdItemResultList(List<MdReportTemplateItemResult> mdItemResultList) {
		this.mdItemResultList = mdItemResultList;
	}


	
	
}
