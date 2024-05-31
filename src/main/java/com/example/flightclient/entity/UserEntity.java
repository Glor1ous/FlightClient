package com.example.flightclient.entity;

import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Long id;


    private String login;


    private String email;


    private String password;

    public SimpleStringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }

}
