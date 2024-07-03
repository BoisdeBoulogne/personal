package com.example.demo.vo;

import com.example.demo.enums.ResultEnum;
import lombok.Data;

@Data
public class Result<T> {
    int code;
    String msg;
    public T data;
    public static <T> Result success() {
        Result result = new Result(ResultEnum.SUCESS.getCode(), ResultEnum.SUCESS.getMessage(), null);
        return result;
    }
    public static <T> Result success(T data) {
        Result result = new Result(ResultEnum.SUCESS.getCode(), ResultEnum.SUCESS.getMessage(),data);
        return result;
    }
    public Result(int code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> Result<T> fail() {
        Result result = new Result(ResultEnum.ERROR_OPERATION.getCode(), ResultEnum.ERROR_OPERATION.getMessage(),null);
        return result;
    }
}
