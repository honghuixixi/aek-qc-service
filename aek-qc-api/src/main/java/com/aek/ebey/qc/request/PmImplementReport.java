package com.aek.ebey.qc.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PmImplementReport{
	
	/**
	 * 医院名称
	 */
	@ApiModelProperty(value="医院名称")
	private String hospital;
	
	@ApiModelProperty(value="设备编号")
	private String no;
	
	/**
	 * 设备名称
	 */
	@ApiModelProperty(value="设备名称")
	private String name;
	/**
	 * 设备型号
	 */
	@ApiModelProperty(value="设备型号")
	private String model;
	
	/**
	 * PM周期
	 */
	@ApiModelProperty(value="PM周期")
	private Integer cycle;
	
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value="设备所在科室名称")
	private String departmentName;
	
	/**
	 * 负责人姓名
	 */
	@ApiModelProperty(value="负责人姓名")
	private String directorName;
	

	/**
	 * items
	 */
	@ApiModelProperty(value="items")
	private List<PmProjectDetail2> items;
	/**
	 * 设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用
	 */
	@ApiModelProperty(value="设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用")
	private Integer live;
	/**
	 * 工时
	 */
	@ApiModelProperty(value="下工时")
	private BigDecimal workTime;
	
	/**
	 * 文件
	 */
	@ApiModelProperty(value="文件")
	private List<PmFile> files;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	
	/**
	 * 报告编号
	 */
	@ApiModelProperty(value="报告编号")
	private String reportNo;
	
	

	/**
	 * 开始日期
	 */
	@ApiModelProperty(value="开始日期")
	private Date startDate;
	
	/**
	 * 结束日期
	 */
	@ApiModelProperty(value="结束日期")
	private Date endDate;
	
	/**
	 * 验收人
	 */
	@ApiModelProperty(value = "验收人")
	private String checkMan;
	
	
	/**
	 * 已验收人
	 */
	@ApiModelProperty(value = "已验收人")
	private String checkedMan;
	/**
	 * 状态(1，待验收 )
	 */
	@ApiModelProperty(value = "状态(1，待验收 )")
	private Integer status;

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}


	public List<PmProjectDetail2> getItems() {
		return items;
	}

	public void setItems(List<PmProjectDetail2> items) {
		this.items = items;
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


	public List<PmFile> getFiles() {
		return files;
	}

	public void setFiles(List<PmFile> files) {
		this.files = files;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
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

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public String getCheckedMan() {
		return checkedMan;
	}

	public void setCheckedMan(String checkedMan) {
		this.checkedMan = checkedMan;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
