package com.example.flightclient.Controller;

import com.example.flightclient.MainApp;
import com.example.flightclient.entity.BookingEntity;
import com.example.flightclient.entity.FlightEntity;
import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.response.ListResponse;
import com.example.flightclient.service.BookingService;
import com.example.flightclient.service.FlightService;
import com.example.flightclient.service.UserContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.flightclient.entity.FlightEntity;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class PricesController {

    @FXML
    private ImageView logoImage;

    @FXML
    private Button buyTicketsButton;

    @FXML
    private Button logoutButton;

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
    private TableColumn<FlightEntity, String> routeColumn;

    @FXML
    private TableColumn<FlightEntity, String> dateColumn;

    @FXML
    private TableColumn<FlightEntity, Integer> priceColumn;

    @FXML
    private TableView<FlightEntity> flightsTable;

    @FXML
    private TableColumn<FlightEntity, String> flightNumberColumn;

    @FXML
    private TableColumn<FlightEntity, String> departureColumn;

    @FXML
    private TableColumn<FlightEntity, String> arrivalColumn;

    private final FlightService flightService = new FlightService();
    private final BookingService bookingService = new BookingService();

    private Stage dialogStage;

    @FXML
    public void initialize() {
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Загружаем рейсы и устанавливаем их в таблицу
        loadFlights();
    }

    private void loadFlights() {
        ListResponse<FlightEntity> response = flightService.getAllFlights();
        if (response.isStatus()) {
            flightsTable.getItems().setAll(response.getData());
        } else {
            // Обрабатываем ошибку
            System.err.println("Failed to load flights: " + response.getStatusText());
        }
    }

    @FXML
    void callRefreshFunc(ActionEvent event) {
        flightsTable.getItems().clear();
        initialize();
    }

    @FXML
    void buyTickets(ActionEvent event) {
        FlightEntity selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
        if (selectedFlight == null) {
            showAlert("Ошибка", "Выберите рейс для бронирования.", Alert.AlertType.ERROR);
            return;
        }

        UserEntity currentUser = getCurrentUser();

        BookingEntity booking = new BookingEntity();
        booking.setUser(currentUser);
        booking.setFlight(selectedFlight);

        bookingService.save(booking);
        showAlert("Успех", "Бронирование успешно создано.", Alert.AlertType.INFORMATION);
    }

    private UserEntity getCurrentUser() {
        return UserContext.getInstance().getCurrentUser();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void callBookingModal(ActionEvent event) {
        MainApp.showBookingModal();
    }

    @FXML
    void handleLogout(ActionEvent event) {
        // Закрытие текущего окна
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleProfileIconClick(ActionEvent event) {
        MainApp.showProfileModal();
    }

}