package Days.Day2.PartTwo;

import Days.Day2.InputConverter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.stream.IntStream;

public class ReportAnalyzer {

    private final InputManipulatable<Integer[][]> manipulatedInput;
    private final int numberOfSafeReports;

    public ReportAnalyzer() throws IOException {
        this.manipulatedInput = new InputConverter();
        this.numberOfSafeReports = validateReports(this.manipulatedInput.getConvertedInput());
    }

    public static void main(String[] args) throws IOException {
        ReportAnalyzer distanceCalculator = new ReportAnalyzer();
        System.out.println(distanceCalculator.getMatches());
    }

    private int validateReports(Integer[][] input) {
        int reports = 0;
        for (int i = 0; i < manipulatedInput.getSize(); i++) {
            if (validateReport(input[i], false)) {
                reports++;
            }
        }
        return reports;
    }

    private boolean validateReport(Integer[] integers, boolean isFirstErrorDeleted) {
        String incrementExpression = getIncrement(integers[0], integers[1]);
        for (int i = 1; i < integers.length; i++) {
            int diff = Math.abs(integers[i] - integers[i - 1]);

            if (!incrementExpression.equals(getIncrement(integers[i - 1], integers[i]))) {
                if (!isFirstErrorDeleted) {
                    //Create multiple Versions of this Array to check if there is a true version after excluding one element
                    return worksAfterExcludingFalseElement(integers);
                }
                return false;
            }
            if (diff != 1 && diff != 2 && diff != 3) {
                if (!isFirstErrorDeleted) {
                    //Create multiple Versions of this Array to check if there is a true version after excluding one element
                    return worksAfterExcludingFalseElement(integers);
                }
                return false;
            }
        }
        return true;
    }

    private boolean worksAfterExcludingFalseElement(Integer[] integers) {
        for (int indexToExclude = 0; indexToExclude < integers.length; indexToExclude++) {
            //Excludes every single number after another to find a working Array
            Integer[] adjustedArray = getRestatedArray(integers, indexToExclude);
            if (validateReport(adjustedArray, true)) {
                return true;
            }
        }
        return false;
    }

    private Integer[] getRestatedArray(Integer[] integers, int indexToExclude) {
        return IntStream.range(0, integers.length)
                .filter(i -> i != indexToExclude)
                .mapToObj(i -> integers[i])
                .toArray(Integer[]::new);
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
}
