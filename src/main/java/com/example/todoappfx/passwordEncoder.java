package com.example.todoappfx;

import java.util.Base64;

public class passwordEncoder {
    private static String encodingLogic(String password){
        //System.out.println(Base64.getEncoder().encodeToString(password.getBytes()));
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static void main(String[] args) {
        encodingLogic("helloworld");
    }
    public String encodePassword(String input){
        return encodingLogic(input);
    }
}
