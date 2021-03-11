import java.text.DecimalFormat;
import java.util.Scanner;

public class User {

    // Constructor to initialise User variables and HealthInformation variables
    public User(String userName, String realName, String email, int age, double height, double weight) {
        this.userName = userName;
        this.realName = realName;
        this.email = email;
        this.age = age;
        healthInformation = new HealthInformation(height, weight);
    }

    // Method to calculate user BMI
    public String calculateBMI() {
        DecimalFormat df = new DecimalFormat("##.#");
        double result = 100 * (healthInformation.getWeight() / Math.pow(healthInformation.getHeight() * 0.1, 2));
        System.out.println("Your BMI is: " + result);
        return df.format(result);
    }

    // Method to calculate user BMR
    public double calculateBMR() {
        Scanner scan = new Scanner(System.in);
        double bmr;
        System.out.println("What is your gender? Enter M/F");
        String gender = scan.nextLine();
        if (gender.equals("M")) {
            bmr = 66.47 + (13.75 * healthInformation.getWeight()) + (5.003 * healthInformation.getHeight())
                    - (6.755 * age);
        } else {
            bmr = 655.1 + (9.563 * healthInformation.getWeight()) + (1.85 * healthInformation.getHeight())
                    - (4.676 * age);
        }
        System.out.println("Your BMR is " + bmr);
        return bmr;
    }

    // Method to provide health feedback
//    public String provideFeedback() {
//
//    }

    // Method for remaining calories?

    // User details
    private String userName;
    private String realName;
    private String email;
    private int age;
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

    public static void main(String[] args) {
        User user = new User("JT93", "Josh Thomson", "JoshT626@hotmail.co.uk", 28, 184, 77.6);
        System.out.println("Username: " + user.userName);
        System.out.println("Full name: " + user.realName);
        System.out.println("Email address: " + user.email);
        System.out.println("Age: " + user.age);
        System.out.println("Height: " + user.healthInformation.getHeight());
        System.out.println("Weight: " + user.healthInformation.getWeight());
        System.out.println("BMI: " + user.calculateBMI());
    }

}
