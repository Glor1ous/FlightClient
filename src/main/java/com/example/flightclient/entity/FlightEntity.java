package com.example.flightclient.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class FlightEntity  {
    private Long id;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private Integer price;

    public String getRoute() {
        return departureCity + "-" + arrivalCity;
    }

    public String getDate() {
        return departureDate;
    }

    public Integer getPrice() {
        return price;
    }

    public StringProperty dateProperty() {
        return new SimpleStringProperty(departureDate);
    }

    public StringProperty routeProperty() {
        return new SimpleStringProperty(departureCity + "-" + arrivalCity);
    }
}
