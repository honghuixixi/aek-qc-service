package com.aek.ebey.qc.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;


public class QcChargeRequest extends BaseRequest{

	/**
	 * 负责人名称
	 */
	@NotEmpty(groups = {Add.class,Edit.class  }, message = "D_001")
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
