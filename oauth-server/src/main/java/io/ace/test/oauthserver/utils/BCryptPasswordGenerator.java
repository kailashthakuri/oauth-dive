package io.ace.test.oauthserver.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordGenerator=new BCryptPasswordEncoder();
        String password = passwordGenerator.encode("password");
        System.out.println(password);
    }
}
