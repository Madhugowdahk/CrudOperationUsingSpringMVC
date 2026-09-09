package com.unibrain.controller;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class PostmanExample {

    public static void main(String[] args) throws IOException {
        // Set the URL for your Spring MVC endpoint
        String url = "http://localhost:8080/webservice/helloo";

        // Create a JSON payload
        String jsonPayload = "{\"name\":\"John\"}";

        // Create a POST request
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json"); // Set Content-Type header
        httpPost.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            // Get the response entity
            HttpEntity entity = response.getEntity();

            // Print the response status code
            System.out.println("Response Status: " + response.getStatusLine());

            // Print the response body
            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
                System.out.println("Response Body: " + responseBody);
            }
        }
    }
}
