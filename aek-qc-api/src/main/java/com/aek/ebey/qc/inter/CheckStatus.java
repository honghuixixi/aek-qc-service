package com.aek.ebey.qc.inter;

public enum CheckStatus {
	
ONE(1,"待验收"),TWO(2,"已验收");

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
private CheckStatus(int code, String desc) {
	this.code = code;
	this.desc = desc;
}

}
