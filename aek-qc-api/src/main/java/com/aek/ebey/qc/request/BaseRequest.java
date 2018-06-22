package com.aek.ebey.qc.request;

import javax.validation.constraints.NotNull;

import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseRequest {

	/**
	 * ID
	 */
	@NotNull(groups = Edit.class, message = "G_001")
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

}
