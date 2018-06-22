package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * PM实施文件表
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@TableName("pm_implement_file")
public class PmImplementFile extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 关联实施id
	 */
	@TableField(value="implement_id")
	private Long implementId;
	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 文件路径
	 */
	private String url;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImplementId() {
		return implementId;
	}

	public void setImplementId(Long implementId) {
		this.implementId = implementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
