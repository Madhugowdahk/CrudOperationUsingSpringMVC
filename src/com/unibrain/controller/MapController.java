package com.unibrain.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

@Controller
@RequestMapping("maps")
public class MapController {

    // Load JSP page
    @GetMapping("/map")
    public String loadMapPage() {
        return "map"; // map.jsp
    }
    
    
    @GetMapping("/openstreetmap")
    public String openstreetmap() {
        return "openstreetmap"; // map.jsp
    }
    
    @GetMapping("/indianmap")
    public String loadindianMapPage() {
        return "indianmap"; // map.jsp
    }
   // AIzaSyDsFvQuUxeK2QTnmajzqW3DedWRsimKtn8
    @GetMapping("/cooridnatemap")
    public String cooridnatemap() {
        return "cordinates"; // map.jsp
    }
    
    @GetMapping("/searchgooglemapapi")
    public String googlesearch() {
        return "googlesearch"; // map.jsp
    }
    
    @GetMapping("/nnnnn")
    public String bnnn() {
        return "nnn"; // map.jsp
    }

    @GetMapping("/opencage")
    public String loadOpenCagePage() {
        return "opencage";
    }

    // Reverse geocoding API
    @GetMapping("/reverseGeocode")
    @ResponseBody
    public String reverseGeocode(@RequestParam("lat") String lat,
                                 @RequestParam("lon") String lon) {
        try {
            String apiUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat="
                    + lat + "&lon=" + lon;

//            URL url = new URL(apiUrl);
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(url.openStream())
//            );
            
            URL url = new URL(apiUrl);

            java.net.URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "CRUD_operaationproject(madhugowdahk70@gmail.com)");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Unable to fetch address\"}";
        }
    }
    
    @GetMapping("/reverseGeocodeOpenCage")
    @ResponseBody
    public String reverseGeocodeOpenCage(@RequestParam("lat") String lat,
                                         @RequestParam("lon") String lon) {
        try {

            String apiKey = "57bd853ff49e4fd9933f4a13225dd8eb"; // 

            String apiUrl = "https://api.opencagedata.com/geocode/v1/json?q="
                    + lat + "+" + lon + "&key=" + apiKey;

            URL url = new URL(apiUrl);

            java.net.URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "MyApp/1.0");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Unable to fetch address\"}";
        }
    }
    
    @GetMapping("/searchLocationOpenCage")
    @ResponseBody
    public String searchLocationOpenCage(@RequestParam("query") String query) {
        try {

            String apiKey = "57bd853ff49e4fd9933f4a13225dd8eb"; 

//            String apiUrl = "https://api.opencagedata.com/geocode/v1/json?q="
//                    + java.net.URLEncoder.encode(query, "UTF-8")
//                    + "&key=" + apiKey;
            
            String apiUrl = "https://api.opencagedata.com/geocode/v1/json?q="
                    + java.net.URLEncoder.encode(query, "UTF-8")
                    + "&key=" + apiKey
                    + "&countrycode=in"
                    + "&proximity=77.5946,12.9716";

            URL url = new URL(apiUrl);

            java.net.URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "MyApp/1.0");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Search failed\"}";
        }
    }
    
    @GetMapping("/searchMap")
    public String loadSearchMapPage() {
        return "NewFile";
    }
    
    
    @GetMapping("/getAddressFromGoogle")
    @ResponseBody
    public String getAddressFromGoogle(@RequestParam("lat") String lat,
                                       @RequestParam("lon") String lon) {
    	System.err.println("we are using google API");
        try {

            String apiKey = "AIzaSyCA_NccaEh7EGWVKDz7a6O8jYS4pTh3wU0"; // same key

            String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                    + lat + "," + lon + "&key=" + apiKey;

            URL url = new URL(apiUrl);

            java.net.URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "MyApp/1.0");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Unable to fetch address\"}";
        }
    }
    
    
    @GetMapping("/mappals")
    public String showMap() {
        return "mappls"; // map.jsp
    }
    
    

}