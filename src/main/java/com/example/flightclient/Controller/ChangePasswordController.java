package com.example.flightclient.Controller;

import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.service.UserContext;
import com.example.flightclient.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordController {

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    private Stage dialogStage;
    private UserEntity currentUser;
    private UserService userService = new UserService();
    private ProfilController profilController;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProfilController(ProfilController profilController) {
        this.profilController = profilController;
    }

    @FXML
    private void handleSave() {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        currentUser = UserContext.getInstance().getCurrentUser();

        if (currentPassword.equals(currentUser.getPassword()) && newPassword != null && !newPassword.isEmpty()) {
            currentUser.setPassword(newPassword);
            userService.update(currentUser);
            profilController.updatePasswordLabel(newPassword);
            dialogStage.close();
        } else {
            // Show error message
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}