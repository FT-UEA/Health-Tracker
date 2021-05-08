package scene;

import javafx.application.Platform;
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

public class Application {

    private static HashMap<String, User> users;
    private static HashMap<String, Group> groups;
    private static User currentUser;
    private String currentUserName;
    private Database database;
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
    private Text exerciseStatusText1;
    @FXML
    private TextField exerciseField1;
    @FXML
    private TitledPane exercisePane2;
    @FXML
    private Text exerciseText2;
    @FXML
    private Text exerciseStatusText2;
    @FXML
    private TextField exerciseField2;
    @FXML
    private TitledPane exercisePane3;
    @FXML
    private Text exerciseText3;
    @FXML
    private Text exerciseStatusText3;
    @FXML
    private TextField exerciseField3;

    @FXML
    private TitledPane weightPane1;
    @FXML
    private Text weightText1;
    @FXML
    private Text weightStatusText1;
    @FXML
    private TextField weightField1;
    @FXML
    private TitledPane weightPane2;
    @FXML
    private Text weightText2;
    @FXML
    private Text weightStatusText2;
    @FXML
    private TextField weightField2;
    @FXML
    private TitledPane weightPane3;
    @FXML
    private Text weightText3;
    @FXML
    private Text weightStatusText3;
    @FXML
    private TextField weightField3;

    @FXML
    private Text feedbackText;

    @FXML
    private Text currentMealText;
    @FXML
    private Text mealCalories;
    private static String currentMeal = "Breakfast";
    private static double breakfastCalories;
    private static double lunchCalories;
    private static double dinnerCalories;

    private ArrayList<Goal> goalList;
    private ArrayList<Goal> weightGoals;
    private ArrayList<Goal> exerciseGoals;

    @FXML
    private Button registerButton;
    @FXML
    private Text registerText;

    @FXML
    private TextField groupNameField;
    @FXML
    private Text groupCreateText;

    @FXML
    private TextField groupInviteCode;
    @FXML
    private Text joinGroupText;

    @FXML
    private TitledPane groupPane1;
    @FXML
    private Text groupText1;
    @FXML
    private TitledPane groupPane2;
    @FXML
    private Text groupText2;
    @FXML
    private TitledPane groupPane3;
    @FXML
    private Text groupText3;

    @FXML
    private TextField groupEmail1;
    @FXML
    private TextField groupEmail2;
    @FXML
    private TextField groupEmail3;


    @FXML
    private Text groupInviteText1;
    @FXML
    private Text groupInviteText2;
    @FXML
    private Text groupInviteText3;

    @FXML
    private Button addGroupGoalButton;
    @FXML
    private TextField groupGoalCode;
    @FXML
    private Text addGroupGoalText;

    @FXML
    private Text loginBMIText;
    @FXML
    private Text loginFeedbackText;

    @FXML
    private Text groupExerciseText1;
    @FXML
    private Text groupExerciseText2;
    @FXML
    private Text groupExerciseText3;

    @FXML
    private Text groupWeightText1;
    @FXML
    private Text groupWeightText2;
    @FXML
    private Text groupWeightText3;



    public Application() throws FileNotFoundException {
        users = new HashMap<>();
        groups = new HashMap<>();
//        users.put("JT", new User("JT", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
//                184, 77, "M"));
        database = new Database();
    }

    public static User getUser() {
        return currentUser;
    }

    // Creates and adds user
    public boolean createUser() throws FileNotFoundException {
        registerButton.setDisable(false);
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
        if (database.userExists(userName.getText())) {
            registerText.setText("Username taken");
            registerButton.setDisable(true);
            System.out.println("Username taken");
            return false;
        } else {
            if (isValidEmailAddress(emailAddress.getText())) {
                users.put(userName.getText(), new User(userName.getText(), fullName.getText(), emailAddress.getText(),
                        Integer.parseInt(age.getText()), Double.parseDouble(height.getText()),
                        Double.parseDouble(weight.getText()), gender));
                currentUserName = userName.getText();
                System.out.println("Current User: " + currentUserName);
                currentUser = users.get(currentUserName);
                System.out.println("User added");
                database.saveToFile(currentUser);
                registerText.setText("User created");
                return true;
            } else {
                registerText.setText("Invalid email format");
                registerButton.setDisable(true);
                System.out.println("Invalid email format");
                return false;
            }
        }
    }

    public static boolean isValidEmailAddress(String email) {
        return email.contains("@") && (email.contains(".co.uk") || email.contains(".com"));
    }

