package Days.Day1.PartOne;

import Days.Day1.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;

public class DistanceCalculatorPartOne {

    private InputManipulatable<Integer[][]> manipulatedInput;
    private int distance;

    public DistanceCalculatorPartOne() throws IOException {
        this.manipulatedInput = new InputFormatter();
        this.distance = calculateDistance(this.manipulatedInput.getFormattedInput());
    }

    private int calculateDistance(Integer[][] input) {
        //Summarizes the Distance
        int distance = 0;
        for (int i = 0; i < manipulatedInput.getSize(); i++) {
            if (input[i][0] > input[i][1]) {
                distance += (input[i][0] - input[i][1]);
            } else {
                distance += (input[i][1] - input[i][0]);
            }
        }
        return distance;
    }

    public int getDistance() {
        return this.distance;
    }

    public static void main(String[] args) throws IOException {
        DistanceCalculatorPartOne distanceCalculator = new DistanceCalculatorPartOne();
        System.out.println(distanceCalculator.getDistance());
    }
}
