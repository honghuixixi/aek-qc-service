package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 巡检模版
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@TableName("qc_template_help")
public class QcTemplateHelp extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 状态，1:系统、2:自定义
	 */
	@TableField(value="template_type")
	private Integer templateType;
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
