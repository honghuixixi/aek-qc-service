package com.aek.ebey.qc.request;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@ApiModel(value = "QcReportResponse", description = "QcReportResponse")
public class QcReportResponse{
	
	@ApiModelProperty(value="实施id")
	private Long id;
	/**
	 * 报告单号
	 */
	@ApiModelProperty(value="报告单号")
	private String reportNo;
	
	/**
	 * 巡检名称
	 */
	@ApiModelProperty(value="巡检名称")
	private String name;
	
	/**
	 * 巡检类型(1，按科室巡检）
	 */
	@ApiModelProperty(value="巡检类型(1，按科室巡检）")
	private Integer planType;
	
	
	/**
	 * 巡检周期
	 */
	@ApiModelProperty(value="巡检周期")
	private Integer planCycle;
	
	/**
	 * 巡检周期类型(1，月 2，天)
	 */
	@ApiModelProperty(value="巡检周期类型(1，月 2，天)")
	private Integer cycleType;
	
	/**
	 * 负责人姓名
	 */
	@ApiModelProperty(value="负责人姓名")
	private String chargeName;
	
	/**
	 * 提交日期
	 */
	@ApiModelProperty(value="提交日期")
	private Date endDate;
	

	/**
	 * 
	 * 验收状态（1，待验收 2，已验收）
	 */
	@ApiModelProperty(value="验收状态（1，待验收 2，已验收）")
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public Integer getPlanCycle() {
		return planCycle;
	}

	public void setPlanCycle(Integer planCycle) {
		this.planCycle = planCycle;
	}

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
