package Days.Day12.PartTwo;

import java.io.IOException;
import java.util.*;

public class LineExtractor {

    private final GardenPlot[] groupedGardenPlots;
    private final List<GardenPlot[]> linesOfGroup;


    public LineExtractor(GardenPlot[] groupedGardenPlots) throws IOException {
        this.groupedGardenPlots = groupedGardenPlots;
        linesOfGroup = extractLines();
    }

    private List<GardenPlot[]> extractLines() {
        Map<Integer, List<GardenPlot>> sortedByYCoords = new TreeMap<>();

        for (GardenPlot gardenPlot : groupedGardenPlots) {
            loadMap(sortedByYCoords, gardenPlot);
        }

        return createSortedLine(sortedByYCoords);
    }

    private List<GardenPlot[]> createSortedLine(Map<Integer, List<GardenPlot>> sortedByYCoords) {
        List<GardenPlot[]> lines = new ArrayList<>();
        for (List<GardenPlot> gardenPlots : sortedByYCoords.values()) {
            gardenPlots.sort(Comparator.comparingInt(plot -> plot.getCoordinates()[1]));
            lines.add(gardenPlots.toArray(new GardenPlot[0]));
        }
        return lines;
    }

    private void loadMap(Map<Integer, List<GardenPlot>> sortedByYCoords, GardenPlot gardenPlot) {
        int y = gardenPlot.getCoordinates()[0];
        sortedByYCoords.putIfAbsent(y, new ArrayList<>());
        sortedByYCoords.get(y).add(gardenPlot);
    }

    public List<GardenPlot[]> getLinesOfGroup() {
        return linesOfGroup;
    }
}
