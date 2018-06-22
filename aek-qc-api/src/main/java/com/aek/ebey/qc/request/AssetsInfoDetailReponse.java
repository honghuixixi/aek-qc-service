package com.aek.ebey.qc.request;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 预台账返回实体
 * 
 * @author Shuangwf
 * @version 1.0, 2017年4月14日
 */
@ApiModel(value = "AssetsInfoDetail", description = "预台账详情信息")
public class AssetsInfoDetailReponse {
	@ApiModelProperty(value = "状态值")
	@NotEmpty
	public Integer status;

	@ApiModelProperty(value = "状态名称")
	@NotEmpty
	public String statusName;

	@ApiModelProperty(value = "设备类型【1:台帐  2:预台帐】")
	@NotEmpty
	public Integer assetsStatus;

	@ApiModelProperty(value = "账簿类型【仓库类型】ID")
	@NotEmpty
	private Integer assetsTypeId;

	@ApiModelProperty(value = "账簿类型")
	@NotEmpty
	private String assetsTypeName;

	@ApiModelProperty(value = "创建时间")
	@NotEmpty
	private Date createTime;

	@ApiModelProperty(value = "验收时间")
	@NotEmpty
	private Date acceptanceDate;

	/**
	 * 验收人员 2017/11/21
	 */
	@ApiModelProperty(value = "验收人员")
	private String acceptancePersonName;

	/**
	 * 验收部门名称 2017/11/21
	 */
	@ApiModelProperty(value = "验收部门")
	private String acceptanceDeptName;

	@ApiModelProperty(value = "创建人姓名")
	@NotEmpty
	private String createByName;

	@ApiModelProperty(value = "创建人Id【添加人】")
	@NotEmpty
	private Long createBy;

	@ApiModelProperty(value = "最后修改人ID")
	@NotEmpty
	private Long updateBy;

	@ApiModelProperty(value = "最后修改人名字")
	@NotEmpty
	private String updateByName;

	@ApiModelProperty(value = "最后修改时间")
	@NotEmpty
	private Date updateTime;

	@ApiModelProperty(value = "台账id")
	@NotEmpty
	private Long assetsId;

	@ApiModelProperty(value = "设备名称")
	@NotEmpty
	private String assetsName;

	@ApiModelProperty(value = "设备编号")
	@NotEmpty
	private String assetsNum;

	@ApiModelProperty(value = "生产商")
	@NotEmpty
	private String factoryName;

	@ApiModelProperty(value = "设备规格")
	@NotEmpty
	private String assetsSpec;

	@ApiModelProperty(value = "品牌")
	@NotEmpty
	private String assetsBrand;

	@ApiModelProperty(value = "注册证号")
	@NotEmpty
	private String regNo;

	@ApiModelProperty(value = "注册证名称")
	@NotEmpty
	private String regName;

	@ApiModelProperty(value = "院内编码")
	@NotEmpty
	private String serialNum;

	@ApiModelProperty(value = "管理级别")
	@NotEmpty
	private Byte manageLevel;

	@ApiModelProperty(value = "管理级别名")
	@NotEmpty
	private String manageLevelName;

	@ApiModelProperty(value = "是否免税")
	@NotEmpty
	private Byte freeTax;

	@ApiModelProperty(value = "PM标志")
	@NotEmpty
	private Byte pmFlag;

	@ApiModelProperty(value = "产地")
	@NotEmpty
	private String prodPlace;

	@ApiModelProperty(value = "单位ID(计数单位)") // 表pm_assets_info
	@NotEmpty
	private Integer unitId;

	@ApiModelProperty(value = "单位")
	@NotEmpty
	private String unitName;

	@ApiModelProperty(value = "出厂编号")
	@NotEmpty
	private String factoryNum;

	@ApiModelProperty(value = "核算类别ID") // 表pm_assets_info
	@NotEmpty
	private Integer assetsClassId;

	@ApiModelProperty(value = "核算类别")
	@NotEmpty
	private String assetsClassName;

	@ApiModelProperty(value = "计量类别ID")
	@NotEmpty
	private Byte measureType;

	@ApiModelProperty(value = "计量类别")
	@NotEmpty
	private String measureTypeName;

	@ApiModelProperty(value = "商检设备")
	@NotEmpty
	private Byte commodityFlag;

