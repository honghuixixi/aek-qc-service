package com.aek.ebey.qc.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("最大允差明细接收类")
public class MdMaximumAllowableErrorDetailBO {

	@ApiModelProperty("项目名称")
	private String itemName;
	@ApiModelProperty("项目描述")
	private String desc;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
