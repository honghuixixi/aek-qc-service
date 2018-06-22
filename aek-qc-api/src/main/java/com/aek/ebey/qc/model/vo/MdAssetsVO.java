package com.aek.ebey.qc.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("新增质控填报设备输出类")
public class MdAssetsVO {

	@ApiModelProperty("设备id")
	private Long assetsId;
	@ApiModelProperty("设备名称")
	private String assetsName;
	@ApiModelProperty("设备编号")
	private String assetsNum;
	@ApiModelProperty("设备规格型号")
	private String assetsSpec;
	@ApiModelProperty("出厂编号")
	private String factoryNum;
	@ApiModelProperty("厂家名称")
	private String factoryName;
	@ApiModelProperty("设备所在部门id")
	private Long deptId;
	@ApiModelProperty("设备所在部门名称")
	private String deptName;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetsId == null) ? 0 : assetsId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Long other =  (Long) obj;
		if (assetsId == null) {
			if (other != null)
				return false;
		} else if (!assetsId.equals(other))
			return false;
		return true;
	}
	
	
}
