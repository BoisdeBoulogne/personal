package com.example.demo.enums;

public enum ResultEnum {
    SUCESS(0,"操作成功"),
    ERROR_UNKNOWN(-1,"操作失败"),
    ERROR_NOT_FOUND(1,"资源未找到"),
    ERROR_OPERATION(2,"操作未成功"),
    ;
    int code;
    String message;
    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
