package com.aek.ebey.qc.enums;

/**
 * 质控模板输入类型
 *	
 * @author HongHui
 * @date   2018年5月21日
 */
public enum MdInputTypeEnum {

    TEXT(1,"文本"),
    SELECT(2,"选择");
    
    private Integer number;
    private String name;
    
    private MdInputTypeEnum(Integer number, String name) {
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
