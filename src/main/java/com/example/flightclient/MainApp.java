package com.example.flightclient;

import com.example.flightclient.Controller.*;

import com.example.flightclient.entity.BookingEntity;
import com.example.flightclient.entity.FlightEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.print.Book;
import java.io.IOException;
import java.util.Optional;

public class MainApp extends Application {

    private static List<Stage> openStages = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        openStages.add(stage);  // Добавляем первичное окно в список
        showLoginWindow(stage);
    }

    public static void showLoginWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        stage.getIcons().add(new Image("/images/icon.png"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        stage.setTitle("Авторизация - 'SpeedFlot'");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        openStages.add(stage);  // Добавляем окно в список
    }

    public static void showMainWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main2.fxml"));
        stage.getIcons().add(new Image("/images/icon.png"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Авиакасса - 'SpeedFlot'");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        openStages.add(stage);  // Добавляем окно в список
    }

    public static void showPriceModal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("price.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Цены");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.showAndWait();
            openStages.add(dialogStage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showBookingModal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("booking3.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Регистрация издания");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.showAndWait();
            openStages.add(dialogStage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showAddFlightModal() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("AddFlightView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Добавить рейс");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
            openStages.add(stage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showProfileModal() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Profil.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Профиль пользователя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(loader.load()));


            dialogStage.showAndWait();
            openStages.add(dialogStage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showChangeEmailModal(ProfilController profilController) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("change_email_dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменить почту");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(loader.load()));
            ChangeEmailController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProfilController(profilController);
            dialogStage.showAndWait();
            openStages.add(dialogStage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showChangePasswordModal(ProfilController profilController) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("change_password_dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменить пароль");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(loader.load()));
            ChangePasswordController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProfilController(profilController);
            dialogStage.showAndWait();
            openStages.add(dialogStage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showUpdateFlightModal(FlightEntity flight) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("UpdateFlight.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Обновить рейс");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));

            UpdateFlightController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setFlight(flight);

            stage.showAndWait();
            openStages.add(stage);  // Добавляем окно в список
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeAllStages() {
        for (Stage stage : openStages) {
            stage.close();
        }
        openStages.clear();  // Очищаем список после закрытия окон
    }

    public static void main(String[] args) {
        launch(args);
    }
}





