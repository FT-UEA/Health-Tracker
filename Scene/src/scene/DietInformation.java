package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    public double getFoodCalories(String food) {
        return foodCalories.get(food);
    }

    public double getDrinkCalories(String drink) {
        return drinkCalories.get(drink);
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
    public boolean addFood(TextField foodField) {
        if (!foodCalories.containsKey(foodField.getText())) {
            System.out.println("Food not in list");
            return false;
        } else {
            consumedCalories += foodCalories.get(foodField.getText());
            System.out.println("Added " + foodField.getText() + " at " + foodCalories.get(foodField.getText()) +
                    " calories.");
            System.out.println("Total calories consumed so far: " + consumedCalories);
            return true;
        }
    }

    public void addCustomFood(String customFoodField, String customFoodCaloriesField, Text customFoodAddedText) {
        String food = customFoodField;
        foodCalories.put(food, Integer.valueOf(customFoodCaloriesField));
        consumedCalories += foodCalories.get(food);
//        int prevMealCal = meals.get(meal);
//        meals.replace(meal, (prevMealCal + foodCalories.get(foodField.getText())));
        customFoodAddedText.setText("Added " + customFoodField + " at " + foodCalories.get(customFoodField) +
                " calories. Total calories consumed so far: " + consumedCalories);
    }

    // Method to add add consumed drink calories
    public boolean addDrink(TextField drinkField) {
        if (!foodCalories.containsKey(drinkField.getText())) {
            System.out.println("Food not in list");
            return false;
        } else {
            consumedCalories += foodCalories.get(drinkField.getText());
            return true;
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
