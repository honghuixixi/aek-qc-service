package com.aek.ebey.qc.request;

public class QcOptionRequest3{
	/**
	 * 选项id
	 */
	private Long id;

	/**
	 * 选项名称
	 */
	private String name;
	
	/**
	 * 默认选中
	 */
	private Boolean isDefault;

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

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	

}
