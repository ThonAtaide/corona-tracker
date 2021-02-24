package com.ataide.corona.coronatracker.domain.util;

import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;

import javax.validation.*;
import java.util.Set;

public class ServerUtil {
    public static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
