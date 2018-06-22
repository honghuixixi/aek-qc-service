package com.aek.ebey.qc.request;

import java.util.List;

public class QcTemplateDetail{

	/**
	 * 模板名称
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 模板状态
	 */
	private String status;
	
	/**
	 * 模板leiixng
	 */
	private Integer type;
	
	
	/**
	 * 模板状态
	 */
	private List<QcProjectRequest3> projects;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<QcProjectRequest3> getProjects() {
		return projects;
	}

	public void setProjects(List<QcProjectRequest3> projects) {
		this.projects = projects;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
