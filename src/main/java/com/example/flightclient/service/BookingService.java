package com.example.flightclient.service;

import com.example.flightclient.entity.BookingEntity;
import com.example.flightclient.entity.FlightEntity;
import com.example.flightclient.response.BaseResponse;
import com.example.flightclient.response.DataResponse;
import com.example.flightclient.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;


import java.lang.reflect.Type;
import java.util.List;

public class BookingService {

    @Getter
    private ObservableList<BookingEntity> bookings = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    private final JsonService jsonService = new JsonService();
    private final ClientProperties clientProperties = new ClientProperties();

    private final Type dataType = new TypeToken<DataResponse<BookingEntity>>() {
    }.getType();
    private final Type listType = new TypeToken<ListResponse<BookingEntity>>() {
    }.getType();






    public List<BookingEntity> getAllByUserId(Long userId) {
        String url = ClientProperties.getInstance().getFindBookingsByUserId() + userId;
        System.out.println("Request URL: " + url); // Логирование URL
        String response = httpService.get(url);
        ListResponse<BookingEntity> bookingList = jsonService.getObject(response, listType);
        if (bookingList.isStatus()) {
            return bookingList.getData();
        } else {
            throw new RuntimeException(bookingList.getStatusText());
        }
    }

    public void save(BookingEntity booking) {
        String tempData = httpService.post(clientProperties.getSaveBooking(), jsonService.getJson(booking));
        DataResponse<BookingEntity> dataResponse = jsonService.getObject(tempData, new TypeToken<DataResponse<BookingEntity>>() {}.getType());
        if (dataResponse.isStatus()) {
            bookings.add(dataResponse.getData());
        } else {
            throw new RuntimeException(dataResponse.getStatusText());
        }
    }


    public void delete(Long bookingId) {
        String tempData = httpService.delete(clientProperties.getDeleteBooking(), bookingId);
        BaseResponse response = jsonService.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            bookings.removeIf(booking -> booking.getId().equals(bookingId));
        } else {
            throw new RuntimeException(response.getStatusText());
        }
    }


}