package Days.Day1;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.Arrays;

public class DistanceCalculatorPartTwo {

    private InputManipulatable<Integer[][]> manipulatedInput;
    private int distance;

    public DistanceCalculatorPartTwo() throws IOException {
        this.manipulatedInput = new StringFormatter();
        this.distance = calculateDistance(this.manipulatedInput.getFormattedInput());
    }

    private int calculateDistance(Integer[][] input) {
        //Finds similar appearances and multiplies them...after that summarizes
        int sum = 0;
        int[] alreadyCheckedNumbers = new int[manipulatedInput.getSize()];
        for (int i = 0; i < manipulatedInput.getSize(); i++) {
            if (Arrays.binarySearch(alreadyCheckedNumbers, input[i][0]) >= 0) {
                continue;
            }
            alreadyCheckedNumbers[i] = input[i][0];
            //Find all similar numbers on left side
            int foundNumber = alreadyCheckedNumbers[i];
            int sameNumber = findSimilarNumbers(i, input, foundNumber);
            int sameMultipliers = findMultipliers(input, foundNumber);
            sum += foundNumber * sameNumber * sameMultipliers;
        }
        return sum;
    }

    private int findMultipliers(Integer[][] input, int foundNumber) {
        int multipliers = 0;
        for (int i = 0; i < manipulatedInput.getSize(); i++) {
            //If number is found a second loop will count the occurrences and return the int
            if (input[i][1] == foundNumber) {
                multipliers++;
                for (int j = i + 1; j < manipulatedInput.getSize(); j++) {
                    if (input[j][1] != foundNumber) {
                        return multipliers;
                    }
                    multipliers++;
                }
            }
        }
        return multipliers;
    }

    private int findSimilarNumbers(int index, Integer[][] input, int foundNumber) {
        int appearance = 0;
        for (int i = index; i < manipulatedInput.getSize(); i++) {
            if (input[i][0] == foundNumber) {
                appearance++;
                continue;
            }
            break;
        }
        return appearance;
    }

    public int getDistance() {
        return this.distance;
    }

    public static void main(String[] args) throws IOException {
        DistanceCalculatorPartTwo distanceCalculator = new DistanceCalculatorPartTwo();
        System.out.println(distanceCalculator.getDistance());
    }
}
