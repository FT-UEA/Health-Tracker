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
    private void chooseExercise() {
        System.out.println("Please enter exercise type");
        Scanner scan = new Scanner(System.in);
        String exercise = scan.nextLine();
        switch (exercise) {
            case "Walking":
                caloriesBurnt("Walking");
                break;
            case "Running":
                break;
            case "Cycling":
                break;
            case "Swimming":
                break;
            default:
                System.out.println("Exercise not found, add custom exercise? Enter Y/N");
                if (scan.nextLine().equals("Y")) {
                    customExerciseType();
                }
                break;
        }
    }

    // Method to calculate exercise calories burnt
    private void caloriesBurnt(String exercise) {
        Scanner scan = new Scanner(System.in);
        int duration;
        double distance;
        System.out.println("Select exercise time or duration? Enter duration/distance");
        if (scan.nextLine().equals("duration")) {
            duration = setTime();
            System.out.println("Calories burnt from " + exercise + ": " + duration * exerciseTypes.get(exercise));
        } else if (scan.nextLine().equals("distance")) {
            distance = setDistance();
            // Use 1.4m/s as a rough speed for all exercises, temporary?
            System.out.println("Calories burnt from " + exercise + ": "
                    + (distance / 1.4) * exerciseTypes.get(exercise));
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
    public void customExerciseType() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter exercise name");
        String exercise = scan.nextLine();
        System.out.println("Please enter exercise Metabolic Equivalent of a Task (MET)");
        Double met = scan.nextDouble();
        exerciseTypes.put(exercise, met);
        System.out.println("Added " + exercise + " with a MET of " + met);
    }

    // Choose distance/time or both

    // Calculate calories burnt

    // Array of exercise types
    private HashMap<String, Double> exerciseTypes;
}
