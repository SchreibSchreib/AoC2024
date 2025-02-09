package Days.Day13.PartOne;

import Days.Day13.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PrizeFilter {

    private final InputManipulatable<Map<Integer, List<Integer[]>>> inputManipulatable;
    private Map<Integer, Integer[]> winnablePrizes;
    private Map<Integer, Integer[]> unWinnablePrizes;

    public PrizeFilter() throws IOException {
        inputManipulatable = new InputFormatter();
        loadMaps();
    }

    private void loadMaps() {
        for (Map.Entry<Integer,List<Integer[]>> entry : inputManipulatable.getConvertedInput().entrySet()) {
            for
        }
    }
}
