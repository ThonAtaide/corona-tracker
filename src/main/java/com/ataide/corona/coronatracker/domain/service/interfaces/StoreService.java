package com.ataide.corona.coronatracker.domain.service.interfaces;

import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import org.springframework.data.domain.Page;

public interface StoreService {

    Page<Store> getStores(Integer page, Integer pageSize);

    Store getStore(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException;

    Store createStore(StoreDto storeDto, User user) throws ConstraintViolationsException;

    Store updateStore(StoreDto storeDto, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException;

    Store validateStoreId(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException;

    Store getStoreByUser(Long userId);
}
