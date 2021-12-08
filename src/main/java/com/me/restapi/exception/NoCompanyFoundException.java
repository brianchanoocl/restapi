package com.me.restapi.exception;

public class NoCompanyFoundException extends RuntimeException {
    public NoCompanyFoundException() {
        super("No company found.");
    }
}
