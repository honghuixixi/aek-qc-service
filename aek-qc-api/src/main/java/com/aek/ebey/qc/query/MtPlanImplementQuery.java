package com.aek.ebey.qc.query;

import com.aek.ebey.qc.inter.LiuHuiFrontPage;
import com.aek.ebey.qc.request.MtPlanImplementResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 保养实施查询参数实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtPlanImplementQuery", description = "保养实施查询参数实体类")
public class MtPlanImplementQuery extends LiuHuiFrontPage<MtPlanImplementResponse>{

	/**
	 * 设备名称/设备编号
	 */
	@ApiModelProperty(value="设备名称/设备编号")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
