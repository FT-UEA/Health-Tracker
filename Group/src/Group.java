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
import javax.xml.crypto.Data;

public class Group implements Serializable {
    private String name;
    private User owner;
    private ArrayList<User> members;
    private ArrayList<String> invite_codes;

    Group(String name, User owner){

            this.name = name;
            this.owner = owner;
            members = new ArrayList<>();
            members.add(owner);

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

    public User getMember(int index){ return members.get(index);}

    public ArrayList<User> getMembersList(){
        ArrayList<User> members_out = new ArrayList<>();
        members_out.addAll(members);
        return members_out;
    }

    public void invite(String email){
        Database database = new Database();
        if(database.containsEmail(email)){
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

        } else{
            System.out.println("Sorry email isnt registered to any known user");
        }



    }

    public void joinGroup(User user, String invite_code ){
        load();
        members.add(user);
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
            this.invite_codes = group.invite_codes;

            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }


    public void createGoalWeight(String goalName, double userWeight, double goalWeight, String dateIn) {

        Goal goal = new Goal(goalName, userWeight, goalWeight, dateIn);
        goal.setFrom(this.name);
        String redisString = "";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(goal);
            so.flush();
            redisString = new String(Base64.getEncoder().encode(bo.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String username = "noreplyapphealth45@gmail.com";
        String password = "Health!23";

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
            Address[] emails = new Address[members.size()];
            for (int i = 0; i < members.size(); i++) {
                System.out.println(members.size());
                System.out.println(members.get(i).getEmail());
                emails[i] = new InternetAddress(members.get(i).getEmail());
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipients(Message.RecipientType.TO, emails);
            message.setSubject("Group goal: " + goalName + " has been created! ");
            message.setText("A goal has been created for all the group members to complete! Copy the text" +
                    " below to add it to your local goal. Good Luck! \n \n \n \n" + "\"" + redisString + "\"");

            Transport.send(message);

            System.out.println("Done");
            save();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void createGoalExercise(String goalName, double distance, String dateIn, String goalTime) {

        Goal goal = new Goal(goalName, distance, dateIn, goalTime);
        String redisString = "";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(goal);
            so.flush();
            redisString = new String(Base64.getEncoder().encode(bo.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String username = "noreplyapphealth45@gmail.com";
        String password = "Health!23";

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
            Address[] emails = new Address[members.size()];
            for (int i = 0; i < members.size(); i++) {
                System.out.println(members.size());
                System.out.println(members.get(i).getEmail());
                emails[i] = new InternetAddress(members.get(i).getEmail());
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipients(Message.RecipientType.TO, emails);
            message.setSubject("Group goal: Goalnamegoeshere has been created! ");
            message.setText("A goal has been created for all the grouop members to complete! Copy the text" +
                    " below to add it to your local goal. Good Luck! \n \n \n \n" + "\"" + redisString + "\"");

            Transport.send(message);

            System.out.println("Done");
            save();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendCompletionEmail(String goal_name, String email){
        String username = "noreplyapphealth45@gmail.com";
        String password = "Health!23";

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
            String name = "";
            Address[] emails = new Address[members.size()];
            for(int i = 0; i < members.size(); i++){
                emails[i] = new InternetAddress(members.get(i).getEmail());
                if(members.get(i).getEmail().equals(email)){
                    name = members.get(i).getUserName();
                }
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipients(Message.RecipientType.TO, emails);
            message.setSubject(name + " has completed " + goal_name + "!");
            message.setText("Congradulations to " + name + " for reaching their goal! It's your turn now!");
            Transport.send(message);


            save();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
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


}
