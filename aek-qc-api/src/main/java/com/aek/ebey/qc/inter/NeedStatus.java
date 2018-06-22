package com.aek.ebey.qc.inter;

public enum NeedStatus {
	
ONE(1,"不需要"),TWO(2,"需要");

private int code;
private String desc;
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
private NeedStatus(int code, String desc) {
	this.code = code;
	this.desc = desc;
}



}
