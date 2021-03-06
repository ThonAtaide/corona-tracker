package com.ataide.corona.coronatracker.domain.service;

import com.ataide.corona.coronatracker.application.dtos.VisitStoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import com.ataide.corona.coronatracker.domain.repository.VisitedStoreRepository;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import com.ataide.corona.coronatracker.domain.service.interfaces.VisitService;
import com.ataide.corona.coronatracker.domain.util.ServerUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.springframework.data.domain.PageRequest.of;

@AllArgsConstructor
@Service
public class VisitServiceImpl implements VisitService {

    private static final Logger logger = LoggerFactory.getLogger(VisitServiceImpl.class);
    private final VisitedStoreRepository repository;
    private final StoreService storeService;

    @Override
    public VisitedStore createVisit(VisitStoreDto visit, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException {
        logger.info("createVisit method called - visitDto {} - storeId {}", visit, storeId);

        ServerUtil.validConstraintViolations(visit);
        Store store = storeService.validateStoreId(storeId);

        VisitedStore.VisitedStoreBuilder builder = VisitedStore.builder();
        VisitedStore visitedStore = builder
                .name(visit.getName())
                .phone(visit.getPhone())
                .store(store)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        return repository.save(visitedStore);
    }

    @Override
    public Page<VisitedStore> getStoreVisits(Long storeId, LocalDateTime start, LocalDateTime end, Integer page, Integer pageSize) throws MissingRequiredParameterException, EntityNotFoundException {
        logger.info("getStoreVisits method called - storeId {} - start {} - end {} ", storeId, start, end);

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        storeService.validateStoreId(storeId);
        PageRequest pageRequest = of(page, pageSize);
        Page<VisitedStore> visitedStoresPage;

        if (start != null && end != null) {
            visitedStoresPage = repository.findVisitsByStoreAndDateStartAndDateEnd(storeId, Timestamp.valueOf(start), Timestamp.valueOf(end), pageRequest);
        } else if (start != null) {
            visitedStoresPage = repository.findVisitsByStoreAndDateStart(storeId, Timestamp.valueOf(start), pageRequest);
        } else if (end != null) {
            visitedStoresPage = repository.findVisitsByStoreAndDateEnd(storeId, Timestamp.valueOf(end), pageRequest);
        } else {
            visitedStoresPage = repository.findVisitsByStoreId(storeId, pageRequest);
        }
        return visitedStoresPage;
    }
}
