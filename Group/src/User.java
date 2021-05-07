import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {
    String name;
    public ArrayList<Group> groups = new ArrayList<>();
    private String email;
    HashMap<String, Double> goal = new HashMap<>();

    User(String name ,String email){
        this.name = name;
        this.email = email;
    }

    User(String name){
        this.name = name;
    }

    public String getUserName(){
        return name;
    }

    public int getGroupSize(){ return groups.size();}

    public Group getGroup(int i){
        return groups.get(i);
    }

    public String getEmail() {
        return email;
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

    public void joinGroup(String invite_code) {

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
                } else{
                    System.out.println(invite_code);
                    System.out.println("Invite is invalid");
                }
                is.close();
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        } else{
            System.out.println("group doesn't exist");
        }

    }

    public void leaveGroup(String group_name){
        for(Group group : this.groups){
            if(group.getName().equals(group_name)){
                this.groups.remove(group);
                group.removeMember(name);
                break;
            }
        }
    }

    public void addGroupGoal(String serializedObject) {
        Goal goal = null;
        try {
            byte b[] = Base64.getDecoder().decode(serializedObject.getBytes());
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            goal = (Goal) si.readObject();

            //add goal to user goal array or hashmap or whatever

            System.out.println(goal.bob);
            System.out.println(goal.dog);
            System.out.println(goal.hello);
            System.out.println(goal.array);


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendCompletionEmail(String goal_name, String from){
        ArrayList<User> arrayListUser = new ArrayList<>();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(from + ".csv"));
            Group group = (Group) is.readObject();
            group.sendCompletionEmail(goal_name, this.email);
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }

    }

}
