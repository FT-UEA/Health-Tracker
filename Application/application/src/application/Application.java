package application;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private HashMap<String, User> users;
    private HashMap<String, Group> groups;
    private User currentUser;
    private String userName;

    public Application() {
        users = new HashMap<>();
        groups = new HashMap<>();
    }

    // Creates and adds user
    public void createUser() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("New user registration");
        System.out.println("Username:");
        userName = scan.nextLine();
        System.out.println("Full name:");
        String fullName = scan.nextLine();
        System.out.println("Email address:");
        String emailAddress = scan.nextLine();
        System.out.println("Age:");
        int age = scan.nextInt();
        System.out.println("Height:");
        double height = scan.nextDouble();
        System.out.println("Weight");
        double weight = scan.nextDouble();
        users.put(userName, new User(userName, fullName, emailAddress, age, height, weight));
        currentUser = users.get(userName);
        System.out.println("User created");
    }

    public void userLogin() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter username");
        String user = scan.nextLine();
        if (users.containsKey(user)) {
            currentUser = users.get(user);
        } else {
            System.out.println("User not registered");
            System.out.println("Register user? (Y/N)");
            if (scan.nextLine().equals("Y")) {
                createUser();
            } else {
                System.exit(0);
            }
        }
    }

    // Adds a user, interacts with diet and health info
    public static void main(String[] args) throws FileNotFoundException {
        // Create application object
        Application application = new Application();
        application.userLogin();
        // Calculate BMI/BMR from healthInformation
        application.users.get(application.userName).healthInformation.calculateBMI();
        application.users.get(application.userName).healthInformation.calculateBMR();
        // Interact with dietInformation
        application.users.get(application.userName).dietInformation.chooseMeal();
        application.users.get(application.userName).dietInformation.foodQuery();
        application.users.get(application.userName).dietInformation.drinkQuery();
        application.users.get(application.userName).dietInformation.caloriesConsumed();
        application.users.get(application.userName).exerciseInformation.chooseExercise();
        // Distance goal
        application.users.get(application.userName).createGoal();
        application.users.get(application.userName).checkGoal();
        // Weight goal
        application.users.get(application.userName).createGoal();
        application.users.get(application.userName).checkGoal();

    }
}
