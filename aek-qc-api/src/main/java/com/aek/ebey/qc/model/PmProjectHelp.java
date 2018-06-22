package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * PM项目
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@TableName("pm_project_help")
public class PmProjectHelp extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * ID主键
	 */
	private Long id;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
