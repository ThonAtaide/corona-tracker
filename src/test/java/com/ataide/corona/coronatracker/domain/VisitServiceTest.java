package com.ataide.corona.coronatracker.domain;

import com.ataide.corona.coronatracker.application.dtos.VisitStoreDto;
import com.ataide.corona.coronatracker.application.exceptions.ConstraintViolationsException;
import com.ataide.corona.coronatracker.application.exceptions.EntityNotFoundException;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import com.ataide.corona.coronatracker.domain.entities.UserType;
import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import com.ataide.corona.coronatracker.domain.repository.VisitedStoreRepository;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import com.ataide.corona.coronatracker.domain.service.interfaces.VisitService;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@AutoConfigureTestDatabase
public class VisitServiceTest {


    @Autowired
    private VisitService service;

    @Autowired
    private VisitedStoreRepository repository;

    @Autowired
    private StoreService storeService;

    private final long storeId = 1;

//    @Test
//    public void registerValidVisit() throws EntityNotFoundException, MissingRequiredParameterException, ConstraintViolationsException {
//        VisitStoreDto visitStoreDto = mockValidVisitStoreDto();
//
//
//
//        VisitedStore visit = service.createVisit(visitStoreDto, 1L);
//
//        assertThat(visitStoreDto).usingRecursiveComparison().ignoringFields("id").isEqualTo(visit);
//    }
//
//    @Test
//    public void createStoreInvalid() {
//        StoreDto storeDto = mockAllFieldsInvalidStoreDto();
//        User user = mockUserToValidStoreDto();
//        assertThrows(ConstraintViolationsException.class, () -> assertThat(service.createStore(storeDto, user)));
//    }
//
//    @Test
//    public void testUpdateValid() throws ConstraintViolationsException, EntityNotFoundException, MissingRequiredParameterException {
//        long storeId = 1;
//        StoreDto storeDto = mockValidStoreDto();
//        storeDto.setId(storeId);
//        User user = mockUserToValidStoreDto();
//
//        when(repository.save(any(Store.class))).thenReturn(mockStoreRepositorySaveReturn(user));
//        when(repository.findById(storeId)).thenReturn(Optional.of(mockStoreRepositorySaveReturn(user)));
//        Store storeCreated = service.updateStore(storeDto, storeId);
//
//        assertThat(storeDto).usingRecursiveComparison().isEqualTo(storeCreated);
//    }
//
//    @Test
//    public void testUpdateMissingParameterException() {
//        long storeId = 1;
//        StoreDto storeDto = mockValidStoreDto();
//        storeDto.setId(storeId);
//
//        assertThrows(MissingRequiredParameterException.class, () -> assertThat(service.updateStore(storeDto, null)));
//    }
//
//    @Test
//    public void testUpdateEntityNotFound() {
//        long storeId = 1;
//        StoreDto storeDto = mockValidStoreDto();
//        storeDto.setId(storeId);
//
//        assertThrows(EntityNotFoundException.class, () -> assertThat(service.updateStore(storeDto, storeId)));
//    }
//
//    @Test
//    public void testUpdateConstraintViolations() {
//        long storeId = 1;
//        StoreDto invalidStoreDto = mockAllFieldsInvalidStoreDto();
//        invalidStoreDto.setId(storeId);
//
//        assertThrows(ConstraintViolationsException.class, () -> assertThat(service.updateStore(invalidStoreDto, storeId)));
//    }
//
//    @Test
//    public void testStoreByIdSuccess() throws EntityNotFoundException, MissingRequiredParameterException {
//        long storeId = 1;
//
//        User user = mockUserToValidStoreDto();
//
//        when(repository.findById(storeId)).thenReturn(Optional.of(mockStoreRepositorySaveReturn(user)));
//        Store storeFetched = service.getStore(storeId);
//
//        assertThat(storeFetched).usingRecursiveComparison().isEqualTo(mockStoreRepositorySaveReturn(user));
//    }
//
//    @Test
//    public void testStoreByIdMissingRequiredParameter() {
//        assertThrows(MissingRequiredParameterException.class, () -> assertThat(service.getStore(null)));
//    }
//
//    @Test
//    public void testStoreByIdNotFound() {
//        assertThrows(EntityNotFoundException.class, () -> assertThat(service.getStore(1L)));
//    }
//
//    @Test
//    public void testGetStores() {
//        int page = 0;
//        int pageSize = 1;
//        List<Store> storeList = listOfMockStoreList();
//
//        when(repository.findAll(PageRequest.of(page, pageSize))).thenReturn(new PageImpl<Store>(storeList.subList(0, 1), PageRequest.of(page,pageSize), 4L));
//        Page<Store> storePage = service.getStores(page, pageSize);
//
//        assertThat(storePage.getTotalElements()).isEqualTo(storeList.size());
//        assertThat(storePage.getTotalPages()).isEqualTo(storeList.size()/pageSize);
//        assertThat(storePage.getContent().size()).isEqualTo(pageSize);
//    }
//
//    @Test
//    public void testGetStoresWithoutPageOrPageSize() {
//
//        List<Store> storeList = listOfMockStoreList();
//
//        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<Store>(storeList, PageRequest.of(0, 10), 4L));
//        Page<Store> storePage = service.getStores(null, null);
//
//        assertThat(storePage.getTotalElements()).isEqualTo(storeList.size());
//        assertThat(storePage.getTotalPages()).isEqualTo(1);
//        assertThat(storePage.getContent().size()).isEqualTo(storeList.size());
//    }
//
//    @Test
//    public void testDefaultConstructor() {
//        Store store = new Store(1L, "Golden", "1633074566", "01234567891011", "13562330", null);
//        assertNotNull(store);
//    }

