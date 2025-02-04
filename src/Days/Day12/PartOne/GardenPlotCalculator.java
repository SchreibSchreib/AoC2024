package Days.Day12.PartOne;

import java.io.IOException;

public class GardenPlotCalculator {

    private final GardenPlotGrouper groupedGardenPlots;
    private final int sum;

    public GardenPlotCalculator() throws IOException {
        groupedGardenPlots = new GardenPlotGrouper();
        sum = summarize();
    }

    private int summarize() {
        int sum = 0;

        for (GardenPlot[] group : groupedGardenPlots.getGroupedGardenPlots()) {
            int area = group.length;
            int perimeter = 0;
            for (GardenPlot gardenPlot : group) {
                perimeter += gardenPlot.getPerimeter();
            }
            sum += area * perimeter;
        }
        return sum;
    }

    public int getSum(){
        return sum;
    }

    public static void main(String[] args) throws IOException {
        GardenPlotCalculator calculator = new GardenPlotCalculator();
        //1489582
    }
}
