package Days.Day5.PartTwo;

import java.io.IOException;
import java.util.*;

public class UpdateRearranger {
    private final ArrayExtractor arrayExtractor;
    private final Map<Integer, List<Integer[]>> orderingRules;
    private final List<Integer[]> wrongUpdates;
    private final List<Integer[]> rearrangedUpdates;

    public UpdateRearranger() throws IOException {
        this.arrayExtractor = new ArrayExtractor();
        this.orderingRules = arrayExtractor.getOrderingRules();
        this.wrongUpdates = arrayExtractor.getListOfIncorrectlyOrderedUpdates();
        this.rearrangedUpdates = loadRearrangedUpdates();
    }

    private List<Integer[]> loadRearrangedUpdates() throws IOException {
        List<Integer[]> rearrangedUpdates = new ArrayList<>();

        for (Integer[] updates : wrongUpdates) {
            rearrangedUpdates.add(rearrangeArray(updates));
        }
        return rearrangedUpdates;
    }

    private Integer[] rearrangeArray(Integer[] updates) {
        Integer[] rearrangedUpdates = new Integer[updates.length];
        for (int i = 0; i < updates.length; i++) {
            if (i == updates.length - 1) {
                break;
            }
            if (!orderingRules.containsKey(updates[i])) {
                int lastNumber = updates[i];

                updates[i] = updates[rearrangedUpdates.length - 1];
                updates[rearrangedUpdates.length - 1] = lastNumber;
            }
            if (!containsCorrectRule(orderingRules.get(updates[i]), updates[i], updates[i + 1])) {
                updates = rearrangeNumber(updates, updates[i], i + 1);
            } else {
                rearrangedUpdates = updates;
            }
        }
        return rearrangedUpdates;
    }

    private Integer[] rearrangeNumber(Integer[] updates, Integer update, int indexOfMistake) {
        List<Integer> rearrangedUpdates = new ArrayList<>(Arrays.asList(updates));
        for (int leftNumbers = indexOfMistake - 1; leftNumbers >= 0; leftNumbers--) {
            if (!containsCorrectRule(orderingRules.get(updates[leftNumbers]), updates[leftNumbers], update)) {
                Collections.swap(rearrangedUpdates, leftNumbers, indexOfMistake);
                break;
            }
        }
        return rearrangedUpdates.toArray(new Integer[rearrangedUpdates.size()]);
    }


    private boolean containsCorrectRule(List<Integer[]> integers, int integerToCheck, int followingInteger) {
        for (Integer[] producePage : integers) {
            if (producePage[0] == integerToCheck && producePage[1] == followingInteger) {
                return true;
            }
        }
        return false;
    }

    public List<Integer[]> getRearrangedUpdates() {
        return this.rearrangedUpdates;
    }

    public Map<Integer, List<Integer[]>> getOrderingRules() {
        return this.orderingRules;
    }

    public static void main(String[] args) throws IOException {
        UpdateRearranger updateRearranger = new UpdateRearranger();

    }
}
