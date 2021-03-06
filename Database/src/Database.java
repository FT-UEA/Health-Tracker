import java.io.*;
import java.util.Collections;

public class Database {

    boolean exists;
    User loadedUser;

    public void saveToFile(User user) {
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream( user.userName + ".csv"));
            os.writeObject(user);
            os.close();
            System.out.println("\nSuccessfully saved! ");
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public User load(String userName) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(userName + ".csv"));
            loadedUser = (User) is.readObject();
            System.out.println("\nSuccessfully Loaded! ");
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedUser;
    }

    public boolean userExists(String userName){
        File f = new File(userName + ".csv");
        exists = f.exists(); // stores true or false in exists

        return exists;
    }

    /*    ---- testing ----
    public static void main(String[] args){
        //User u = new User("JaG", "James", "J.G@e.com", 19,  85.2, 74.3);
        //Database d = new Database();
        //d.saveToFile(u);
        //System.out.println(d.userExists("JaG"));
        //User u2 = new User("Isa", "Ishan", "I.S@e.com", 19,  85.2, 74.3);
        //d.saveToFile(u2);
        //d.load("JaG");
    }*/
}