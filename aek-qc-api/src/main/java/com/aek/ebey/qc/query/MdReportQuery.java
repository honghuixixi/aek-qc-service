package com.aek.ebey.qc.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.qc.model.vo.MdReportPageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页请求类")
public class MdReportQuery extends PageHelp<MdReportPageVO>{

	@ApiModelProperty("质控单号/设备编号/设备名称")
	private String keyword;
	@ApiModelProperty("部门id")
	private Long deptId;
	@ApiModelProperty("填报时间开始")
	private String checkTimeStart;
	@ApiModelProperty("填报时间结束")
	private String checkTimeEnd;
	@ApiModelProperty("质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)")
	private Integer status;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	public String getCheckTimeStart() {
		return checkTimeStart;
	}
	public void setCheckTimeStart(String checkTimeStart) {
		this.checkTimeStart = checkTimeStart;
	}
	public String getCheckTimeEnd() {
		return checkTimeEnd;
	}
	public void setCheckTimeEnd(String checkTimeEnd) {
		this.checkTimeEnd = checkTimeEnd;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
