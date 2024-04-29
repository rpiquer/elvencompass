package com.elvencompass.maps.common.exception;

public class DTOValidationException extends RuntimeException{
    private static final String DESCRIPTION = "Validation error";

    public DTOValidationException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
