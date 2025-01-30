package Days.Day11.PartTwo;

import Days.Day11.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoneRuleApplier {

    private List<Double> stoneCollection;
    private int numberOfStones;
    private final int numberOfBlinks = 40;
    private final int multiplier = 2024;

    public StoneRuleApplier() throws IOException {
        InputManipulatable<List<Double>> inputManipulatable = new InputFormatter();
        stoneCollection = inputManipulatable.getConvertedInput();
        numberOfStones = inputManipulatable.getSize();
        numberOfStones = applyRulesOnEachBlink(numberOfBlinks);
    }

    private int applyRulesOnEachBlink(int numberOfBlinks) {
        for (int i = 0; i < numberOfBlinks; i++) {
            stoneCollection = applyRules(stoneCollection);
        }
        return stoneCollection.size();
    }

    private List<Double> applyRules(List<Double> stoneCollection) {
        List<Double> newRules = new ArrayList<>();

        for (Double stone : stoneCollection) {
            if (stone == 0) {
                newRules.add(increaseStone(stone));
                continue;
            }

            int digitsOfStone = (int) Math.floor(Math.log10(stone)) + 1;

            if (digitsOfStone % 2 == 0) {
                addBothNumbersToNewRules(newRules, stone, digitsOfStone);
            } else {
                newRules.add(mulipliedValue(stone));
            }
        }
        return newRules;
    }

    private void addBothNumbersToNewRules(List<Double> newRules, Double stone, double digitsOfStone) {
        double middleOfStone = digitsOfStone / 2;
        middleOfStone = Math.pow(10, middleOfStone);
        newRules.add(Math.floor(stone / middleOfStone));
        newRules.add(stone % middleOfStone);
    }


    private Double mulipliedValue(Double stone) {
        return stone * multiplier;
    }

    private Double increaseStone(Double stone) {
        return stone + 1;
    }


    public static void main(String[] args) throws IOException {
        StoneRuleApplier testApplier = new StoneRuleApplier();
        //Works fine for 25 but bruteforce won´t work for higher number of blinks
        //maybe look for a pattern that every number will take at some point (hot guess: look at the 0 and check the behaviour)
    }
}
