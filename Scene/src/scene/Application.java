package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private static HashMap<String, User> users;
    private static HashMap<String, Group> groups;
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
    private Text loginMessage;
    @FXML
    private Text bmi;
    @FXML
    private Text bmr;
    @FXML
    private CheckBox male;
    @FXML
    private CheckBox female;

    @FXML
    private Text dashUser;
    @FXML
    private Text dashName;
    @FXML
    private Text dashEmail;
    @FXML
    private Text dashAge;
    @FXML
    private Text dashWeight;
    @FXML
    private Text dashHeight;
    @FXML
    private Text dashGender;

    @FXML
    private Text caloriesText;

    @FXML
    private TextArea foodCaloriesText;
    @FXML
    private Text foodAddedText;
    @FXML
    private Text customFoodAddedText;
    @FXML
    private TextField foodField;
    @FXML
    private TextField customFoodField;
    @FXML
    private TextField customFoodCaloriesField;


    @FXML
    private TextField drinkField;
    @FXML
    private TextArea drinkCaloriesText;
    @FXML
    private Text drinkAddedText;
    @FXML
    private TextField customDrinkField;
    @FXML
    private TextField customDrinkCaloriesField;
    @FXML
    private Text customDrinkAddedText;

    @FXML
    private TextField weightGoalName;
    @FXML
    private TextField goalWeight;
    @FXML
    private TextField weightDate;
    @FXML
    private Text weightText;

    @FXML
    private TextField exerciseGoalName;
    @FXML
    private TextField goalDuration;
    @FXML
    private TextField goalDistance;
    @FXML
    private TextField exerciseDate;
    @FXML
    private Text exerciseText;

    @FXML
    private TitledPane exercisePane1;
    @FXML
    private Text exerciseText1;
    @FXML
    private TextField exerciseField1;
    @FXML
    private TitledPane exercisePane2;
    @FXML
    private Text exerciseText2;
    @FXML
    private TextField exerciseField2;
    @FXML
    private TitledPane exercisePane3;
    @FXML
    private Text exerciseText3;
    @FXML
    private TextField exerciseField3;

    @FXML
    private TitledPane weightPane1;
    @FXML
    private Text weightText1;
    @FXML
    private TextField weightField1;
    @FXML
    private TitledPane weightPane2;
    @FXML
    private Text weightText2;
    @FXML
    private TextField weightField2;
    @FXML
    private TitledPane weightPane3;
    @FXML
    private Text weightText3;
    @FXML
    private TextField weightField3;

    @FXML
    private Text feedbackText;


    public Application() throws FileNotFoundException {
        users = new HashMap<>();
        groups = new HashMap<>();
        users.put("JT", new User("JT", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
                184, 77, "M"));
    }

    // Creates and adds user
    public void createUser() throws FileNotFoundException {
        male = new CheckBox();
        female = new CheckBox();
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

    public void setDashText() {
        dashUser.setText(currentUser.getUserName());
        dashName.setText(currentUser.getRealName());
        dashEmail.setText(currentUser.getEmail());
        dashAge.setText(Integer.toString(currentUser.healthInformation.getAge()));
        dashWeight.setText(Double.toString(currentUser.healthInformation.getWeight()));
        dashHeight.setText(Double.toString(currentUser.healthInformation.getHeight()));
        dashGender.setText(currentUser.healthInformation.getGender());
    }

    public void addFood() {
        String food = foodField.getText();
        if (!currentUser.dietInformation.addFood(foodField)) {
            foodAddedText.setText("Food not in list, please add custom food item");
        } else {
            foodAddedText.setText("Added " + food + " at " + currentUser.dietInformation.getFoodCalories(food) +
                    " calories." +
                    "Total calories consumed so far: \" + consumedCalories");
        }
    }

    public void addCustomFood() {
        currentUser.dietInformation.addCustomFood(customFoodField.getText(), customFoodCaloriesField.getText(), customFoodAddedText);
    }

    public void addDrink() {
        String drink = drinkField.getText();
        if (!currentUser.dietInformation.addFood(drinkField)) {
            drinkAddedText.setText("Food not in list, please add custom food item");
        } else {
            drinkAddedText.setText("Added " + drink + " at " + currentUser.dietInformation.getFoodCalories(drink) +
                    " calories." +
                    "Total calories consumed so far: \" + consumedCalories");
        }
    }

    public void addCustomDrink() {
        currentUser.dietInformation.addCustomFood(customDrinkField.getText(), customDrinkCaloriesField.getText(), customDrinkAddedText);
    }

    public void caloriesConsumed() {
        caloriesText.setText(String.valueOf(currentUser.dietInformation.getConsumedCalories()));
    }

    public void createWeightGoal() {
        currentUser.active_goals.put(weightGoalName.getText(), new Goal(weightGoalName.getText(),
                currentUser.healthInformation.getWeight(), Double.parseDouble(goalWeight.getText()),
                weightDate.getText()));
        weightText.setText("Weight goal added");
        System.out.println("Weight goal added");
    }

    public void createExerciseGoal() {
        currentUser.active_goals.put(exerciseGoalName.getText(), new Goal(exerciseGoalName.getText(),
                Double.parseDouble(goalDistance.getText()), exerciseDate.getText()));
        exerciseText.setText("Exercise goal added");
        System.out.println("Exercise goal added");
    }

    public void loadGoals() {
        ArrayList<Goal> goalList = new ArrayList<>(currentUser.active_goals.values());
        ArrayList<Goal> weightGoals = new ArrayList<>();
        ArrayList<Goal> exerciseGoals = new ArrayList<>();
        for (Goal g : goalList) {
            if (g.getType().equals("weight")) {
                weightGoals.add(g);
            } else {
                exerciseGoals.add(g);
            }
        }
        weightPane1.setVisible(false);
        weightPane2.setVisible(false);
        weightPane3.setVisible(false);
        exercisePane1.setVisible(true);
        exercisePane2.setVisible(true);
        exercisePane3.setVisible(true);
        if (weightGoals.size() >= 1) {
            weightPane1.setText(weightGoals.get(0).goalName);
            weightText1.setText(weightGoals.get(0).goalName);
            weightPane1.setVisible(true);
        }
        if (weightGoals.size() >= 2) {
            weightPane2.setText(weightGoals.get(1).goalName);
            weightText2.setText(weightGoals.get(1).goalName);
            weightPane2.setVisible(true);
        }
        if (weightGoals.size() >= 3) {
            weightPane3.setText(weightGoals.get(2).goalName);
            weightText3.setText(weightGoals.get(2).goalName);
            weightPane3.setVisible(true);
        }

        if (exerciseGoals.size() >= 1) {
            exercisePane1.setText(weightGoals.get(0).goalName);
            exerciseText1.setText(weightGoals.get(0).goalName);
            exercisePane1.setVisible(true);
        }
        if (exerciseGoals.size() >= 2) {
            exercisePane2.setText(weightGoals.get(1).goalName);
            exerciseText2.setText(weightGoals.get(1).goalName);
            exercisePane2.setVisible(true);
        }
        if (exerciseGoals.size() >= 3) {
            exercisePane3.setText(weightGoals.get(2).goalName);
            exerciseText3.setText(weightGoals.get(2).goalName);
            exercisePane3.setVisible(true);
        }
    }

//    public void checkWeightGoal() {
//        if (!currentUser.active_goals.containsKey(weightCheckName.getText())) {
//            weightCheckText.setText("Goal does not exist");
//        } else if (currentUser.completed_goals.containsKey(weightCheckName.getText())) {
//            weightCheckText.setText("Goal has been completed");
//        } else {
//            currentUser.active_goals.get(weightCheckName.getText()).checkWeightGoal(weightCheckText,
//                    Double.parseDouble(weightCheck.getText()));
//            if (currentUser.active_goals.get(weightCheckName.getText()).isComplete) {
//                // Remove from active and add to completed
//                currentUser.completed_goals.put(weightCheckName.getText(),
//                        currentUser.active_goals.get(weightCheckName.getText()));
//                System.out.println("asdasd");
//                currentUser.active_goals.remove(weightCheckName.getText());
//            }
//        }
//    }

//    public void checkExerciseGoal() {
//        if (!currentUser.active_goals.containsKey(exerciseCheckName.getText())) {
//            exerciseCheckText.setText("Goal does not exist");
//        } else if (currentUser.completed_goals.containsKey(exerciseCheckName.getText())) {
//            exerciseCheckText.setText("Goal has been completed");
//        }
//    }

    public void changeScreenCheckGoal(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Check Goal.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
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

    public void changeScreenLogin(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenRegister(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenHealthInfo(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Health Information.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenDietInfo(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Diet Information.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenSettings(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Settings.fxml"));
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

    public void changeScreenAddFood(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Food.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddDrink(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Drink.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenWeightGoal(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Weight Goal.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenExerciseGoal(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Exercise Goal.fxml"));
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
        this.bmi.setText(Integer.toString((int) currentUser.healthInformation.getBmi()));
        if (currentUser.healthInformation.getBmi() < 18) {
            feedbackText.setText("Underweight");
        } else if (currentUser.healthInformation.getBmi() > 24) {
            feedbackText.setText("Overweight");
        } else {
            feedbackText.setText("Healthy");
        }
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
        this.bmr.setText(Integer.toString((int) currentUser.healthInformation.getBmr()));
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
