package martintrollip.task1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import martintrollip.task1.util.ChristmasUtils;

import java.time.Clock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    private static final String HH_MM_SS = "hh:mm:ss";
    private static final DateTimeFormatter HH_MM_SS_FORMATTER = DateTimeFormatter.ofPattern(HH_MM_SS);

    private final ChristmasUtils christmasUtils = new ChristmasUtils(Clock.systemDefaultZone());

    @FXML private Label systemTimeLabel;
    @FXML private Button loadCSVButton;

    @FXML
    public void initialize() {
        showCurrentTime();
    }

    private void showCurrentTime() {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(
                () -> Platform.runLater(() -> systemTimeLabel.setText(LocalDateTime.now().format(HH_MM_SS_FORMATTER))), 0, 100,
                TimeUnit.MILLISECONDS);
    }

    @FXML
    protected void handleDaysUntilChristmasAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Days until Christmas");
        alert.setContentText(christmasUtils.getDaysUntilChristmasMessage());
        alert.show();
    }

    @FXML
    protected void handleLoadCSVAction(ActionEvent event) {
        loadCSVButton.setText("Sign in button pressed");
    }
}
