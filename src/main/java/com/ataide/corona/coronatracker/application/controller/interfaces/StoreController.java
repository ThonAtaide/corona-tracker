package com.ataide.corona.coronatracker.application.controller.interfaces;

import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/store")
public interface StoreController {

    @GetMapping
    Response getStores(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping(value = "/{store}")
    Response getStore(@PathVariable(name = "store") Long storeId);

    @PutMapping(value = "/{storeId}")
    Response updateStore(@RequestBody(required = true) StoreDto storeDto, @PathVariable(name = "storeId") Long storeId);
}
