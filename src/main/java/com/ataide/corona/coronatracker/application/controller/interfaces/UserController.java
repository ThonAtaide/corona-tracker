package com.ataide.corona.coronatracker.application.controller.interfaces;

import com.ataide.corona.coronatracker.application.dtos.UserLoginDto;
import com.ataide.corona.coronatracker.application.dtos.UserRegisterDto;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.application.exceptions.UsernameAllocatedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/user")
public interface UserController {

    @PostMapping
    Response registerUser(@RequestBody UserRegisterDto userRegisterDto) throws UsernameAllocatedException, MissingRequiredParameterException, ConstraintViolationsException;

    @PostMapping(value = "/login")
    Response login(@RequestBody UserLoginDto userCredentials) throws ConstraintViolationsException, EntityNotFoundException;
}
