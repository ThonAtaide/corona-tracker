package com.ataide.corona.coronatracker.domain.service.interfaces;

import com.ataide.corona.coronatracker.application.dtos.UserLoginDto;
import com.ataide.corona.coronatracker.application.dtos.UserRegisterDto;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.application.exceptions.UsernameAllocatedException;

import java.sql.SQLException;

public interface UserService {

    Response registerUser(UserRegisterDto userRegisterDto) throws UsernameAllocatedException, MissingRequiredParameterException, ConstraintViolationsException;

    Response login(UserLoginDto userCredentials) throws ConstraintViolationsException, EntityNotFoundException;
}
