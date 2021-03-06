package com.ataide.corona.coronatracker.domain.util;

import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

public class ServerUtil {

    public static void validConstraintViolations(Object object) throws ConstraintViolationsException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>(validator.validate(object));

        if (constraintViolations.size() > 0 ) throw new ConstraintViolationsException(constraintViolations);
    }
}
