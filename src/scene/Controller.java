package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    // Changes Logged Out to Login scene
    public void changeScreenLogin(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void changeScreenRegister(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void changeScreenLoggedOut(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Logged Out.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void changeScreenDashboard(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
}
