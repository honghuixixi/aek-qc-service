package com.aek.ebey.qc.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "PmTemplateDetail", description = "PM模板信息")
public class PmTemplateDetail extends BaseRequest{

	/**
	 * 模板名称
	 */
	@ApiModelProperty(value="模板名称")
	private String name;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;

	/**
	 * 模板状态
	 */
	@ApiModelProperty(value="模板状态 1：启用 2：停用")
	private Integer status;
	
	/**
	 * 模板类型1：系统模板 2：自定义
	 */
	@ApiModelProperty(value="模板类型1：系统模板 2：自定义")
	private Integer type;
	
	
	/**
	 * 模板状态
	 */
	@ApiModelProperty(value="项目列表")
	private List<PmProjectRequest> items;


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


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public List<PmProjectRequest> getItems() {
		return items;
	}


	public void setItems(List<PmProjectRequest> items) {
		this.items = items;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}

	
}
