package com.aek.ebey.qc.request;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
public class QcAssetsResponse2 {
	/**
	 * 设备ID
	 */
	private Long id;
	

	/**
	 * 设备图片
	 */
	private String imgUrl;
	
	/**
	 * 设备名称
	 */
	private String name;
	
	/**
	 * 设备所在科室名称
	 */
	private String deptName;
	
	/**
	 * 设备编号
	 */
	private String assetNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	


}
