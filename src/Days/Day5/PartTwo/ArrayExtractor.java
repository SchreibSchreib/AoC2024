package Days.Day5.PartTwo;

import Days.Day5.InputMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArrayExtractor {
    private final InputMapper inputMapper;
    private final List<Integer[]> listOfIncorrectlyOrderedUpdates;
    private final Map<Integer, List<Integer[]>> orderingRules;

    public ArrayExtractor() throws IOException {
        this.inputMapper = new InputMapper();
        this.orderingRules = inputMapper.getOrderingRules();
        this.listOfIncorrectlyOrderedUpdates = extractNumbers();
    }

    private List<Integer[]> extractNumbers() {
        List<Integer[]> incorrectlyOrderedUpdates = new ArrayList<>();
        List<Integer[]> producePages = inputMapper.getProducePages();

        for (Integer[] producePage : producePages) {
            if (!isInCorrectOrder(orderingRules, producePage)) {
                incorrectlyOrderedUpdates.add(producePage);
            }
        }
        return incorrectlyOrderedUpdates;
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

    public List<Integer[]> getListOfIncorrectlyOrderedUpdates() {
        return this.listOfIncorrectlyOrderedUpdates;
    }

    public Map<Integer, List<Integer[]>> getOrderingRules() {
        return this.orderingRules;
    }

    public static void main(String[] args) throws IOException {
        ArrayExtractor numberExtractor = new ArrayExtractor();
    }

}
