package com.ataide.corona.coronatracker.domain;

import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import com.ataide.corona.coronatracker.domain.repository.StoreRepository;
import com.ataide.corona.coronatracker.domain.service.StoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @InjectMocks
    private StoreServiceImpl service;

    @Mock
    private StoreRepository repository;

    @Test
    public void createStoreSuccess() throws MissingRequiredParameterException, ConstraintViolationsException {
        StoreDto storeDto = mockValidStoreDto();
        User user = mockUserToValidStoreDto();
        Store storeMockedReturn = new Store(1L, "Golden Sports", "1633075499", "04196935000901", "14876986", user);

        when(repository.save(any(Store.class))).thenReturn(storeMockedReturn);
        Store storeCreated = service.createStore(storeDto, user);

        assertThat(storeDto).usingRecursiveComparison().ignoringFields("id").isEqualTo(storeCreated);
    }

//    @Test
//    public void createStoreInvalid() throws MissingRequiredParameterException, ConstraintViolationsException {
//        StoreDto storeDto = mockAllFieldsInvalidStoreDto();
//        User user = mockUserToValidStoreDto();
//        service.createStore(storeDto, user);
//        assertNotNull(storeDto);
//    }

    public StoreDto mockValidStoreDto() {
        StoreDto.StoreDtoBuilder builder = StoreDto.builder();
        return builder
                .name("Golden Sports")
                .phone("1633075499")
                .cnpj("04196935000901")
                .cep("14876986")
                .build();
    }

    public User mockUserToValidStoreDto() {
        User.UserBuilder builder = User.builder();
        return builder
                .username("test123")
                .password("test123")
                .build();
    }

    public StoreDto mockAllFieldsInvalidStoreDto() {
        StoreDto.StoreDtoBuilder builder = StoreDto.builder();
        return builder
                .name("Go")
                .phone("3307-5499")
                .cnpj("196935000901")
                .cep("876986")
                .build();
    }
}
