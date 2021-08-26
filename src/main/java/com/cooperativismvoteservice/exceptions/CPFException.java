package com.cooperativismvoteservice.exceptions;

public class CPFException extends Exception {

    public CPFException(String message) {
        super(message);
    }

    public CPFException(Throwable cause) {
        super(cause);
    }

    public CPFException(String message, Throwable cause) {
        super(message, cause);
    }
}
