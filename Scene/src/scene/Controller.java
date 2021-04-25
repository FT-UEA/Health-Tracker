package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenRegister(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenLoggedOut(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Logged Out.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenHealthInfo(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Health Information.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenDietInfo(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Diet Information.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenSettings(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenDashboardNoCreate(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenGroupDashboard(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Group Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenGoalDashboard(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Goal Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }
}
