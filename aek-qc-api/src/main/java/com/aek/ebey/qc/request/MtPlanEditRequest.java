package com.aek.ebey.qc.request;

import java.util.Date;

public class MtPlanEditRequest {

	private Long id;
	
	private Date nextImplementTime;
	
	private String administrator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNextImplementTime() {
		return nextImplementTime;
	}

	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	
	
}
