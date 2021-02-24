package com.ataide.corona.coronatracker.domain.service;

import com.ataide.corona.coronatracker.application.dtos.RegisterUserDto;
import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import com.ataide.corona.coronatracker.domain.entities.UserType;
import com.ataide.corona.coronatracker.domain.repository.StoreRepository;
import com.ataide.corona.coronatracker.domain.repository.UserRepository;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import com.ataide.corona.coronatracker.domain.util.ServerUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.Set;

import static org.springframework.data.domain.PageRequest.of;

@AllArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    private StoreRepository repository;

    @Override
    public Page<Store> getStores(Integer page, Integer pageSize) {
        logger.info("getStores method called - page {} - pageSize {}", page, pageSize);

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        return repository.findAll(of(page, pageSize));
    }

    @Override
    public Store getStore(Long storeId) throws Exception {
        logger.info("getStore method called - storeId {}",storeId);

        if (storeId == null) {
            throw new EntityNotFoundException("Loja não encontrada.");
        }
        return repository.findById(storeId).orElse(null);
    }

    @Override
    public Store createStore(StoreDto storeDto, User user) throws MissingRequiredParameterException, ConstraintViolationsException {
        logger.info("createStore method called - storeDto {}", storeDto);

        Validator validator = ServerUtil.getValidator();
        Set<ConstraintViolation<StoreDto>> constraintViolations = validator.validate(storeDto);

        validConstraintViolations(constraintViolations);

        user.setRole(UserType.STORE.toString());

        Store.StoreBuilder builder = Store.builder();
        Store store = builder
                .name(storeDto.getName())
                .phone(storeDto.getPhone())
                .cnpj(storeDto.getCnpj())
                .cep(storeDto.getCep())
                .user(user)
                .build();

        return repository.save(store);
    }

    private void validConstraintViolations(Set<ConstraintViolation<StoreDto>> constraintViolations) throws ConstraintViolationsException {
        if (constraintViolations.size() > 0 ) throw new ConstraintViolationsException(new ArrayList<>(constraintViolations));
    }

    @Override
    public Store updateStore(StoreDto storeDto, Long storeId) throws MissingRequiredParameterException {
        logger.info("updateStore method called - storeDto {} - storeId {}", storeDto, storeId);

        if (storeId == null) throw new MissingRequiredParameterException("Dados de entrada incompletos");

        Store.StoreBuilder builder = Store.builder();
        Store store = builder
                .id(storeId)
                .name(storeDto.getName())
                .phone(storeDto.getPhone())
                .cnpj(storeDto.getCnpj())
                .cep(storeDto.getCep())
                .build();

        return repository.save(store);
    }




}
