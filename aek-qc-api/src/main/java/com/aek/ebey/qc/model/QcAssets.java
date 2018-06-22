package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@TableName("qc_assets")
public class QcAssets extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 关联实施id
	 */
	@TableField(value="implement_id")
	private Long implementId;
	
	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 设备id
	 */
	@TableField(value="assets_id")
	private Long assetsId;
	
	/**
	 * 设备图片
	 */
	@TableField(value="img_url")
	private String imgUrl;
	/**
	 * 设备名称
	 */
	@TableField(value="assets_name")
	private String assetsName;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 设备所在科室id
	 */
	@TableField(value="assets_dept_id")
	private Long assetsDeptId;
	/**
	 * 设备所在科室名称
	 */
	@TableField(value="assets_dept_name")
	private String assetsDeptName;
	/**
	 * 设备型号
	 */
	@TableField(value="assets_spec")
	private String assetsSpec;
	/**
	 * 设备编号
	 */
	@TableField(value="assets_num")
	private String assetsNum;
	
	/**
	 * 设备状态（1，台帐 2，预台帐）
	 */
	@TableField(value="status")
	private Integer assetsStatus;
	
	/**
	 * 结果
	 */
	private String result;
	
	/**
	 * 答案结果(1，不全 ， 2 全）
	 */
	@TableField(value="result_status")
	private Integer resultStatus;
	
	/**
	 * 巡检状态取值 1:待巡检 2:已巡检
	 */
	@TableField(value="im_status")
	private Integer imStatus;
	
	/**
	 * 提交时间
	 */
	@TableField(value="create_time")
	private Date createTime;
	


	public Long getImplementId() {
		return implementId;
	}

	public void setImplementId(Long implementId) {
		this.implementId = implementId;
	}

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getAssetsDeptId() {
		return assetsDeptId;
	}

	public void setAssetsDeptId(Long assetsDeptId) {
		this.assetsDeptId = assetsDeptId;
	}

	public String getAssetsDeptName() {
		return assetsDeptName;
	}

	public void setAssetsDeptName(String assetsDeptName) {
		this.assetsDeptName = assetsDeptName;
	}

	public String getAssetsSpec() {
		return assetsSpec;
	}

	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}

	public String getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getImStatus() {
		return imStatus;
	}

	public void setImStatus(Integer imStatus) {
		this.imStatus = imStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}
