package com.aek.ebey.qc.request;

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
@ApiModel(value = "QcImplementSubmitRequest")
public class QcImplementSubmitRequest {
	/**
	 * 关联巡检计划id
	 */
	private Long id;
	/**
	 * 下次巡检日期（时间戳）
	 */
	private Date nexDate;
	
	/**
	 * 时间结束时间
	 */
	private Date actualEndDate;
	
	
	/**
	 * 下次实施人
	 */
	@ApiModelProperty(value = "下次实施人")
	private QcChargeRequest nextChargeMan;
	
	/**
	 * 计划验收人
	 */
	@ApiModelProperty(value = "计划验收人")
	private List<QcChargeRequest> checkMan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getNexDate() {
		return nexDate;
	}
	public void setNexDate(Date nexDate) {
		this.nexDate = nexDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public List<QcChargeRequest> getCheckMan() {
		return checkMan;
	}
	public void setCheckMan(List<QcChargeRequest> checkMan) {
		this.checkMan = checkMan;
	}
	public QcChargeRequest getNextChargeMan() {
		return nextChargeMan;
	}
	public void setNextChargeMan(QcChargeRequest nextChargeMan) {
		this.nextChargeMan = nextChargeMan;
	}
	
	
}
