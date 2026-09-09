package com.unibrain.service;

import org.springframework.stereotype.Service;

import com.unibrain.Model.UserResponse;
import com.unibrain.controller.ApiKeyGenerator;

@Service
public class AuthService {

    // Simulating DB validation
    public boolean validateUser(String username, String password) {
        // In real app  DB lookup + password hash check
        return "admin".equals(username) && "admin123".equals(password);
    }

    public String generateApiKey(String username, String password) {

        if (!validateUser(username, password)) {
            throw new RuntimeException("Invalid username or password");
        }

        String apiKey = ApiKeyGenerator.generate();

        //  save apiKey mapped to user in DB
        // apiKeyRepository.save(username, apiKey);

        return apiKey;
    }
    
    
    // Simulating DB check
    public boolean isValidApiKey(String apiKey) {

    	
        // In real app  DB lookup
        return "c8GMAuWRS4rgpL-JdZS6x5Ra1XbX6XB7H-Uln1ea7qY".equals(apiKey);
    }

    public UserResponse getUserData(String apiKey,String siteName) {

    	if(!siteName.equals("bmtc")) {
        if (!isValidApiKey(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }
    }
        // Normally fetched from DB
        return new UserResponse(
                "admin",
                "admin@company.com",
                "ADMIN"
        );
    }

}
