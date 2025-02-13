package Days.Day11.PartTwo;

import Days.Day11.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.*;

public class StoneRuleApplier {

    private final List<Double> stoneCollection;
    private final Map<Double, Double[]> ruleApplier = new HashMap<>();
    private Map<Double, Double> mappedStones;
    private long numberOfStones;
    private final int numberOfBlinks = 75;
    private final int multiplier = 2024;

    public StoneRuleApplier() throws IOException {
        InputManipulatable<List<Double>> inputManipulatable = new InputFormatter();
        stoneCollection = inputManipulatable.getConvertedInput();
        numberOfStones = inputManipulatable.getSize();
        mappedStones = initialLoadOfMap();
        numberOfStones = applyRulesOnEachBlink(numberOfBlinks);
    }

    private Map<Double, Double> initialLoadOfMap() {
        Map<Double, Double> map = new HashMap<>();
        for (Double stone : stoneCollection) {
            map.put(stone, 1.0);
        }
        return map;
    }

    private long applyRulesOnEachBlink(int numberOfBlinks) {
        for (int i = 0; i < numberOfBlinks; i++) {
            mappedStones = calculateNewMap(mappedStones);
        }

        return sumResult(mappedStones);
    }

    private long sumResult(Map<Double, Double> mappedStones) {
        long sum = 0;
        for (Map.Entry<Double, Double> entry : mappedStones.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }


    private Map<Double, Double> calculateNewMap(Map<Double, Double> mappedStones) {
        Map<Double, Double> newRules = new HashMap<>();

        for (Map.Entry<Double, Double> entry : mappedStones.entrySet()) {
            double keyForMappedStone = entry.getKey();

            if (!ruleApplier.containsKey(entry.getKey())) {
                applyRules(keyForMappedStone);
            }

            calculateStoneCollection(ruleApplier, newRules, keyForMappedStone, mappedStones);
        }
        return newRules;
    }

    private void calculateStoneCollection(Map<Double, Double[]> ruleApplier, Map<Double, Double> newRules, double keyForMappedStone, Map<Double, Double> mappedStones) {
        Double[] stoneResultAfterCalculation = ruleApplier.get(keyForMappedStone);

        for (Double stone : stoneResultAfterCalculation) {
            if (newRules.containsKey(stone)) {
                newRules.put(stone, newRules.get(stone) + mappedStones.getOrDefault(keyForMappedStone, 1.0));
            } else {
                newRules.put(stone, mappedStones.getOrDefault(keyForMappedStone, 1.0));
            }
        }

        return;
    }

    private void applyRules(double stone) {
        if (stone == 0) {
            ruleApplier.put(stone, new Double[]{increaseStone(stone)});
            return;
        }

        int digitsOfStone = (int) Math.floor(Math.log10(stone)) + 1;

        if (digitsOfStone % 2 == 0) {
            addBothNumbersToNewRules(stone, digitsOfStone);
        } else {
            ruleApplier.put(stone, new Double[]{multipliedValue(stone)});
        }
    }

    private void addBothNumbersToNewRules(Double stone, double digitsOfStone) {
        double middleOfStone = digitsOfStone / 2;
        middleOfStone = Math.pow(10, middleOfStone);
        ruleApplier.put(stone, new Double[]{(Math.floor(stone / middleOfStone)), stone % middleOfStone});
    }


    private Double multipliedValue(Double stone) {
        return stone * multiplier;
    }

    private Double increaseStone(Double stone) {
        return stone + 1;
    }


    public static void main(String[] args) throws IOException {
        StoneRuleApplier testApplier = new StoneRuleApplier();
        //240954878211138
    }
}
