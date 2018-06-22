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
 * @since 2017-11-28
 */
@ApiModel(value = "PmPlanEditRequest", description = "PM计划编辑信息")
public class PmPlanEditRequest extends BaseRequest {

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
	 * 下次实施日期
	 */
	@ApiModelProperty(value="下次实施日期")
	private Date nextDate;
	
	/**
	 * 负责人
	 */
	@ApiModelProperty(value="负责人")
	private QcChargeRequest director;
	
	/**
	 * 验收人
	 */
	@ApiModelProperty(value="验收人")
	private QcChargeRequest check;

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

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public QcChargeRequest getDirector() {
		return director;
	}

	public void setDirector(QcChargeRequest director) {
		this.director = director;
	}

	public QcChargeRequest getCheck() {
		return check;
	}

	public void setCheck(QcChargeRequest check) {
		this.check = check;
	}

	
}
