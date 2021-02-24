package com.ataide.corona.coronatracker.domain.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    STORE("STORE"),
    PERSON("PERSON"),
    HEALTH_UNITY("HEALTH_UNITY");

    private final String userType;

    public UserType getUserType(String userType) {
        if (userType.equals(STORE)) {
            return STORE;
        } else if(userType.equals(PERSON)) {
            return PERSON;
        } else if (userType.equals(HEALTH_UNITY)) {
            return HEALTH_UNITY;
        }
        return null;
    }
}
