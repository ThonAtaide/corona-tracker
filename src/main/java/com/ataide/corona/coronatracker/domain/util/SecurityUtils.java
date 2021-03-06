package com.ataide.corona.coronatracker.domain.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SecurityUtils {
    public static String encrypt(String plainText) {
        String saltGenerated = BCrypt.gensalt();
        return BCrypt.hashpw(plainText, saltGenerated);
    }
}
