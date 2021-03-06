package com.ataide.corona.coronatracker.domain.service.interfaces;

import com.ataide.corona.coronatracker.application.dtos.VisitStoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface VisitService {

    VisitedStore createVisit(VisitStoreDto visit, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException;

    Page<VisitedStore> getStoreVisits(Long storeId, LocalDateTime start, LocalDateTime end, Integer page, Integer pageSize) throws MissingRequiredParameterException, EntityNotFoundException;

}
