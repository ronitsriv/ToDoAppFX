package com.example.todoappfx;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class passwordEncoder {
    private static String encodePassword(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static String decodePassword(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    public static String callEncode(String input){
        //System.out.println(encodePassword(input));
        return encodePassword(input);
    }

    public static String callDecode(String input){
        //System.out.println(decodePassword(input));
        return decodePassword(input);
    }
}
