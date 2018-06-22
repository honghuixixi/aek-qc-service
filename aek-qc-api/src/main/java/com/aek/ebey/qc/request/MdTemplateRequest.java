package com.aek.ebey.qc.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模板请求实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value = "MdTemplateRequest", description = "质控模板数据")
public class MdTemplateRequest {

    @ApiModelProperty(value="质控模版ID")
    @NotNull(groups = Edit.class, message = "MD_003")
    private Long id;
    
    @ApiModelProperty(value="质控模版名称")
    @NotNull(groups = {Add.class,Edit.class}, message = "MD_001")
    @Length(max = 40, groups = {Add.class,Edit.class}, message = "MD_002")
    private String name;
    
    @ApiModelProperty(value="保养模版备注")
    @Length(max = 300, groups = {Add.class,Edit.class}, message = "MD_004")
    private String remarks;

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
    
}
