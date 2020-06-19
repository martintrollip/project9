package martintrollip.task1.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import martintrollip.task1.csv.LoadCSVTask;
import martintrollip.task1.csv.NextCSVTask;
import martintrollip.task1.csv.Reader;
import martintrollip.task1.csv.Sale;
import martintrollip.task1.util.AlertUtil;
import martintrollip.task1.util.ChristmasUtils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Controller {
    private static final String HH_MM_SS = "hh:mm:ss";
    private static final DateTimeFormatter HH_MM_SS_FORMATTER = DateTimeFormatter.ofPattern(HH_MM_SS);

    private final ChristmasUtils christmasUtils = new ChristmasUtils(Clock.systemDefaultZone());

    @FXML private Label systemTimeLabel;

    @FXML private Button loadButton;
    @FXML private Button nextButton;

    @FXML private TableView<Sale> salesTableView;
    @FXML private TableColumn<Sale, String> regionColumn;
    @FXML private TableColumn<Sale, String> countryColumn;
    @FXML private TableColumn<Sale, String> itemType;
    @FXML private TableColumn<Sale, String> salesChannel;
    @FXML private TableColumn<Sale, String> orderPriority;
    @FXML private TableColumn<Sale, String> orderDate;
    @FXML private TableColumn<Sale, String> orderId;
    @FXML private TableColumn<Sale, String> shipDate;
    @FXML private TableColumn<Sale, String> unitsSold;
    @FXML private TableColumn<Sale, String> unitPrice;
    @FXML private TableColumn<Sale, String> unitCost;
    @FXML private TableColumn<Sale, String> totalRevenue;
    @FXML private TableColumn<Sale, String> totalCost;
    @FXML private TableColumn<Sale, String> totalProfit;

    @FXML private ObservableList<Sale> list = FXCollections.observableArrayList();

    private final String file = "\\docs\\1500000 Sales Records.csv";
    //private final String file = "\\docs\\test_sales.csv";

    private Reader saleReader;

    @FXML
    public void initialize() {
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        itemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        salesChannel.setCellValueFactory(new PropertyValueFactory<>("salesChannel"));
        orderPriority.setCellValueFactory(new PropertyValueFactory<>("orderPriority"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        shipDate.setCellValueFactory(new PropertyValueFactory<>("shipDate"));
        unitsSold.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        unitCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        totalRevenue.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        totalProfit.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));

        showCurrentTime();
    }

    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    private void showCurrentTime() {
        timer.scheduleAtFixedRate(
                () -> Platform.runLater(() -> systemTimeLabel.setText(LocalDateTime.now().format(HH_MM_SS_FORMATTER))), 0, 100,
                TimeUnit.MILLISECONDS);
    }

    @FXML
    protected void handleDaysUntilChristmasAction(ActionEvent event) {
        AlertUtil.showInformation("Days until Christmas", christmasUtils.getDaysUntilChristmasMessage());
    }

    @FXML
    protected void handleLoadCSVAction(ActionEvent event) {
        String originalText = loadButton.getText();
        loadCSV(originalText);
    }

    @FXML
    protected void handleNextAction(ActionEvent event) {
        next();
    }

    private void loadCSV(String originalText) {
        LoadCSVTask task = new LoadCSVTask(file);
        task.setOnRunning(runningEvent -> {
            loadButton.setDisable(true);
            nextButton.setDisable(true);
            loadButton.setText("Loading...");
        });

        task.setOnSucceeded(successEvent -> {
            saleReader = task.getValue();
            next();
            loadButton.setText(originalText);
            loadButton.setDisable(false);
        });

        task.setOnFailed(failedEvent -> {
            nextButton.setDisable(true);
            loadButton.setText(originalText);
            AlertUtil.showError("File not found",
                    "Please ensure the following file exists: " + System.getProperty("user.dir") + file);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

    private void next() {
        String originalText = nextButton.getText();

        NextCSVTask task = new NextCSVTask(saleReader);
        task.setOnRunning(runningEvent -> {
            loadButton.setDisable(true);
            nextButton.setDisable(true);
            nextButton.setText("Loading...");
        });

        task.setOnSucceeded(successEvent -> {
            list.clear();
            list.addAll(task.getValue().stream().map(Sale::from).collect(Collectors.toList()));
            salesTableView.setItems(list);
            nextButton.setDisable(!saleReader.hasNext());
            nextButton.setText(originalText);
            loadButton.setDisable(false);
        });

        task.setOnFailed(failedEvent -> {
            loadButton.setDisable(false);
            nextButton.setDisable(true);
            nextButton.setText(originalText);
            AlertUtil.showError("Something went wrong", "Unable to load next set of results");
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

    public void shutdown() {
        System.out.println("Stopping");
        timer.shutdown();
        Platform.exit();
    }
}
