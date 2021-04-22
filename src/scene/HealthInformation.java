package scene;

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

    // Method to calculate user BMI
    public String calculateBMI() {
        DecimalFormat df = new DecimalFormat("##.#");
        double result = 100 * (weight / Math.pow(height * 0.1, 2));
        System.out.println("Your BMI is: " + df.format(result));
        return df.format(result);
    }

    // Method to calculate user BMR
    public void calculateBMR() {
        DecimalFormat df = new DecimalFormat("##.#");
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
        System.out.println("Your BMR is " + df.format(bmr));
    }

    private final double height;
    private final double weight;
    private final int age;

    public static void main(String[] args) {
        HealthInformation hi = new HealthInformation(184, 77, 28);
        hi.calculateBMI();
        hi.calculateBMR();
    }
}
