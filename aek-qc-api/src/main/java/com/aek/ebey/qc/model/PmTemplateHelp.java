package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * PM模版
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@TableName("pm_template_help")
public class PmTemplateHelp extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * ID主键
	 */
	private Long id;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
