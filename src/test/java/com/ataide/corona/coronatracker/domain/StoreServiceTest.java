package com.ataide.corona.coronatracker.domain;

import com.ataide.corona.coronatracker.application.dtos.StoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import com.ataide.corona.coronatracker.domain.repository.StoreRepository;
import com.ataide.corona.coronatracker.domain.repository.UserRepository;
import com.ataide.corona.coronatracker.domain.service.StoreServiceImpl;
import com.ataide.corona.coronatracker.util.DatabaseUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StoreServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceTest.class);

    @Autowired
    private StoreServiceImpl service;

    @Autowired
    private StoreRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void tearDown() {
        DatabaseUtils.clearDatabaseData(dataSource);
        logger.info("Database clear");
    }

    @Test
    public void createStoreSuccess() throws ConstraintViolationsException {
        StoreDto storeDto = mockValidStoreDto();
        User user = userRepository.findById(5L).orElseThrow(NullPointerException::new);
        int expectedSize = repository.findAll().size() + 1;

        Store storeCreated = service.createStore(storeDto, user);
        assertThat(storeDto).usingRecursiveComparison().ignoringFields("id").isEqualTo(storeCreated);
        assertThat(expectedSize).isEqualTo(repository.findAll().size());
    }

    @Test
    public void createStoreInvalid() {
        StoreDto storeDto = mockAllFieldsInvalidStoreDto();
        User user = userForInvalidStoreDto();
        assertThrows(ConstraintViolationsException.class, () -> assertThat(service.createStore(storeDto, user)));
    }

    @Test
    public void testUpdateValid() throws ConstraintViolationsException, EntityNotFoundException, MissingRequiredParameterException {
        long storeId = 4;
        StoreDto storeDto = storeDtoForUpdateValid();

        Store storeCreated = service.updateStore(storeDto, storeId);
        assertThat(storeDto).usingRecursiveComparison().isEqualTo(storeCreated);
    }

    @Test
    public void testUpdateMissingParameterException() {
        StoreDto storeDto = storeDtoForUpdateValid();
        assertThrows(MissingRequiredParameterException.class, () -> assertThat(service.updateStore(storeDto, null)));
    }

    @Test
    public void testUpdateEntityNotFound() {
        long storeId = 99;
        StoreDto storeDto = storeDtoForUpdateValid();
        storeDto.setId(storeId);

        assertThrows(EntityNotFoundException.class, () -> assertThat(service.updateStore(storeDto, storeId)));
    }

    @Test
    public void testUpdateConstraintViolations() {
        long storeId = 1;
        StoreDto invalidStoreDto = mockAllFieldsInvalidStoreDto();
        invalidStoreDto.setId(storeId);

        assertThrows(ConstraintViolationsException.class, () -> assertThat(service.updateStore(invalidStoreDto, storeId)));
    }
/////////////////////////////
    @Test
    public void testStoreByIdSuccess() throws EntityNotFoundException, MissingRequiredParameterException {
        long storeId = 1;
        Store store = repository.findById(storeId).orElseThrow(NullPointerException::new);

        Store storeFetched = service.getStore(storeId);
        assertThat(storeFetched).usingRecursiveComparison().isEqualTo(store);
    }

    @Test
    public void testStoreByIdMissingRequiredParameter() {
        assertThrows(MissingRequiredParameterException.class, () -> assertThat(service.getStore(null)));
    }

    @Test
    public void testStoreByIdNotFound() {
        assertThrows(EntityNotFoundException.class, () -> assertThat(service.getStore(10L)));
    }

    @Test
    public void testGetStores() {
        int page = 0;
        int pageSize = 1;

        Page<Store> storePage = service.getStores(page, pageSize);

        assertThat(storePage.getTotalElements()).isEqualTo(4);
        assertThat(storePage.getTotalPages()).isEqualTo(4);
        assertThat(storePage.getContent().size()).isEqualTo(pageSize);
    }

    @Test
    public void testGetStoresWithoutPageOrPageSize() {

        Page<Store> storePage = service.getStores(null, null);

        assertThat(storePage.getTotalElements()).isEqualTo(4);
        assertThat(storePage.getTotalPages()).isEqualTo(1);
        assertThat(storePage.getContent().size()).isEqualTo(4);
    }

    @Test
    public void testDefaultConstructor() {
        Store store = new Store(1L, "Golden", "1633074566", "01234567891011", "13562330", null);
        assertNotNull(store);
    }

    private StoreDto mockValidStoreDto() {
        StoreDto.StoreDtoBuilder builder = StoreDto.builder();
        return builder
                .name("TestUserStore")
                .phone("1633071000")
                .cnpj("04196935000910")
                .cep("14876444")
                .build();
    }

    private User userForInvalidStoreDto() {
        User.UserBuilder builder = User.builder();
        return builder
                .username("test123")
                .password("test123")
                .build();
    }

    private StoreDto storeDtoForUpdateValid() {
        return StoreDto.builder()
                .id(4L)
                .name("Casas Bahia")
                .phone("1478996655")
                .cep("13571458")
                .cnpj("78141312111098")
                .build();
    }

    private StoreDto mockAllFieldsInvalidStoreDto() {
        StoreDto.StoreDtoBuilder builder = StoreDto.builder();
        return builder
                .name("Go")
                .phone("3307-5499")
                .cnpj("196935000901")
                .cep("876986")
                .build();
    }
}
