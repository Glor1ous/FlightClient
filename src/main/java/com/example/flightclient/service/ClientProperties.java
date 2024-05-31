package com.example.flightclient.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientProperties {
    private final Properties properties = new Properties();
    private static ClientProperties instance;

    public String userGetAll;
    public String userSave;

    public String userUpdate;

    public String userFindByLoginAndEmail;
    public String userRegister;
    public String userLogin;
    public String flightGetAll;

    public String updateFlight;


    public String createFlight;
    public String flightFindById;
    public String flightSave;
    public String flightUpdate;
    public String flightDelete;
    public String bookingGetAllByUserId;
    public String bookingSave;
    public String bookingDelete;

    private String loginUrl;
    private String registerUrl;

    public ClientProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IOException("config.properties file not found");
            }
            properties.load(inputStream);

            loginUrl = properties.getProperty("user.login");
            registerUrl = properties.getProperty("user.register");
            flightGetAll = properties.getProperty("flight.getAll");
            createFlight = properties.getProperty("flight.createFlight");
            flightFindById = properties.getProperty("flight.findById");
            flightSave = properties.getProperty("flight.save");

            flightDelete = properties.getProperty("flight.delete");

            updateFlight = properties.getProperty("flight.update");


            bookingGetAllByUserId = properties.getProperty("booking.getAllByUserId");
            bookingSave = properties.getProperty("booking.save");
            bookingDelete = properties.getProperty("booking.delete");

            userGetAll = properties.getProperty("user.getAll");
            userSave = properties.getProperty("user.save");
            userUpdate = properties.getProperty("user.update");
            userFindByLoginAndEmail = properties.getProperty("user.findByLoginAndEmail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientProperties getInstance() {
        if (instance == null) {
            instance = new ClientProperties();
        }
        return instance;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public String getFindBookingsByUserId() {
        return bookingGetAllByUserId;
    }

    public String getSaveBooking() {
        return bookingSave;
    }

    public String getDeleteBooking() {
        return bookingDelete;
    }

    public String getUserGetAll() {
        return userGetAll;
    }

    public String getUserSave() {
        return userSave;
    }

    public String  getUserUpdate() {
        return userUpdate;
    }

    public String getUserFindByLoginAndEmail() {
        return userFindByLoginAndEmail;
    }

    public String getAllFlights() {
        return flightGetAll;
    }



    public String getUpdateFlight() {
        return updateFlight;
    }

    public String  getCreateFlight() {
        return createFlight;
    }


    public String getFlightDelete() {
        return flightDelete;
    }
}
