package com.aek.ebey.qc.enums;

/**
 * 质控模板查询类型
 *	
 * @author HongHui
 * @date   2018年5月21日
 */
public enum MdQueryTypeEnum {

    AEK(0,"爱怡康"),
    COMMON(1,"普通机构");

    private Integer number;
    private String name;

    private MdQueryTypeEnum(Integer number, String name) {
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
