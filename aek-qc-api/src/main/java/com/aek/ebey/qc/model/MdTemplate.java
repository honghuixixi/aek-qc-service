package com.aek.ebey.qc.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 质控检测系统模板表
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@ApiModel(value="质控模版实体类")
@TableName("md_template")
public class MdTemplate extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
    @ApiModelProperty(value="主键ID")
	private Long id;
	/**
	 * 模板名称
	 */
    @ApiModelProperty(value="名称")
    @TableField(value="name")
	private String name;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
    @TableField(value="remarks")
	private String remarks;
	/**
	 * 启用标记(0=停用，1=启用)
	 */
    @ApiModelProperty(value="启用标记(0=停用，1=启用)")
    @TableField(value="enable")
	private Boolean enable;
    /**
     * 发布状态(0=待发布，1=已发布)
     */
    @ApiModelProperty(value="发布状态(0=待发布，1=已发布)")
    @TableField(value="release_flag")
    private Boolean releaseFlag;
	/**
	 * 创建人ID
	 */
    @ApiModelProperty(value="创建人ID")
	@TableField(value="create_by")
	private Long createBy;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value="创建时间")
	@TableField(value="create_time")
	private Date createTime;
	/**
	 * 更新人ID
	 */
    @ApiModelProperty(value="更新人ID")
	@TableField(value="update_by")
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	@TableField(value="update_time")
	private Date updateTime;

	/**
     * 删除标记(0=未删除，1=已删除)
     */
    @ApiModelProperty(value="删除标记(0=未删除，1=已删除)")
    @TableField(value="del_flag")
    private Boolean delFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    public Boolean getReleaseFlag() {
        return releaseFlag;
    }

    public void setReleaseFlag(Boolean releaseFlag) {
        this.releaseFlag = releaseFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
	
}
