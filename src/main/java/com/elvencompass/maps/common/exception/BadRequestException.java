package com.elvencompass.maps.common.exception;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad request";

    public BadRequestException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
