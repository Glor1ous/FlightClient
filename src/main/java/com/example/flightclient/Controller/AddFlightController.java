package com.example.flightclient.Controller;


import com.example.flightclient.entity.FlightEntity;
import com.example.flightclient.service.FlightService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFlightController {

    @FXML
    private TextField departureCityField;

    @FXML
    private TextField arrivalCityField;

    @FXML
    private TextField departureDateField;

    @FXML
    private TextField priceField;



    private final FlightService flightService = new FlightService();

    @FXML
    private void handleSubmit() {
        String departureCity = departureCityField.getText();
        String arrivalCity = arrivalCityField.getText();
        String departureDate = departureDateField.getText();
        String priceText = priceField.getText();

        if (departureCity.isEmpty() || arrivalCity.isEmpty() || departureDate.isEmpty() || priceText.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            int price = Integer.parseInt(priceText);
            FlightEntity newFlight = new FlightEntity(null, departureCity, arrivalCity, departureDate, price);
            flightService.createFlight(newFlight);
            closeWindow();
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Пожалуйста, введите корректное значение цены.");
        }


    }

    @FXML
    private void handleExit() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) departureCityField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}