    private VisitStoreDto mockValidVisitStoreDto() {
        VisitStoreDto.VisitStoreDtoBuilder builder = VisitStoreDto.builder();
        return builder
                .name("Golden Sports")
                .phone("1633075499")
                .build();
    }

    private VisitedStore mockValidVisitStore() {
        VisitedStore.VisitedStoreBuilder builder = VisitedStore.builder();
        return builder
                .id(1L)
                .name("Golden Sports")
                .phone("1633075499")
                .build();
    }
//
//    private User mockUserToValidStoreDto() {
//        User.UserBuilder builder = User.builder();
//        return builder
//                .username("test123")
//                .password("test123")
//                .build();
//    }
//
//    private StoreDto mockAllFieldsInvalidStoreDto() {
//        StoreDto.StoreDtoBuilder builder = StoreDto.builder();
//        return builder
//                .name("Go")
//                .phone("3307-5499")
//                .cnpj("196935000901")
//                .cep("876986")
//                .build();
//    }
//
    private Store mockStoreEntity() {
        return Store.builder()
                .id(1L)
                .name("Golden Sports")
                .phone("1633075499")
                .cnpj("04196935000901")
                .cep("14876986")
                .user(new User(1L, "testGolden", "123456", UserType.STORE.name()))
                .build();
    }
//
//    private List<Store> listOfMockStoreList() {
//        Store storeOne = Store.builder()
//                .id(1L)
//                .name("Golden Sports")
//                .phone("1633075499")
//                .cnpj("04196935000901")
//                .cep("14876986")
//                .user(new User(1L, "goldenSports", "123456", UserType.STORE.name()))
//                .build();
//
//        Store storeTwo = Store.builder()
//                .id(2L)
//                .name("Drogaria Rosário")
//                .phone("1633384791")
//                .cnpj("04196935011901")
//                .cep("14876981")
//                .user(new User(2L, "rosarioStore", "rosario123456", UserType.STORE.name()))
//                .build();
//
//        Store storeThree = Store.builder()
//                .id(3L)
//                .name("Tecunsh")
//                .phone("1933885499")
//                .cnpj("12396935000901")
//                .cep("58776986")
//                .user(new User(3L, "tecunsh", "Tecunsh123456", UserType.STORE.name()))
//                .build();
//
//        Store storeFour = Store.builder()
//                .id(4L)
//                .name("Neidinha Frios")
//                .phone("1633075400")
//                .cnpj("04196935100901")
//                .cep("14815986")
//                .user(new User(4L, "Neidinha", "neidinha123456", UserType.STORE.name()))
//                .build();
//
//        return List.of(storeOne, storeTwo, storeThree, storeFour);
//    }
//


}
