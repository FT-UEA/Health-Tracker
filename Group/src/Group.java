import java.util.*;
import javax.mail.*;
import java.util.regex.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;
import javax.activation.*;

public class Group {
    private String name;
    private User owner;
    private ArrayList<User> members;
    private ArrayList<Goal> goals;
    private ArrayList<Goal> completedGoals;
    private String code;

    Group(String name, User owner){
        this.name = name;
        this.owner = owner;
        members = new ArrayList<>();
        members.add(owner);
        goals = new ArrayList<>();
        completedGoals = new ArrayList<>();
       // String code = name.toLowerCase();
       // code = code.replaceAll("\\s");
       // System.out.println(code);

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
        String username = "willywantstoplay@gmail.com";
        String password = "set password";
        String to = email;

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
            message.setFrom(new InternetAddress("willywantstoplay@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Invite to " + name);
            message.setText("You have been invited to join " + name + " here is your invite code");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void joinGroup(User user ){  // temp join needs changing // error handling if already joied
        /*
        Invite is sent through email with invite code
        Need an ArrayList with all groups that currently exist
        Use invite code to scan and match with current group, join that group
        Done through user side
         */

        members.add(user); // temp solution

    }

    public void removeMember(String username){ // temp solution, removes user from list
        for(int i = 0; i < members.size(); i++){
            if(username.equals(members.get(i).getUserName())){
                System.out.println("User " + members.get(i).getUserName() + " has been removed");
                members.remove(i);
                break;
            }
        }


    }

    public static void main(String[] args) {
        User user = new User("Will");
        User user2 = new User("bob");
        Group group = new Group("Team alpha", user);
        group.joinGroup(user2);

       // group.invite("william.andrews314@gmail.com");
        System.out.println(group.getTotalMembers());
        group.removeMember("bob");
        System.out.println(group.getTotalMembers());


    }

}
