package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 质控模板项目选项表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@TableName("md_report_template_item_option")
public class MdReportTemplateItemOption extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * copyID
	 */
	@TableField(exist=false)
	private Long copy;
	/**
	 * 系统选项ID
	 */
	@TableField(value="source_item_option_id")
	private Long sourceItemOptionId;
	/**
	 * 报告模板项目ID
	 */
	@TableField(value="item_id")
	private Long itemId;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(value="update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSourceItemOptionId() {
		return sourceItemOptionId;
	}

	public void setSourceItemOptionId(Long sourceItemOptionId) {
		this.sourceItemOptionId = sourceItemOptionId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCopy() {
		return copy;
	}

	public void setCopy(Long copy) {
		this.copy = copy;
	}

}
