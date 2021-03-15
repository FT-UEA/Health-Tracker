package com.Goal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Goal {
    private float weight;
    private LocalDate gD;
    LocalDate date2 = LocalDate.now();

    //arrays for goals
    String[] goals = new String[4];
    ArrayList<String[]> goalList = new ArrayList<>();

    public Goal(){
        setWeight();
        setDate();
        goalSaver();
        runningGoal();
        goalCheck();
    }

    public void setWeight() {
        System.out.print("Enter a weight goal in kg: ");
        Scanner scanner = new Scanner(System.in);
        this.weight = scanner.nextFloat();
    }

    public void setDate() {
        System.out.println("Enter date to complete this goal (yyyy-mm-dd): ");
        Scanner scan=new Scanner(System.in);
        String date=scan.next();
        this.gD = LocalDate.parse(date);
    }

    public void goalSaver(){
        //save goal in array
        String goalDate = gD.toString();
        String tempWeight = String.valueOf(weight);
        goals[0] = goalDate;
        goals[1] = tempWeight;
        //save array to arrayList
        goalList.add(goals);
    }

    public void printAllGoals(){
        for (String[] strings : goalList) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public void goalCheck() {
        //checks users progress towards goal
        System.out.println("Enter you current weight: ");
        Scanner sc = new Scanner(System.in);
        float currentWeight = sc.nextFloat();
        LocalDate date1 = LocalDate.parse(goals[0]);
        float w = Float.parseFloat(goals[1]);
        if (date1.isAfter(date2)) {
            if(currentWeight == w){
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("But you have exceeded you goal date\n");
            }else{
                System.out.println("Unfortunately, you have not met your goal " + date1 + " " + date2);
            }
        }
        if (date1.isBefore(date2)) {
            if(currentWeight == w){
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("You've beaten your date, why not set another goal\n");
            }else{
                System.out.println("Unfortunately, you have not met your goal carry on you still have time\n");
            }
        }
        if (date1.isEqual(date2)) {
            if(currentWeight == w){
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("Just in time\n");
            }else{
                System.out.println("Your goal us due today");
                System.out.println("Unfortunately, you have not met your goal\n");
            }
        }
        //asks if user wants to set another goal
        String ans;
        boolean repeat = true;
        while (repeat) {
            System.out.println("Do you want to set new goal?");
            Scanner scanner = new Scanner(System.in);
            ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("Yes")) {
                new Goal();
                repeat = false;
            } else if (ans.equalsIgnoreCase("No")) {
                System.out.println("Go smash those goals");
                repeat = false;
            } else {
                System.out.println("Invalid try again ...");
            }
        }
    }

    public void runningGoal(){
        System.out.println("Would you like to enter a running goal? ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Yes")){
            //get user input dist and time
            System.out.println("Enter distance ");
            new Scanner(System.in);
            double dist = scanner.nextDouble();
            System.out.println("Enter time (HH:mm:ss) ");
            Scanner sc = new Scanner(System.in);
            //store dist and time
            String time = sc.nextLine();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(time, df);
            System.out.println("Distance set for: " + dist + " Time set for: " + localTime );
            //convert to string and store in array
            String str = Double.toString(dist);
            String goalTime = localTime.toString();
            goals[2] = str;
            goals[3] = goalTime;
        }
        else{
            goals[2] = null;
            goals[3] = null;
        }
    }

    public static void main(String[] args){
        Goal g = new Goal();
        g.printAllGoals();
    }
}
