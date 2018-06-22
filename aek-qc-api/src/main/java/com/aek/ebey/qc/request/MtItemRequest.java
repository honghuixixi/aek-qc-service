package com.aek.ebey.qc.request;

import org.hibernate.validator.constraints.Length;

import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="项目请求")
public class MtItemRequest {

	@ApiModelProperty(value="项目id")
	private Long id;
	@Length(max = 40, groups = Edit.class, message = "M_011")
	@ApiModelProperty(value="项目名称")
	private String itemName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
