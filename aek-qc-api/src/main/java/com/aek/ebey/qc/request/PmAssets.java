package com.aek.ebey.qc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@ApiModel(value = "PmAssets", description = "PM设备信息")
public class PmAssets extends BaseRequest {
	/**
	 * 设备编号
	 */
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
	
	@ApiModelProperty(value="设备所在科室")
	private Long departmentId;
	
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value="设备所在科室名称")
	private String departmentName;
	
	/**
	 * 设备状态
	 */
	@ApiModelProperty(value="设备状态")
	private Integer equipmentStatusType;
	
	/**
	 * 设备状态
	 */
	@ApiModelProperty(value="设备状态")
	private String equipmentStatusText;
	
	

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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getEquipmentStatusType() {
		return equipmentStatusType;
	}

	public void setEquipmentStatusType(Integer equipmentStatusType) {
		this.equipmentStatusType = equipmentStatusType;
	}

	public String getEquipmentStatusText() {
		return equipmentStatusText;
	}

	public void setEquipmentStatusText(String equipmentStatusText) {
		this.equipmentStatusText = equipmentStatusText;
	}
	
}
