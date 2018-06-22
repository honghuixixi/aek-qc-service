package com.aek.ebey.qc.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模版子选择项目选项返回实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value="质控模版子选择项目选项返回实体类 ")
public class MdTemplateItemOptionVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 项目ID
     */
    @ApiModelProperty(value="项目ID")
    private Long itemId;
    /**
     * 选项名称
     */
    @ApiModelProperty(value="选项名称")
    private String name;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer sort;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    
}
