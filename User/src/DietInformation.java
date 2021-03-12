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

    // Method to choose meal
    public void chooseMeal(String meal) {
        Scanner scan = new Scanner(System.in);
        if (!meals.containsKey(meal)) {
            System.out.println("Meal not present, add custom meal? Enter Y/N");
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
        System.out.println(mealName + " addeed.");
    }

    // Method to provide calorific information for each meal

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
            if (scan.nextLine().equals("Y")) {
                System.out.println("Please enter food name.");
                String foodName = scan.nextLine();
                System.out.println("Please enter food calories");
                String foodCalories = scan.nextLine();
                addCustomFood(foodName, Integer.parseInt(foodCalories));
            }
            consumedCalories += foodCalories.get(food);
            int prevMealCal = meals.get(meal);
            meals.replace(meal, (prevMealCal + foodCalories.get(food)));
            System.out.println("Added " + food + " at " + foodCalories.get(food) + " calories.");
            System.out.println("Total calories consumed so far: " + consumedCalories);
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
            if (scan.nextLine().equals("Y")) {
                System.out.println("Please enter drink name.");
                String foodName = scan.nextLine();
                System.out.println("Please enter drink calories");
                String drinkCalories = scan.nextLine();
                addCustomFood(foodName, Integer.parseInt(drinkCalories));
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
