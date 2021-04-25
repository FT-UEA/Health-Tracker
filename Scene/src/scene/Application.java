package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private HashMap<String, User> users;
    private HashMap<String, Group> groups;
    private static User currentUser;
    private String currentUserName;
    @FXML
    private TextField userName;
    @FXML
    private TextField fullName;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField age;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextArea loginMessage;
    @FXML
    private TextArea bmi;
    @FXML
    private TextArea bmr;
    @FXML
    private CheckBox male;
    @FXML
    private CheckBox female;

    public Application() throws FileNotFoundException {
        users = new HashMap<>();
        groups = new HashMap<>();
        users.put("JT", new User("JT", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
                184, 77, "M"));
    }

    // Creates and adds user
    public void createUser() throws FileNotFoundException {
        String gender;
        if (male.isSelected()) {
            gender = "M";
        } else if (female.isSelected()) {
            gender = "F";
        } else {
            gender = "M";
        }
        users.put(userName.getText(), new User(userName.getText(), fullName.getText(), emailAddress.getText(),
                Integer.parseInt(age.getText()), Double.parseDouble(height.getText()),
                Double.parseDouble(weight.getText()), gender));
        currentUserName = userName.getText();
        System.out.println("Current User: " + currentUserName);
        currentUser = users.get(currentUserName);
        System.out.println("User added");
        System.out.println(users.keySet());
    }

    public void userLogin(ActionEvent event) throws Exception {
        if (users.containsKey(userName.getText())) {
            System.out.println("User IS registered");
            currentUserName = userName.getText();
            System.out.println("User: " + currentUserName);
            currentUser = users.get(currentUserName);
            System.out.println("Current User: " + currentUser.getUserName());
            changeScreenDashboardNoCreate(event);
        } else {
            loginMessage.setText("User not registered");
            System.out.println("User not registered");
        }
    }

    public void changeScreenDashboard(ActionEvent event) throws Exception {
        createUser();
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenLoggedOut(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Logged Out.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        System.out.println("Dashboard User: " + currentUser.getUserName());
        window.show();
    }

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

    // Method to calculate user BMI
    public void calculateBMI() {
        currentUser.healthInformation.setBmi(100 * (currentUser.healthInformation.getWeight() /
                Math.pow(currentUser.healthInformation.getHeight() * 0.1, 2)));
        this.bmi.setText(Integer.toString((int)currentUser.healthInformation.getBmi()));
    }

    // Method to calculate user BMR
    public void calculateBMR() {
        if (currentUser.healthInformation.getGender().equals("M")) {
            currentUser.healthInformation.setBmr(66.47 + (13.75 * currentUser.healthInformation.getWeight()) +
                    (5.003 * currentUser.healthInformation.getHeight()) -
                    (6.755 * currentUser.healthInformation.getAge()));
        } else {
            currentUser.healthInformation.setBmr(655.1 + (9.563 * currentUser.healthInformation.getWeight()) +
                    (1.85 * currentUser.healthInformation.getHeight())
                    - (4.676 * currentUser.healthInformation.getAge()));
        }
        this.bmr.setText(Integer.toString((int)currentUser.healthInformation.getBmr()));
    }

    // Adds a user, interacts with diet and health info
    public static void main(String[] args) throws FileNotFoundException {
        // Create application object
//        Application application = new Application();
//        application.userLogin();
//        // Calculate BMI/BMR from healthInformation
//        application.users.get(application.userName).healthInformation.calculateBMI();
//        application.users.get(application.userName).healthInformation.calculateBMR();
//        // Interact with dietInformation
//        application.users.get(application.userName).dietInformation.chooseMeal();
//        application.users.get(application.userName).dietInformation.foodQuery();
//        application.users.get(application.userName).dietInformation.drinkQuery();
//        application.users.get(application.userName).dietInformation.caloriesConsumed();
//        application.users.get(application.userName).exerciseInformation.chooseExercise();
//        // Distance goal
//        application.users.get(application.userName).createGoal();
//        application.users.get(application.userName).checkGoal();
    }
}
