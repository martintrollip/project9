package martintrollip.task1.util;

import javafx.scene.control.Alert;

/**
 * Utility for showing alerts
 *
 * @author Martin Trollip
 * @since 2020/06/16 15:39
 */
public class AlertUtil {

    /**
     * Show INFORMATION alert
     *
     * @param title Alert title
     * @param contentText The alert body content
     */
    public static void showInformation(String title, String contentText) {
        showAlert(Alert.AlertType.INFORMATION, title, contentText);
    }

    /**
     * Show ERROR alert
     *
     * @param title Alert title
     * @param contentText The alert body content
     */
    public static void showError(String title, String contentText) {
        showAlert(Alert.AlertType.ERROR, title, contentText);
    }

    private static void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.show();
    }
}
