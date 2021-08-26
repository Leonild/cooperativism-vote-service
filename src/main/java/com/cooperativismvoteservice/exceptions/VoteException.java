package com.cooperativismvoteservice.exceptions;

public class VoteException extends Exception {

    public VoteException(String message) {
        super(message);
    }

    public VoteException(Throwable cause) {
        super(cause);
    }

    public VoteException(String message, Throwable cause) {
        super(message, cause);
    }

}
