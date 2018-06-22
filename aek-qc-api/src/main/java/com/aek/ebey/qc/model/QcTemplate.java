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
 * @since 2017-11-02
 */
@TableName("qc_template")
public class QcTemplate extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
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
	/**
	 * 作废标记，0：启用，1：删除
	 */
	@TableField(value="del_flag")
	private Boolean delFlag;
	
	/**
	 * 是否有选项(0：没有，1：有)
	 */
	@TableField(value="has_project")
	private Boolean hasProject;
	


	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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


	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Boolean getHasProject() {
		return hasProject;
	}

	public void setHasProject(Boolean hasProject) {
		this.hasProject = hasProject;
	}

}
