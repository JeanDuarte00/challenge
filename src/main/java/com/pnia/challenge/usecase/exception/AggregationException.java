package com.pnia.challenge.usecase.exception;

public class AggregationException extends Exception{

    public AggregationException(final String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public AggregationException(final String errorMessage) {
        super(errorMessage);
    }
}
