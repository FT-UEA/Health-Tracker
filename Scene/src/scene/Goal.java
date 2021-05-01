package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    public double goalWeight;

    LocalDate date2 = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalTime timeSet;

    // Constructor for groups
    public Goal(String goalName) {
        this.goalName = goalName;
    }

    // Constructor for user
    public Goal(String goalName, double userWeight, double goalWeight, String dateIn) {
        this.goalName = goalName;
        this.weight = userWeight;
        this.goalWeight = goalWeight;
        System.out.println(dateIn);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.goalDate = LocalDate.parse(dateIn, formatter);
        this.type = "weight";
    }

    public Goal(String goalName, double distance, String dateIn) {
        this.goalName = goalName;
        this.distance = distance;
        System.out.println(dateIn);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.goalDate = LocalDate.parse(dateIn, formatter);
        this.type = "exercise";
    }

    public String getType() {
        return type;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void checkWeightGoal(Text text, double currentWeight) {
        text.setText("aisufdhasifujhaf");
        if (goalDate.isAfter(date2)) {
            if (currentWeight == weight) {
                text.setText("Congratulations you've hit your weight goal!");
                isComplete = true;
            } else {
                text.setText("You have not hit your weight but keep going there's still time!");
            }
        }
        if (goalDate.isEqual(date2)) {
            if (currentWeight == weight) {
                text.setText("Congratulations you've hit your weight goal!");
                isComplete = true;
            } else {
                text.setText("Unfortunately, you have not met your goal.");
//                System.out.println("Would you like to mark this goal as complete ");
//                Scanner scanner = new Scanner(System.in);
//                String response = scanner.nextLine();
//                if (response.equalsIgnoreCase("Yes")) {
//                    isComplete = true;
//                } else {
//                    System.out.println("\nOk we'll keep that goal");
//                }
            }
        }
        if (goalDate.isBefore(date2)) {
            text.setText("You have exceeded your goal date, deleting this goal");
            isComplete = true;
        }
        if (isComplete) {
            text.setText("This goal is complete");
        } else {
            text.setText("This goal is not complete");
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

    public void checkExerciseGoal(Text text, double currentDistance) {
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

    public void changeScreenSettings(ActionEvent event) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Settings.fxml"));
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



