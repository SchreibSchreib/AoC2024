package Days.Day5.PartTwo;

import Days.Day5.InputMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NumberExtractor {
    private UpdateRearranger updateRearranger;
    private final List<Integer> listOfExtractedNumbers;

    public NumberExtractor() throws IOException {
        updateRearranger = new UpdateRearranger();
        listOfExtractedNumbers = extractNumbers();
    }

    private List<Integer> extractNumbers() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer[]> producePages = updateRearranger.getRearrangedUpdates();
        Map<Integer, List<Integer[]>> orderingRules = updateRearranger.getOrderingRules();

        for (Integer[] producePage : producePages) {
            if (isInCorrectOrder(orderingRules, producePage)) {
                numbers.add(extractNumber(producePage));
            }
        }
        return numbers;
    }

    private Integer extractNumber(Integer[] producePage) {
        return producePage[Math.round((float) producePage.length / 2) - 1];
    }

    private boolean isInCorrectOrder(Map<Integer, List<Integer[]>> orderingRules, Integer[] producePage) {
        for (int i = 0; i < producePage.length; i++) {
            if (i == producePage.length - 1) {
                return true;
            }
            int integerToCheck = producePage[i];
            int followingInteger = producePage[i + 1];
            if (!orderingRules.containsKey(integerToCheck)) {
                break;
            }
            if (!containsCorrectRule(orderingRules.get(integerToCheck), integerToCheck, followingInteger)) {
                break;
            }
        }
        return false;
    }

    private boolean containsCorrectRule(List<Integer[]> integers, int integerToCheck, int followingInteger) {
        for (Integer[] producePage : integers) {
            if (producePage[0] == integerToCheck && producePage[1] == followingInteger) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getListOfExtractedNumbers() {
        return listOfExtractedNumbers;
    }

    public static void main(String[] args) throws IOException {
        NumberExtractor numberExtractor = new NumberExtractor();
    }

}
