package com.example.flightclient.service;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public class JsonService {
    private final Gson gson = new Gson();

    public <T>T getObject(String response, Type type) {
        return gson.fromJson(response,type);
    }
    public <T>String getJson(T data) {
        return gson.toJson(data);
    }
}