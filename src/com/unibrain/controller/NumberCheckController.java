package com.unibrain.controller;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCheckController {

    private static String secureKey;

    @GetMapping("/generateToken")
    public String generateToken() {
        if (secureKey == null) {
            secureKey = generateSecureKey();
        }
        return secureKey;
    }

    private String generateSecureKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[16]; // 128 bits
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    @GetMapping("/checkNumber")
    public String checkNumber(@RequestHeader("Authorization") String token, @RequestParam("number") Integer number) {
    	System.out.println("number is:"+number);
    	System.out.println("token is:"+token);
        if (!validateToken(token)) {
            return "Invalid token";
        }

        boolean isNumberOne = isNumberOne(number); // Assuming this method calls the API and returns the result
        return String.valueOf(isNumberOne);
    }

    private boolean validateToken(String token) {
    	System.out.println("secure key is:"+secureKey);
        return secureKey.equals(token);
    }

    private boolean isNumberOne(Integer number) {
        // Your logic to check if the number is 1
    	int num=number;
    	switch(num)
    	{
    	case 1:
    		System.out.println("it is monday");
    		return false;
    		
    	case 2:
    		System.out.println("is is tuesday");
    		break;
    	case 3:
    		System.out.println("it is monday");
    		break;
    		
    		default:
    		System.out.println("it is sunday");
    	}
        return true;
    }
}
