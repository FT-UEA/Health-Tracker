import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Scanner;

public class Database {
    boolean exists;
    User loadedUser;


    public void loadGroups(User user){
        try {
            for(int i = 0; i < user.getGroupSize(); i++){
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(user.getGroup(i).getName() + ".csv"));
                System.out.println(user.getGroup(i).getTotalMembers());
                Group loadedGroup = (Group) is.readObject();
                user.setGroup(i, loadedGroup);
                System.out.println(user.getGroup(i).getTotalMembers());
                is.close();

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveToFile(User user) {
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream( user.name ));
            os.writeObject(user);
            os.close();
            System.out.println("\nSuccessfully saved! ");
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public User load(String userName) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(userName));
            loadedUser = (User) is.readObject();
            System.out.println("\nSuccessfully Loaded! ");
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedUser;
    }

    public void saveEmail(String email) {
        try{
            FileWriter fw = new FileWriter("userEmails.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(email);
            bw.newLine();
            bw.close();

        } catch(IOException e){
            e.printStackTrace();

        }

    }

    public boolean containsEmail(String email) {
        boolean contain = false;
        try{
            File file = new File("userEmails.txt");
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                if(email.equals(data)){
                    contain = true;
                    break;
                }
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return contain;
    }

    public boolean userExists(String userName){
        File f = new File(userName );
        exists = f.exists(); // stores true or false in exists

        return exists;
    }

}
