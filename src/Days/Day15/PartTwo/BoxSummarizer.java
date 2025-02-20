package Days.Day15.PartTwo;

import Days.Day15.PartOne.Enums.tileState;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BoxSummarizer {

    private final int multiplicator = 100;
    private final int result;

    public BoxSummarizer() throws IOException {
        Map<Integer, List<Tile>> evaluatedMap = new RobotMover().getEvaluatedWarehouseMap();
        result = calculateResult(evaluatedMap);
    }

    private int calculateResult(Map<Integer, List<Tile>> evaluatedMap) {
        int result = 0;
        for (int yIndex = 1; yIndex < evaluatedMap.size() - 1; yIndex++) {
            for (int xIndex = 1; xIndex < evaluatedMap.get(yIndex).size() - 1; xIndex++) {
                if (evaluatedMap.get(yIndex).get(xIndex).getState() == tileState.MOVEABLE) {
                    result += yIndex * multiplicator + xIndex;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BoxSummarizer testSummarizer = new BoxSummarizer();


    }
}
