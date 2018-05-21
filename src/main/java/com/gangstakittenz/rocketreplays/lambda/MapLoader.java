package com.gangstakittenz.rocketreplays.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class MapLoader implements RequestHandler<Map<String,Object>, String> {
    private static final String mapBaseUrl = "https://www.rocketleaguereplays.com/api/maps/";

    public String handleRequest(Map<String, Object> stringObjectMap, Context context) {
        try {
            HttpClient apiClient = HttpClientBuilder.create().build();
            HttpGet getMapsRequest = new HttpGet(mapBaseUrl);
            HttpResponse response = apiClient.execute(getMapsRequest);
            String responseString = getResponseString(response);

            return responseString;
        }
        catch(Exception e){
                return "Something went wrong:" + e.getMessage();
        }
    }
    private String getResponseString(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        MapLoader loader = new MapLoader();
        String response = loader.handleRequest(null, null);
        System.out.println(response);
    }
}