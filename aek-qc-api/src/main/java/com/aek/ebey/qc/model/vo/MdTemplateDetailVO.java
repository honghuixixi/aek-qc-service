package com.aek.ebey.qc.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模版详情返回实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value="质控模版详情返回实体类")
public class MdTemplateDetailVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 模板名称
     */
    @ApiModelProperty(value="名称")
    private String name;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remarks;
    /**
     * 启用标记(0=停用，1=启用)
     */
    @ApiModelProperty(value="启用标记(0=停用，1=启用)")
    private Boolean enable;
    
    /**
     * 发布状态(0=待发布，1=已发布)
     */
    @ApiModelProperty(value="发布状态(0=待发布，1=已发布)")
    private Boolean releaseFlag;
    
    /**
     * 模板项目集合
     */
    @ApiModelProperty(value="模板项目集合")
    private List<MdTemplateItemVO> items;

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

    public List<MdTemplateItemVO> getItems() {
        return items;
    }

    public void setItems(List<MdTemplateItemVO> items) {
        this.items = items;
    }

    public Boolean getReleaseFlag() {
        return releaseFlag;
    }

    public void setReleaseFlag(Boolean releaseFlag) {
        this.releaseFlag = releaseFlag;
    }
     
}
