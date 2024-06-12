package com.literlaura.alura_challenge.service;

import com.literlaura.alura_challenge.models.ResultsAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConnectionAPI {
    private static DataConverter converter = new DataConverter();

    public static String getJsonData(String api_url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api_url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    public static ResultsAPI resultsList(String encodedURL) {
        var result = converter.convertData(getJsonData(encodedURL), ResultsAPI.class);
        return result;
    }
}
