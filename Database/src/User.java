import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User implements Serializable {
    String userName;
    String realName;
    String email;
    int age;
    double height;
    double weight;
    public HashMap<String, Double> active_goals;
    public HashMap<String, Double> completed_goals;
    public User(String userName, String realName, String email, int age, double height, double weight){
        this.userName = userName;
        this.realName = realName;
        this.email = email;
        this.age = age;
        this.height = height;
        this.weight =weight;

    }
    public String getUserName() {
        return userName;
    }

}
