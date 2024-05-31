package com.example.flightclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity  {

    private Long id;

    private UserEntity user;


    private FlightEntity flight;

}
