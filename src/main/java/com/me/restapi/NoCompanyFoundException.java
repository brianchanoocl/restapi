package com.me.restapi;

public class NoCompanyFoundException extends RuntimeException {
    public NoCompanyFoundException() {
        super("No company found.");
    }
}
