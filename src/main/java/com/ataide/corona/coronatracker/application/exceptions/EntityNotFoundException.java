package com.ataide.corona.coronatracker.application.exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
