package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 质控报告模板表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@TableName("md_report_template")
public class MdReportTemplate extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 系统模板ID
	 */
	@TableField(value="source_template_id")
	private Long sourceTemplateId;
	/**
	 * 质控报告ID
	 */
	@TableField(value="md_report_id")
	private Long mdReportId;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 启用标记(0=停用，1=启用)
	 */
	private Boolean enable;
	/**
	 * 创建人ID
	 */
	@TableField(value="create_by")
	private Long createBy;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 更新人ID
	 */
	@TableField(value="update_by")
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@TableField(value="update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSourceTemplateId() {
		return sourceTemplateId;
	}

	public void setSourceTemplateId(Long sourceTemplateId) {
		this.sourceTemplateId = sourceTemplateId;
	}

	public Long getMdReportId() {
		return mdReportId;
	}

	public void setMdReportId(Long mdReportId) {
		this.mdReportId = mdReportId;
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
