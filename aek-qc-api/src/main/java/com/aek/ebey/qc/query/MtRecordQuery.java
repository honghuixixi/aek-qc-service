package com.aek.ebey.qc.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.qc.model.MtImplementResult;

public class MtRecordQuery extends PageHelp<MtImplementResult>{
	
	private Long planId;

	private String startDate;
	
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	
}