	@ApiModelProperty(value = "质检设备")
	@NotEmpty
	private Byte qualityFlag;

	/* 使用信息 */
	@ApiModelProperty(value = "申购科室")
	@NotEmpty
	private Integer applyDeptId;

	@ApiModelProperty(value = "申购科室名称")
	@NotEmpty
	private String applyDeptName;

	/**
	 * 购置类别 2017/11/21
	 */
	@ApiModelProperty(value = "购置类别")
	@NotEmpty
	private Integer applyType;

	/**
	 * 申购日期 2017/11/21
	 */
	@ApiModelProperty(value = "申购日期")
	@NotEmpty
	private Date applyDate;

	/**
	 * 论证日期 2017/11/21
	 */
	@ApiModelProperty(value = "论证日期")
	@NotEmpty
	private Date proofDate;

	/**
	 * 预到日期 2017/11/21
	 */
	@ApiModelProperty(value = "expect_date")
	@NotEmpty
	private Date expectDate;

	/**
	 * 申购理由 2017/11/21
	 */
	@ApiModelProperty(value = "申购理由")
	@NotEmpty
	private String applyReason;

	@ApiModelProperty(value = "使用科室")
	@NotEmpty
	private Long deptId;

	@ApiModelProperty(value = "使用科室名称")
	@NotEmpty
	private String deptName;

	@ApiModelProperty(value = "管理科室ID")
	@NotEmpty
	private Integer manageDeptId;

	@ApiModelProperty(value = "管理科室")
	@NotEmpty
	private String manageDeptName;

	@ApiModelProperty(value = "仓库类型")
	@NotEmpty
	private Integer whId;

	@ApiModelProperty(value = "启用日期")
	@NotEmpty
	private Date startUseDate;

	@ApiModelProperty(value = "用途ID")
	@NotEmpty
	private Integer purpose;

	@ApiModelProperty(value = "用途")
	@NotEmpty
	private String purposeName;

	@ApiModelProperty(value = "保修日期")
	@NotEmpty
	private Date warrantyDate;

	@ApiModelProperty(value = "折旧方法ID")
	@NotEmpty
	private Integer depType;

	@ApiModelProperty(value = "折旧方法")
	@NotEmpty
	private String depTypeName;

	/* ……………………………………………………………………【采购信息】……………………………………………………… */
	/* 采购基本信息 */
	@ApiModelProperty(value = "设备来源ID")
	@NotEmpty
	private Integer purchaseTypeId;

	@ApiModelProperty(value = "设备来源")
	@NotEmpty
	private String purchaseTypeName;

	@ApiModelProperty(value = "购入日期")
	@NotEmpty
	private Date purchaseDate;

	/**
	 * 采购方式:1=国际招标、2=政府采购、3=院内采购、4=分散采购、5=自行采购、6=其他 2017/11/21
	 */
	@ApiModelProperty(value = "采购方式")
	@NotEmpty
	private Integer purchaseWay;

	/**
	 * 招标形式:1=公开招标、2=邀请招标、3=竞争性谈判、4=单一来源采购、5=询价采购、6=其他 2017/11/21
	 */
	@ApiModelProperty(value = "招标形式")
	@NotEmpty
	private Integer tenderForm;

	/**
	 * 单项预算 2017/11/21
	 */
	@ApiModelProperty(value = "单项预算")
	@NotEmpty
	private Long singleBudget;

	/**
	 * 单项预算 2017/11/21
	 */
	@ApiModelProperty(value = "单项预算")
	@NotEmpty
	private String singleBudgetStr;

	/**
	 * 中标时间 2017/11/21
	 */
	@ApiModelProperty(value = "中标时间")
	@NotEmpty
	private Date winTenderDate;

	/**
	 * 立项依据 2017/11/21
	 */
	@ApiModelProperty(value = "立项依据")
	@NotEmpty
	private String approveProjectAccord;

	@ApiModelProperty(value = "供应商")
	@NotEmpty
	private String splName;

	@NotEmpty
	private Long price;

	@ApiModelProperty(value = "设备单价")
	private String priceStr;

	@ApiModelProperty(value = "折旧年限")
	private String oldYear;

	@ApiModelProperty(value = "折旧残值")
	@NotEmpty
	private String lessPrice;

	@ApiModelProperty(value = "资金来源比例Id")
	private Integer fundSourcesId;

