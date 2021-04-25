package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Group {
    private String name;
    private User owner;
    private ArrayList<User> members;
    private ArrayList<Goal> goals;
    private ArrayList<Goal> completedGoals;
    private String code;

    Group(String name, User owner) {
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

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public void createGoal() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter goal name");
        String goalName = scan.nextLine();
        goals.add(new Goal(goalName));
    }

    public int getTotalMembers() {
        return members.size();
    }

    public void invite(String email) {
        String username = "willywantstoplay@gmail.com";
        String password = "set password";

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
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Invite to " + name);
            message.setText("You have been invited to join " + name + " here is your invite code");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void joinGroup(User user) {  // temp join needs changing // error handling if already joied
        /*
        Invite is sent through email with invite code
        Need an ArrayList with all groups that currently exist
        Use invite code to scan and match with current group, join that group
        Done through user side
         */

        members.add(user); // temp solution

    }

    public void removeMember(String username) { // temp solution, removes user from list
        for (int i = 0; i < members.size(); i++) {
            if (username.equals(members.get(i).getUserName())) {
                System.out.println("User " + members.get(i).getUserName() + " has been removed");
                members.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        User user = new User("JT93", "Josh Thomson", "JoshT626@hotmail.co.uk", 28,
//                184, 77.6);
//        User user2 = new User("BB99", "Billy Bob", "billy_bob@hotmail.co.uk", 25,
//                178, 75);
//        Group group = new Group("Team alpha", user);
//        group.joinGroup(user2);
//
//        // group.invite("william.andrews314@gmail.com");
//        System.out.println(group.getTotalMembers());
//        group.removeMember("bob");
//        System.out.println(group.getTotalMembers());
    }

    public void changeScreenLoggedOut(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Logged Out.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void changeScreenDashboardNoCreate(ActionEvent event) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Personal Dashboard.fxml"));
        Scene loginScene = new Scene(loginRoot);
        // This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

}
