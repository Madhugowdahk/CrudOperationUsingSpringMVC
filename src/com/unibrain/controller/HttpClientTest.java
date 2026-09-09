package com.unibrain.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.client.utils.URIBuilder;

public class HttpClientTest {
    public void callHttpStartAuctionMethod(Integer auctionItemId, String dataSourceName) throws URISyntaxException {
        System.out.println("Inside this method");
        String requestURL = "http://localhost:8080/eBID_Unibrain_15_8_2015";
        String protocol = "http";
        int port = 8080;

        HttpClient httpClient = new HttpClient();
        Protocol myProtocol = new Protocol(protocol, new MySSLSocketFactory(), port);
        httpClient.getHostConfiguration().setHost(requestURL, port, myProtocol);

        URIBuilder buildUrl = new URIBuilder(requestURL + "/auctionStartSchedularService/startAuctionItems");
        buildUrl.addParameter("itemId", auctionItemId.toString());
        buildUrl.addParameter("dataSourceName", dataSourceName);

        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(buildUrl.build().toString());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        int returnCode = 0;
        try {
            returnCode = httpClient.executeMethod(postMethod);
            System.out.println("return code:" + returnCode);
            String responseBody = postMethod.getResponseBodyAsString();
            System.out.println("Response body:" + responseBody);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        HttpClientTest clientTest = new HttpClientTest();
        clientTest.callHttpStartAuctionMethod(169, "bmtc");
    }
}
