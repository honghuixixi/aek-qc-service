package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 计量设备信息表
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@TableName("ms_check_history")
public class MsCheckHistory extends BaseModel {

	private static final long serialVersionUID = 1L;
	/**
	 * 计量设备ID
	 */
	@TableField(value = "measure_assets_id")
	private Long measureAssetsId;
	/**
	 * 机构ID
	 */
	@TableField(value = "tenant_id")
	private Long tenantId;
	/**
	 * 设备id
	 */
	@TableField(value = "assets_id")
	private Long assetsId;
	/**
	 * 资产图片
	 */
	@TableField(value = "img_url")
	private String imgUrl;
	/**
	 * 设备名称
	 */
	@TableField(value = "assets_name")
	private String assetsName;
	/**
	 * 设备所在科室id
	 */
	@TableField(value = "assets_dept_id")
	private Long assetsDeptId;
	/**
	 * 设备所在科室名称
	 */
	@TableField(value = "assets_dept_name")
	private String assetsDeptName;
	/**
	 * 设备编号
	 */
	@ApiModelProperty(value = "设备编号")
	@TableField(value = "assets_num")
	private String assetsNum;
	/**
	 * 设备型号
	 */
	@TableField(value = "assets_spec")
	private String assetsSpec;
	/**
	 * 计量编号
	 */
	@TableField(value = "measure_num")
	private String measureNum;
	/**
	 * 生产商ID
	 */
	@TableField(value = "factory_id")
	private Integer factoryId;
	/**
	 * 生产商
	 */
	@TableField(value = "factory_name")
	private String factoryName;
	 /**
     * 出厂编号
     */
    @TableField(value="factory_num")
    private String factoryNum;
    /**
     * 院内编码
     */
    @TableField(value="serial_num")
    private String serialNum;
	/**
	 * 供应商ID
	 */
	@TableField(value = "spl_id")
	private Integer splId;
	/**
	 * 供货单位名称
	 */
	@TableField(value = "spl_name")
	private String splName;
	/**
	 * 计量类别（1，A类 2，B类 3，C类）
	 */
	@TableField(value = "measure_type")
	private Integer measureType;
	/**
	 * 计量分类
	 */
	@TableField(value = "measure_category")
	private Integer measureCategory;
	/**
	 * 计量周期(单位月)
	 */
	@TableField(value = "measure_cycle")
	private Integer measureCycle;
	/**
	 * 下次检定日期
	 */
	@TableField(value = "next_check_time")
	private Date nextCheckTime;
	/**
	 * 检定方式（1，院内 2，外包）
	 */
	@TableField(value = "check_mode")
	private Integer checkMode;
	/**
	 * 负责人ID
	 */
	@TableField(value = "charge_user_id")
	private Long chargeUserId;
	/**
	 * 负责人姓名
	 */
	@TableField(value = "charge_user_name")
	private String chargeUserName;
	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 状态：1=在库、2=在用、3=预登、4=待报损、5=报损、6=退货'
	 */
	@ApiModelProperty(value = "状态：1=在库、2=在用、3=预登、4=待报损、5=报损、6=退货'")
	@TableField(value = "assets_status")
	private Integer assetsStatus;
	/**
	 * 计量资产种类（1，固定 2，非固定）
	 */
	@TableField(value = "assets_type")
	private Integer assetsType;
	/**
	 * 管理类别（1，非强制性计量设备 2，强制性计量设备）
	 */
	@TableField(value = "measure_manage_type")
	private Integer measureManageType;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time")
	private Date createTime;
	/**
	 * 提交时间
	 */
	@TableField(value = "submit_time")
	private Date submitTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMeasureAssetsId() {
		return measureAssetsId;
	}

	public void setMeasureAssetsId(Long measureAssetsId) {
		this.measureAssetsId = measureAssetsId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
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

	public String getMeasureNum() {
		return measureNum;
	}

	public void setMeasureNum(String measureNum) {
		this.measureNum = measureNum;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getSplId() {
		return splId;
	}

	public void setSplId(Integer splId) {
		this.splId = splId;
	}

	public String getSplName() {
		return splName;
	}

	public void setSplName(String splName) {
		this.splName = splName;
	}

	public Integer getMeasureType() {
		return measureType;
	}

	public void setMeasureType(Integer measureType) {
		this.measureType = measureType;
	}

	public Integer getMeasureCategory() {
		return measureCategory;
	}

	public void setMeasureCategory(Integer measureCategory) {
		this.measureCategory = measureCategory;
	}

	public Integer getMeasureCycle() {
		return measureCycle;
	}

	public void setMeasureCycle(Integer measureCycle) {
		this.measureCycle = measureCycle;
	}

	public Date getNextCheckTime() {
		return nextCheckTime;
	}

	public void setNextCheckTime(Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}

	public Integer getCheckMode() {
		return checkMode;
	}

	public void setCheckMode(Integer checkMode) {
		this.checkMode = checkMode;
	}

	public Long getChargeUserId() {
		return chargeUserId;
	}

	public void setChargeUserId(Long chargeUserId) {
		this.chargeUserId = chargeUserId;
	}

	public String getChargeUserName() {
		return chargeUserName;
	}

	public void setChargeUserName(String chargeUserName) {
		this.chargeUserName = chargeUserName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(Integer assetsType) {
		this.assetsType = assetsType;
	}

	public Integer getMeasureManageType() {
		return measureManageType;
	}

	public void setMeasureManageType(Integer measureManageType) {
		this.measureManageType = measureManageType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public String getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

}
