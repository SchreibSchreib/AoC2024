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
    private final int numberOfBlinks = 25;
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
            if (ruleApplier.containsKey(entry.getKey())) {
                for (Double stone : ruleApplier.get(entry.getKey())) {
                    if (mappedStones.containsKey(stone)) {
                        newRules.put(stone, mappedStones.get(stone) + entry.getValue());
                    }
                    else {
                        newRules.put(stone, 1.0);
                    }
                }
            } else {
                applyRules(entry.getKey());
                for (Double stone : ruleApplier.get(entry.getKey())) {
                    if (mappedStones.containsKey(stone)){
                        newRules.put(stone, mappedStones.get(stone) + entry.getValue());
                    }
                    newRules.put(stone, 1.0);
                }
            }
        }
        return newRules;
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
        //Works fine for 25 but bruteforce won´t work for higher number of blinks
        //maybe look for a pattern that every number will take at some point (hot guess: look at the 0 and check the behaviour)
        //0
        //1
        //2024
        //20 24
        //2 0 2 4
        //4048 1 4048 8096
        //40 48 2024 40 48 8096
        //4 0 4 8 20 24 4 8 80 96
        //8096 1 8096 16192 2 0 2 4 8 8 0 9 6
        //80 96 2024 80 96 32772608 4048 1 4048 16192 16192 18216 12144

        //Ergebnis hoch 3 = zu hohes ergebnis

    }
}
