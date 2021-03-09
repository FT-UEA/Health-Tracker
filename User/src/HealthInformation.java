import java.text.DecimalFormat;

public class HealthInformation {

    public HealthInformation(final double height, final double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    private final double height;
    private final double weight;

}
