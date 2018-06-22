package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.QcAssetsResponse;

public class QcAssetsByImplementIdQuery extends LiuHuiFrontPage<QcAssetsResponse>{

	/**
	 *实施id
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
