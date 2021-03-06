package com.ataide.corona.coronatracker.application.exceptions;

import javax.validation.ConstraintViolation;
import java.util.Collection;

public class UsernameAllocatedException extends Exception{
    private Collection<ConstraintViolation> violations;

    public UsernameAllocatedException() {
    }

    public UsernameAllocatedException(Collection<ConstraintViolation> violations) {
        this.violations = violations;
    }

    public UsernameAllocatedException(String message) {
        super(message);
    }
}
