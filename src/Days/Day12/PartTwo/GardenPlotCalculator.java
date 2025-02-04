package Days.Day12.PartTwo;

import java.io.IOException;
import java.util.List;

public class GardenPlotCalculator {

    private final GardenPlotGrouper groupedGardenPlots;
    private final int sum;

    public GardenPlotCalculator() throws IOException {
        groupedGardenPlots = new GardenPlotGrouper();
        sum = summarize();
    }

    private int summarize() throws IOException {
        int sum = 0;

        for (GardenPlot[] group : groupedGardenPlots.getGroupedGardenPlots()) {
            int area = group.length;
            int perimeter = calculatePerimeter(new LineExtractor(group).getLinesOfGroup());
        }
        return sum;
    }

    private int calculatePerimeter(List<GardenPlot[]> linesOfGroup) {
        int perimeter = 0;

        for (int i = 0; i < linesOfGroup.size(); i++) {
            perimeter += analyzeLine(linesOfGroup, linesOfGroup.get(i), i);

        }
    }

    private int analyzeLine(List<GardenPlot[]> linesOfGroup, GardenPlot[] gardenPlots, int indexY) {
        if (isFirstOrLastLine(indexY, linesOfGroup)) {
            if (indexY == ) {}
            return calculateFirstLine(linesOfGroup, gardenPlots, indexY);
        }
        return calculateLine(linesOfGroup, gardenPlots, indexY);
    }

    private int calculateLine(List<GardenPlot[]> linesOfGroup, GardenPlot[] gardenPlots, int indexY) {
    }


    private int calculateFirstLine(List<GardenPlot[]> linesOfGroup, GardenPlot[] gardenPlots, int indexY) {
        if (isStraightLine(linesOfGroup.get(indexY))) {
            if (gardenPlots[0].getPerimeter() == 4) {
                return 4;
            } else {
                return 3;
            }
        }
        return getChunks(gardenPlots) * 3;
    }

    private int getChunks(GardenPlot[] gardenPlots) {
        int chunks = 1;
        GardenPlot lastCheckedGardenPlot = gardenPlots[0];

        for (GardenPlot gardenPlot : gardenPlots) {
            if (gardenPlot.getCoordinates()[1] - lastCheckedGardenPlot.getCoordinates()[1] == -1) {
                lastCheckedGardenPlot = gardenPlot;
                chunks++;
            }
        }
        return chunks;
    }

    private boolean isStraightLine(GardenPlot[] gardenPlots) {
        for (int i = 0; i < gardenPlots.length; i++) {
            if (gardenPlots[i].getCoordinates()[1] != i + gardenPlots[0].getCoordinates()[1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isFirstOrLastLine(int i, List<GardenPlot[]> linesOfGroup) {
        return i == 0 || i == linesOfGroup.size() - 1;
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws IOException {
        GardenPlotCalculator calculator = new GardenPlotCalculator();
        //1489582
    }
}
