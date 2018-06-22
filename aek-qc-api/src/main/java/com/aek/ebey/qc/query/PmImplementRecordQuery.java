package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.PmImplementRecord;

public class PmImplementRecordQuery extends LiuHuiFrontPage<PmImplementRecord>{
	
	/**
	 * 计划id
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
