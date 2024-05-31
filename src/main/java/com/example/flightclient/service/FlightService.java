package com.example.flightclient.service;

import com.example.flightclient.entity.BookingEntity;
import com.example.flightclient.entity.FlightEntity;
import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.response.BaseResponse;
import com.example.flightclient.response.DataResponse;
import com.example.flightclient.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.awt.print.Book;
import java.lang.reflect.Type;

public class FlightService {

    private Type listType = new TypeToken<ListResponse<FlightEntity>>() {}.getType();
    private final HttpService httpService = new HttpService();
    private final JsonService jsonService = new JsonService();
    private final ClientProperties clientProperties = new ClientProperties();
    @Getter
    private ObservableList<FlightEntity> flight = FXCollections.observableArrayList();



    public ListResponse<FlightEntity> getAllFlights() {
        String url = clientProperties.getAllFlights();
        String response = httpService.get(url);
        return jsonService.getObject(response, listType);
    }

    public void delete(FlightEntity flight) {
        String tempData= httpService.delete(clientProperties.getFlightDelete(), flight.getId());
        BaseResponse response = jsonService.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.flight.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatusText());
        }
    }


    public void getAll() {
        ListResponse<FlightEntity> flightList = new ListResponse<>();
        flightList = jsonService.getObject(httpService.get(clientProperties.getAllFlights()), listType);
        if (flightList.isStatus()) {
            this.flight.addAll(flightList.getData());
            this.flight.forEach(System.out::println);
        } else {
            throw new RuntimeException(flightList.getStatusText());
        }
    }

    public void updateFlight(FlightEntity flight) {

        String tempData = httpService.put(clientProperties.getUpdateFlight(), jsonService.getJson(flight));

        BaseResponse response = jsonService.getObject(tempData, BaseResponse.class);

        if (!response.isStatus()) {
            throw new RuntimeException(response.getStatusText());
        }
    }

    public void createFlight(FlightEntity flight) {
        // Сериализация объекта FlightEntity в JSON строку
        String flightJson = jsonService.getJson(flight);
        System.out.println("Creating flight with JSON: " + flightJson);

        // Отправка POST-запроса с JSON строкой
        String tempData = httpService.post(clientProperties.getCreateFlight(), flightJson);

        if (tempData == null) {
            throw new RuntimeException("Error: POST request failed or returned null response");
        }

        System.out.println("Response from server: " + tempData);

        BaseResponse response = jsonService.getObject(tempData, BaseResponse.class);

        if (response == null) {
            throw new RuntimeException("Error: Failed to parse response from server");
        }

        if (response.isStatus()) {
            this.flight.add(flight);
        } else {
            throw new RuntimeException(response.getStatusText());
        }
    }





}

