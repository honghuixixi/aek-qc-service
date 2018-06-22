package com.aek.ebey.qc.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QcTemplateRequest", description = "模板信息")
public class QcTemplateRequest extends BaseRequest{

	/**
	 * 模板名称
	 */
	@ApiModelProperty(value="模板名称")
	@Length(max = 40, groups = {Add.class,Edit.class  }, message = "Q_001")
	@NotEmpty(groups = {Add.class,Edit.class  }, message = "D_001")
	private String name;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	@Length(max = 40, groups = {Add.class,Edit.class  }, message = "Q_001")
	private String remarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
