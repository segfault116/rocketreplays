package com.gangstakittenz.rocketreplays.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class MapLoader implements RequestHandler<Map<String,Object>, String> {

    public String handleRequest(Map<String, Object> stringObjectMap, Context context) {
        return "Hello World";
    }
}