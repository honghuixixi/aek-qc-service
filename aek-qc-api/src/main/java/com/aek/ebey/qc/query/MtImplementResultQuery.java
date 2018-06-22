package com.aek.ebey.qc.query;

import java.util.Date;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.MtImplementResultPageResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 保养实施报告查询参数实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtImplementResultQuery", description = "保养实施报告查询参数实体类")
public class MtImplementResultQuery extends LiuHuiFrontPage<MtImplementResultPageResponse>{

	/**
	 * 设备名称/设备编号
	 */
	@ApiModelProperty(value="设备名称/设备编号")
	private String keyword;
	
	/**
	 * 开始实施日期
	 */
	@ApiModelProperty(value="开始实施日期")
	private String startImplementDate;
	
	/**
	 * 结束实施日期
	 */
	@ApiModelProperty(value="结束实施日期")
	private String endImplementDate;
	
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value="部门ID")
	private Long deptId;
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStartImplementDate() {
		return startImplementDate;
	}

	public void setStartImplementDate(String startImplementDate) {
		this.startImplementDate = startImplementDate;
	}

	public String getEndImplementDate() {
		return endImplementDate;
	}

	public void setEndImplementDate(String endImplementDate) {
		this.endImplementDate = endImplementDate;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
}
