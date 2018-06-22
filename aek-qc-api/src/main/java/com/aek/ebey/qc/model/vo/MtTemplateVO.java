package com.aek.ebey.qc.model.vo;

import java.util.List;

import com.aek.ebey.qc.model.MtTemplate;
import com.aek.ebey.qc.model.MtTemplateItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="保养模版详情")
public class MtTemplateVO extends MtTemplate {

	private static final long serialVersionUID = 1L; 
	
	@ApiModelProperty(value="保养模板项目内容")
	private List<MtTemplateItem> items;

	public List<MtTemplateItem> getItems() {
		return items;
	}

	public void setItems(List<MtTemplateItem> items) {
		this.items = items;
	}
	
}
