package Controllers.AddBook;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import util.NotificationUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookPageController implements Initializable {

    private final AddBookController addBookController = new AddBookController();

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtPublishedYear;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCategory.setItems(FXCollections.observableArrayList(
                "Fiction",
                "Non-Fiction",
                "Science",
                "Technology",
                "History",
                "Biography",
                "Education"
        ));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtIsbn.clear();
        txtTitle.clear();
        txtAuthor.clear();
        cmbCategory.setValue(null);
        txtPublishedYear.clear();
        txtQuantity.clear();
    }

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        String isbn = txtIsbn.getText().trim();
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = cmbCategory.getValue();
        String yearText = txtPublishedYear.getText().trim();
        String quantityText = txtQuantity.getText().trim();

        // 1. Check if any field is blank
        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || category == null || yearText.isEmpty() || quantityText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Information", "Please fill in all fields.");
            return;
        }

        // 2. Validate ISBN format (ISBN-10 or ISBN-13)
        if (!addBookController.isValidIsbn(isbn)) {
            showAlert(Alert.AlertType.WARNING, "Invalid ISBN", "Please enter a valid ISBN-10 (10 digits) or ISBN-13 (13 digits starting with 978 or 979).");
            return;
        }

        // 3. Try converting Published Year and Quantity into whole numbers
        int publishedYear;
        int quantity;
        try {
            publishedYear = Integer.parseInt(yearText);
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Number", "Published Year and Quantity must be whole numbers.");
            return;
        }

        // 4. Check that the numbers make sense
        if (!addBookController.isValidYear(publishedYear)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Year", "Please enter a valid 4-digit published year.");
            return;
        }

        if (!addBookController.isValidQuantity(quantity)) {
            showAlert(Alert.AlertType.WARNING,"Invalid Quantity", "Quantity must be at least 1.");
            return;
        }

        // 5. All checks passed! save the Book object
        Book newBook = new Book(isbn, title, author, category, publishedYear, quantity);
        addBookController.addBook(newBook);

        Stage stage = (Stage) txtIsbn.getScene().getWindow();
        NotificationUtil.showSuccess(stage, "Book added: " + title);
        btnClearOnAction(event);

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

}
