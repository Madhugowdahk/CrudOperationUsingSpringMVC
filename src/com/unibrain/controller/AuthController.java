package com.unibrain.controller;

import org.springframework.web.bind.annotation.*;

import com.auctionkeys.example.ApiKeyResponse;
import com.unibrain.Model.ApiKeyRequest;
import com.unibrain.Model.UserResponse;
import com.unibrain.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
  //Your API expects JSON in request body, but in Postman you are sending Basic Auth headers only.
   //we don't need to set it in authorization header in postman and we have to send it in body(raw json only)
    @PostMapping("/generate-api-key")
    public ApiKeyResponse generateApiKey(@RequestBody ApiKeyRequest request) {
      System.out.println(" it is reaching or not");
        String apiKey = authService.generateApiKey(
                request.getUsername(),
                request.getPassword()
        );

        return new ApiKeyResponse(apiKey);
    }
    
    
//    If you want username/password from Authorization header, then your controller must change.

//     Controller change
//    @PostMapping("/generate-api-key")
//    public ApiKeyResponse generateApiKey(
//            @RequestHeader("Authorization") String authHeader) {
//
//        String base64Credentials = authHeader.substring("Basic ".length());
//        byte[] decoded = Base64.getDecoder().decode(base64Credentials);
//        String credentials = new String(decoded);
//
//        String[] values = credentials.split(":");
//        String username = values[0];
//        String password = values[1];
//
//        String apiKey = authService.generateApiKey(username, password);
//        return new ApiKeyResponse(apiKey);
//    }
//
//     Postman
//
//    Authorization  Basic Auth
//
//    Username: admin
//
//    Password: admin123
//
//    Body:  NOT REQUIRED
    
    
    @GetMapping("/user/details")
    public UserResponse getUserDetails(
            @RequestHeader("X-API-KEY") String apiKey,@RequestHeader("SiteName") String siteName) {

        return authService.getUserData(apiKey,siteName);
    }


}
