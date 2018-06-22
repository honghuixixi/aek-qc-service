package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

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
@ApiModel(value = "PmPlanDetail", description = "PM计划详情")
public class PmPlanDetail{
	
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
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	private Date nextDate;
	
	/**
	 * 上次实施时间
	 */
	@ApiModelProperty(value="上次实施时间")
	private Date prevDate;
	/**
	 * 状态 1:启用、2:停用
	 */
	@ApiModelProperty(value="状态 1:启用、2:停用")
	private Integer status;
	
	/**
	 * 巡检模板
	 */
	@ApiModelProperty(value="巡检模板")
	private List<PmProjectDetail> template;
	
	/**
	 * 验收人名称
	 */
	@ApiModelProperty(value = "验收人名称")
	private String checkName;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public Date getPrevDate() {
		return prevDate;
	}

	public void setPrevDate(Date prevDate) {
		this.prevDate = prevDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<PmProjectDetail> getTemplate() {
		return template;
	}

	public void setTemplate(List<PmProjectDetail> template) {
		this.template = template;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	
}
