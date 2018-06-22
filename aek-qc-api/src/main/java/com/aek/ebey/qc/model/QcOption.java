package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
@TableName("qc_option")
public class QcOption extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 关联项目 id
	 */
	@TableField(value="project_id")
	private Long projectId;
	/**
	 * 选项名称
	 */
	private String name;
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;
	/**
	 * 是否默认，0：否，1：是
	 */
	@TableField(value="is_default")
	private Boolean haveDefault;
	/**
	 * 作废标记，0：启用，1：删除
	 */
	@TableField(value="del_flag")
	private Boolean delFlag;


	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public Boolean getHaveDefault() {
		return haveDefault;
	}

	public void setHaveDefault(Boolean haveDefault) {
		this.haveDefault = haveDefault;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}


}
