package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 保养实施结果附件表
 *
 * @author Honghui
 * @since 2018-03-21
 */
@TableName("mt_implement_result_file")
public class MtImplementResultFile extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 保养实施结果ID
	 */
	@TableField(value="result_id")
	private Long resultId;
	/**
	 * 文件名称
	 */
	@TableField(value="file_name")
	private String fileName;
	/**
	 * 文件路径
	 */
	@TableField(value="file_path")
	private String filePath;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

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
