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
    }

    // Method to receive calorific information about a food
    public void foodQuery(String food) {
        if (!foodCalories.containsKey(food)) {
            addFood(food);
        } else {
            System.out.println(food + " contains " + foodCalories.get(food) + " calories");
        }
    }

    // Method to receive calorific information about a drink
    public void drinkQuery(String drink) {
        if (!foodCalories.containsKey(drink)) {
            addFood(drink);
        } else {
            System.out.println(drink + " contains " + foodCalories.get(drink) + " calories");
        }
    }

    // Method to add add consumed food calories
    public void addFood(String food) {
        Scanner scan = new Scanner(System.in);
        if (!foodCalories.containsKey(food)) {
            System.out.println("Food not in list, add custom? Enter Y/N");
            String response = scan.nextLine();
            if (response.equals("Y")) {
                System.out.println("Please enter food name.");
                String foodName = scan.nextLine();
                System.out.println("Please enter food calories");
                String foodCalories = scan.nextLine();
                addCustomFood(foodName, Integer.parseInt(foodCalories));
            }
        } else {
            consumedCalories += foodCalories.get(food);
            System.out.println("Added " + food + " at " + foodCalories.get(food) + " calories.");
            System.out.println("Total calories consumed so far: " + consumedCalories);
        }
    }

    // Method to add add consumed drink calories
    public void addDrink(String drink) {
        Scanner scan = new Scanner(System.in);
        if (!drinkCalories.containsKey(drink)) {
            System.out.println("Drink not in list, add custom? Enter Y/N");
            String response = scan.nextLine();
            if (response.equals("Y")) {
                System.out.println("Please enter drink name.");
                String foodName = scan.nextLine();
                System.out.println("Please enter drink calories");
                String drinkCalories = scan.nextLine();
                addCustomFood(foodName, Integer.parseInt(drinkCalories));
            }
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

    // Method to reset the consumed calories to 0
    public void resetCalories() {
        consumedCalories = 0;
    }

    // Calories consumed
    private double consumedCalories;
    // HashMap of food and calories
    HashMap<String, Integer> foodCalories;
    // HashMap of drink and calories
    HashMap<String, Integer> drinkCalories;

    public static void main(String[] args) throws FileNotFoundException {
        DietInformation dietInformation = new DietInformation();
        for (java.util.Map.Entry<String, Integer> stringDoubleEntry : dietInformation.foodCalories.entrySet()) {
            System.out.println(stringDoubleEntry);
        }
        for (java.util.Map.Entry<String, Integer> stringDoubleEntry : dietInformation.drinkCalories.entrySet()) {
            System.out.println(stringDoubleEntry);
        }
    }
}
