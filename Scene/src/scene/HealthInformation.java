package scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HealthInformation {

    public HealthInformation(final double height, final double weight, final int age, final String gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
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

    public double getBmi() {
        return bmi;
    }

    public double getBmr() {
        return bmr;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    private final double height;
    private final double weight;
    private final int age;
    @FXML
    private double bmi = 0;
    @FXML
    private double bmr = 0;
    private String gender;

    // Method to calculate user BMI
    public void calculateBMI() {
        setBmi(100 * (getWeight() /
                Math.pow(getHeight() * 0.1, 2)));
    }

    // Method to calculate user BMR
    public void calculateBMR() {
        if (getGender().equals("M")) {
            setBmr(66.47 + (13.75 * getWeight()) +
                    (5.003 * getHeight()) -
                    (6.755 * getAge()));
        } else {
            setBmr(655.1 + (9.563 * getWeight()) +
                    (1.85 * getHeight())
                    - (4.676 * getAge()));
        }
    }

    public static void main(String[] args) {
//        HealthInformation hi = new HealthInformation(184, 77, 28);
//        hi.calculateBMI();
//        hi.calculateBMR();
    }
}
