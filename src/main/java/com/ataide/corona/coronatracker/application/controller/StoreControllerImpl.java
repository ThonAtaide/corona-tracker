package com.ataide.corona.coronatracker.application.controller;

import com.ataide.corona.coronatracker.application.controller.interfaces.StoreController;
import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class StoreControllerImpl implements StoreController {

    private StoreService service;

    @Override
    public Response getStores(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Response getStore(Long storeId) {
        return null;
    }

    @Override
    public Response updateStore(StoreDto storeDto, Long storeId) {
        return null;
    }
}
