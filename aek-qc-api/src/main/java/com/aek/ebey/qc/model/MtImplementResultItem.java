package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 保养实施项目结果表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@TableName("mt_implement_result_item")
public class MtImplementResultItem extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 保养实施结果ID
	 */
	@TableField(value="result_id")
	private Long resultId;
	/**
	 * 保养计划模板ID 
	 */
	@TableField(value="plan_template_id")
	private Long planTemplateId;
	/**
	 * 保养计划模板项目ID
	 */
	@TableField(value="plan_template_item_id")
	private Long planTemplateItemId;
	/**
	 * 保养计划模板项目名称
	 */
	@TableField(value="plan_template_item_name")
	private String planTemplateItemName;
	/**
	 * 保养计划模板项目结果(1=正常，2=不正常)
	 */
	@TableField(value="item_result")
	private Integer itemResult;
	/**
	 * 保养计划模板项目结果备注
	 */
	@TableField(value="item_remarks")
	private String itemRemarks;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

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
