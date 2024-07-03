package com.example.demo.exception;

public class PoiExpection extends RuntimeException {
    private PoiExpection(String message) {
        super(message);
    }
    public static PoiExpection NotFound(){
        return new PoiExpection("Not Found");
    }
    public static PoiExpection OperateFail(){
        return new PoiExpection("Operate Fail");
    }
    public static PoiExpection Unknown(){
        return new PoiExpection("Unknown");
    }
}
