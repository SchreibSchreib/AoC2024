package Days.Day12.PartOne;

import Days.Day12.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class GardenPlotGrouper {

    private final InputManipulatable<List<char[]>> inputManipulatable;
    private final List<GardenPlot[]> convertedInput;
    private final List<GardenPlot[]> groupedGardenPlots = new ArrayList<>();
    private final int[] boundsY;
    private final int[] boundsX;

    public GardenPlotGrouper() throws IOException {
        inputManipulatable = new InputFormatter();
        convertedInput = new GardenPlotMapBuilder(inputManipulatable.getConvertedInput()).getGardenPlotMap();
        boundsY = new int[]{0, convertedInput.size()};
        boundsX = new int[]{0, convertedInput.getFirst().length};
        findAllGroups();

    }


    private void findAllGroups() {
        for (GardenPlot[] gardenPlots : convertedInput) {
            for (GardenPlot gardenPlot : gardenPlots) {
                if (gardenPlot.isAlreadyInGroup()) {
                    continue;
                }
                groupedGardenPlots.add(searchAlgorithm(gardenPlot));
            }
        }
    }

    private GardenPlot[] searchAlgorithm(GardenPlot gardenPlot) {
        List<GardenPlot> gardenPlots = new ArrayList<>();
        ArrayDeque<GardenPlot> plotStack = new ArrayDeque<>();
        plotStack.push(gardenPlot);
        gardenPlot.setAlreadyInGroup();

        while (!plotStack.isEmpty()) {
            GardenPlot plot = plotStack.pop();
            int[] index = plot.getCoordinates();
            gardenPlots.add(plot);

            searchPattern(plotStack, index, plot);
        }
        return gardenPlots.toArray(new GardenPlot[0]);
    }

    private void searchPattern(ArrayDeque<GardenPlot> plotStack, int[] index, GardenPlot gardenPlot) {
        // insert sidenumber here and let it be calculated with the search pattern
        lookForYCoordinates(plotStack, index, gardenPlot);
        lookForXCoordinates(plotStack, index, gardenPlot);

    }

    private void lookForXCoordinates(ArrayDeque<GardenPlot> plotStack, int[] index, GardenPlot gardenPlot) {
        for (int xIndex = index[1] - 1; xIndex <= index[1] + 1; xIndex += 2) {
            if (isIndexInBoundsX(xIndex)) {
                GardenPlot nextPlot = convertedInput.get(index[0])[xIndex];
                if (nextPlot.getPlotSymbol() == gardenPlot.getPlotSymbol()
                        && !nextPlot.isAlreadyInGroup()) {
                    nextPlot.setAlreadyInGroup();
                    plotStack.push(nextPlot);
                }
            }
        }
    }

    private void lookForYCoordinates(ArrayDeque<GardenPlot> plotStack, int[] index, GardenPlot gardenPlot) {
        for (int yIndex = index[0] - 1; yIndex <= index[0] + 1; yIndex += 2) {
            if (isIndexInBoundsY(yIndex)) {
                GardenPlot nextPlot = convertedInput.get(yIndex)[index[1]];
                if (nextPlot.getPlotSymbol() == gardenPlot.getPlotSymbol()
                        && !nextPlot.isAlreadyInGroup()) {
                    nextPlot.setAlreadyInGroup();
                    plotStack.push(nextPlot);
                }
            }
        }
    }

    private boolean isIndexInBoundsX(int xIndex) {
        return xIndex >= boundsX[0] && xIndex < boundsX[1];
    }

    private boolean isIndexInBoundsY(int yIndex) {
        return yIndex >= boundsY[0] && yIndex < boundsY[1];
    }

    public static void main(String[] args) throws IOException {
        GardenPlotGrouper testGrouper = new GardenPlotGrouper();

    }
}