	@ApiModelProperty(value = "经费来源")
	@NotEmpty
	private String fundSourcesName;

	@ApiModelProperty(value = "资金来源比例金额")
	private String fundSourceMoneys;

	@ApiModelProperty(value = "到货时间")
	@NotEmpty
	private Date arrivalDate;

	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private Long contractId;

	/* 合同信息 */
	@ApiModelProperty(value = "合同编号")
	@NotEmpty
	private String contractNo;

	@ApiModelProperty(value = "合同名称")
	@NotEmpty
	private String contractName;

	@ApiModelProperty(value = "供应商名称")
	@NotEmpty
	private String supplierName;

	@ApiModelProperty(value = "签订日期")
	@NotEmpty
	private Date startDate;

	@NotEmpty
	private Long contractPrice;

	@ApiModelProperty(value = "合同金额") // 合同价格
	private String contractPriceStr;

	@ApiModelProperty(value = "合同截止日期")
	@NotEmpty
	private Date endDate;

	@ApiModelProperty(value = "乙方联系人")
	@NotEmpty
	private String contractContactName;

	@ApiModelProperty(value = "乙方联系电话")
	@NotEmpty
	private String contractContactPhone;

	@ApiModelProperty(value = "档案编号")
	@NotEmpty
	private String archivesCode;

	@ApiModelProperty(value = "档案管理员")
	@NotEmpty
	private String archivesManager;

	@ApiModelProperty(value = "合同内容")
	@NotEmpty
	private String contractContent;

	/* 发票信息 */
	@ApiModelProperty(value = "发票号【单个台账下的所有】")
	@NotEmpty
	private String invoiceNos;

	@ApiModelProperty(value = "审核状态(0：暂存 1：待验收 2:验收通过 3:验收未通过)")
	@NotEmpty
	private Integer verfyStatus;

	@ApiModelProperty(value = "审核状态")
	@NotEmpty
	private String verfyStatusName;

	@ApiModelProperty(value = "验收日期")
	private Date verifyDate;

	@ApiModelProperty(value = "验收说明")
	private String verifyRemark;

	@ApiModelProperty(value = "台账图片")
	private String assetsImg;

	@ApiModelProperty(value = "验收人")
	private String verfyByName;

	@ApiModelProperty(value = "验收编号")
	private String verfyNum;

	/**
	 * 三级分类代码
	 */
	@ApiModelProperty(value = "三级分类代码")
	private String threeLevelCode;

	@ApiModelProperty(value = "0：入库新增，1：批量导入 2:新建")
	private String assetsSourceName;

	/**
	 * 验收时间
	 */
	@ApiModelProperty(value = "验收时间")
	private Date verifyOperateTime;

	@ApiModelProperty(value = "是否国产（1，国产 2，进口）")
	private Integer madeIn;

	@ApiModelProperty(value = "维修状态（1，正常 2，维修中）")
	private Integer repairStatus;

	@ApiModelProperty(value = "维修状态名称")
	private String repairStatusName;

	@ApiModelProperty(value = "巡检标识1启用0不启用")
	private Integer pollingFlag;

	public String getAssetsImg() {
		return assetsImg;
	}

	public void setAssetsImg(String assetsImg) {
		this.assetsImg = assetsImg;
	}

	public String getVerfyByName() {
		return verfyByName;
	}

	public void setVerfyByName(String verfyByName) {
		this.verfyByName = verfyByName;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getAssetsSpec() {
		return assetsSpec;
	}

	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}

	public String getAssetsBrand() {
		return assetsBrand;
	}

