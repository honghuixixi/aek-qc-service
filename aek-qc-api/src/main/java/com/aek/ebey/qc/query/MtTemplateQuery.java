package com.aek.ebey.qc.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.qc.model.MtTemplate;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="模版分页查询类")
public class MtTemplateQuery extends PageHelp<MtTemplate> {

	@ApiModelProperty(value="输入关键字（模版名称）")
	private String keyword;
	@ApiModelProperty(value="停启用(0=停用,1=启用)")
	private Boolean enable;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
