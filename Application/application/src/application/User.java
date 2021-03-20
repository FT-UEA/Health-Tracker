package application;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class User {

    // Constructor to initialise User variables and HealthInformation variables
    public User(String userName, String realName, String email, int age, double height, double weight) throws FileNotFoundException {
        this.userName = userName;
        this.realName = realName;
        this.email = email;
        healthInformation = new HealthInformation(height, weight, age);
        dietInformation = new DietInformation();
        exerciseInformation = new ExerciseInformation();
        groups = new HashMap<>();
        goals = new HashMap<>();
    }

    // Method to provide health feedback
//    public String provideFeedback() {
//
//    }

    // Create goal
    public void createGoal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter goal name");
        String goalName = scan.nextLine();
        goals.put(goalName, new Goal(goalName, healthInformation.getWeight(), "22/03/2021"));
    }

    // Display goal info

    // Display group info

    // User details
    private final String userName;
    private final String realName;
    private final String email;
    // HealthInformation object
    private final HealthInformation healthInformation;
    // DietInformation object
    public DietInformation dietInformation;
    // ExerciseInformation object
    public ExerciseInformation exerciseInformation;
    // Array of groups the user is in
    private HashMap<String, Group> groups;
    // Array of goals the user has set
    private HashMap<String, Goal> goals;

    public static void main(String[] args) throws FileNotFoundException {
        User user = new User("JT93", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
                184, 77.6);
        System.out.println("Username: " + user.userName);
        System.out.println("Full name: " + user.realName);
        System.out.println("Email address: " + user.email);
        System.out.println("Age: " + user.healthInformation.getAge());
        System.out.println("Height: " + user.healthInformation.getHeight());
        System.out.println("Weight: " + user.healthInformation.getWeight());
        System.out.println("BMI: " + user.healthInformation.calculateBMI());
    }

}
