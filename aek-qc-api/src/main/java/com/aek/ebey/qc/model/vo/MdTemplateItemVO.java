package com.aek.ebey.qc.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 质控模版项目返回实体类
 *	
 * @author HongHui
 * @date   2018年5月17日
 */
@ApiModel(value="质控模版项目返回实体类")
public class MdTemplateItemVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 模板ID
     */
    @ApiModelProperty(value="模板ID")
    private Long templateId;
    /**
     * 项目名称
     */
    @ApiModelProperty(value="项目名称")
    private String name;
    /**
     * 项目备注
     */
    @ApiModelProperty(value="项目备注")
    private String remarks;
    
    /**
     * 输入类型(1=文本，2=选择)
     */
    @ApiModelProperty(value="输入类型(1=文本，2=选择)")
    private Integer inputType;
    /**
     * 选择类型(1=单选，2=多选)(input_type=选择)
     */
    @ApiModelProperty(value="选择类型(1=单选，2=多选)(input_type=选择)")
    private Integer selectType;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer sort;
    
    /**
     * 子文本项目集合
     */
    @ApiModelProperty(value="子文本项目集合")
    private List<MdTemplateChildItemVO> childItems;
    
    /**
     * 项目选项集合
     */
    @ApiModelProperty(value="项目选项集合")
    private List<MdTemplateItemOptionVO> childItemOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<MdTemplateChildItemVO> getChildItems() {
        return childItems;
    }

    public void setChildItems(List<MdTemplateChildItemVO> childItems) {
        this.childItems = childItems;
    }

    public List<MdTemplateItemOptionVO> getChildItemOptions() {
        return childItemOptions;
    }

    public void setChildItemOptions(List<MdTemplateItemOptionVO> childItemOptions) {
        this.childItemOptions = childItemOptions;
    }
}

//不用
//*******************************************************************************************
/*import com.aek.ebey.qc.model.MdTemplateItemOption;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("系统模板子项目")
public class MdTemplateItemVO {

	@ApiModelProperty("子项目ID")
	private Long id;
	@ApiModelProperty("模板ID")
	private Long templateId;
	@ApiModelProperty("父项目ID")
	private Long parentId;
	@ApiModelProperty("项目名称")
	private String name;
	@ApiModelProperty("项目备注")
	private String remarks;
	@ApiModelProperty("输入类型(1=文本，2=选择)")
	private Integer inputType;
	@ApiModelProperty("列数(type=子项目,input_type=文本)")
	private Integer columns;
	@ApiModelProperty("跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)")
	private Boolean crossRow;
	@ApiModelProperty("项目类型(1=子项目,2=子项目备注)")
	private Integer type;
	@ApiModelProperty("选择类型(1=单选，2=多选)(input_type=选择)")
	private Integer selectType;	
	@ApiModelProperty("排序")
	private Integer sort;	
	@ApiModelProperty("项目选项集合")
	private List<MdTemplateItemOption> MdTemplateItemOptionList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public Integer getInputType() {
		return inputType;
	}
	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}
	public Integer getColumns() {
		return columns;
	}
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	public Boolean getCrossRow() {
		return crossRow;
	}
	public void setCrossRow(Boolean crossRow) {
		this.crossRow = crossRow;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSelectType() {
		return selectType;
	}
	public void setSelectType(Integer selectType) {
		this.selectType = selectType;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<MdTemplateItemOption> getMdTemplateItemOptionList() {
		return MdTemplateItemOptionList;
	}
	public void setMdTemplateItemOptionList(List<MdTemplateItemOption> mdTemplateItemOptionList) {
		MdTemplateItemOptionList = mdTemplateItemOptionList;
	}
}*/
//*******************************************************************************************
