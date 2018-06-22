package com.aek.ebey.qc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PmProjectDetail", description = "PM项目信息")
public class PmProjectDetail{
	
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value="项目名称")
	private String name;
	
	/**
	 * 选项列表
	 */
	@ApiModelProperty(value="选项列表")
	private String[] options;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	
}
