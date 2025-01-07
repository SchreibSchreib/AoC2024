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
        List<Integer> rearrangedUpdatesList = new LinkedList<>();

        for (int i = 0; i < updates.length; i++) {
            if (i == 0) {
                rearrangedUpdatesList.add(updates[i]);
                continue;
            }
            if (orderingRules.containsKey(updates[i])) {
                rearrangedUpdatesList.add(checkForNewIndex(i - 1, updates[i], rearrangedUpdatesList),updates[i]);
            } else {
                rearrangedUpdatesList.add(updates[i]);
            }
        }
        return rearrangedUpdatesList.toArray(new Integer[0]);
    }

    private Integer checkForNewIndex(int actualIndex, int foundNumber, List<Integer> rearrangedUpdatesList) {
        int newIndex = actualIndex + 1;
        for (int iteratingIndex = actualIndex; iteratingIndex >= 0; iteratingIndex--) {
            for (Integer[] entry : orderingRules.get(foundNumber)) {
                if (entry[1].equals(rearrangedUpdatesList.get(iteratingIndex))) {
                    newIndex = iteratingIndex;
                    break;
                }
            }
        }
        return newIndex;
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
