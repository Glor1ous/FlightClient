package com.example.flightclient.Controller;

import com.example.flightclient.entity.FlightEntity;
import com.example.flightclient.service.FlightService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateFlightController {

    @FXML
    private TextField departureCityField;

    @FXML
    private TextField arrivalCityField;

    @FXML
    private TextField departureDateField;

    @FXML
    private TextField priceField;

    private FlightEntity flight;
    private Stage dialogStage;
    private boolean okClicked = false;
    private final FlightService flightService = new FlightService();

    @FXML
    private void initialize() {
        // Initialize the controller.
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
        departureCityField.setText(flight.getDepartureCity());
        arrivalCityField.setText(flight.getArrivalCity());
        departureDateField.setText(flight.getDepartureDate());
        priceField.setText(String.valueOf(flight.getPrice()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            flight.setDepartureCity(departureCityField.getText());
            flight.setArrivalCity(arrivalCityField.getText());
            flight.setDepartureDate(departureDateField.getText());
            flight.setPrice(Integer.parseInt(priceField.getText()));

            flightService.updateFlight(flight);

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (departureCityField.getText() == null || departureCityField.getText().length() == 0) {
            errorMessage += "Нет действительного города отправления!\n";
        }
        if (arrivalCityField.getText() == null || arrivalCityField.getText().length() == 0) {
            errorMessage += "Нет действительного города прибытия!\n";
        }
        if (departureDateField.getText() == null || departureDateField.getText().length() == 0) {
            errorMessage += "Нет действительной даты вылета!\n";
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Нет действительной цены!\n";
        } else {
            try {
                Integer.parseInt(priceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Нет действительной цены (должна быть целой)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Недопустимые поля");
            alert.setHeaderText("Пожалуйста, исправьте недопустимые поля");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}