package com.example.flightclient.service;

import okhttp3.*;

import java.io.IOException;

public class HttpService {
    private static OkHttpClient client = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public String get(String url) {
        String result = "";
        System.out.println("GET URL: " + url);  // Логирование URL
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Bad request: " + response.code() + " " + response.message());
            }
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public String post(String url, String json) {
        System.out.println("POST URL: " + url);
        System.out.println("POST JSON: " + json);

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Response code: " + response.code() + ", message: " + response.message());
                throw new IOException("Bad request: " + response.code() + " " + response.message());
            }
            String responseBody = response.body().string();
            System.out.println("Response body: " + responseBody);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String put(String url, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String delete(String url, Long id){
        String result = "";
        Request.Builder requestBuilder = new Request.Builder().url(url + id).delete();

        Request request = requestBuilder.build();
        try(Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Bad request: " + response.code() + " " + response.message());
            }
            result = response.body().string();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            return result;
        }
    }


}