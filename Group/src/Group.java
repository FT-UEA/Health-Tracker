import java.util.*;
public class Group {
    private String name;
    private User owner;
    private ArrayList<User> members;
    private ArrayList<Goal> goals;
    private ArrayList<Goal> completedGoals;

    Group(String name, User owner){
        this.name = name;
        this.owner = owner;
        members = new ArrayList<>();
        members.add(owner);
        goals = new ArrayList<>();
        completedGoals = new ArrayList<>();

    }

    public String getName(){
        return name;
    }

    public User getOwner(){
        return owner;
    }

    public void createGoal(){
       goals.add(new Goal());

    }

    public int getTotalMembers(){
        return members.size();
    }

    public void invite(String email){

    }
    public void removeMember(User user){

    }

   /* public void getGroupFeedBack(){

    } */


}
