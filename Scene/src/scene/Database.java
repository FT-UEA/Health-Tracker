package scene;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    boolean exists;
    boolean anyActiveGoals;
    boolean anyCompletedGoals;
    boolean inGroup;
    User loadedUser;

    public void saveToFile(User user) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(user.getUserName() + ".csv"));
            os.writeObject(user);
            os.close();
            System.out.println("\nSuccessfully saved! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User load(String userName) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(userName + ".csv"));
            User person = (User) is.readObject(); //reads in user from database
            //creates new user object from the saved info in the database
            User user = new User(person.getUserName(), person.getRealName(), person.getEmail(), person.getAge(),
                    person.getHeight(), person.getWeight(), person.getGender());

            anyActiveGoals = person.active_goals.isEmpty();            //checks for any active goals
            anyCompletedGoals = person.completed_goals.isEmpty();      //checks for any completed goals
            inGroup = person.groups.isEmpty();                         //checks if user is in a group

            //if the user has goals it will add them to the goals hashmap
            if (!anyActiveGoals) {
                user.active_goals.putAll(person.active_goals);
            }
            //if the user has completed goals it will add them to the completed goals hashmap
            if (!anyCompletedGoals) {
                user.completed_goals.putAll(person.active_goals);
            }
            //if the user is in a group it will add it to the groups hashmap
            if (!inGroup) {
                user.groups.addAll(person.groups);
            }

            loadedUser = user;
            System.out.println("\nSuccessfully Loaded! ");
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedUser;
    }


    public void load(User user) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(user.getUserName() + ".csv"));
            User person = (User) is.readObject();
            user.setUserName(person.getUserName());
            user.setRealName(person.getRealName());
//            user.email = person.email;
            user.setEmail(person.getEmail());
//            user.age = person.age;
            user.setAge(person.getAge());
//            user.height = person.height;
            user.setHeight(person.getHeight());
//            user.weight = person.weight;
            user.setHeight(person.getWeight());
            anyActiveGoals = person.active_goals.isEmpty();
            anyCompletedGoals = person.completed_goals.isEmpty();
            //if the user has goals it will add them to the goals hashmap
            if (!anyActiveGoals) {
                user.active_goals.putAll(person.active_goals);
            }
            if (!anyCompletedGoals) {
                user.completed_goals.putAll(person.active_goals);
            }
            System.out.println("\nSuccessfully Loaded! ");
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String userName) {
        File f = new File(userName + ".csv");
        exists = f.exists(); // stores true or false in exists
        return exists;
    }
    /*  ---- testing ----
    public static void main(String[] args){
        User u = new User("JaG", "James", "J.G@e.com", 19,  85.2, 74.3);
        Database d = new Database();
        d.saveToFile(u);
        System.out.println(d.userExists("JaG"));
        User u2 = new User("Isa", "Ishan", "I.S@e.com", 19,  85.2, 74.3);
        d.saveToFile(u2);
        d.load(u);

    }*/
}