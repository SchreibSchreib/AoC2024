package Days.Day2.PartOne;

import Days.Day2.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;

public class ReportAnalyzer {

    private InputManipulatable<Integer[][]> manipulatedInput;
    private int numberOfSafeReports;

    public ReportAnalyzer() throws IOException {
        this.manipulatedInput = new InputFormatter();
        this.numberOfSafeReports = validateReports(this.manipulatedInput.getFormattedInput());
    }

    private int validateReports(Integer[][] input) {
        int reports = 0;
        for (int i = 0; i < manipulatedInput.getSize(); i++) {
            if (validateReport(input[i])) {
                reports++;
            }
        }
        return reports;
    }

    private boolean validateReport(Integer[] integers) {
        String incrementExpression = getIncrement(integers[0], integers[1]);
        for (int i = 1; i < integers.length; i++) {
            int diff = Math.abs(integers[i] - integers[i - 1]);

            if (!incrementExpression.equals(getIncrement(integers[i - 1], integers[i]))) {
                return false;
            }
            if (diff != 1 && diff != 2 && diff != 3) {
                return false;
            }
        }
        return true;
    }

    private String getIncrement(Integer firstIndex, Integer secondIndex) {
        if (firstIndex < secondIndex) {
            return "increasing";
        }
        return "decreasing";
    }


    public int getMatches() {
        return this.numberOfSafeReports;
    }

    public static void main(String[] args) throws IOException {
        ReportAnalyzer distanceCalculator = new ReportAnalyzer();
        System.out.println(distanceCalculator.getMatches());
    }
}
