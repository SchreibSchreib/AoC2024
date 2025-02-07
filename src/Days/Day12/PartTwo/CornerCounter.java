package Days.Day12.PartTwo;

import java.io.IOException;
import java.util.*;

public class CornerCounter {

    private final GardenPlot[] groupedGardenPlots;
    private Map<Integer, List<GardenPlot>> groupedGardenPlotsMap;
    private final int cornersOfGroup;


    public CornerCounter(GardenPlot[] groupedGardenPlots) throws IOException {
        this.groupedGardenPlots = groupedGardenPlots;
        groupedGardenPlotsMap = loadPlotsToMap();
        cornersOfGroup = countCorners();
    }

    private Map<Integer, List<GardenPlot>> loadPlotsToMap() {
        Map<Integer, List<GardenPlot>> map = new HashMap<>();

        for (GardenPlot plot : groupedGardenPlots) {
            map.putIfAbsent(plot.getCoordinates()[0], new ArrayList<>());
            map.get(plot.getCoordinates()[0]).add(plot);
        }

        return map;
    }

    private int countCorners() {
        int cornersFound = 0;

        for (GardenPlot plot : groupedGardenPlots) {
            cornersFound += cornersOfCurrentPlot(plot);
        }
        return cornersFound;
    }

    private int cornersOfCurrentPlot(GardenPlot plot) {
        int cornersFound = 0;
        int[][] diagonalCoordinates = getDiagonalCoordinates(plot.getCoordinates());
        List<int[]> potentialCornerCoordinates = findPotentialCornerCoordinates(diagonalCoordinates);

        for (int[] potentialCornerCoordinate : potentialCornerCoordinates) {
            cornersFound += validateCorner(potentialCornerCoordinate,
                    potentialCornerCoordinate[0] - plot.getCoordinates()[0],
                    potentialCornerCoordinate[1] - plot.getCoordinates()[1]);
        }

        return cornersFound;
    }

    private int validateCorner(int[] potentialCornerCoordinate, int yDirection, int xDirection) {
        if (yDirection == -1 && xDirection == -1) {
            return handleTopLeft(potentialCornerCoordinate);
        }
        if (yDirection == -1 && xDirection == 1) {
            return handleTopRight(potentialCornerCoordinate);
        }
        if (yDirection == 1 && xDirection == -1) {
            return handleBottomLeft(potentialCornerCoordinate);
        } else {
            return handleBottomRight(potentialCornerCoordinate);
        }
    }

    private int handleBottomRight(int[] potentialCornerCoordinate) {
        if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0] - 1)
                && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0] - 1), potentialCornerCoordinate[1])) {
            if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] - 1)) {
                return 1;
            }
        } else {
            if (!groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    || !checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] - 1)) {
                return 1;
            }
        }
        return 0;
    }

    private int handleBottomLeft(int[] potentialCornerCoordinate) {
        if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0] - 1)
                && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0] - 1), potentialCornerCoordinate[1])) {
            if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] + 1)) {
                return 1;
            }
        } else {
            if (!groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    || !checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] + 1)) {
                return 1;
            }
        }
        return 0;
    }

    private int handleTopRight(int[] potentialCornerCoordinate) {
        if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0] + 1)
                && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0] + 1), potentialCornerCoordinate[1])) {
            if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] - 1)) {
                return 1;
            }
        } else {
            if (!groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    || !checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] - 1)) {
                return 1;
            }
        }
        return 0;
    }

    private int handleTopLeft(int[] potentialCornerCoordinate) {
        if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0] + 1)
                && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0] + 1), potentialCornerCoordinate[1])) {
            if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    && checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] + 1)) {
                return 1;
            }
        } else {
            if (!groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    || !checkList(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), potentialCornerCoordinate[1] + 1)) {
                return 1;
            }
        }
        return 0;
    }

    private boolean checkList(List<GardenPlot> gardenPlots, int xIndex) {
        for (GardenPlot plot : gardenPlots) {
            if (plot.getCoordinates()[1] == xIndex) {
                return true;
            }
        }
        return false;
    }


    private List<int[]> findPotentialCornerCoordinates(int[][] diagonalCoordinates) {
        List<int[]> potentialCornerCoordinates = new ArrayList<>();

        for (int[] diagonalCoordinate : diagonalCoordinates) {
            if (doesDiagonalGardenPlotExist(diagonalCoordinate)) {
                continue;
            }
            potentialCornerCoordinates.add(diagonalCoordinate);
        }

        return potentialCornerCoordinates;
    }

    private boolean doesDiagonalGardenPlotExist(int[] diagonalCoordinate) {
        if (!groupedGardenPlotsMap.containsKey(diagonalCoordinate[0])) {
            return false;
        }
        for (GardenPlot plot : groupedGardenPlotsMap.get(diagonalCoordinate[0])) {
            if (plot.getCoordinates()[1] == diagonalCoordinate[1]) {
                return true;
            }
        }
        return false;
    }

    private int[][] getDiagonalCoordinates(int[] coordinates) {
        int[][] diagonalCoordinates = new int[4][2];
        int counter = 0;

        for (int yIndex = 1; yIndex >= -1; yIndex -= 2) {
            for (int xIndex = -1; xIndex <= 1; xIndex += 2) {
                diagonalCoordinates[counter][0] = coordinates[0] - yIndex;
                diagonalCoordinates[counter][1] = coordinates[1] - xIndex;
                counter++;
            }
        }
        return diagonalCoordinates;
    }

    public int getCornersOfGroup() {
        return cornersOfGroup;
    }
}
