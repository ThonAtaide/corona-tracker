package com.ataide.corona.coronatracker.application.controller;

import com.ataide.corona.coronatracker.application.controller.interfaces.StoreController;
import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.dtos.VisitStoreDto;
import com.ataide.corona.coronatracker.application.dtos.response.*;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import com.ataide.corona.coronatracker.domain.service.interfaces.VisitService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class StoreControllerImpl implements StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreControllerImpl.class);
    private final StoreService storeService;
    private final VisitService visitService;

    @Override
    public Response getStores(Integer page, Integer pageSize) {
        logger.info("getStore Called - page {} - size {}", page, pageSize);

        Page<Store> pageable = storeService.getStores(page, pageSize);

        List<RecordData> storeCollection = pageable.getContent().stream()
                .map(StoreDto::new).collect(Collectors.toList());

        PageableRecord.PageableRecordBuilder builder = PageableRecord.builder();
        builder
            .currentPage(page.longValue())
            .pageSize(pageSize.longValue())
            .totalElements(pageable.getTotalElements())
            .totalPages((long) pageable.getTotalPages())
            .collection(storeCollection);

        return ResponseData.builder()
                .statusCode(HttpStatus.OK.value())
                .data(builder.build())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response getStore(Long storeId) throws MissingRequiredParameterException, EntityNotFoundException {
        logger.info("getStore called - storeId {}", storeId);

        Store store = storeService.getStore(storeId);

        return ResponseData.builder()
                .statusCode(HttpStatus.OK.value())
                .data(new StoreDto(store))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response updateStore(StoreDto storeDto, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException {
        logger.info("updateStore called - storeId {} - storeDto {}", storeId, storeDto);

        Store store = storeService.updateStore(storeDto, storeId);

        if (store == null) {
            logger.error("Some error occurred and the store wasn't updated.");

            return ResponseError.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .errors(Collections.singletonList("Houve um erro e a loja não foi atualizada."))
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        return ResponseData.builder()
                .statusCode(HttpStatus.OK.value())
                .data(new StoreDto(store))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response createVisit(VisitStoreDto visit, Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException {
        logger.info("createVisit called - visit {} - storeDto {}", visit, storeId);

        VisitedStore visitCreated = visitService.createVisit(visit, storeId);

        if (visitCreated == null) {
            logger.error("Some error occurred and the store wasn't updated.");
            return ResponseError.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .errors(Collections.singletonList("Ocorreu um erro e a visita não foi registrada."))
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        return ResponseData.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(new VisitStoreDto(visitCreated))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response getStoreVisits(Long storeId, LocalDateTime start, LocalDateTime end, Integer page, Integer pageSize) throws MissingRequiredParameterException, EntityNotFoundException {
        logger.info("getStoreVisits called - visit {} - start date {} - end date {}", storeId, start, end);

        Page<VisitedStore> visitStorePage = visitService.getStoreVisits(storeId, start, end, page, pageSize);
        List<RecordData> visitDtoList = visitStorePage.getContent().stream().map(VisitStoreDto::new).collect(Collectors.toList());
        PageableRecord data = PageableRecord.builder()
                .collection(visitDtoList)
                .currentPage(page.longValue())
                .totalElements(visitStorePage.getTotalElements())
                .totalPages((long) visitStorePage.getTotalPages())
                .build();

        return ResponseData.builder()
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
