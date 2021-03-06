package com.ataide.corona.coronatracker.application.controller.interfaces;

import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.dtos.VisitStoreDto;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/store")
public interface StoreController {

    @GetMapping
    Response getStores(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping(value = "/{store}")
    Response getStore(@PathVariable(name = "store") Long storeId) throws Exception;

    @PutMapping(value = "/{storeId}")
    Response updateStore(@RequestBody StoreDto storeDto, @PathVariable(name = "storeId") Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException;

    @PostMapping(value = "/{storeId}/visit")
    Response createVisit(@RequestBody VisitStoreDto visit, @PathVariable(name = "storeId") Long storeId) throws MissingRequiredParameterException, EntityNotFoundException, ConstraintViolationsException;

    @GetMapping(value = "/{storeId}/visit")
    Response getStoreVisits(@PathVariable(name = "storeId") Long storeId, @RequestParam(name = "start", required = false) LocalDateTime start,
                            @RequestParam(name = "end", required = false) LocalDateTime end, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize) throws MissingRequiredParameterException, EntityNotFoundException;
}
