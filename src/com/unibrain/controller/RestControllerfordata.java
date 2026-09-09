package com.unibrain.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
//YourController.java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.undertow.server.handlers.form.FormData;

@CrossOrigin(origins = "http://file:///F:/HTMLANDCSSANDJS/index.html")
@RestController
@RequestMapping("/api")
public class RestControllerfordata {

 @PostMapping("/submitFormData")
 public String submitFormData(@RequestBody FormData formData) {
     // Process the received data
     System.out.println("Received data: " + formData.toString());

     // You can perform further processing here

     // Return a response if needed
     return "Data received successfully!";
 }
}
