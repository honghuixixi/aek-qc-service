package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

import com.aek.ebey.qc.model.MtImplementResultFile;
import com.aek.ebey.qc.model.MtImplementResultItem;
import com.baomidou.mybatisplus.annotations.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实施结果返回实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtImplementResultResponse", description = "MtImplementResultResponse")
public class MtImplementResultResponse {

	/**
	 * 实施结果ID
	 */
	@ApiModelProperty(value="实施结果ID")
	private Long id;
	
	/**
	 * 保养报告编号
	 */
	@ApiModelProperty(value="保养报告编号")
	private String reportNo;
	
	/**
	 * 保养实施实际结束时间
	 */
	@ApiModelProperty(value="保养实施实际结束时间")
	private Date actualEndDate;

	/**
	 * 实施结束时间
	 */
	@ApiModelProperty(value="实施结束时间")
	private Date endDate;
	
	/**
	 * 设备现状(1=正常工作,2=小问题不影响使用,3=有故障需要维修,4=不能使用)
	 */
	@ApiModelProperty(value="设备现状(1=正常工作,2=小问题不影响使用,3=有故障需要维修,4=不能使用)")
	private Integer assetsStatus;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	/**
	 * 实施人姓名
	 */
	@ApiModelProperty(value="实施人姓名")
	private String implementUserName;
	
	/**
	 * 实施状态(1=实施中，2=已实施)
	 */
	@ApiModelProperty(value="实施状态(1=实施中，2=已实施)")
	private Integer status;
	
	/**
	 * 实施附件集合
	 */
	@ApiModelProperty(value="实施附件集合")
	private List<MtImplementResultFile> files;
	
	/**
	 * 实施项目结果集合
	 */
	@ApiModelProperty(value="实施项目结果集合")
	private List<MtImplementResultItem> items;
	
	/**
	 * 资产ID
	 */
	@ApiModelProperty(value="资产ID")
	private Long assetsId;
	/**
	 * 资产编号
	 */
	@ApiModelProperty(value="资产编号")
	private String assetsNum;
	/**
	 * 资产院内编码
	 */
	@ApiModelProperty(value="资产院内编码")
	private String hospitalAssetsNum;
	/**
	 * 资产名称
	 */
	@ApiModelProperty(value="资产名称")
	private String assetsName;
	/**
	 * 设备型号
	 */
	@ApiModelProperty(value="设备型号")
	private String assetsSpec;
	/**
	 * 设备所在科室ID
	 */
	@ApiModelProperty(value="设备所在科室ID")
	private Long assetsDeptId;
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value="设备所在科室名称")
	private String assetsDeptName;
	
	/**
	 * 保养频率(1=每天，2=每周)
	 */
	@ApiModelProperty(value="保养频率(1=每天，2=每周)")
	private Integer rate;
	
	/**
	 * 专管人
	 */
	@ApiModelProperty(value="专管人")
	private String administrator;
	
	/**
	 * 上次实施时间
	 */
	@ApiModelProperty(value="上次实施时间")
	private Date lastImplementTime;
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	private Date nextImplementTime;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 所属机构名称
	 */
	@ApiModelProperty(value="所属机构名称")
	private String tenantName;

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

	public String getImplementUserName() {
		return implementUserName;
	}

	public void setImplementUserName(String implementUserName) {
		this.implementUserName = implementUserName;
	}

	public List<MtImplementResultFile> getFiles() {
		return files;
	}

	public void setFiles(List<MtImplementResultFile> files) {
		this.files = files;
	}

	public List<MtImplementResultItem> getItems() {
		return items;
	}

	public void setItems(List<MtImplementResultItem> items) {
		this.items = items;
	}

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public String getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}

	public String getHospitalAssetsNum() {
		return hospitalAssetsNum;
	}

	public void setHospitalAssetsNum(String hospitalAssetsNum) {
		this.hospitalAssetsNum = hospitalAssetsNum;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getAssetsSpec() {
		return assetsSpec;
	}

	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}

	public Long getAssetsDeptId() {
		return assetsDeptId;
	}

	public void setAssetsDeptId(Long assetsDeptId) {
		this.assetsDeptId = assetsDeptId;
	}

	public String getAssetsDeptName() {
		return assetsDeptName;
	}

	public void setAssetsDeptName(String assetsDeptName) {
		this.assetsDeptName = assetsDeptName;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public Date getLastImplementTime() {
		return lastImplementTime;
	}

	public void setLastImplementTime(Date lastImplementTime) {
		this.lastImplementTime = lastImplementTime;
	}

	public Date getNextImplementTime() {
		return nextImplementTime;
	}

	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
}
