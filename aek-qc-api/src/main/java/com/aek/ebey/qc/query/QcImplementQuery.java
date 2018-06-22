package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.QcImplementResponse;

public class QcImplementQuery extends LiuHuiFrontPage<QcImplementResponse>{

	/**
	 * 巡检名称/单号关键词
	 */
	private String keyword;
	
	/**
	 * 巡检状态    状态 1:启用、2:停用
	 */
	private int status;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	

}
