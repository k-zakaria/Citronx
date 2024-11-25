package com.capps.citronix.web.errors.field;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message) {
        super(message);
    }
}
