package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实施结果请求实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtImplementResultRequest", description = "MtImplementResultRequest")
public class MtImplementResultRequest {

	/**
	 * 保养计划实施ID
	 */
	@ApiModelProperty(value="保养计划实施ID")
	private Long planImplementId;
	/**
	 * 保养计划ID
	 */
	@ApiModelProperty(value="保养计划ID")
	private Long planId;
	/**
	 * 保养实施实际时间
	 */
	@ApiModelProperty(value="保养实施实际时间")
	private Date actualEndDate;
	/**
	 * 实施结束时间
	 */
	@ApiModelProperty(value="实施结束时间")
	private Date endDate;
	/**
	 * 设备现状(1=正常工作,2=小问题不影响使用,3=有故障需要维修,4=不能使用)
	 */
	@ApiModelProperty(value="设备现状(1=正常工作,2=小问题不影响使用,3=有故障需要维修,4=不能使用)")
	private Integer assetsStatus;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	private Date nextImplementTime;

	/**
	 * 附件
	 */
	@ApiModelProperty(value="附件")
	private List<MtImplementResultFileRequest> files;
	
	/**
	 * 保养项目结果集合
	 */
	@ApiModelProperty(value="保养项目结果集合")
	private List<MtImplementResultItemRequest> templateItemResults;

	public Long getPlanImplementId() {
		return planImplementId;
	}

	public void setPlanImplementId(Long planImplementId) {
		this.planImplementId = planImplementId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<MtImplementResultFileRequest> getFiles() {
		return files;
	}

	public void setFiles(List<MtImplementResultFileRequest> files) {
		this.files = files;
	}

	public List<MtImplementResultItemRequest> getTemplateItemResults() {
		return templateItemResults;
	}

	public void setTemplateItemResults(List<MtImplementResultItemRequest> templateItemResults) {
		this.templateItemResults = templateItemResults;
	}

	public Date getNextImplementTime() {
		return nextImplementTime;
	}

	public void setNextImplementTime(Date nextImplementTime) {
		this.nextImplementTime = nextImplementTime;
	}
	
}
