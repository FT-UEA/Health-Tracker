import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {
    private String name;
    public ArrayList<Group> groups = new ArrayList<>();

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

/*    public void loadGroup(String group_name){ // used to keep the group updated in the user
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.name + ".csv"));
            Group group = (Group) is.readObject();

            for(int i = 0; i < groups.size(); i++){
                if(groups.get(i).getName().equals(group.getName())){
                    groups.add(i, group);
                    break;
                }
            }
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }*/

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
}
