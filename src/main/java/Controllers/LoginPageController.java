package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            System.out.println("Login Successful!");
        }else {
            lblError.setText("Invalid username or password.");
        }
    }

}
