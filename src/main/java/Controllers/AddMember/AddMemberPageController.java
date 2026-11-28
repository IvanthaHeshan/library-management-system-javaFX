package Controllers.AddMember;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;
import util.NotificationUtil;

public class AddMemberPageController {

    private final AddMemberController addMemberController = new AddMemberController();

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtPhone;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtMemberId.clear();
        txtFullName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
    }

    @FXML
    void btnRegisterMemberOnAction(ActionEvent event) {
        String memberId = txtMemberId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();

        // 1. Check if any field is blank
        if (memberId.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Information", "Please fill in all fields.");
            return;
        }

        // 2. Check for duplicate Member ID
        if (addMemberController.isDuplicateMemberId(memberId)) {
            showAlert(Alert.AlertType.WARNING, "Duplicate Member ID", "A member with ID '" + memberId + "' is already registered.");
            return;
        }

        // 3. Validate Email format
        if (!addMemberController.isValidEmail(email)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Email", "Please enter a valid email address (e.g. name@example.com).");
            return;
        }

        // 4. Validate Phone Number format
        if (!addMemberController.isValidPhoneNumber(phone)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Phone Number", "Please enter a valid 10-digit phone number (e.g. 0771234567).");
            return;
        }

        // 5. All checks passed! Save the Member object
        Member newMember = new Member(memberId, fullName, email, phone, address);
        addMemberController.addMember(newMember);

        Stage stage = (Stage) txtMemberId.getScene().getWindow();
        NotificationUtil.showSuccess(stage, "Member registered: " + fullName);
        btnClearOnAction(event);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
