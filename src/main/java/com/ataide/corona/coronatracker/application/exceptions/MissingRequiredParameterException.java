package com.ataide.corona.coronatracker.application.exceptions;

public class MissingRequiredParameterException extends Exception{
    public MissingRequiredParameterException() {
    }

    public MissingRequiredParameterException(String message) {
        super(message);
    }
}
