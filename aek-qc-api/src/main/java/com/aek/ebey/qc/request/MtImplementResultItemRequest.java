package com.aek.ebey.qc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实施结果附件请求实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtImplementResultItemRequest", description = "MtImplementResultItemRequest")
public class MtImplementResultItemRequest {
	
	/**
	 * 保养计划模板ID 
	 */
	@ApiModelProperty(value="保养计划模板ID")
	private Long planTemplateId;
	/**
	 * 保养计划模板项目ID
	 */
	@ApiModelProperty(value="保养计划模板ID")
	private Long planTemplateItemId;
	/**
	 * 保养计划模板项目名称
	 */
	@ApiModelProperty(value="保养计划模板项目名称")
	private String planTemplateItemName;
	/**
	 * 保养计划模板项目结果(1=正常，2=不正常)
	 */
	@ApiModelProperty(value="保养计划模板项目结果(1=正常，2=不正常)")
	private Integer itemResult;
	/**
	 * 保养计划模板项目结果备注
	 */
	@ApiModelProperty(value="保养计划模板项目结果备注")
	private String itemRemarks;
	
	public Long getPlanTemplateId() {
		return planTemplateId;
	}
	public void setPlanTemplateId(Long planTemplateId) {
		this.planTemplateId = planTemplateId;
	}
	public Long getPlanTemplateItemId() {
		return planTemplateItemId;
	}
	public void setPlanTemplateItemId(Long planTemplateItemId) {
		this.planTemplateItemId = planTemplateItemId;
	}
	public String getPlanTemplateItemName() {
		return planTemplateItemName;
	}
	public void setPlanTemplateItemName(String planTemplateItemName) {
		this.planTemplateItemName = planTemplateItemName;
	}
	public Integer getItemResult() {
		return itemResult;
	}
	public void setItemResult(Integer itemResult) {
		this.itemResult = itemResult;
	}
	public String getItemRemarks() {
		return itemRemarks;
	}
	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}
	
}
