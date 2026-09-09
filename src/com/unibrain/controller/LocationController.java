package com.unibrain.controller;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class LocationController {

    @GetMapping("/search")
    public String searchLocation(@RequestParam String query) {

        String accessToken = "584ed90f-f568-4771-878f-60e91ddad5e0"; // get dynamically ideally

        String url = "https://atlas.mapmyindia.com/api/places/search/json?query=" + query;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );
System.err.println(response);
        return response.getBody();
    }
    
    @GetMapping("/latlng")
    public String getLatLng(@RequestParam String eLoc) {

        String accessToken = "cf2c5f71-ed5e-46f0-8767-92a45dcff74c"; // NOT map SDK key

        String url = "https://atlas.mapmyindia.com/api/places/search/json?eLoc=" + eLoc;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        System.out.println("LatLng API response: " + response.getBody());

        return response.getBody();
    }
    
    
    @GetMapping("/reverse-geocode")
    public Map<String, Object> getAddress(
            @RequestParam String lat,
            @RequestParam String lon) {

        String url = "https://nominatim.openstreetmap.org/reverse?format=json"
                + "&lat=" + lat
                + "&lon=" + lon;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "CRUD_operaationproject");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        System.err.println(response.getBody());
        return response.getBody();
    }

}