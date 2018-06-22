package com.aek.ebey.qc.request;

import java.util.List;

import com.aek.ebey.qc.model.MtAssets;
import com.aek.ebey.qc.model.MtPlan;

public class MtPlanRequest extends MtPlan {

	private static final long serialVersionUID = 1L;
	
	private List<MtAssets> mtAssets;

	public List<MtAssets> getMtAssets() {
		return mtAssets;
	}

	public void setMtAssets(List<MtAssets> mtAssets) {
		this.mtAssets = mtAssets;
	}
	
}
