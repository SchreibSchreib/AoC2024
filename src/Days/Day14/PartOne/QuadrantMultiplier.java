package Days.Day14.PartOne;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QuadrantMultiplier {

    private final QuadrantRobotCalculator quadrantRobotCalculator;
    private final int result;

    public QuadrantMultiplier() throws IOException {
        quadrantRobotCalculator = new QuadrantRobotCalculator();
        result = calculateResult();
    }

    private int calculateResult() {
        int result = 1;

        for (Map.Entry<Integer, List<Robot>> quadrant : quadrantRobotCalculator.getPositionsOfRobots().entrySet()) {
            result *= quadrant.getValue().size();
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        QuadrantMultiplier quadrantMultiplier = new QuadrantMultiplier();
        //216027840
    }
}
