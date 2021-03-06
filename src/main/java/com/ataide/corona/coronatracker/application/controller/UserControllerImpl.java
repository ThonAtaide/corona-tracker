package com.ataide.corona.coronatracker.application.controller;

import com.ataide.corona.coronatracker.application.controller.interfaces.UserController;
import com.ataide.corona.coronatracker.application.dtos.UserLoginDto;
import com.ataide.corona.coronatracker.application.dtos.UserRegisterDto;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.application.exceptions.UsernameAllocatedException;
import com.ataide.corona.coronatracker.domain.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
    private final UserService service;

    @Override
    public Response registerUser(UserRegisterDto userRegisterDto) throws UsernameAllocatedException, MissingRequiredParameterException, ConstraintViolationsException {
        logger.info("registerUser called - {}", userRegisterDto);
        return service.registerUser(userRegisterDto);
    }

    @Override
    public Response login(UserLoginDto userCredentials) throws ConstraintViolationsException, EntityNotFoundException {
        logger.info("login called - {}", userCredentials);

        service.login(userCredentials);
        return null;
    }
}
