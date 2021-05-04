package scene;

import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User implements Serializable {

    // Constructor to initialise User variables and HealthInformation variables
    public User(String userName, String realName, String email, int age, double height, double weight, String gender)
            throws FileNotFoundException {
        this.userName = userName;
        this.realName = realName;
        this.email = email;
        healthInformation = new HealthInformation(height, weight, age, gender);
        dietInformation = new DietInformation();
        exerciseInformation = new ExerciseInformation();
        groups = new ArrayList<>();
        active_goals = new HashMap<>();
        completed_goals = new HashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public int getGroupSize(){ return groups.size();}

    public Group getGroup(int i){
        return groups.get(i);
    }

    public Group getGroup(String name){
        Group group_out = null;
        for(Group group: groups){
            if(group.getName().equals(name)){
                group_out = group;
            }
        }
        System.out.println("Yes?? " + group_out);
        return group_out;
    }


    public void createGroup(String group_name){
        groups.add(new Group(group_name, this));
    }

    public void joinGroup(Text text, String invite_code) {

        String group_name = invite_code.replaceAll("_.+", "");
        File f = new File(group_name + ".csv");
        System.out.println(group_name);

        System.out.println(f.exists());
        if(f.exists()){
            try{
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(group_name +".csv"));
                Group group = (Group) is.readObject();

                if(group.containsInvite_code(invite_code)){
                    groups.add(group);
                    group.joinGroup(this, invite_code);
                    text.setText("Group joined" + group_name);
                } else{
                    System.out.println(invite_code);
                    System.out.println("Invite is invalid");
                    text.setText("Invite is invalid");
                }
                is.close();
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        } else{
            System.out.println("Group doesn't exist");
            text.setText("Group doesn't exist");
        }
    }

    public void leaveGroup(String group_name){
        for(Group group : this.groups){
            if(group.getName().equals(group_name)){
                this.groups.remove(group);
                group.removeMember(userName);
                break;
            }
        }
    }

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
//    public HashMap<String, Group> groups;
    public ArrayList<Group> groups;
    // HashMap of goals the user has active
    public HashMap<String, Goal> active_goals;
    // HashMap of goals the user has completed
    public HashMap<String, Goal> completed_goals;

    public static void main(String[] args) throws FileNotFoundException {
//        User user = new User("JT93", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
//                184, 77.6, "M");
//        System.out.println("Username: " + user.userName);
//        System.out.println("Full name: " + user.realName);
//        System.out.println("Email address: " + user.email);
//        System.out.println("Age: " + user.healthInformation.getAge());
//        System.out.println("Height: " + user.healthInformation.getHeight());
//        System.out.println("Weight: " + user.healthInformation.getWeight());
////        System.out.println("BMI: " + user.healthInformation.calculateBMI());
    }

}