    public void userLogin(ActionEvent event) throws Exception {
        if (database.userExists(userName.getText())) {
            users.put(userName.getText(), database.load(userName.getText()));
            currentUser = database.load(userName.getText());
            System.out.println("Loaded user: " + currentUser.getUserName());
            System.out.println("Goal: " + currentUser.active_goals.get("Weight Goal"));
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
            if (currentMeal.equals("Breakfast")) {
                breakfastCalories += currentUser.dietInformation.getFoodCalories(food);
                System.out.println(breakfastCalories);
            } else if (currentMeal.equals("Lunch")) {
                lunchCalories += currentUser.dietInformation.getFoodCalories(food);
            } else {
                dinnerCalories += currentUser.dietInformation.getFoodCalories(food);
            }
            foodAddedText.setText("Added " + food + " at " + currentUser.dietInformation.getFoodCalories(food) +
                    " calories." +
                    "Total calories consumed so far: \" + consumedCalories");
        }
    }

    public void addCustomFood() {
        String food = customFoodField.getText();
        currentUser.dietInformation.addCustomFood(food, customFoodCaloriesField.getText(),
                customFoodAddedText);
        if (currentMeal.equals("Breakfast")) {
            System.out.println("Food cal: " + currentUser.dietInformation.getFoodCalories(food));
            breakfastCalories = currentUser.dietInformation.getFoodCalories(food);
        } else if (currentMeal.equals("Lunch")) {
            lunchCalories += currentUser.dietInformation.getFoodCalories(food);
        } else {
            dinnerCalories += currentUser.dietInformation.getFoodCalories(food);
        }
    }

    public void addDrink() {
        String drink = drinkField.getText();
        if (!currentUser.dietInformation.addFood(drinkField)) {
            drinkAddedText.setText("Food not in list, please add custom food item");
        } else {
            if (currentMeal.equals("Breakfast")) {
                breakfastCalories += currentUser.dietInformation.getFoodCalories(drinkField.getText());
            } else if (currentMeal.equals("Lunch")) {
                lunchCalories += currentUser.dietInformation.getFoodCalories(drinkField.getText());
            } else {
                dinnerCalories += currentUser.dietInformation.getFoodCalories(drinkField.getText());
            }
        }
    }

    public void addCustomDrink() {
        currentUser.dietInformation.addCustomFood(customDrinkField.getText(), customDrinkCaloriesField.getText(),
                customDrinkAddedText);
        if (currentMeal.equals("Breakfast")) {
            breakfastCalories += currentUser.dietInformation.getFoodCalories(customDrinkField.getText());
        } else if (currentMeal.equals("Lunch")) {
            lunchCalories += currentUser.dietInformation.getFoodCalories(customDrinkField.getText());
        } else {
            dinnerCalories += currentUser.dietInformation.getFoodCalories(customDrinkField.getText());
        }
    }

