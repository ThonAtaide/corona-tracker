package com.ataide.corona.coronatracker.domain.repository;

import com.ataide.corona.coronatracker.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository <Store, Long> {

    @Query("SELECT s FROM Store s WHERE s.user =':userId'")
    Store findStoreByUser(Long userId);
}
