import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Goal {
    private float weight;
    private LocalDate date1;
    private final LocalDate date2 = LocalDate.now();
    private boolean isComplete;
    private double distance;
    private LocalTime time;

    public Goal(){
        setWeight();
        setDate();
        runningGoal();
        goalCheck();
    }

    public void setWeight() {
        System.out.print("Enter a weight goal in kg: ");
        Scanner scanner = new Scanner(System.in);
        this.weight = scanner.nextFloat();
    }

    public void setDate() {
        System.out.println("Enter date to complete this goal (dd-mm-yy): ");
        Scanner scan=new Scanner(System.in);
        String date=scan.next();
        //checks if the date is valid and stores or outputs error
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
            this.date1 = LocalDate.parse(date, formatter);
        }catch (DateTimeException e){
            System.err.println("Invalid date, Make sure you enter the correct date format\n");
        }
    }

    public float getWeight(){
        return weight;
    }

    public LocalDate getDate() {
        return date1;
    }

    public double getDistance(){
        return distance;
    }

    public LocalTime getTime(){
        return time;
    }

    public boolean getStatus() {
        return isComplete;
    }

    public void goalCheck() {
        System.out.println("Enter you current weight: ");
        Scanner sc = new Scanner(System.in);
        float currentWeight = sc.nextFloat();
        if (date1.isAfter(date2)) {
            if(currentWeight == getWeight()){
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("But you have exceeded you goal date\n");
                isComplete = true;
            }else{
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
            if(currentWeight == getWeight()){
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("You've beaten your date, why not set another goal\n");
                isComplete = true;
            }else{
                System.out.println("Unfortunately, you have not met your goal carry on you still have time\n");
            }
        }
        if (date1.isEqual(date2)) {
            if(currentWeight == getWeight()){
                System.out.println("Congratulations you've hit your weight goal!");
                System.out.println("Just in time\n");
                isComplete = true;
            }else{
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
        if (isComplete){
            System.out.println("This goal is complete");
        }else{
            System.out.println("Goal is not complete");
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
            this.distance = dist;
            System.out.println("Enter time (HH:mm:ss) ");
            Scanner sc = new Scanner(System.in);
            //store dist and time
            String time = sc.nextLine();
            //checks if the time is valid and stores it or outputs error
            try{
                DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime = LocalTime.parse(time, df);
                this.time = localTime;
                System.out.println("Distance set for: " + dist + " Time set for: " + localTime + "\n");
            }catch (DateTimeException e){
                System.err.println("Invalid date, Make sure you enter the correct date format\n");
            }
        }
    }

    public void setNewGoal(){
        String ans;
        boolean repeat = true;
        while (repeat) {
            System.out.println("Do you want to set new goal?");
            Scanner scanner = new Scanner(System.in);
            ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("Yes")) {
                new Goal();
                repeat = false;
            }
            else if (ans.equalsIgnoreCase("No")) {
                System.out.println("Go smash those goals");
                repeat = false;
            }else{
                System.out.println("\nInvalid try again ... \n");
            }
        }
    }

    public static void main(String[] args){
        Goal goal = new Goal();
        goal.setNewGoal();
    }
}



