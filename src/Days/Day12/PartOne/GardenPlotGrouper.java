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
    private final List<GardenPlot[]> groupedGardenPlots;
    private final int[] boundsY;
    private final int[] boundsX;

    public GardenPlotGrouper() throws IOException {
        inputManipulatable = new InputFormatter();
        convertedInput = new GardenPlotMapBuilder(inputManipulatable.getConvertedInput()).getGardenPlotMap();
        boundsY = new int[]{0, convertedInput.size()};
        boundsX = new int[]{0, convertedInput.getFirst().length};
        groupedGardenPlots = groupInput();

    }

    private List<GardenPlot[]> groupInput() {
        List<GardenPlot[]> listOfGroupedGardenPlots = new ArrayList<>(inputManipulatable.getSize());

        for (int yIndex = 0; yIndex < convertedInput.size(); yIndex++) {
            listOfGroupedGardenPlots.add(findNewGroup(yIndex));
        }

    }

    private GardenPlot[] findNewGroup(int yIndex) {
        for (GardenPlot[] gardenPlots : convertedInput) {
            for (GardenPlot gardenPlot : gardenPlots) {
                if (gardenPlot.isAlreadyInGroup()) {
                    continue;
                }
                GardenPlot[] foundGroup = searchAlgorithm(gardenPlot);
            }
        }
    }

    private GardenPlot[] searchAlgorithm(GardenPlot gardenPlot) {
        List<GardenPlot> gardenPlots = new ArrayList<>();
        ArrayDeque<GardenPlot> plotStack = new ArrayDeque<>();
        plotStack.push(gardenPlot);

        while (!plotStack.isEmpty()) {
            int[] index = gardenPlot.getCoordinates();
            GardenPlot plot = plotStack.pop();
            plot.setAlreadyInGroup();
            gardenPlots.add(plot);

            searchPattern(plotStack, index, plot);
        }
    }

    private void searchPattern(ArrayDeque<GardenPlot> plotStack, int[] index, GardenPlot gardenPlot) {
        for (int yIndex = index[0] - 1; yIndex <= index[0] + 1; yIndex += 2) {
            if (convertedInput.get(yIndex)[index[1]].getPlotSymbol() == gardenPlot.getPlotSymbol()
                    && !convertedInput.get(yIndex)[index[1]].isAlreadyInGroup()) {
                plotStack.push(convertedInput.get(yIndex)[index[1]]);
            }
        }
    }

    private boolean isIndexInBoundsX(int xIndex) {
        return xIndex >= boundsX[0] && xIndex < boundsX[1];
    }

    private boolean isIndexInBoundsY(int yIndex) {
        return yIndex >= boundsY[0] && yIndex < boundsY[1];
    }
}
