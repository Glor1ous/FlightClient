package com.example.flightclient.Controller;

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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;
import java.util.Optional;

/*

public class BookingController {


    private Stage dialogStage;

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
    private TableView<BookingEntity> bookingTable;

    @FXML
    private TableColumn<BookingEntity, String> emailColumn;

    @FXML
    private TableColumn<BookingEntity, String> flightColumn;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button buyTicketsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private MenuItem showBookingModal;


    private final BookingService bookingService = new BookingService();

    @FXML
    public void initialize() {
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        flightColumn.setCellValueFactory(new PropertyValueFactory<>("flight"));




    }





    @FXML
    void callRefreshFunc(ActionEvent event) {
        bookingTable.getItems().clear();
        initialize();
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


}*/






public class BookingController {

    private Stage dialogStage;

    @FXML
    private Button fileButton;

    @FXML
    private Button pricesButton;

    @FXML
    private Button ticketsButton;

    @FXML
    private Button myBookingsButton;

    @FXML
    private Button deleteBookingButton;

    @FXML
    private ImageView profileIcon;

    @FXML
    private TableView<BookingEntity> bookingTable;

    @FXML
    private TableColumn<BookingEntity, String> emailColumn;

    @FXML
    private TableColumn<BookingEntity, String> flightColumn;

    @FXML
    private TableColumn<BookingEntity, String> routeColumn;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button buyTicketsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private MenuItem showBookingModal;

    private final BookingService bookingService = new BookingService();

    @FXML
    public void initialize() {
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getUser().emailProperty());
        flightColumn.setCellValueFactory(cellData -> cellData.getValue().getFlight().dateProperty());
        routeColumn.setCellValueFactory(cellData -> cellData.getValue().getFlight().routeProperty());

        refreshTable();
    }

    private void refreshTable() {
        try {
            UserEntity currentUser = getCurrentUser();
            if (currentUser == null) {
                showAlert("Error", "Текущий пользователь не установлен.", Alert.AlertType.ERROR);
                return;
            }
            Long userId = currentUser.getId();
            List<BookingEntity> bookings = bookingService.getAllByUserId(userId);
            bookingTable.getItems().setAll(bookings);
        } catch (Exception e) {
            showAlert("Error", "Не удалось загрузить брони: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private UserEntity getCurrentUser() {
        return UserContext.getInstance().getCurrentUser();
    }

    @FXML
    void callRefreshFunc(ActionEvent event) {
        refreshTable();
    }

    @FXML
    void deleteBooking(ActionEvent event) {
        BookingEntity selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            showAlert("Error", "\n" + "Пожалуйста, выберите бронирование для удаления.", Alert.AlertType.ERROR);
            return;
        }
        try {
            bookingService.delete(selectedBooking.getId());
            refreshTable();
            showAlert("Success", "Бронирование успешно удалено!!!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "\n" + "Не удалось удалить бронирование: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void handleLogout(ActionEvent event) {
        // Закрытие текущего окна
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

}









