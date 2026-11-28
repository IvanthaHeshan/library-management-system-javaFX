package Controllers;

import Controllers.AddBook.AddBookController;
import Controllers.AddMember.AddMemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainDashboardController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private VBox dashboardOverviewPane;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private Label lblTotalMembers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDashboardCounts();
    }

    private void updateDashboardCounts() {
        lblTotalBooks.setText(String.valueOf(AddBookController.getTotalBookCount()));
        lblTotalMembers.setText(String.valueOf(AddMemberController.getTotalMemberCount()));
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        updateDashboardCounts();
        mainBorderPane.setCenter(dashboardOverviewPane);
    }

    private void loadCenterView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainBorderPane.setCenter(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        loadCenterView("/View/AddBookPage.fxml");
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        loadCenterView("/View/AddMemberPage.fxml");
    }

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
