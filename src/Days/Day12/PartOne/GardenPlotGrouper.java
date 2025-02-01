package Days.Day12.PartOne;

import Days.Day12.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GardenPlotGrouper {

    private final InputManipulatable<List<char[]>> inputManipulatable;
    private final List<char[]> input;
    private final List<List<GardenPlot>> groupedGardenPlots;

    public GardenPlotGrouper() throws IOException {
        inputManipulatable = new InputFormatter();
        input = inputManipulatable.getConvertedInput();
        groupedGardenPlots = groupInput();
    }

    private List<List<GardenPlot>> groupInput() {
        List<List<GardenPlot>> listOfGroupedGardenPlots = new ArrayList<>();


    }
}
