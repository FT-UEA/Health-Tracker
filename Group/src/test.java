import java.util.*;
public class test {

    public static void main(String[] args) {
        ArrayList<Group> groupArrayList = new ArrayList<>();
        Group group1 = new Group("Phantom Troupe", new User("Chrollo Lucifer") );
        Group group2 = new Group("Hunter Association", new User("Chairman Netero") );
        groupArrayList.add(group1);
        groupArrayList.add(group2);
        

    }
}
