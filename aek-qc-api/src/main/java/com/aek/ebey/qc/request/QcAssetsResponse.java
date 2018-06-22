package com.aek.ebey.qc.request;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public class QcAssetsResponse {
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 设备名称
	 */
	private String name;
	
	/**
	 * 设备所在科室名称
	 */
	private String department;
	
	/**
	 * 设备型号
	 */
	private String model;
	
	/**
	 * 设备编号
	 */
	private String equipmentNo;
	
	
	/**
	 * 设备id
	 */
	private Long id;
	
	/**
	 * 答案
	 */
	private String answers;


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getEquipmentNo() {
		return equipmentNo;
	}


	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAnswers() {
		return answers;
	}


	public void setAnswers(String answers) {
		this.answers = answers;
	}
	


}
