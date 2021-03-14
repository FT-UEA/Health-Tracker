import java.text.DecimalFormat;
import java.util.Scanner;

public class HealthInformation {

    public HealthInformation(final double height, final double weight, final int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public void resetCalories() {
        remainingCalories = 0;
    }

    // Method to calculate user BMI
    public String calculateBMI() {
        DecimalFormat df = new DecimalFormat("##.#");
        double result = 100 * (height / Math.pow(height * 0.1, 2));
        System.out.println("Your BMI is: " + result);
        return df.format(result);
    }

    // Method to calculate user BMR
    public double calculateBMR() {
        Scanner scan = new Scanner(System.in);
        double bmr;
        System.out.println("What is your gender? Enter M/F");
        String gender = scan.nextLine();
        if (gender.equals("M")) {
            bmr = 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
        } else {
            bmr = 655.1 + (9.563 * weight) + (1.85 * height)
                    - (4.676 * age);
        }
        System.out.println("Your BMR is " + bmr);
        return bmr;
    }

    private final double height;
    private final double weight;
    private final int age;
    private double remainingCalories;

}
