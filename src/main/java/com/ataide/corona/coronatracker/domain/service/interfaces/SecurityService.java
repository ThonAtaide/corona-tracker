package com.ataide.corona.coronatracker.domain.service.interfaces;

public interface SecurityService {
    String getToken(String subject);
    String getSubject(String token);
    String encrypt(String plainText);
    Boolean authenticate(String candidatePassword, String databasePassword);
}
