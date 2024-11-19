package com.capps.citronix.web.errors.harvest;

public class HarvestAlreadyExistsException extends RuntimeException {
    public HarvestAlreadyExistsException(String message) {
        super(message);
    }
}
