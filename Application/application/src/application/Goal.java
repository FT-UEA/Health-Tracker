package application;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Goal {
    public double weight;
    public LocalDate goalDate;
    public LocalTime goalTime;
    public double distance;
    public boolean isComplete;
    public String goalName;
    public String type;

    LocalDate date2 = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalTime timeSet;

    //constructor
    public Goal(String goalName, double weight) {
        this.goalName = goalName;
        this.weight = weight;
        chooseGoal();
    }

    public void chooseGoal() {
        System.out.println("Weight");
        System.out.println("Exercise");
        System.out.println("Choose which goal you would like to set: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Weight")) {
            type = "weight";
            setWeightGoal();
        }
        if (choice.equalsIgnoreCase("Exercise")) {
            type = "exercise";
            setExerciseGoal();
        }

    }

    public void setWeightGoal() {
        System.out.print("Enter a weight goal in kg: ");
        Scanner scanner = new Scanner(System.in);
        this.weight = scanner.nextFloat();
        System.out.println("Enter date to complete this goal (dd-mm-yyyy): ");
        Scanner scan = new Scanner(System.in);
        String dateIn = scan.next();
        //checks if the date is valid and stores or outputs error
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateIn, formatter);
            if (date.isBefore(date2)) {
                System.out.println("Unfortunately this date is before the current date, cannot set");
                System.out.println("try again\n");
                setWeightGoal();
            } else {
                this.goalDate = date;
            }
        } catch (DateTimeException e) {
            System.err.println("Invalid date, Make sure you enter the correct date format\n");
        }
    }

    public void checkWeightGoal(double currentWeight) {
        if (goalDate.isAfter(date2)) {
            if (currentWeight == weight) {
                System.out.println("\nCongratulations you've hit your weight goal!");
                System.out.println("With time left to spare");
                isComplete = true;
            } else {
                System.out.println("\nYou have not hit your weight but keep going there's still time!");
            }
        }
        if (goalDate.isEqual(date2)) {
            if (currentWeight == weight) {
                System.out.println("\nCongratulations you've hit your weight goal!");
                System.out.println("Just in time");
                isComplete = true;
            } else {
                System.out.println("\nYour goal us due today");
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
        if (goalDate.isBefore(date2)) {
            System.out.println("\nYou have exceeded your goal date, deleting this goal");
            isComplete = true;
        }
        if (isComplete) {
            System.out.println("\nThis goal is complete");
        } else {
            System.out.println("\nGoal is not complete");
        }
    }

    public void setExerciseGoal() {
        //get user input dist and time
        System.out.println("Enter distance ");
        Scanner scanner = new Scanner(System.in);
        double dist = scanner.nextDouble();
        this.distance = dist;
        System.out.println("Enter time (HH:mm:ss) ");
        Scanner sc = new Scanner(System.in);
        //store dist and time
        String time = sc.nextLine();
        //checks if the time is valid and stores it or outputs error
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(time, df);
            this.goalTime = localTime;
            System.out.println("Distance set for: " + dist + " Time set for: " + localTime + "\n");
        } catch (DateTimeException e) {
            System.err.println("Invalid date, Make sure you enter the correct date format\n");
        }
        this.timeSet = LocalTime.now();
    }

    public void checkExerciseGoal(double currentDistance) {
        //calculating time passed since goal set
        long hourToAdd = goalTime.getHour();
        long minToAdd = goalTime.getMinute();
        long secondsToAdd = goalTime.getSecond();
        LocalTime timePassed = timeSet.plusHours(hourToAdd).plusMinutes(minToAdd).plusSeconds(secondsToAdd).withNano(0);
        //check status of goal
        if (timePassed.isAfter(currentTime)) {
            if (currentDistance == distance) {
                System.out.println("You ran your distance but did not hit your time");
                isComplete = true;
            }
            if (currentDistance > distance) {
                System.out.println("You went further than your distance but didn't hit your time");
            } else {
                System.out.println("Unfortunately you have failed this goal, time not met, distance not met");
            }
            isComplete = true;
        }
        if (timePassed.isBefore(currentTime)) {
            if (currentDistance == distance) {
                System.out.println("Well done you've hit your distance and beaten your time");
                isComplete = true;
            }
            if (currentDistance > distance) {
                System.out.println("Well done you went further than your distance and beat your time");
                isComplete = true;
            } else {
                System.out.println("Your times not up keep going you haven't met your distance");
            }
        } else {//is equal time
            if (currentDistance == distance) {
                System.out.println("Well done you've hit your time and distance");
                isComplete = true;
            }
            if (currentDistance > distance) {
                System.out.println("Well done you went further than your distance and hit your time");
                isComplete = true;
            }
        }
        if (isComplete) {
            System.out.println("\nThis goal is complete");
        } else {
            System.out.println("\nGoal is not complete");
        }
    }
}



