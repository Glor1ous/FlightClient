package com.example.flightclient.Controller;

import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.service.UserContext;
import com.example.flightclient.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeEmailController {

    @FXML
    private TextField newEmailField;

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
        String newEmail = newEmailField.getText();
        if (newEmail != null && !newEmail.isEmpty()) {
            currentUser = UserContext.getInstance().getCurrentUser();
            currentUser.setEmail(newEmail);
            userService.update(currentUser);
            profilController.updateEmailLabel(newEmail);
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