package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.QcAssetsResponse2;

public class QcAssetsQuery2 extends LiuHuiFrontPage<QcAssetsResponse2>{

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 计划id
	 */
	private Long id;
	
	/**
	 * 取值 1:待巡检 2:已巡检
	 */
	private int status;
	/**
	 * 搜索关键词
	 */
	private String keyword;

	



}
