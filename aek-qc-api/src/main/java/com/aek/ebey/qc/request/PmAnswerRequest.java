package com.aek.ebey.qc.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "PmOptionAnswerRequest", description = "PmOptionAnswerRequest")
public class PmAnswerRequest {
	/**
	 * 项目ID
	 */
	@ApiModelProperty(value="项目ID")
	private Long id;
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value="项目名称")
	private String name;
	/**
	 * 选项
	 */
	@ApiModelProperty(value="选项")
	private List<PmOptionAnswerRequest> options;
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
	public List<PmOptionAnswerRequest> getOptions() {
		return options;
	}
	public void setOptions(List<PmOptionAnswerRequest> options) {
		this.options = options;
	}
	
	

}
