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

        for (int[] potentialCornerCoordinate : diagonalCoordinates) {
            cornersFound += validateCorner(potentialCornerCoordinate,
                    potentialCornerCoordinate[0] - plot.getCoordinates()[0],
                    potentialCornerCoordinate[1] - plot.getCoordinates()[1]);
        }

        return cornersFound;
    }

    private int validateCorner(int[] potentialCornerCoordinate, int yDirection, int xDirection) {
        GardenPlot diagonalPlot = tryToGetDiagonalPlot(potentialCornerCoordinate);
        String cornerToCheck = evaluateCornerPosition(yDirection, xDirection);

        if (diagonalPlot == null) {
            return checkForValidCorner(potentialCornerCoordinate, cornerToCheck);
        }
        return checkForValidCorner(diagonalPlot, cornerToCheck);
    }

    private String evaluateCornerPosition(int yDirection, int xDirection) {
        if (yDirection == -1) {
            if (xDirection == -1) {
                return "TopLeft";
            }
            return "TopRight";
        }
        if (xDirection == -1) {
            return "BottomLeft";
        }
        return "BottomRight";
    }

    private int checkForValidCorner(int[] potentialCornerCoordinate, String cornerToCheck) {
        int[] neighbourPositions = getNeighbourPositions(cornerToCheck);
        if (isValidCorner(potentialCornerCoordinate, neighbourPositions)) {
            return 1;
        }
        return 0;
    }

    private boolean isValidCorner(int[] potentialCornerCoordinate, int[] neighbourPositions) {
        int yToCheck = potentialCornerCoordinate[0] + neighbourPositions[0];
        int xToCheck = potentialCornerCoordinate[1] + neighbourPositions[1];

        if (groupedGardenPlotsMap.containsKey(yToCheck)
                && listContainsXCoordinates(groupedGardenPlotsMap.get(yToCheck), potentialCornerCoordinate[1])) {
            return groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    && listContainsXCoordinates(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), xToCheck);
        } else {
            return !groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])
                    || !listContainsXCoordinates(groupedGardenPlotsMap.get(potentialCornerCoordinate[0]), xToCheck);
        }
    }

    private boolean listContainsXCoordinates(List<GardenPlot> gardenPlots, int xIndex) {
        for (GardenPlot plot : gardenPlots) {
            if (plot.getCoordinates()[1] == xIndex) {
                return true;
            }
        }
        return false;
    }


    private int checkForValidCorner(GardenPlot foundPlot, String cornerToCheck) {
        int[] neighbourPositions = getNeighbourPositions(cornerToCheck);
        if (isValidCorner(foundPlot, neighbourPositions)) {
            return 1;
        }
        return 0;
    }

    private boolean isValidCorner(GardenPlot foundPlot, int[] neighbourPositions) {
        int yToCheck = foundPlot.getCoordinates()[0] + neighbourPositions[0];
        int xToCheck = foundPlot.getCoordinates()[1] + neighbourPositions[1];

        if (groupedGardenPlotsMap.containsKey(yToCheck)
                && !listContainsXCoordinates(groupedGardenPlotsMap.get(yToCheck), foundPlot.getCoordinates()[1])) {
            if (groupedGardenPlotsMap.containsKey(foundPlot.getCoordinates()[0])
                    && !listContainsXCoordinates(groupedGardenPlotsMap.get(foundPlot.getCoordinates()[0]), xToCheck)) {
                return true;
            }
        }
        return false;
    }


    private int[] getNeighbourPositions(String cornerToCheck) {
        switch (cornerToCheck) {
            case "TopLeft":
                return new int[]{1, 1};
            case "TopRight":
                return new int[]{1, -1};
            case "BottomLeft":
                return new int[]{-1, 1};
            default:
                return new int[]{-1, -1};
        }
    }

    private GardenPlot tryToGetDiagonalPlot(int[] potentialCornerCoordinate) {
        if (groupedGardenPlotsMap.containsKey(potentialCornerCoordinate[0])) {
            for (GardenPlot plot : groupedGardenPlotsMap.get(potentialCornerCoordinate[0])) {
                if (plot.getCoordinates()[1] == potentialCornerCoordinate[1]) {
                    return plot;
                }
            }
        }
        return null;
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
