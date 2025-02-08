package Days.Day12.PartTwo;

import java.io.IOException;

public class GardenPlotCalculator {

    private final GardenPlotGrouper groupedGardenPlots;
    private final int sum;

    public GardenPlotCalculator() throws IOException {
        groupedGardenPlots = new GardenPlotGrouper();
        sum = summarize();
    }

    private int summarize() throws IOException {
        int sum = 0;

        for (GardenPlot[] gardenPlotGroup : groupedGardenPlots.getGroupedGardenPlots()) {
            int corners = countCorners(gardenPlotGroup);
            sum += gardenPlotGroup.length * corners;
        }
        return sum;
    }

    private int countCorners(GardenPlot[] gardenPlotGroup) throws IOException {
        return new CornerCounter(gardenPlotGroup).getCornersOfGroup();
    }


    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws IOException {
        GardenPlotCalculator calculator = new GardenPlotCalculator();
        //914966
    }
}
