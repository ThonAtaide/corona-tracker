package com.ataide.corona.coronatracker.domain.service;

import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import com.ataide.corona.coronatracker.domain.repository.StoreRepository;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import com.ataide.corona.coronatracker.domain.util.ServerUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    private final StoreRepository repository;

    @Override
    public Page<Store> getStores(Integer page, Integer pageSize) {
        logger.info("getStores method called - page {} - pageSize {}", page, pageSize);

        if (page == null) page = 0;
        if (pageSize == null) pageSize = 10;

        return repository.findAll(of(page, pageSize));
    }

    @Override
    public Store getStore(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException {
        logger.info("getStore method called - storeId {}",storeId);
        return validateStoreId(storeId);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Store createStore(StoreDto storeDto, User user) throws ConstraintViolationsException {
        logger.info("createStore method called - storeDto {}", storeDto);

        ServerUtil.validConstraintViolations(storeDto);

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Store updateStore(StoreDto storeDto, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException {
        logger.info("updateStore method called - storeDto {} - storeId {}", storeDto, storeId);

        ServerUtil.validConstraintViolations(storeDto);
        Store store = validateStoreId(storeId);

        store.setId(storeId);
        store.setName(storeDto.getName());
        store.setPhone(storeDto.getPhone());
        store.setCnpj(storeDto.getCnpj());
        store.setCep(storeDto.getCep());

        return repository.save(store);
    }

    public Store validateStoreId(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException {
        if (storeId == null) throw new MissingRequiredParameterException("O id da loja não foi informado.");

        return repository.findById(storeId).orElseThrow(() -> new EntityNotFoundException("Loja não encontrada."));
    }

    @Override
    public Store getStoreByUser(Long userId) {
        logger.info("getStoreByUser method called - userId {}", userId);
        return repository.findStoreByUser(userId);
    }
}
