package com.example.flightclient.Controller;

import com.example.flightclient.MainApp;
import com.example.flightclient.entity.BookingEntity;
import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.service.UserContext;
import com.example.flightclient.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilController {

    @FXML
    private Label emailLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button logoutButton;

    private Stage dialogStage;
    private UserEntity currentUser;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser != null) {
            emailLabel.setText(currentUser.getEmail());
            passwordLabel.setText(maskPassword(currentUser.getPassword()));
        }
    }

    @FXML
    private void handleExit() {
        try {
            MainApp.closeAllStages();  // Закрываем все открытые окна
            MainApp.showLoginWindow(new Stage());  // Переход на окно авторизации
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleChangeEmail() {
        MainApp.showChangeEmailModal(this);
    }

    @FXML
    private void handleChangePassword() {
        MainApp.showChangePasswordModal(this);
    }

    public void updateEmailLabel(String newEmail) {
        emailLabel.setText(newEmail);
    }

    public void updatePasswordLabel(String newPassword) {
        passwordLabel.setText(maskPassword(newPassword));  // Установите звездочки для нового пароля
    }

    private String maskPassword(String password) {
        return "*".repeat(password.length());
    }

    @FXML
    void handleLogout(ActionEvent event) {
        // Закрытие текущего окна
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

}


