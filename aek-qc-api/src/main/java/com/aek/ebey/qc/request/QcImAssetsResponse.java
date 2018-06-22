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
public class QcImAssetsResponse{
	
	/**
	 * 模板项目名称列表
	 */
	private List<QcProjectRequest4> model;
	
	/**
	 * 答案
	 */
	private String answers;
	
	/**
	 * 答案完整取值：1：不完整2：完整
	 */
	private Integer status;
	
	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 设备详情
	 */
	private QcImAssets2 assetInfo;

	public List<QcProjectRequest4> getModel() {
		return model;
	}

	public void setModel(List<QcProjectRequest4> model) {
		this.model = model;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public QcImAssets2 getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(QcImAssets2 assetInfo) {
		this.assetInfo = assetInfo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	
}
