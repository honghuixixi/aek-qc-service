package com.aek.ebey.qc.enums;

/**
 * 质控模板项目类型
 *	
 * @author HongHui
 * @date   2018年5月21日
 */
public enum MdTypeEnum {

    SUBITEM(1,"子项目"),
    SUBITEM_REMARKS(2,"子项目备注");
    
    private Integer number;
    private String name;
    
    private MdTypeEnum(Integer number, String name) {
        this.number = number;
        this.name = name;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
