package application;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Goal {
    private final double weight;
    private final LocalDate date1;
    private final LocalDate date2 = LocalDate.now();
    public boolean isComplete;
    public String goalName;

    //constructor
    public Goal(String goalName, double weight, String date) {
        this.goalName = goalName;
        this.weight = weight;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        this.date1 = LocalDate.parse(date, formatter);
        exerciseGoal();
    }

    public void goalCheck(double currentWeight) {
        if (date1.isAfter(date2)) {
            if (currentWeight == weight) {
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("But you have exceeded you goal date\n");
                isComplete = true;
            } else {
                System.out.println("Unfortunately, you have not met your goal " + date1 + " " + date2);
                System.out.println("Would you like to mark this goal as complete ");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("Yes")) {
                    isComplete = true;
                } else {
                    System.out.println("\nOk we'll keep that goal");
                }
            }
        }
        if (date1.isBefore(date2)) {
            if (currentWeight == weight) {
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("You've beaten your date, why not set another goal\n");
                isComplete = true;
            } else {
                System.out.println("Unfortunately, you have not met your goal carry on you still have time\n");
            }
        }
        if (date1.isEqual(date2)) {
            if (currentWeight == weight) {
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("Just in time\n");
                isComplete = true;
            } else {
                System.out.println("Your goal us due today");
                System.out.println("Unfortunately, you have not met your goal\n");
                System.out.println("Would you like to mark this goal as complete ");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("Yes")) {
                    isComplete = true;
                } else {
                    System.out.println("\nOk we'll keep that goal");
                }
            }
        }
        if (isComplete) {
            System.out.println("This goal is complete");
        } else {
            System.out.println("Goal is not complete");
        }
    }

    public void exerciseGoal() {
        Scanner scanner = new Scanner(System.in);
        //get user input dist and time
        System.out.println("Enter distance ");
        double dist = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter time (HH:mm:ss) ");
        //store dist and time
        String time = scanner.nextLine();
        //checks if the time is valid and stores it or outputs error
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(time, df);
            System.out.println("Distance set for: " + dist + " Time set for: " + localTime + "\n");
        } catch (DateTimeException e) {
            System.err.println("Invalid date, Make sure you enter the correct date format\n");
        }
    }

    public static void main(String[] args) {
        Goal g = new Goal("blah", 77, "20-03-21");
    }
}



