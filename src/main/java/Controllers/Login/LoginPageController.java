package Controllers.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.NotificationUtil;

import java.io.IOException;

public class LoginPageController {

    private final LoginController loginController = new LoginController();

    @FXML
    private Button btnClear;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtUserName.clear();
        txtPassword.clear();
        lblError.setText("");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() ||  password.isEmpty()) {
            lblError.setText("Please enter both username and password.");
        }else if (loginController.checkUsernameAndPassword(username, password)) {
            lblError.setText("");

            try {
                Stage dashboardStage = new Stage();
                dashboardStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/MainDashboard.fxml"))));
                dashboardStage.setTitle("Library Management System - Dashboard");
                dashboardStage.setMaximized(true);
                dashboardStage.show();

                // Show auto-disappearing notification on the Dashboard
                NotificationUtil.showSuccess(dashboardStage, "Login Successful!");

                // Close the Login window
                Stage loginStage = (Stage) btnLogin.getScene().getWindow();
                loginStage.close();
            } catch (IOException e) {
                lblError.setText("Error loading Dashboard.");
                e.printStackTrace();
            }

        }else {
            lblError.setText("Invalid username or password.");
        }
    }

}
