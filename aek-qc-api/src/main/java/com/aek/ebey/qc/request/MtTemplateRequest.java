package com.aek.ebey.qc.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MtTemplateRequest", description = "MtTemplateRequest")
public class MtTemplateRequest {

	@ApiModelProperty(value="保养模版id")
	@NotNull(groups = Edit.class, message = "M_002")
	private Long id;
	
	@ApiModelProperty(value="保养模版名称")
	@NotNull(groups = {Add.class,Edit.class}, message = "M_001")
	@Length(max = 40, groups = {Add.class,Edit.class}, message = "M_003")
	private String name;
	
	@ApiModelProperty(value="保养模版备注")
	@Length(max = 40, groups = {Add.class,Edit.class}, message = "M_004")
	private String remarks;

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
	
	
}
