package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * PM项目选项
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
@TableName("pm_option")
public class PmOption extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * ID主键
	 */
	private Long id;
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
	 * 作废标记，0：启用，1：删除
	 */
	@TableField(value="del_flag")
	private Boolean delFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	

}
