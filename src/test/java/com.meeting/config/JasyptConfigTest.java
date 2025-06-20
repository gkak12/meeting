package com.meeting.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptConfigTest {

    @Test
    void stringEncryptor() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgresql123!@#";

        System.out.println("url: ".concat(jasyptEncoding(url)));
        System.out.println("username: ".concat(jasyptEncoding(username)));
        System.out.println("password: ".concat(jasyptEncoding(password)));
    }

    private String jasyptEncoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(System.getenv("JASYPT_KEY"));

        return pbeEnc.encrypt(value);
    }

    @Test
    void stringDecryptor() {
        String url = "geEBTZoT6kX4c3nxCiMY7a1bDuhtjehKkR8Ko5xm16j7foxZjrKE6DFRbJHOqalrpGpjYRVNHfc=";
        String username = "1XpdYOgSCnKxRL8zIRl1dLVAY//H1Yw3";
        String password = "JQVkw4WXboSDk/kDAMmE+hj0bAc4KEs4WDDMQP7o8Fc=";

        System.out.println("url: ".concat(jasyptDecoding(url)));
        System.out.println("username: ".concat(jasyptDecoding(username)));
        System.out.println("password: ".concat(jasyptDecoding(password)));
    }

    private String jasyptDecoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(System.getenv("JASYPT_KEY"));

        return pbeEnc.decrypt(
                value
                    .replace("ENC(", "")
                    .replace(")", "")
        );
    }
}
