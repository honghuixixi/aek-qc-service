package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.MsAssetsRecordResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(value = "MsAssetsRecordQuery", description = "MsAssetsRecordQuery")
public class MsAssetsRecordQuery extends LiuHuiFrontPage<MsAssetsRecordResponse>{
	
	/**
	 * 管理类别（1，非强制性计量设备 2，强制性计量设备）
	 */
    @ApiModelProperty(value="管理类别（1，非强制性计量设备 2，强制性计量设备）")
	private Integer measureManageType;
    
    /**
	 * 设备所在科室id
	 */
    @ApiModelProperty(value="设备所在科室id")
	private Long assetsDeptId;
    
    /**
	 * 设备名称/计量编号
	 */
    @ApiModelProperty(value="设备名称/计量编号")
	private String keyword;
	
    /**
	 * 开始日期
	 */
    @ApiModelProperty(value="开始时间")
	private String startDate;
	
	/**
	 * 结束日期
	 */
    @ApiModelProperty(value="结束日期")
	private String endDate;

	public Integer getMeasureManageType() {
		return measureManageType;
	}

	public void setMeasureManageType(Integer measureManageType) {
		this.measureManageType = measureManageType;
	}

	public Long getAssetsDeptId() {
		return assetsDeptId;
	}

	public void setAssetsDeptId(Long assetsDeptId) {
		this.assetsDeptId = assetsDeptId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}
