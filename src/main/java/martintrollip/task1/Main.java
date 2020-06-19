package martintrollip.task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import martintrollip.task1.controller.Controller;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("task1.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        stage.setTitle("Task 1");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHidden(hidden -> controller.shutdown());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
