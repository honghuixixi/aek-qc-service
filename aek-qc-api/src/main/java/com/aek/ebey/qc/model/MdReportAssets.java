package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 质控检测设备信息表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-17
 */
@TableName("md_report_assets")
public class MdReportAssets extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 质控报告ID
	 */
	@TableField(value="md_report_id")
	private Long mdReportId;
	/**
	 * 设备id
	 */
	@TableField(value="assets_id")
	private Long assetsId;
	/**
	 * 设备名称
	 */
	@TableField(value="assets_name")
	private String assetsName;
	/**
	 * 设备编号
	 */
	@TableField(value="assets_num")
	private String assetsNum;
	/**
	 * 设备规格型号
	 */
	@TableField(value="assets_spec")
	private String assetsSpec;
	/**
	 * 出厂编号
	 */
	@TableField(value="factory_num")
	private String factoryNum;
	/**
	 * 厂家名称
	 */
	@TableField(value="factory_name")
	private String factoryName;
	/**
	 * 设备所在部门
	 */
	@TableField(value="dept_id")
	private Long deptId;
	/**
	 * 设备所在部门名称
	 */
	@TableField(value="dept_name")
	private String deptName;

	/**
	 * 作废标识(0=未作废、1=作废)
	 */
	@TableField(value="del_flag")
	private Boolean delFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMdReportId() {
		return mdReportId;
	}

	public void setMdReportId(Long mdReportId) {
		this.mdReportId = mdReportId;
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

	public String getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(String assetsNum) {
		this.assetsNum = assetsNum;
	}

	public String getAssetsSpec() {
		return assetsSpec;
	}

	public void setAssetsSpec(String assetsSpec) {
		this.assetsSpec = assetsSpec;
	}

	public String getFactoryNum() {
		return factoryNum;
	}

	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
}
