package com.capps.citronix.web.errors.farm;

public class FarmExisteException extends RuntimeException {
    public FarmExisteException(String meg) {
        super(meg);
    }
}
