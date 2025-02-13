package Days.Day13.PartTwo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Summarizer {

    private final PrizeFilter filteredPrizes = new PrizeFilter();
    private final List<Long> totalAButtonPushes = new ArrayList<Long>();
    private final List<Long> totalBButtonPushes = new ArrayList<Long>();
    private final long summarizedPrize;

    public Summarizer() throws IOException {
        loadTotalPushedButtonLists();
        summarizedPrize = summarizeLists();
    }

    private long summarizeLists() {
        int bButtonCosts = 1;
        int aButtonCosts = 3;

        return totalAButtonPushes.stream().mapToLong(Long::longValue).sum() * aButtonCosts
                + totalBButtonPushes.stream().mapToLong(Long::longValue).sum() * bButtonCosts;
    }

    private void loadTotalPushedButtonLists() {
        List<List<Integer[]>> winnablePrizes = filteredPrizes.getWinnablePrizes();

        for (List<Integer[]> list : winnablePrizes) {
            ClawMachineSolver currentClawMachine = createClawMachineSolver(list);
            totalAButtonPushes.add(currentClawMachine.getResultAPresses());
            totalBButtonPushes.add(currentClawMachine.getResultBPresses());
        }
    }

    private ClawMachineSolver createClawMachineSolver(List<Integer[]> list) {
        Button buttonA = new Button(list.getFirst()[0], list.getFirst()[1]);
        Button buttonB = new Button(list.get(1)[0], list.get(1)[1]);

        return new ClawMachineSolver(buttonA, buttonB, list.get(2)[0], list.get(2)[1]);
    }

    public long getSummarizedPrize(){
        return summarizedPrize;
    }

    public static void main(String[] args) throws IOException {
        Summarizer summarizer = new Summarizer();
        //95843948914827
    }

}
