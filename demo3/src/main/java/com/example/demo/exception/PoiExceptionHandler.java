package com.example.demo.exception;

import com.example.demo.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PoiExceptionHandler {
    @ExceptionHandler(PoiExpection.class)
    public Result poiExceptionHandler(Exception e) {
        return Result.fail();
    }
    @ExceptionHandler(Exception.class)
    public Result serverErrorHandler(Exception e) {
        return Result.fail();
    }
}
