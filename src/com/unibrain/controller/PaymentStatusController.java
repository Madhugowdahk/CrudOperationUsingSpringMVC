package com.unibrain.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentStatusController {

    private static final String AUTH_TOKEN = "my-secret-token";

    @GetMapping("/status/{paymentNumber}")
    @ResponseBody
    public ResponseEntity<String> getPaymentStatus(
            @PathVariable("paymentNumber") String paymentNumber,
            HttpServletRequest request,
            HttpServletResponse response) {
    	System.out.println("the value becomes old now...");

        // Read Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.equals(AUTH_TOKEN)) {
            return new ResponseEntity<>("Unauthorized - Invalid token", HttpStatus.UNAUTHORIZED);
        }

        // Simulate payment check (dummy example)
        boolean paymentSuccess = checkPaymentStatus(paymentNumber);

        if (paymentSuccess) {
            return new ResponseEntity<>("Payment Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment Failed", HttpStatus.OK);
        }
    }

    // Dummy logic to simulate payment success/failure
    private boolean checkPaymentStatus(String paymentNumber) {
        // Example: mark even numbers as success
        return Integer.parseInt(paymentNumber) % 2 == 0;
    }
}
