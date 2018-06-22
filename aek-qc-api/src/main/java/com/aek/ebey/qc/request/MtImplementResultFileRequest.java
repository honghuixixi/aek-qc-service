package com.aek.ebey.qc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实施结果附件请求实体类
 *	
 * @author HongHui
 * @date   2018年3月22日
 */
@ApiModel(value = "MtImplementResultFileRequest", description = "MtImplementResultFileRequest")
public class MtImplementResultFileRequest {

	/**
	 * 文件名称
	 */
	@ApiModelProperty(value="文件名称")
	private String fileName;
	/**
	 * 文件路径
	 */
	@ApiModelProperty(value="文件路径")
	private String filePath;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
