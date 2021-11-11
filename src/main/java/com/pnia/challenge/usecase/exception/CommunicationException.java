package com.pnia.challenge.usecase.exception;

public class CommunicationException extends Exception{

    public CommunicationException(final String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
