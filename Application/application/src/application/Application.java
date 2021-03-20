package application;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private HashMap<String, User> users;
    private HashMap<String, Group> groups;

    public Application() {
        users = new HashMap<String, User>();
//        groups = new HashMap<String, Group>();
    }

    // Creates and adds user
    public void createUser() throws FileNotFoundException {
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
    public static void main(String[] args) throws FileNotFoundException {
        Application application = new Application();
        application.createUser();
        application.users.get("JoshT").dietInformation.chooseMeal();
        application.users.get("JoshT").dietInformation.foodQuery();
        application.users.get("JoshT").dietInformation.drinkQuery();
        application.users.get("JoshT").dietInformation.caloriesConsumed();
        application.users.get("JoshT").exerciseInformation.chooseExercise();
    }
}
