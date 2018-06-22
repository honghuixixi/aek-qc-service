package com.aek.ebey.qc.request;

public class QcTemplateResponse{
	
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
	 * 模板类型
	 */
	private String type;
	
	/**
	 * 模板状态
	 */
	private Integer status;
	
	/**
	 * 状态，1:系统、2:自定义
	 */
	private Integer templateType;
	
	

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}


	
	
	

}
