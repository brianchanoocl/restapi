package com.me.restapi.advice;

public class ErrorResponse {

    private String message;
    private int code;

    public ErrorResponse(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
