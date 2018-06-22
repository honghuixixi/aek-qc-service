package com.aek.ebey.qc.model.bo;

import java.io.Serializable;

import com.aek.ebey.qc.model.MdReport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("质控报告传递类")
public class MdReportBO extends MdReport implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("质控报告模板Id")
	private Long reportTemplateId;

	public Long getReportTemplateId() {
		return reportTemplateId;
	}

	public void setReportTemplateId(Long reportTemplateId) {
		this.reportTemplateId = reportTemplateId;
	}
	
}
