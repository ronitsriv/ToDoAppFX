package com.example.todoappfx;

import java.util.Base64;

public class passwordEncoder {
    private String encodingLogic(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String encodePassword(String input){
        return encodingLogic(input);
    }
}