    public void caloriesConsumed() {
        System.out.println(currentMeal);
        caloriesText.setText(String.valueOf(currentUser.dietInformation.getConsumedCalories()));
        currentMealText.setText(currentMeal);
        System.out.println(breakfastCalories);
        if (currentMeal.equals("Breakfast")) {
            mealCalories.setText(String.valueOf(breakfastCalories));
        } else if (currentMeal.equals("Lunch")) {
            mealCalories.setText(Double.toString(lunchCalories));
        } else {
            mealCalories.setText(String.valueOf(dinnerCalories));
        }
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
                Double.parseDouble(goalDistance.getText()), exerciseDate.getText(), goalDuration.getText()));
        exerciseText.setText("Exercise goal added");
        System.out.println("Exercise goal added");
    }

    public void loadGoals() {
        goalList = new ArrayList<>(currentUser.active_goals.values());
        weightGoals = new ArrayList<>();
        exerciseGoals = new ArrayList<>();
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
        exercisePane1.setVisible(false);
        exercisePane2.setVisible(false);
        exercisePane3.setVisible(false);

        if (weightGoals.size() >= 1) {
            weightPane1.setText(weightGoals.get(0).getGoalName());
            weightText1.setText(weightGoals.get(0).getGoalName());
            weightPane1.setVisible(true);
        }
        if (weightGoals.size() >= 2) {
            weightPane2.setText(weightGoals.get(1).getGoalName());
            weightText2.setText(weightGoals.get(1).getGoalName());
            weightPane2.setVisible(true);
        }
        if (weightGoals.size() >= 3) {
            weightPane3.setText(weightGoals.get(2).getGoalName());
            weightText3.setText(weightGoals.get(2).getGoalName());
            weightPane3.setVisible(true);
        }

        if (exerciseGoals.size() >= 1) {
            exercisePane1.setText(exerciseGoals.get(0).getGoalName());
            exerciseText1.setText(exerciseGoals.get(0).getGoalName());
            exercisePane1.setVisible(true);
        }
        if (exerciseGoals.size() >= 2) {
            exercisePane2.setText(exerciseGoals.get(1).getGoalName());
            exerciseText2.setText(exerciseGoals.get(1).getGoalName());
            exercisePane2.setVisible(true);
        }
        if (exerciseGoals.size() >= 3) {
            exercisePane3.setText(exerciseGoals.get(2).getGoalName());
            exerciseText3.setText(exerciseGoals.get(2).getGoalName());
            exercisePane3.setVisible(true);
        }
    }

    public void createGroup() {
        if (Group.checkGroup(groupNameField.getText())) {
            groupCreateText.setText("Group name already exists");
            groupNameField.clear();
        } else {
            currentUser.createGroup(groupNameField.getText());
            groupCreateText.setText("Group created");
        }
    }

    public void setMealBreakfast() {
        currentMealText.setText("Breakfast");
        currentMeal = "Breakfast";
        if (breakfastCalories == 0.0) {
            mealCalories.setText("0.0");
        } else {
            mealCalories.setText(String.valueOf(breakfastCalories));
        }

    }

    public void setMealLunch() {
        currentMealText.setText("Lunch");
        currentMeal = "Lunch";
        if (lunchCalories == 0.0) {
            mealCalories.setText("0.0");
        } else {
            mealCalories.setText(String.valueOf(lunchCalories));
        }
    }

    public void setMealDinner() {
        currentMealText.setText("Dinner");
        currentMeal = "Dinner";
        if (dinnerCalories == 0.0) {
            mealCalories.setText("0.0");
        } else {
            mealCalories.setText(String.valueOf(dinnerCalories));
        }
    }

    public void inviteMember1() {
        if (isValidEmailAddress(groupEmail1.getText())) {
            currentUser.groups.get(0).invite(groupEmail1.getText());
            groupInviteText1.setText(groupEmail1.getText() + " invited");
        } else {
            groupInviteText1.setText("Invalid email");
            groupEmail1.clear();
        }
    }

    public void inviteMember2() {
        if (isValidEmailAddress(groupEmail2.getText())) {
            currentUser.groups.get(1).invite(groupEmail2.getText());
            groupInviteText2.setText(groupEmail2.getText() + " invited");
        } else {
            groupInviteText2.setText("Invalid email");
            groupEmail2.clear();
        }
    }

    public void inviteMember3() {
        if (isValidEmailAddress(groupEmail3.getText())) {
            currentUser.groups.get(2).invite(groupEmail3.getText());
            groupInviteText3.setText(groupEmail3.getText() + " invited");
        } else {
            groupInviteText3.setText("Invalid email");
            groupEmail3.clear();
        }
    }

    public void joinGroup() {
        currentUser.joinGroup(joinGroupText, groupInviteCode.getText());
    }

    public void loadGroups() {
        groupPane1.setVisible(false);
        groupPane2.setVisible(false);
        groupPane3.setVisible(false);

        if (currentUser.groups.size() >= 1) {
            groupPane1.setText(currentUser.groups.get(0).getName());
            groupText1.setText(currentUser.groups.get(0).getName());
            groupPane1.setVisible(true);
        }
        if (currentUser.groups.size() >= 2) {
            groupPane2.setText(currentUser.groups.get(1).getName());
            groupText2.setText(currentUser.groups.get(1).getName());
            groupPane2.setVisible(true);
        }
        if (currentUser.groups.size() >= 3) {
            groupPane3.setText(currentUser.groups.get(2).getName());
            groupText3.setText(currentUser.groups.get(2).getName());
            groupPane3.setVisible(true);
        }
    }

    public void addGroupGoal() {
        if (!currentUser.addGroupGoal(groupGoalCode.getText())) {
            addGroupGoalText.setText("Invalid code");
            groupGoalCode.clear();
        } else {
            addGroupGoalText.setText("Group goal added");
        }

    }

    public void createGroupExerciseGoal1() {
        // Add completion text here
        currentUser.groups.get(0).createGoalExercise(exerciseGoalName.getText(),
                Double.parseDouble(goalDistance.getText()), exerciseDate.getText(), goalDuration.getText());
        System.out.println("Group goal " + exerciseGoalName.getText() + " created");
    }

    public void createGroupExerciseGoal2() {
        currentUser.groups.get(1).createGoalExercise(exerciseGoalName.getText(),
                Double.parseDouble(goalDistance.getText()), exerciseDate.getText(), goalDuration.getText());
        System.out.println("Group goal " + exerciseGoalName.getText() + " created");
    }

    public void createGroupExerciseGoal3() {
        currentUser.groups.get(2).createGoalExercise(exerciseGoalName.getText(),
                Double.parseDouble(goalDistance.getText()), exerciseDate.getText(), goalDuration.getText());
        System.out.println("Group goal " + exerciseGoalName.getText() + " created");
    }

    public void createGroupWeightGoal1() {
        currentUser.groups.get(0).createGoalWeight(weightGoalName.getText(), currentUser.healthInformation.getWeight(),
                Double.parseDouble(goalWeight.getText()), weightDate.getText());
        System.out.println("Group goal " + weightGoalName.getText() + " created");
    }

    public void createGroupWeightGoal2() {
        currentUser.groups.get(1).createGoalWeight(weightGoalName.getText(), currentUser.healthInformation.getWeight(),
                Double.parseDouble(goalWeight.getText()), weightDate.getText());
        System.out.println("Group goal " + weightGoalName.getText() + " created");
    }

    public void createGroupWeightGoal3() {
        currentUser.groups.get(2).createGoalWeight(weightGoalName.getText(), currentUser.healthInformation.getWeight(),
                Double.parseDouble(goalWeight.getText()), weightDate.getText());
        System.out.println("Group goal " + weightGoalName.getText() + " created");
    }

    public void leaveGroup1() {
        currentUser.leaveGroup(currentUser.groups.get(0).getName());
        groupText1.setText("Left group");
    }

    public void leaveGroup2() {
        currentUser.leaveGroup(currentUser.groups.get(1).getName());
        groupText2.setText("Left group");
    }

    public void leaveGroup3() {
        currentUser.leaveGroup(currentUser.groups.get(2).getName());
        groupText3.setText("Left group");
    }

    public void checkWeightGoal() {
        currentUser.active_goals.get(weightGoals.get(0).getGoalName()).checkWeightGoal(currentUser, weightStatusText1, weightText1,
                Double.parseDouble(weightField1.getText()));
        if (currentUser.active_goals.get(weightGoals.get(0).getGoalName()).isComplete()) {
            // Remove from active and add to completed
            currentUser.completed_goals.put(weightGoals.get(0).getGoalName(),
                    currentUser.active_goals.get(weightGoals.get(0).getGoalName()));
            System.out.println("Goal moved to completed");
            currentUser.active_goals.remove(weightGoals.get(0).getGoalName());
        }
    }

    public void checkWeightGoal1() {
        currentUser.active_goals.get(weightGoals.get(1).getGoalName()).checkWeightGoal(currentUser, weightStatusText2, weightText2,
                Double.parseDouble(weightField2.getText()));
        if (currentUser.active_goals.get(weightGoals.get(1).getGoalName()).isComplete()) {
            // Remove from active and add to completed
            currentUser.completed_goals.put(weightGoals.get(1).getGoalName(),
                    currentUser.active_goals.get(weightGoals.get(1).getGoalName()));
            System.out.println("Goal moved to completed");
            currentUser.active_goals.remove(weightGoals.get(1).getGoalName());
        }
    }

    public void checkWeightGoal2() {
        currentUser.active_goals.get(weightGoals.get(2).getGoalName()).checkWeightGoal(currentUser, weightStatusText3, weightText2,
                Double.parseDouble(weightField2.getText()));
        if (currentUser.active_goals.get(weightGoals.get(2).getGoalName()).isComplete()) {
            // Remove from active and add to completed
            currentUser.completed_goals.put(weightGoals.get(2).getGoalName(),
                    currentUser.active_goals.get(weightGoals.get(2).getGoalName()));
            System.out.println("Goal moved to completed");
            currentUser.active_goals.remove(weightGoals.get(2).getGoalName());
        }
    }

    public void checkExerciseGoal() {
        currentUser.active_goals.get(exerciseGoals.get(0).getGoalName()).checkExerciseGoal(currentUser, exerciseStatusText1, exerciseText1,
                Double.parseDouble(exerciseField1.getText()));
        if (currentUser.active_goals.get(exerciseGoals.get(0).getGoalName()).isComplete()) {
            // Remove from active and add to completed
            currentUser.completed_goals.put(exerciseGoals.get(0).getGoalName(),
                    currentUser.active_goals.get(exerciseGoals.get(0).getGoalName()));
            System.out.println("Goal moved to completed");
            currentUser.active_goals.remove(exerciseGoals.get(0).getGoalName());
        }
    }

    public void checkExerciseGoal1() {
        currentUser.active_goals.get(exerciseGoals.get(1).getGoalName()).checkExerciseGoal(currentUser, exerciseStatusText2, exerciseText2,
                Double.parseDouble(exerciseField2.getText()));
        if (currentUser.active_goals.get(exerciseGoals.get(1).getGoalName()).isComplete()) {
            // Remove from active and add to completed
            currentUser.completed_goals.put(exerciseGoals.get(1).getGoalName(),
                    currentUser.active_goals.get(exerciseGoals.get(1).getGoalName()));
            System.out.println("Goal moved to completed");
            currentUser.active_goals.remove(exerciseGoals.get(1).getGoalName());
        }
    }

    public void checkExerciseGoal2() {
        currentUser.active_goals.get(exerciseGoals.get(2).getGoalName()).checkExerciseGoal(currentUser, exerciseStatusText3, exerciseText3,
                Double.parseDouble(exerciseField3.getText()));
        if (currentUser.active_goals.get(exerciseGoals.get(2).getGoalName()).isComplete()) {
            // Remove from active and add to completed
            currentUser.completed_goals.put(exerciseGoals.get(2).getGoalName(),
                    currentUser.active_goals.get(exerciseGoals.get(2).getGoalName()));
            System.out.println("Goal moved to completed");
            currentUser.active_goals.remove(exerciseGoals.get(2).getGoalName());
        }
    }

    public void changeScreenAddGroupGoal(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Goal.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddGroupExerciseGoal1(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Exercise Goal1.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddGroupExerciseGoal2(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Exercise Goal2.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddGroupExerciseGoal3(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Exercise Goal3.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddGroupWeightGoal1(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Weight Goal1.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddGroupWeightGoal2(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Weight Goal2.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenAddGroupWeightGoal3(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Add Group Weight Goal3.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenInviteMembers1(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Invite Members1.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();

    }

    public void changeScreenInviteMembers2(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Invite Members2.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenInviteMembers3(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Invite Members3.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenCheckGoal(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Check Goal.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenLoginFeedback(ActionEvent event) throws Exception {
        if (createUser()) {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
            Scene loginScene = new Scene(loginRoot);
            // This line gets the stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setTitle("Health Tracker");
            window.show();
        } else {
            changeScreenLoginFeedback(event);
            userName.clear();
            fullName.clear();
            age.clear();
            height.clear();
            weight.clear();
            male.setSelected(false);
            female.setSelected(false);
        }
    }

    public void changeScreenDashboard(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenBackLoggedOut(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Logged Out.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenLoggedOut(ActionEvent event) throws Exception {
        database.saveToFile(currentUser);
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

    public void changeScreenGroupGoal1(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Group Goal1.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenGroupGoal2(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Group Goal2.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenGroupGoal3(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Group Goal3.fxml"));
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

    public void changeScreenCreateGroup(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Create Group.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenJoinGroup(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Join Group.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Health Tracker");
        window.show();
    }

    public void changeScreenMyGroups(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("My Groups.fxml"));
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

    public void calculateBMIFeedback() {
        currentUser.healthInformation.setBmi(100 * (currentUser.healthInformation.getWeight() /
                Math.pow(currentUser.healthInformation.getHeight() * 0.1, 2)));
        this.bmi.setText(Integer.toString((int) currentUser.healthInformation.getBmi()));
        if (currentUser.healthInformation.getBmi() < 18) {
            loginFeedbackText.setText("Underweight");
            loginFeedbackText.setText("Your BMI indicates you are underweight, would you like to set a goal?");
        } else if (currentUser.healthInformation.getBmi() > 24) {
            loginFeedbackText.setText("Overweight");
            loginFeedbackText.setText("Your BMI indicates you are overweight, would you like to set a goal?");
        } else {
            loginFeedbackText.setText("Healthy");
            loginFeedbackText.setText("Your BMI indicates you are healthy, would you like to set a goal?");
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
