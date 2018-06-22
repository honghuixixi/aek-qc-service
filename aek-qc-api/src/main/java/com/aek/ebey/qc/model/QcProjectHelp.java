package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@TableName("qc_project_help")
public class QcProjectHelp extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 关联模版id
	 */
	@TableField(value="template_id")
	private Long templateId;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;


	
	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
