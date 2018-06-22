package com.aek.ebey.qc.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PmProjectDetail", description = "PM项目信息")
public class PmProjectDetail2{
	
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value="项目名称")
	private String name;
	
	/**
	 * 选项列表
	 */
	@ApiModelProperty(value="选项列表")
	private List<PmOptionAnswerRequest> options;


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
