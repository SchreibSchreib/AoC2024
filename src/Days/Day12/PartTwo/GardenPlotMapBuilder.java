package Days.Day12.PartTwo;

import java.util.ArrayList;
import java.util.List;

public class GardenPlotMapBuilder {

    private List<GardenPlot[]> gardenPlotMap = new ArrayList<>();

    public GardenPlotMapBuilder(List<char[]> gardenPlots) {
        gardenPlotMap = buildMap(gardenPlots);
    }

    private List<GardenPlot[]> buildMap(List<char[]> gardenPlots) {
        List<GardenPlot[]> map = new ArrayList<>();
        for (int yIndex = 0; yIndex < gardenPlots.size(); yIndex++) {
            GardenPlot[] gardenPlot = new GardenPlot[gardenPlots.get(yIndex).length];
            for (int xIndex = 0; xIndex < gardenPlot.length; xIndex++) {
                gardenPlot[xIndex] = new GardenPlot(yIndex, xIndex, gardenPlots.get(yIndex)[xIndex]);
            }
            map.add(gardenPlot);
        }
        return map;
    }

    public List<GardenPlot[]> getGardenPlotMap() {
        return gardenPlotMap;
    }
}
