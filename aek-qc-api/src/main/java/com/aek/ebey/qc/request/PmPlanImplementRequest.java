package com.aek.ebey.qc.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@ApiModel(value = "PmPlanImplementRequest", description = "PM实施信息暂存提交")
public class PmPlanImplementRequest{

	@ApiModelProperty(value="实施id")
	private Long id;
	
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
	
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value="设备所在科室名称")
	private String departmentName;
	
	/**
	 * PM周期
	 */
	@ApiModelProperty(value="PM周期")
	private Integer cycle;

	/**
	 * PM等级
	 */
	@ApiModelProperty(value="PM等级")
	private Integer level;
	
	/**
	 * 负责人姓名
	 */
	@ApiModelProperty(value="负责人姓名")
	private String directorName;
	
	/**
	 * 上次实施时间
	 */
	@ApiModelProperty(value="上次实施时间")
	private Date prevDate;
	
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	private Date nextDate;
	
	/**
	 * 设备状态
	 */
	@ApiModelProperty(value="设备状态")
	private String equipmentStatus;
	
	/**
	 * 提示语
	 */
	@ApiModelProperty(value="提示语")
	private String attention;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public Date getPrevDate() {
		return prevDate;
	}

	public void setPrevDate(Date prevDate) {
		this.prevDate = prevDate;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

}
