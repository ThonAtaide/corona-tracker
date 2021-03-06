package com.ataide.corona.coronatracker.application.exceptions;

public class InvalidUsernameOrPasswordException extends Exception{
    public InvalidUsernameOrPasswordException() {
    }

    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }
}
