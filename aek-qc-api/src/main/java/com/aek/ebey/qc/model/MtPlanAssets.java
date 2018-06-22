package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 保养计划资产信息表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@TableName("mt_plan_assets")
public class MtPlanAssets extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 保养计划ID
	 */
	@TableField(value="plan_id")
	private Long planId;
	/**
	 * 资产ID
	 */
	@TableField(value="assets_id")
	private Long assetsId;
	/**
	 * 资产编号
	 */
	@TableField(value="assets_num")
	private String assetsNum;
	/**
	 * 资产院内编码
	 */
	@TableField(value="hospital_assets_num")
	private String hospitalAssetsNum;
	/**
	 * 资产名称
	 */
	@TableField(value="assets_name")
	private String assetsName;
	/**
	 * 设备型号
	 */
	@TableField(value="assets_spec")
	private String assetsSpec;
	/**
	 * 出厂编号
	 */
	@ApiModelProperty(value="出厂编号")
	@TableField(value="factory_num")
	private String factoryNum;
	/**
	 * 设备所在科室ID
	 */
	@TableField(value="assets_dept_id")
	private Long assetsDeptId;
	/**
	 * 设备所在科室名称
	 */
	@TableField(value="assets_dept_name")
	private String assetsDeptName;
	/**
	 * 设备加入保养计划时间
	 */
	@TableField(value="create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public String getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}

	public String getHospitalAssetsNum() {
		return hospitalAssetsNum;
	}

	public void setHospitalAssetsNum(String hospitalAssetsNum) {
		this.hospitalAssetsNum = hospitalAssetsNum;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getAssetsSpec() {
		return assetsSpec;
	}

	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFactoryNum() {
		return factoryNum;
	}

	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
	}

}
