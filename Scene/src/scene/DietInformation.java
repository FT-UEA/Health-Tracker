package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DietInformation {

    // Constructor that initialises hashmaps
    public DietInformation() throws FileNotFoundException {
        this.foodCalories = new HashMap<>();
        caloriesInit("food_calories.csv", foodCalories);
        this.drinkCalories = new HashMap<>();
        caloriesInit("drink_calories.csv", drinkCalories);
        this.meals = new HashMap<>();
        meals.put("Breakfast", 0);
        meals.put("Lunch", 0);
        meals.put("Dinner", 0);
        meals.put("Snack", 0);
    }

    public double getConsumedCalories() {
        return consumedCalories;
    }

    // Method to choose meal
    public void chooseMeal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select meal. Choose from Breakfast/Lunch/Dinner/Snack or type 'Custom' to " +
                "enter a new custom meal");
        String meal = scan.nextLine();
        if (!meals.containsKey(meal) || meal.equals("Custom")) {
            System.out.println("Add custom meal? Enter Y/N");
            if (scan.nextLine().equals("Y")) {
                addCustomMeal();
            }
        } else {
            this.meal = meal;
        }
    }

    // Add custom meal
    public void addCustomMeal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter meal name");
        String mealName = scan.nextLine();
        meals.put(mealName, 0);
        System.out.println(mealName + " added.");
        this.meal = mealName;
    }

    // Method to add add consumed food calories
    public void addFood() {
        Scanner scan = new Scanner(System.in);
        if (!foodCalories.containsKey(foodField.getText())) {
            System.out.println("Food not in list, add custom? Enter Y/N");
            foodAddedText.setText("Food not in list, please add custom food item");
        } else {
            consumedCalories += foodCalories.get(foodField.getText());
            foodAddedText.setText("Added " + foodField.getText() + " at " + foodCalories.get(foodField.getText()) +
                    " calories." +
                    "Total calories consumed so far: \" + consumedCalories");
            System.out.println("Added " + foodField.getText() + " at " + foodCalories.get(foodField.getText()) +
                    " calories.");
            System.out.println("Total calories consumed so far: " + consumedCalories);
        }
    }

    public void addCustomFood() {
        String foodName = customFoodField.getText();
        foodCalories.put(foodName, Integer.parseInt(customFoodCaloriesField.getText()));
        consumedCalories += foodCalories.get(foodName);
//        int prevMealCal = meals.get(meal);
//        meals.replace(meal, (prevMealCal + foodCalories.get(foodField.getText())));
        customFoodAddedText.setText("Added " + foodField.getText() + " at " + foodCalories.get(foodField.getText()) +
                " calories. Total calories consumed so far: " + consumedCalories);
        System.out.println("Added " + foodField.getText() + " at " + foodCalories.get(foodField.getText()) +
                " calories.");
        System.out.println("Total calories consumed so far: " + consumedCalories);
    }

    // Method to add add consumed drink calories
    public void addDrink(String drink) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Current meal :" + meal);
        if (!drinkCalories.containsKey(drink)) {
            System.out.println("Drink not in list, add custom? Enter Y/N");
            if (scan.nextLine().equals("Y")) {
                System.out.println("Please enter drink calories");
                String drinkCalories = scan.nextLine();
                addCustomFood(drink, Integer.parseInt(drinkCalories));
            }
            consumedCalories += drinkCalories.get(drink);
            int prevMealCal = meals.get(meal);
            meals.replace(meal, (prevMealCal + foodCalories.get(drink)));
            System.out.println("Added " + drink + " at " + drinkCalories.get(drink) + " calories.");
            System.out.println("Total calories consumed so far: " + consumedCalories);
        } else {
            consumedCalories += drinkCalories.get(drink);
            System.out.println("Added " + drink + " at " + drinkCalories.get(drink) + " calories.");
            System.out.println("Total calories consumed so far: " + consumedCalories);
        }
    }

    // Method to provide calorific information of meal
    public void addCustomFood(String item, int calories) {
        foodCalories.put(item, calories);
    }

    public void addCustomDrink(String item, int calories) {
        drinkCalories.put(item, calories);
    }

    // Method to read csv of food & drink calories into hashmap
    public void caloriesInit(String filename, HashMap<String, Integer> map) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split(",");
            words[0] = words[0].replaceAll("[^a-zA-Z ]", "");
            map.put(words[0], Integer.parseInt(words[1]));
        }
        sc.close();
    }

    // Method to display calories consumed
    public void caloriesConsumed() {
        System.out.println("Consumed calories: " + consumedCalories);
    }

    // Method to reset the consumed calories to 0 as well as all meals
    public void resetCalories() {
        consumedCalories = 0;
        meals.replace("Breakfast", 0);
        meals.replace("Lunch", 0);
        meals.replace("Dinner", 0);
        meals.replace("Snack", 0);
    }

    // Calories consumed
    private double consumedCalories;
    // HashMap of food and calories
    private final HashMap<String, Integer> foodCalories;
    // HashMap of drink and calories
    private final HashMap<String, Integer> drinkCalories;
    // HashMap of meals and calories
    private HashMap<String, Integer> meals;
    private String meal;
    @FXML
    private TextArea foodCaloriesText;
    @FXML
    private TextArea foodAddedText;
    @FXML
    private TextArea customFoodAddedText;
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
    private TextArea drinkAddedText;
    @FXML
    private TextField customDrinkField;
    @FXML
    private TextField customDrinkCaloriesField;

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
        window.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
//        DietInformation dietInformation = new DietInformation();
//        // List food and drink
////        for (java.util.Map.Entry<String, Integer> stringDoubleEntry : dietInformation.foodCalories.entrySet()) {
////            System.out.println(stringDoubleEntry);
////        }
////        for (java.util.Map.Entry<String, Integer> stringDoubleEntry : dietInformation.drinkCalories.entrySet()) {
////            System.out.println(stringDoubleEntry);
////        }
//        dietInformation.chooseMeal();
//        dietInformation.foodQuery();
//        dietInformation.drinkQuery();
//        dietInformation.caloriesConsumed();
    }
}
