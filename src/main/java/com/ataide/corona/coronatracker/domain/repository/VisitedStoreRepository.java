package com.ataide.corona.coronatracker.domain.repository;

import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitedStoreRepository extends JpaRepository <VisitedStore, Long> {
}
