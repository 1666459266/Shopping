package com.shopping.core.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class GeneratePassword {
    public static void main(String[] args) {
        String password = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(password);
    }
}
