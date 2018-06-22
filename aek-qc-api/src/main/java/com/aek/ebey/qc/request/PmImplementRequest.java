package com.aek.ebey.qc.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@ApiModel(value = "PmImplementRequest", description = "PM实施暂存Or提交")
public class PmImplementRequest {
	/**
	 * 实施id
	 */
	@ApiModelProperty(value="实施id")
	private Long id;
	
	/**
	 * 1:暂存 2：提交
	 */
	@ApiModelProperty(value="1:暂存 2：提交")
	private Integer type;
	/**
	 * 下次实施日期（时间戳）
	 */
	@ApiModelProperty(value="下次实施日期（时间戳）")
	private Date nextDate;
	/**
	 * template
	 */
	@ApiModelProperty(value="template")
	private List<PmAnswerRequest> template;
	
	/**
	 * 设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用
	 */
	@ApiModelProperty(value="设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用")
	private Integer live;
	/**
	 * 工时
	 */
	@ApiModelProperty(value="下工时")
	private BigDecimal workTime;
	
	/**
	 * 文件
	 */
	@ApiModelProperty(value="文件")
	private List<PmImplementFileRequest> files;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	/**
	 * 实际开始日期
	 */
	@ApiModelProperty(value="实际开始日期")
	private Date  actualStartDate;
	
	/**
	 * 实际结束日期
	 */
	@ApiModelProperty(value="实际结束日期")
	private Date  actualEndDate;
	
	/**
	 * 计划验收人
	 */
	@ApiModelProperty(value = "计划验收人")
	private QcChargeRequest checkMan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public List<PmAnswerRequest> getTemplate() {
		return template;
	}

	public void setTemplate(List<PmAnswerRequest> template) {
		this.template = template;
	}

	public Integer getLive() {
		return live;
	}

	public void setLive(Integer live) {
		this.live = live;
	}

	public BigDecimal getWorkTime() {
		return workTime;
	}

	public void setWorkTime(BigDecimal workTime) {
		this.workTime = workTime;
	}

	public List<PmImplementFileRequest> getFiles() {
		return files;
	}

	public void setFiles(List<PmImplementFileRequest> files) {
		this.files = files;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public QcChargeRequest getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(QcChargeRequest checkMan) {
		this.checkMan = checkMan;
	}
	
}
