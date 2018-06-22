package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * PM答案
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@TableName("pm_answers")
public class PmAnswers extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 关联实施id
	 */
	@TableField(value="implement_id")
	private Long implementId;
	/**
	 * 项目id
	 */
	@TableField(value="project_id")
	private Long projectId;
	/**
	 * 项目名称
	 */
	@TableField(value="project_name")
	private String projectName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImplementId() {
		return implementId;
	}

	public void setImplementId(Long implementId) {
		this.implementId = implementId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
}
