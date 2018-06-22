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
 * @since 2017-11-28
 */
@ApiModel(value = "PmPlanRequest", description = "PM计划信息")
public class PmPlanRequest extends BaseRequest {

	/**
	 * 设备信息
	 */
	@ApiModelProperty(value="设备信息")
	private List<PmAssets> equipmentIds;
	
	/**
	 * 关联模版id
	 */
	@ApiModelProperty(value="关联模版id")
	private Long templateId;
	/**
	 * 巡检周期
	 */
	@ApiModelProperty(value="巡检周期")
	private Integer cycle;
	
	/**
	 * PM等级
	 */
	@ApiModelProperty(value="PM等级")
	private Integer level;
	
	/**
	 * 实施开始日期
	 */
	@ApiModelProperty(value="实施开始日期")
	private Date startDate;
	
	/**
	 * 负责人
	 */
	@ApiModelProperty(value="负责人")
	private QcChargeRequest director;
	
	/**
	 * 验收人id
	 */
	@ApiModelProperty(value = "验收人id")
	private Long checkId;
	/**
	 * 验收人名称
	 */
	@ApiModelProperty(value = "验收人名称")
	private String checkName;

	public List<PmAssets> getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(List<PmAssets> equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public QcChargeRequest getDirector() {
		return director;
	}

	public void setDirector(QcChargeRequest director) {
		this.director = director;
	}

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	
}
