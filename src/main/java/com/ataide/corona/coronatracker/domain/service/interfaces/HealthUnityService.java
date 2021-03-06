package com.ataide.corona.coronatracker.domain.service.interfaces;

import com.ataide.corona.coronatracker.application.dtos.HealthUnityDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.HealthUnity;
import com.ataide.corona.coronatracker.domain.entities.User;
import org.springframework.data.domain.Page;

public interface HealthUnityService {

    Page<HealthUnityDto> getHealthUnits(Integer page, Integer pageSize);

    HealthUnity getHealthUnity(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException;

    HealthUnity createHealthUnit(HealthUnityDto healthUnityDto, User user) throws ConstraintViolationsException;

    HealthUnity updateHealthUnit(HealthUnityDto healthUnityDto, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException;

    HealthUnity validateHealthUnitsId(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException;

    HealthUnity getHealthUnityByUser(Long userId);
}
