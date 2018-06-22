package com.aek.ebey.qc.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class PmImplementSheet{
	
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
	private List<PmProjectDetail> items;


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


	public List<PmProjectDetail> getItems() {
		return items;
	}


	public void setItems(List<PmProjectDetail> items) {
		this.items = items;
	}
	
	
}
