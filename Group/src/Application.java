import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);


//noreplyapphealth45@gmail.com

        // scan only works for 1 word in this test

        System.out.println("Create User: ");
        String name = scan.next();
        User user = new User(name);
        while(true){
            Scanner scan_int = new Scanner(System.in);
            System.out.println("1. Create Group ");
            System.out.println("2. Join Group ");
            System.out.println("3. Create goal for group");
            System.out.println("4. Leave group");
            int input = scan_int.nextInt();
            if(input == 1){
                System.out.println(user.getGroupSize());
                System.out.println("Group name: ");
                String group_name = scan.next();
                user.createGroup(group_name);
                System.out.println("Created group");
                System.out.println("Send invite to email: ");
                user.getGroup(0).invite("noreplyapphealth45@gmail.com");
                System.out.println("restart with new user to test invite");
                System.out.println(user.getGroupSize());
                System.out.println("--------------");
            } else if(input ==2){
                System.out.println("enter code: ");
                String code = scan.next();
                user.joinGroup(code);
                System.out.println(user.getGroup(0).getName());
                System.out.println(user.getGroup(0).getTotalMembers());
                System.out.println(user.getGroup(0).getOwner().getUserName());

            } else if(input == 3){
                // entering name for demo, in final program we shouldnt need to enter a name (hopefully)

                System.out.println("Enter Group name: ");
                String group_name = scan.next();
                Group group = user.getGroup(group_name);
                System.out.println("Goals before: " + group.getTotalGoals());
                group.addGoal();
                System.out.println("Goals after: " + group.getTotalGoals());

            } else if (input == 4) {
                // entering name for demo, in final program we shouldnt need to enter a name (hopefully)

                System.out.println("Enter Group name: ");
                String group_name = scan.next();
                Group group = user.getGroup(group_name);
                System.out.println("Group members before: " + group.getTotalMembers());
                System.out.println("User groups before: " + user.getGroupSize());
                user.leaveGroup(group_name);
                System.out.println("Group members after: " + group.getTotalMembers());
                System.out.println("User groups after: " + user.getGroupSize());


            }

        }











    }


}

