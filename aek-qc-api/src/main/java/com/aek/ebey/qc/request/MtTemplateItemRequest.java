package com.aek.ebey.qc.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.validator.group.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="添加模版项目请求")
public class MtTemplateItemRequest {

	@ApiModelProperty("模版id")
	@NotNull(groups=Add.class,message="M_002")
	private Long templateId;
	@ApiModelProperty("项目集合")
	private List<MtTemplateItem> items;
	
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public List<MtTemplateItem> getItems() {
		return items;
	}
	public void setItems(List<MtTemplateItem> items) {
		this.items = items;
	}
	
}
