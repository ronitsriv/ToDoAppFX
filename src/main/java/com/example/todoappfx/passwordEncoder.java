package com.example.todoappfx;

import java.util.Base64;

public class passwordEncoder {
    private static String encodePassword(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    private static String decodePassword(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }

    public static String callEncode(String input){
        System.out.println(encodePassword(input));
        return encodePassword(input);
    }

    public static String callDecode(String input){
        System.out.println(decodePassword(input));
        return decodePassword(input);
    }
}
