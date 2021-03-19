package application;

import java.util.HashMap;
import java.util.Scanner;

public class Application {

    HashMap<String, User> users;
    HashMap<String, Group> groups;

    public Application() {
        users = new HashMap<User, String>();
        groups = new HashMap<Group, String>();
    }

    // Creates and adds user
    public void createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("New user registration");
        System.out.println("Username:");
        String userName = scan.nextLine();
        System.out.println("Full name:");
        String fullname = scan.nextLine();
        System.out.println("Email address:");
        String emailAddress = scan.nextLine();
        System.out.println("Age:");
        int age = scan.nextInt();
        System.out.println("Height:");
        double height = scan.nextDouble();
        System.out.println("Weight");
        double weight = scan.nextDouble();
        users.put(userName, new User(userName, fullname, emailAddress, age, height, weight));
        System.out.println("User created");
    }

    // Adds a user, interacts with diet and health info
    public static void main(String[] args) {
        Application application = new Application();
        application.createUser();
        users.get("JoshT").dietInformation.chooseMeal();
        users.get("JoshT").dietInformation.foodQuery();
        users.get("JoshT").dietInformation.drinkQuery();
        users.get("JoshT").dietInformation.caloriesConsumed();
        users.get("JoshT").exerciseInformation.chooseExercise();
        users.get("JoshT").exerciseInformation.caloriesBurnt();
    }
}
