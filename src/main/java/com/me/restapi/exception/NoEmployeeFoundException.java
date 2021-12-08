package com.me.restapi.exception;

import java.util.function.Supplier;

public class NoEmployeeFoundException extends RuntimeException {

    public NoEmployeeFoundException() {
        super("No employee found.");
    }
}
