package com.ataide.corona.coronatracker.domain.repository;

import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;

@Repository
public interface VisitedStoreRepository extends JpaRepository <VisitedStore, Long> {
    @Query("SELECT v FROM VisitedStore v WHERE STORE_ID=:storeId AND (CREATED_AT BETWEEN :start AND :end)")
    Page<VisitedStore> findVisitsByStoreAndDateStartAndDateEnd(Long storeId, Timestamp start, Timestamp end, Pageable pageRequest);

    @Query("SELECT v FROM VisitedStore v WHERE STORE_ID=:storeId AND CREATED_AT >= :start")
    Page<VisitedStore> findVisitsByStoreAndDateStart(Long storeId, Timestamp start, Pageable pageRequest);

    @Query("SELECT v FROM VisitedStore v WHERE STORE_ID=:storeId AND CREATED_AT <= :end")
    Page<VisitedStore> findVisitsByStoreAndDateEnd(Long storeId, Timestamp end, Pageable pageRequest);

    @Query("SELECT v FROM VisitedStore v WHERE STORE_ID=:storeId")
    Page<VisitedStore> findVisitsByStoreId(Long storeId, Pageable pageRequest);
}
