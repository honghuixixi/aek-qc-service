package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.QcTemplateResponse;

public class QcTemplateQuery extends LiuHuiFrontPage<QcTemplateResponse>{

	/**
	 * 模板名称关键词
	 */
	private String keyword;
	
	/**
	 * 模板状态
	 */
	private Integer status;
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
