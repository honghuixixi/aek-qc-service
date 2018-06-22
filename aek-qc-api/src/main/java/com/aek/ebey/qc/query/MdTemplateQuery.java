package com.aek.ebey.qc.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.qc.model.MdTemplate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="质控模版分页查询类")
public class MdTemplateQuery extends PageHelp<MdTemplate> {

	@ApiModelProperty(value="输入关键字（模版名称）")
	private String keyword;
	@ApiModelProperty(value="发布状态(0=待发布,1=已发布)")
	private Boolean releaseFlag;
	@ApiModelProperty(value="启用停用标记(0=停用,1=启用)")
	private Boolean enable;
	@ApiModelProperty(value="查询类型(0=爱怡康,1=普通机构)")
	private Integer type;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

    public Boolean getReleaseFlag() {
        return releaseFlag;
    }

    public void setReleaseFlag(Boolean releaseFlag) {
        this.releaseFlag = releaseFlag;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
