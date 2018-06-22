package com.aek.ebey.qc.request;

import java.util.Date;

public class PmEnable {
	/**
	 * 计划id
	 */
	private Long id;
	
	/**
	 * 下次实施日期
	 */
	private Date nextDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}
	

}
