package Days.Day13.PartOne;

import Days.Day13.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeFilter {

    private final InputManipulatable<Map<Integer, List<Integer[]>>> inputManipulatable;
    private final List<List<Integer[]>> winnablePrizes = new ArrayList<>();
    private final List<List<Integer[]>> unWinnablePrizes = new ArrayList<>();

    public PrizeFilter() throws IOException {
        inputManipulatable = new InputFormatter();
        loadMaps();
    }

    private void loadMaps() {
        for (Map.Entry<Integer, List<Integer[]>> entry : inputManipulatable.getConvertedInput().entrySet()) {
            int[] xValues = new int[]{entry.getValue().get(0)[0], entry.getValue().get(1)[0]};
            int[] yValues = new int[]{entry.getValue().get(0)[1], entry.getValue().get(1)[1]};
            int[] targetValues = new int[]{entry.getValue().get(2)[0], entry.getValue().get(2)[1]};

            if (isGreatestCommonDivisorDividableToTarget(xValues, targetValues[0]) && isGreatestCommonDivisorDividableToTarget(yValues, targetValues[1])) {
                winnablePrizes.add(entry.getValue());
            } else {
                unWinnablePrizes.add(entry.getValue());
            }

        }
    }

    private boolean isGreatestCommonDivisorDividableToTarget(int[] values, int targetValue) {
        int biggerValue = Math.max(values[0], values[1]);
        int smallerValue = Math.min(values[0], values[1]);
        return targetValue % new GreatestCommonDivisorCalculator(biggerValue, smallerValue).getGreatestCommonDivisor() == 0;
    }

    public static void main(String[] args) throws IOException {
        PrizeFilter filter = new PrizeFilter();

    }
}
