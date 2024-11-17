package com.capps.citronix.web.errors.farm;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException(String meg) {
        super(meg);
    }
}
