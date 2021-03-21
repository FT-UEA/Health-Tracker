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
        active_goals = new HashMap<>();
    }

    // Method to provide health feedback
//    public String provideFeedback() {
//
//    }

    // Create goal
    public void createGoal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("New goal");
        System.out.println("Please enter goal name");
        String goalName = scan.nextLine();
        active_goals.put(goalName, new Goal(goalName, healthInformation.getWeight()));
    }

    public void checkGoal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the goal you would like to check");
        String goalName = scan.nextLine();
        if (active_goals.containsKey(goalName)) {
            if (active_goals.get(goalName).type.equals("weight")) {
                System.out.println("Weight goal");
                System.out.println("Please enter your current weight");
                double currentWeight = Double.parseDouble(scan.nextLine());
                active_goals.get(goalName).checkWeightGoal(currentWeight);
            } else {
                System.out.println("Exercise goal");
                System.out.println("Please enter your current distance (m)");
                double currentDistance = Double.parseDouble(scan.nextLine());
                active_goals.get(goalName).checkExerciseGoal(currentDistance);
            }
            if (active_goals.get(goalName).isComplete) {
                completed_goals.put(goalName, active_goals.get(goalName));
                active_goals.remove(goalName);
            }
        } else {
            if (completed_goals.containsKey(goalName)) {
                System.out.println("Goal has been completed");
                System.out.println("Create new goal? (Y/N)");
                if (scan.nextLine().equals("Y")) {
                    createGoal();
                }
            } else {
                System.out.println("Goal does not exist");
                System.out.println("Create new goal? (Y/N)");
                if (scan.nextLine().equals("Y")) {
                    createGoal();
                }
            }
        }
    }

    // Display group info

    // User details
    private final String userName;
    private final String realName;
    private final String email;
    // HealthInformation object
    public final HealthInformation healthInformation;
    // DietInformation object
    public DietInformation dietInformation;
    // ExerciseInformation object
    public ExerciseInformation exerciseInformation;
    // HashMap of groups the user is in
    public HashMap<String, Group> groups;
    // HashMap of goals the user has active
    public HashMap<String, Goal> active_goals;
    // HashMap of goals the user has completed
    public HashMap<String, Goal> completed_goals;

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