	public void setAssetsBrand(String assetsBrand) {
		this.assetsBrand = assetsBrand;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Byte getManageLevel() {
		return manageLevel;
	}

	public void setManageLevel(Byte manageLevel) {
		this.manageLevel = manageLevel;
	}

	public Byte getFreeTax() {
		return freeTax;
	}

	public void setFreeTax(Byte freeTax) {
		this.freeTax = freeTax;
	}

	public Byte getPmFlag() {
		return pmFlag;
	}

	public void setPmFlag(Byte pmFlag) {
		this.pmFlag = pmFlag;
	}

	public String getProdPlace() {
		return prodPlace;
	}

	public void setProdPlace(String prodPlace) {
		this.prodPlace = prodPlace;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getFactoryNum() {
		return factoryNum;
	}

	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
	}

	public Integer getAssetsClassId() {
		return assetsClassId;
	}

	public void setAssetsClassId(Integer assetsClassId) {
		this.assetsClassId = assetsClassId;
	}

	public Byte getMeasureType() {
		return measureType;
	}

	public void setMeasureType(Byte measureType) {
		this.measureType = measureType;
	}

	public Byte getCommodityFlag() {
		return commodityFlag;
	}

	public void setCommodityFlag(Byte commodityFlag) {
		this.commodityFlag = commodityFlag;
	}

	public Byte getQualityFlag() {
		return qualityFlag;
	}

	public void setQualityFlag(Byte qualityFlag) {
		this.qualityFlag = qualityFlag;
	}

	public Integer getApplyDeptId() {
		return applyDeptId;
	}

	public void setApplyDeptId(Integer applyDeptId) {
		this.applyDeptId = applyDeptId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getManageDeptId() {
		return manageDeptId;
	}

	public void setManageDeptId(Integer manageDeptId) {
		this.manageDeptId = manageDeptId;
	}

	public Integer getWhId() {
		return whId;
	}

	public void setWhId(Integer whId) {
		this.whId = whId;
	}

	public Date getStartUseDate() {
		return startUseDate;
	}

	public void setStartUseDate(Date startUseDate) {
		this.startUseDate = startUseDate;
	}

	public String getPurposeName() {
		return purposeName;
	}

	public void setPurpose(String purposeName) {
		this.purposeName = purposeName;
	}

	public Date getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(Date warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	public Integer getDepType() {
		return depType;
	}

	public void setDepType(Integer depType) {
		this.depType = depType;
	}

	public Integer getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(Integer purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getSplName() {
		return splName;
	}

	public void setSplName(String splName) {
		this.splName = splName;
	}

	public Integer getFundSourcesId() {
		return fundSourcesId;
	}

	public void setFundSourcesId(Integer fundSourcesId) {
		this.fundSourcesId = fundSourcesId;
	}

	public String getFundSourceMoneys() {
		return fundSourceMoneys;
	}

	public void setFundSourceMoneys(String fundSourceMoneys) {
		this.fundSourceMoneys = fundSourceMoneys;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getContractContactName() {
		return contractContactName;
	}

	public void setContractContactName(String contractContactName) {
		this.contractContactName = contractContactName;
	}

	public String getContractContactPhone() {
		return contractContactPhone;
	}

	public void setContractContactPhone(String contractContactPhone) {
		this.contractContactPhone = contractContactPhone;
	}

	public String getArchivesCode() {
		return archivesCode;
	}

	public void setArchivesCode(String archivesCode) {
		this.archivesCode = archivesCode;
	}

	public String getArchivesManager() {
		return archivesManager;
	}

	public void setArchivesManager(String archivesManager) {
		this.archivesManager = archivesManager;
	}

	public String getContractContent() {
		return contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getInvoiceNos() {
		return invoiceNos;
	}

	public void setInvoiceNos(String invoiceNos) {
		this.invoiceNos = invoiceNos;
	}

	public int getVerfyStatus() {
		return verfyStatus;
	}

	public void setVerfyStatus(int verfyStatus) {
		this.verfyStatus = verfyStatus;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getVerfyNum() {
		return verfyNum;
	}

	public void setVerfyNum(String verfyNum) {
		this.verfyNum = verfyNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public Long getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(Long contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getContractPriceStr() {
		return contractPriceStr;
	}

	public void setContractPriceStr(String contractPriceStr) {
		this.contractPriceStr = contractPriceStr;
	}

	public Integer getAssetsTypeId() {
		return assetsTypeId;
	}

	public void setAssetsTypeId(Integer assetsTypeId) {
		this.assetsTypeId = assetsTypeId;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAssetsTypeName() {
		return assetsTypeName;
	}

	public void setAssetsTypeName(String assetsTypeName) {
		this.assetsTypeName = assetsTypeName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getAssetsClassName() {
		return assetsClassName;
	}

	public void setAssetsClassName(String assetsClassName) {
		this.assetsClassName = assetsClassName;
	}

	public Integer getPurpose() {
		return purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

	public String getPurchaseTypeName() {
		return purchaseTypeName;
	}

	public void setPurchaseTypeName(String purchaseTypeName) {
		this.purchaseTypeName = purchaseTypeName;
	}

	public String getFundSourcesName() {
		return fundSourcesName;
	}

	public void setFundSourcesName(String fundSourcesName) {
		this.fundSourcesName = fundSourcesName;
	}

	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}

	public String getDepTypeName() {
		return depTypeName;
	}

	public void setDepTypeName(String depTypeName) {
		this.depTypeName = depTypeName;
	}

	public String getVerfyStatusName() {
		return verfyStatusName;
	}

	public void setVerfyStatusName(String verfyStatusName) {
		this.verfyStatusName = verfyStatusName;
	}

	public void setVerfyStatus(Integer verfyStatus) {
		this.verfyStatus = verfyStatus;
	}

	public String getMeasureTypeName() {
		return measureTypeName;
	}

	public void setMeasureTypeName(String measureTypeName) {
		this.measureTypeName = measureTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getManageDeptName() {
		return manageDeptName;
	}

	public void setManageDeptName(String manageDeptName) {
		this.manageDeptName = manageDeptName;
	}

	public String getManageLevelName() {
		return manageLevelName;
	}

	public void setManageLevelName(String manageLevelName) {
		this.manageLevelName = manageLevelName;
	}

	public String getLessPrice() {
		return lessPrice;
	}

	public void setLessPrice(String lessPrice) {
		this.lessPrice = lessPrice;
	}

	public String getOldYear() {
		return oldYear;
	}

	public void setOldYear(String oldYear) {
		this.oldYear = oldYear;
	}

	public String getThreeLevelCode() {
		return threeLevelCode;
	}

	public void setThreeLevelCode(String threeLevelCode) {
		this.threeLevelCode = threeLevelCode;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getAssetsSourceName() {
		return assetsSourceName;
	}

	public void setAssetsSourceName(String assetsSourceName) {
		this.assetsSourceName = assetsSourceName;
	}

	public Date getVerifyOperateTime() {
		return verifyOperateTime;
	}

	public void setVerifyOperateTime(Date verifyOperateTime) {
		this.verifyOperateTime = verifyOperateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public Integer getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(Integer madeIn) {
		this.madeIn = madeIn;
	}

	public Integer getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(Integer repairStatus) {
		this.repairStatus = repairStatus;
	}

	public String getRepairStatusName() {
		return repairStatusName;
	}

	public void setRepairStatusName(String repairStatusName) {
		this.repairStatusName = repairStatusName;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getProofDate() {
		return proofDate;
	}

	public void setProofDate(Date proofDate) {
		this.proofDate = proofDate;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Integer getPurchaseWay() {
		return purchaseWay;
	}

	public void setPurchaseWay(Integer purchaseWay) {
		this.purchaseWay = purchaseWay;
	}

	public Integer getTenderForm() {
		return tenderForm;
	}

	public void setTenderForm(Integer tenderForm) {
		this.tenderForm = tenderForm;
	}

	public Long getSingleBudget() {
		return singleBudget;
	}

	public void setSingleBudget(Long singleBudget) {
		this.singleBudget = singleBudget;
	}

	public Date getWinTenderDate() {
		return winTenderDate;
	}

	public void setWinTenderDate(Date winTenderDate) {
		this.winTenderDate = winTenderDate;
	}

	public String getApproveProjectAccord() {
		return approveProjectAccord;
	}

	public void setApproveProjectAccord(String approveProjectAccord) {
		this.approveProjectAccord = approveProjectAccord;
	}

	public String getAcceptancePersonName() {
		return acceptancePersonName;
	}

	public void setAcceptancePersonName(String acceptancePersonName) {
		this.acceptancePersonName = acceptancePersonName;
	}

	public String getAcceptanceDeptName() {
		return acceptanceDeptName;
	}

	public void setAcceptanceDeptName(String acceptanceDeptName) {
		this.acceptanceDeptName = acceptanceDeptName;
	}

	public Date getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(Date expectDate) {
		this.expectDate = expectDate;
	}

	public String getSingleBudgetStr() {
		return singleBudgetStr;
	}

	public void setSingleBudgetStr(String singleBudgetStr) {
		this.singleBudgetStr = singleBudgetStr;
	}

	public Integer getPollingFlag() {
		return pollingFlag;
	}

	public void setPollingFlag(Integer pollingFlag) {
		this.pollingFlag = pollingFlag;
	}

}