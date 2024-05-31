package com.example.flightclient.Controller;

import com.example.flightclient.MainApp;
import com.example.flightclient.entity.FlightEntity;
import com.example.flightclient.response.ListResponse;
import com.example.flightclient.service.FlightService;
import com.example.flightclient.service.UserContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.print.Book;
import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    private Button fileButton;

    @FXML
    private Button pricesButton;

    @FXML
    private Button ticketsButton;

    @FXML
    private Button myBookingsButton;

    @FXML
    private ImageView profileIcon;

    @FXML
    private TableView<FlightEntity> flightsTable;

    @FXML
    private TableColumn<FlightEntity, String> routeColumn;

    @FXML
    private TableColumn<FlightEntity, String> dateColumn;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button buyTicketsButton;

    @FXML
    private Button add_button;

    @FXML
    private Button deleteBookingButton;

    @FXML
    private Button updateBookingButton;

    @FXML
    private Button logoutButton;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private final FlightService flightService = new FlightService();

    @FXML
    public void initialize() {


        try {
            flightService.getAll();
        } catch (Exception e ) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Иди включи сервер, либо иди *****");
            alert.setContentText("Обратитесь в тех.поддержку.....");
            alert.showAndWait();
        }



        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));



        // loadFlights();
        flightsTable.setItems(flightService.getFlight());
        // Установка изображения логотипа

        Image logo = new Image(getClass().getResourceAsStream("/images/logoprofil.png"));
        profileIcon.setImage(logo);


        Image logo2 = new Image(getClass().getResourceAsStream("/images/logo.png"));
        logoImage.setImage(logo2);


        if (!UserContext.getInstance().isAdmin()) {
            add_button.setVisible(false);
            deleteBookingButton.setVisible(false);
            updateBookingButton.setVisible(false);
        }
    }

    // Установка изображения логотипа



    // Установка изображения логотипа

   /* private void loadFlights() {
        ListResponse<FlightEntity> response = flightService.getAllFlights();
        if (response.isStatus()) {
            flightsTable.getItems().setAll(response.getData());
        } else {
            // Handle error
            System.err.println("Failed to load flights: " + response.getStatusText());
        }
    }*/



    @FXML
    void callRefreshFunc(ActionEvent event) {
        flightsTable.getItems().clear();
        initialize();
    }

    @FXML
    private void handleExit() {
        // Обработчик для кнопки "Выход"
        System.exit(0);
    }




    @FXML
    void callPriceModal(ActionEvent event) {
        MainApp.showPriceModal();
    }


    @FXML
    void deleteBookingAction(ActionEvent event) {
        FlightEntity selectedBook = flightsTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            flightService.delete(selectedBook);

/*            alert.setTitle("Успешно");
            alert.setHeaderText("Данные удалены");
            alert.showAndWait();*/

            flightsTable.getItems().clear();
            initialize();
        } else {
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс ");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    void changeBookAction(ActionEvent event) {
        FlightEntity selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            MainApp.showUpdateFlightModal(selectedFlight);
            flightsTable.getItems().clear();
            initialize();
        } else {
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    void callFlightModal(ActionEvent event) {
        Optional<FlightEntity> flight = Optional.empty();
        MainApp.showAddFlightModal();
        flightsTable.getItems().clear();
        initialize();
    }

    @FXML
    void callHelpModal(ActionEvent event) throws IOException {

        Process process = new ProcessBuilder("hh.exe", "E:\\FlightClient7\\help.chm").start();
    }

    @FXML
    void callBookingModal(ActionEvent event) {

        MainApp.showBookingModal();
    }

    @FXML
    void handleProfileIconClick(ActionEvent event) {
        MainApp.showProfileModal();
    }



}
