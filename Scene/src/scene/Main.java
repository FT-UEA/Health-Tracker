package scene;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Logged Out.fxml"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Health Tracker");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            Database database = new Database();
            if (scene.Application.getUser() != null) {
                database.saveToFile(scene.Application.getUser());
            }
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
