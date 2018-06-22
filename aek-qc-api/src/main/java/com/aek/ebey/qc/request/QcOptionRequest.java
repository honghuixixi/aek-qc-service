package com.aek.ebey.qc.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;


public class QcOptionRequest{
	
	private Long id;

	/**
	 * 模板名称
	 */
	@Length(max = 40, groups = {Add.class,Edit.class  }, message = "Q_001")
	@NotEmpty(groups = {Add.class,Edit.class  }, message = "D_001")
	private String name;
	
	/**
	 * 默认选中
	 */
	private Boolean isDefault;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
