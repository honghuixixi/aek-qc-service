package com.aek.ebey.qc.inter;

/**
 * 计量状态
 */
public enum MsStatus {

    ONE(1, "待完善"),
    TWO(2, "已完善");

    int code;
    String message;

    MsStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (MsStatus s : MsStatus.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
