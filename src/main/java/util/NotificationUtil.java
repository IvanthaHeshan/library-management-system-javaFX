package util;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationUtil {

    public static void showSuccess(Stage ownerStage, String message) {
        Popup popup = new Popup();

        Label label = new Label("✓  " + message);
        label.setStyle(
                "-fx-background-color: #2e7d32;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 14 26 14 26;" +
                        "-fx-background-radius: 8;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 12, 0.2, 0, 4);"
        );

        popup.getContent().add(label);
        popup.setAutoFix(true);

        // Center on the middle of the screen once JavaFX calculates the popup size
        popup.setOnShown(e -> {
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            popup.setX(bounds.getMinX() + (bounds.getWidth() - popup.getWidth()) / 2);
            popup.setY(bounds.getMinY() + (bounds.getHeight() - popup.getHeight()) / 2);
        });

        popup.show(ownerStage);

        // Stay visible for 2.5 seconds, then fade out over 0.5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), label);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> popup.hide());

        delay.setOnFinished(e -> fadeOut.play());
        delay.play();
    }
}