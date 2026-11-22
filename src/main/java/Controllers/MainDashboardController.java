package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainDashboardController {

    @FXML
    private Button btnLogout;

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        // 1. Create a confirmation popup
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");

        // 2. Wait for the user's choice
        Optional<ButtonType> result = alert.showAndWait();

        // 3. Only log out if they clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {

            try {
                Stage loginStage = new Stage();
                loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/LoginPage.fxml"))));
                loginStage.setTitle("Library Management System - Login");
                loginStage.setResizable(false);
                loginStage.show();

                // Close the Dashboard window
                Stage dashBoardStage = (Stage) btnLogout.getScene().getWindow();
                dashBoardStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
