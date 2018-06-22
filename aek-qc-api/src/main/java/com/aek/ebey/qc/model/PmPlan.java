package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * PM巡检计划
 * </p>
 *
 * @author Liuhui
 * @since 2018-05-21
 */
@ApiModel(value = "PmPlan")
@TableName("pm_plan")
public class PmPlan extends BaseModel {

    private static final long serialVersionUID = 1L;
	/**
	 * 设备id
	 */
	@TableField(value="assets_id")
	@ApiModelProperty(value = "设备id")
	private Long assetsId;
	/**
	 * 设备编号
	 */
	@TableField(value="assets_num")
	@ApiModelProperty(value = "设备编号")
	private String assetsNum;
	/**
	 * 设备名称
	 */
	@TableField(value="assets_name")
	@ApiModelProperty(value = "设备名称")
	private String assetsName;
	/**
	 * 设备型号
	 */
	@TableField(value="assets_spec")
	@ApiModelProperty(value = "设备型号")
	private String assetsSpec;
	/**
	 * 设备所在科室id
	 */
	@TableField(value="assets_dept_id")
	@ApiModelProperty(value = "设备所在科室id")
	private Long assetsDeptId;
	/**
	 * 设备所在科室名称
	 */
	@TableField(value="assets_dept_name")
	@ApiModelProperty(value = "设备所在科室名称")
	private String assetsDeptName;
	/**
	 * 设备状态
	 */
	@TableField(value="assets_status")
	@ApiModelProperty(value = "设备状态")
	private Integer assetsStatus;
	/**
	 * 设备状态
	 */
	@TableField(value="assets_status_text")
	@ApiModelProperty(value = "设备状态")
	private String assetsStatusText;
	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	@ApiModelProperty(value = "机构ID")
	private Long tenantId;
	/**
	 * 机构名称
	 */
	@TableField(value="tenant_name")
	@ApiModelProperty(value = "机构名称")
	private String tenantName;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 关联模版id
	 */
	@TableField(value="template_id")
	@ApiModelProperty(value = "关联模版id")
	private Long templateId;
	/**
	 * 原来模版id
	 */
	@TableField(value="pre_template_id")
	@ApiModelProperty(value = "原来模版id")
	private Long preTemplateId;
	/**
	 * 巡检周期
	 */
	private Integer cycle;
	/**
	 * PM登记1：一级 2：二级 3：三级
	 */
	private Integer level;
	/**
	 * 首次巡检开始时间
	 */
	@TableField(value="first_time")
	@ApiModelProperty(value = "首次巡检开始时间")
	private Date firstTime;
	/**
	 * 上次巡检时间
	 */
	@TableField(value="pre_time")
	@ApiModelProperty(value = "上次巡检时间")
	private Date preTime;
	/**
	 * 下次巡检时间
	 */
	@TableField(value="next_time")
	@ApiModelProperty(value = "下次巡检时间")
	private Date nextTime;
	/**
	 * 负责人ID
	 */
	@TableField(value="charge_id")
	@ApiModelProperty(value = "负责人ID")
	private Long chargeId;
	/**
	 * 负责人姓名
	 */
	@TableField(value="charge_name")
	@ApiModelProperty(value = "负责人姓名")
	private String chargeName;
	/**
	 * 验收人id
	 */
	@TableField(value="check_id")
	@ApiModelProperty(value = "验收人id")
	private Long checkId;
	/**
	 * 验收人名称
	 */
	@TableField(value="check_name")
	@ApiModelProperty(value = "验收人名称")
	private String checkName;
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;
	/**
	 * 巡检状态 1:待实施、2:实施中
	 */
	@TableField(value="check_status")
	@ApiModelProperty(value = "巡检状态 1:待实施、2:实施中")
	private Integer checkStatus;
	/**
	 * 作废标记，0：启用，1：删除
	 */
	@TableField(value="del_flag")
	@ApiModelProperty(value = "作废标记，0：启用，1：删除")
	private Boolean delFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public String getAssetsStatusText() {
		return assetsStatusText;
	}

	public void setAssetsStatusText(String assetsStatusText) {
		this.assetsStatusText = assetsStatusText;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getPreTemplateId() {
		return preTemplateId;
	}

	public void setPreTemplateId(Long preTemplateId) {
		this.preTemplateId = preTemplateId;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getPreTime() {
		return preTime;
	}

	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}


}
