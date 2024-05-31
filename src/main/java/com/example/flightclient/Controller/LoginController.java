package com.example.flightclient.Controller;

import com.example.flightclient.response.DataResponse;
import com.example.flightclient.service.*;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.MainApp;
import com.example.flightclient.entity.UserEntity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView logoImage;

    @FXML
    private TextField emailField;

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private HttpService httpService = new HttpService();
    private JsonService jsonService = new JsonService();

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @FXML
    public void initialize() {
        Image logo2 = new Image(getClass().getResourceAsStream("/images/logo.png"));
        logoImage.setImage(logo2);
    }

    @FXML
    private void handleLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            errorMessage.setText("Пожалуйста, заполните все поля.");
            return;
        }

        if (password.length() < 6) {
            errorMessage.setText("Пароль должен содержать не менее 6 символов.");
            return;
        }

        if (!EMAIL_PATTERN.matcher(email).find()) {
            errorMessage.setText("Пожалуйста, введите корректный email.");
            return;
        }

        if (login.equals("admin") && password.equals("admin1711") && email.equals("admin@gmail.com")) {
            // Если пользователь админ
            UserContext.getInstance().setCurrentUser(new UserEntity(null, login, email, password));
            UserContext.getInstance().setAdmin(true);

            try {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                MainApp.showMainWindow();
            } catch (IOException e) {
                errorMessage.setText("Ошибка при загрузке главного окна.");
            }
        } else {
            UserEntity user = new UserEntity(null, login, email, password);
            String jsonResponse = httpService.post(ClientProperties.getInstance().getLoginUrl(), jsonService.getJson(user));
            DataResponse<UserEntity> response = jsonService.getObject(jsonResponse, new TypeToken<DataResponse<UserEntity>>() {}.getType());

            if (response.isStatus()) {
                UserContext.getInstance().setCurrentUser(response.getData());

                try {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    MainApp.showMainWindow();
                } catch (IOException e) {
                    errorMessage.setText("Ошибка при загрузке главного окна.");
                }
            } else {
                errorMessage.setText(response.getStatusText());
            }
        }
    }

    @FXML
    private void handleRegistration() {
        String login = loginField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            errorMessage.setText("Пожалуйста, заполните все поля.");
            return;
        }

        UserEntity user = new UserEntity(null, login, email, password);
        String registerUrl = ClientProperties.getInstance().getRegisterUrl();
        String userJson = jsonService.getJson(user);

        System.out.println("POST URL: " + registerUrl);
        System.out.println("POST JSON: " + userJson);

        String jsonResponse = httpService.post(registerUrl, userJson);
        DataResponse<UserEntity> response = jsonService.getObject(jsonResponse, new TypeToken<DataResponse<UserEntity>>() {}.getType());

        if (response.isStatus()) {
            errorMessage.setText("Регистрация прошла успешно. Войдите в систему.");
        } else {
            errorMessage.setText("Ошибка регистрации: " + response.getStatusText());
        }
    }
}
