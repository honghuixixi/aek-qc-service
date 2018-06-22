package com.aek.ebey.qc.request;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class QcImplementSheet{
	
	/**
	 * 医院名称
	 */
	private String hospital;
	
	/**
	 * 计划编号
	 */
	private String planNo;
	
	/**
	 * 巡检科室
	 */
	private String scope;
	
	/**
	 * 负责人
	 */
	private String director;
	
	/**
	 * 设备列表
	 */
	private List<QcAssetsResponse> records;
	/**
	 * 巡检模板
	 */
	private List<QcProjectRequest4> template;
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public List<QcAssetsResponse> getRecords() {
		return records;
	}
	public void setRecords(List<QcAssetsResponse> records) {
		this.records = records;
	}
	public List<QcProjectRequest4> getTemplate() {
		return template;
	}
	public void setTemplate(List<QcProjectRequest4> template) {
		this.template = template;
	}
	
	
}
