package com.capps.citronix.web.errors.field;

public class MaxFieldsExceededException extends RuntimeException {
    public MaxFieldsExceededException(String message) {
        super(message);
    }
}

