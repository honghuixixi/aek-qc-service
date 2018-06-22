package com.aek.ebey.qc.model.vo;

import java.util.Date;
import java.util.List;

import com.aek.ebey.qc.model.MtPlanTemplateItem;
import com.aek.ebey.qc.request.MtPlanRequest;

import io.swagger.annotations.ApiModelProperty;

public class MtPlanDetailVO extends MtPlanRequest {
 
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="上次实施时间")
	private Date lastImplementTime;
	
	private List<MtPlanTemplateItem> mtPlanTemplateItems;

	public Date getLastImplementTime() {
		return lastImplementTime;
	}

	public void setLastImplementTime(Date lastImplementTime) {
		this.lastImplementTime = lastImplementTime;
	}

	public List<MtPlanTemplateItem> getMtPlanTemplateItems() {
		return mtPlanTemplateItems;
	}

	public void setMtPlanTemplateItems(List<MtPlanTemplateItem> mtPlanTemplateItems) {
		this.mtPlanTemplateItems = mtPlanTemplateItems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
