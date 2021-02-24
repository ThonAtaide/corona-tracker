package com.ataide.corona.coronatracker.application.exceptions;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.Set;

public class ConstraintViolationsException extends Exception{
    private Collection<ConstraintViolation> violations;

    public ConstraintViolationsException() {
    }

    public ConstraintViolationsException(Collection<ConstraintViolation> violations) {
        this.violations = violations;
    }

    public ConstraintViolationsException(String message) {
        super(message);
    }
}
