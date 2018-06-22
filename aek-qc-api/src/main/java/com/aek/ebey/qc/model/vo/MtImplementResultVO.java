package com.aek.ebey.qc.model.vo;

import java.util.Date;
import java.util.List;

public class MtImplementResultVO {

	private Long id;
	private Date endDate; 
	private Integer assetsStatus;
	private String implementUserName;
	private String administrator;
	private List<MtImplementResultItemsVO> mtImplementResultItemsVo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getImplementUserName() {
		return implementUserName;
	}
	public void setImplementUserName(String implementUserName) {
		this.implementUserName = implementUserName;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	public List<MtImplementResultItemsVO> getMtImplementResultItemsVo() {
		return mtImplementResultItemsVo;
	}
	public void setMtImplementResultItemsVo(List<MtImplementResultItemsVO> mtImplementResultItemsVo) {
		this.mtImplementResultItemsVo = mtImplementResultItemsVo;
	}
	
}
