package com.aek.ebey.qc.request;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class QcPlanRequest extends BaseRequest {

	/**
	 * 巡检名称
	 */
	private String name;
	/**
	 * 巡检类型(1，按科室巡检）
	 */
	private Integer type;
	
	/**
	 * 科室列表
	 */
	private List<QcDeptRequest> scope;
	
	/**
	 * 关联模版id
	 */
	private Long templateId;
	
	/**
	 * 巡检周期
	 */
	private Integer cycle;
	
	/**
	 * 巡检周期类型(1，月 2，天)
	 */
	private Integer cycleType;
	
	/**
	 * 首次巡检开始时间
	 */
	private Date date;
	
	/**
	 * 负责人
	 */
	private QcChargeRequest director;
	
	/**
	 * 计划验收人
	 */
	private List<QcChargeRequest> checkMan;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<QcDeptRequest> getScope() {
		return scope;
	}

	public void setScope(List<QcDeptRequest> scope) {
		this.scope = scope;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public QcChargeRequest getDirector() {
		return director;
	}

	public void setDirector(QcChargeRequest director) {
		this.director = director;
	}

	public List<QcChargeRequest> getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(List<QcChargeRequest> checkMan) {
		this.checkMan = checkMan;
	}
	
	

	
}
