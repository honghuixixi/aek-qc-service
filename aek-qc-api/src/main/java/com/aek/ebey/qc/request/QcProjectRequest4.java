package com.aek.ebey.qc.request;

import java.util.List;


public class QcProjectRequest4{
	/**
	 * 项目id
	 */
	private Long id; 
	
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 选项列表
	 */
	private List<QcOptionRequest3> projects;

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

	public List<QcOptionRequest3> getProjects() {
		return projects;
	}

	public void setProjects(List<QcOptionRequest3> projects) {
		this.projects = projects;
	}

	


	
}
