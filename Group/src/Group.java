import java.awt.desktop.AppForegroundListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import javax.mail.*;
import java.util.regex.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;
import javax.activation.*;

public class Group implements Serializable {
    private String name;
    private User owner;
    private ArrayList<User> members;
    private ArrayList<Goal> goals;
    private ArrayList<Goal> completedGoals;
    private ArrayList<String> invite_codes;

    Group(String name, User owner){
        this.name = name;
        this.owner = owner;
        members = new ArrayList<>();
        members.add(owner);
        goals = new ArrayList<>();
        completedGoals = new ArrayList<>();
        invite_codes = new ArrayList<>();
        save();

    }

    public String getName(){
        return name;
    }

    public User getOwner(){
        return owner;
    }

    public int getTotalMembers(){
        return members.size();
    }

    public int getTotalGoals(){ return goals.size();}

    public int getTotalCompletedGoals(){ return completedGoals.size();}

    public Goal getGoal(int index){ return goals.get(index);}

    public Goal getCompletedGoal(int index){ return completedGoals.get(index);}

    public User getMember(int index){ return members.get(index);}

    public ArrayList<Goal> getGoalList(){
        ArrayList<Goal> goals_out = new ArrayList<>();
        goals_out.addAll(goals);
        return goals_out;
    }

    public ArrayList<Goal> getCompletedGoalList(){
        ArrayList<Goal> completed_goals_out = new ArrayList<>();
        completed_goals_out.addAll(completedGoals);
        return completed_goals_out;
    }

    public ArrayList<User> getMembersList(){
        ArrayList<User> members_out = new ArrayList<>();
        members_out.addAll(members);
        return members_out;
    }



    public void invite(String email){
        String username = "noreplyapphealth45@gmail.com";
        String password = "Health!23";
        String to = email;
        String alpha_numeric = name;
        alpha_numeric = alpha_numeric + "_" + getAlphaNumericString(10);
        invite_codes.add(alpha_numeric);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Invite to " + name);
            message.setText("You have been invited to join " + name + " here is your invite code " + "\"" +  alpha_numeric + "\"");

            Transport.send(message);

            System.out.println("Done");
            save();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    public void joinGroup(User user, String invite_code ){  // temp join needs changing // error handling if already joied
        load();
        members.add(user); // temp solution
        invite_codes.remove(invite_code);
        save();

    }

    public void removeMember(String username){ // temp solution, removes user from list
        System.out.println("this user:" + username);
        for(int i = 0; i < members.size(); i++){
            if(username.equals(members.get(i).getUserName())){
                System.out.println("User " + members.get(i).getUserName() + " has been removed");
                members.remove(i);
                break;
            }
        }

    }


    public boolean containsInvite_code(String code) {
        if(invite_codes.contains(code)){
            return true;
        } else{
            return false;
        }
    }

    public void save() {

        File f = new File(name + ".csv");

        if(f.exists()){
           // error handling here
        }

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(name + ".csv"));
            os.writeObject(this);
            os.close();
        } catch (IOException e ){
            e.printStackTrace();
        }

        System.out.println("saved file");

    }


    public void load() {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.name + ".csv"));
            Group group = (Group) is.readObject();

            this.members = group.members;
            this.goals = group.goals;
            this.completedGoals = group.completedGoals;
            this.invite_codes = group.invite_codes;

            is.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }


    public void addGoal(){
        load();
        goals.add(new Goal());
        save();

    }

    public void addCompletedGoal(Goal completed){
        load();
        completedGoals.add(completed);
        goals.remove(completed);
        save();

    }

    public String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }





    // old save with counter for files
//    public void save() {
//        int count = 1;
//        String filename = "groups ";
//        File f = new File(filename + count + ".csv");
//
//        System.out.println(f.exists());
//        while(f.exists()){
//            count++;
//            f = new File(filename + count + ".csv");
//        }
//
//
//        try{
//            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename + count + ".csv"));
//            os.writeObject(this);
//            os.close();
//        } catch (IOException e ){
//            e.printStackTrace();
//        }
//
//        this.id = count;
//        this.join_id = count + "_" +  name;
//        System.out.println(this.join_id);
//        System.out.println("saved file");
//
//    }

}
