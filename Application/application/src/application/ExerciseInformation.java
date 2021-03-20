package application;

import java.util.HashMap;
import java.util.Scanner;

public class ExerciseInformation {

    public ExerciseInformation() {
        exerciseTypes = new HashMap<>();
        exerciseTypes.put("Walking", 3.8);
        exerciseTypes.put("Running", 9.8);
        exerciseTypes.put("Cycling", 9.5);
        exerciseTypes.put("Swimming", 8.0);
    }

    // Choose exercise type
    void chooseExercise() {
        System.out.println("Please enter exercise type");
        Scanner scan = new Scanner(System.in);
        String exercise = scan.nextLine();
        switch (exercise) {
            case "Walking" -> caloriesBurnt("Walking");
            case "Running" -> caloriesBurnt("Running");
            case "Cycling" -> caloriesBurnt("Cycling");
            case "Swimming" -> caloriesBurnt("Swimming");
            default -> {
                System.out.println("Exercise not found, add custom exercise? Enter Y/N");
                if (scan.nextLine().equals("Y")) {
                    customExerciseType(exercise);
                }
            }
        }
    }

    // Method to calculate exercise calories burnt
    private void caloriesBurnt(String exercise) {
        Scanner scan = new Scanner(System.in);
        int duration;
        double distance;
        int calories;
        System.out.println("Select exercise time or duration? Enter duration/distance");
        String response = scan.nextLine();
        if (response.equals("duration")) {
            duration = setTime();
            calories = (int)(duration * exerciseTypes.get(exercise));
            System.out.println("Calories burnt from " + exercise + ": " + calories);
        } else if (response.equals("distance")) {
            distance = setDistance();
            // Use 1.4m/s as a rough speed for all exercises, temporary
            calories = (int)((distance / 1.4) * exerciseTypes.get(exercise));
            System.out.println("Calories burnt from " + exercise + ": "
                    + calories);
        } else {
            caloriesBurnt(exercise);
        }
    }

    // Method to define exercise time
    public int setTime() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter exercise time in minutes");
        return scan.nextInt();
    }

    // Method to define exercise distance
    public double setDistance() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter distance in metres ");
        return scan.nextDouble();
    }

    // Add custom exercise type and MET doing it
    public void customExerciseType(String exercise) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter exercise Metabolic Equivalent of a Task (MET)");
        Double met = scan.nextDouble();
        exerciseTypes.put(exercise, met);
        System.out.println("Added " + exercise + " with a MET of " + met);
        caloriesBurnt(exercise);
    }

    // Array of exercise types
    private HashMap<String, Double> exerciseTypes;

    public static void main(String[] args) {
        ExerciseInformation exerciseInformation = new ExerciseInformation();
        exerciseInformation.chooseExercise();
    }
}
