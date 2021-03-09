import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class DietInformation {

    public DietInformation() throws FileNotFoundException {
        this.foodCalories = new HashMap<>();
        caloriesInit("food_calories.csv",foodCalories);
        this.drinkCalories = new HashMap<>();
        caloriesInit("drink_calories.csv",drinkCalories);
    }

    // Method to provide calorific information of food or drink

    // Method to provide calorific information of meal

    // Method to read csv of food & calories into hashmap
    public void caloriesInit(String filename, HashMap<String, Integer> map) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split(",");
            words[0] = words[0].replaceAll("[^a-zA-Z ]","");
            map.put(words[0], Integer.parseInt(words[1]));
        }
        sc.close();
    }

    // Method to read csv of drink & calories into hashmap

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
