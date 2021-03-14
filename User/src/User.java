import java.io.FileNotFoundException;
import java.text.DecimalFormat;
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

    }

    // Method to provide health feedback
//    public String provideFeedback() {
//
//    }

    // Method for remaining calories?

    // Display group info

    // Display goal info

    // User details
    private String userName;
    private String realName;
    private String email;
    // HealthInformation object
    private HealthInformation healthInformation;
    // DietInformation object
    private DietInformation dietInformation;
    // ExerciseInformation object
    private ExerciseInformation exerciseInformation;
    // Array of groups the user is in
//    private Group[] groups;
    // Array of goals the user has set
//    private Goal[] goals;

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
