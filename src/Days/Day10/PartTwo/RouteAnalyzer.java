package Days.Day10.PartTwo;

import java.util.*;

public class RouteAnalyzer {

    private Height currentHeight;
    private final List<Height> walkedPath = new ArrayList<>();
    private final Deque<Height> crossRoads = new ArrayDeque<>();
    private final List<Height[]> mapInformation;
    private final int[] boundsY;
    private final int[] boundsX;
    private int numberOfWorkingPaths = 0;

    public RouteAnalyzer(Height currentHeight, List<Height[]> mapInformation) {
        this.currentHeight = currentHeight;
        this.mapInformation = mapInformation;
        boundsY = new int[]{0, mapInformation.size()};
        boundsX = new int[]{0, mapInformation.getFirst().length};
        processRoutes();

    }

    private void processRoutes() {
        analyzeRoute();
        while (!crossRoads.isEmpty()) {
            RouteAnalyzer newRoute = new RouteAnalyzer(crossRoads.pop(), mapInformation);
            numberOfWorkingPaths += newRoute.getNumberOfWorkingPaths();
        }
    }

    private void analyzeRoute() {
        while (true) {
            if (currentHeight.getHeight() == 9) {
                numberOfWorkingPaths++;
                break;
            }

            List<Height> foundFields = searchPattern();

            if (foundFields.isEmpty()) {
                break;
            }

            if (foundFields.size() > 1) {
                loadHeightsToDeque(foundFields);
            }
            Height nextHeight = foundFields.getFirst();

            if (nextHeight.getHeight() == 9) {
                numberOfWorkingPaths++;
                break;
            }

            currentHeight = nextHeight;
        }
    }

    private void loadHeightsToDeque(List<Height> foundFields) {
        crossRoads.addAll(foundFields.subList(1, foundFields.size()));
    }


    private List<Height> searchPattern() {
        List<Height> foundFields = new ArrayList<>();

        for (int y = -1; y <= 1; y += 2) {
            int yIndex = currentHeight.getIndex()[0] + y;
            int xIndex = currentHeight.getIndex()[1];
            if (isIndexInBoundsY(yIndex)) {
                if (mapInformation.get(yIndex)[xIndex].getHeight() == currentHeight.getNextHeightToSearch()) {
                    foundFields.add(mapInformation.get(yIndex)[xIndex]);
                }
            }
        }

        for (int x = -1; x <= 1; x += 2) {
            int yIndex = currentHeight.getIndex()[0];
            int xIndex = currentHeight.getIndex()[1] + x;
            if (isIndexInBoundsX(xIndex)) {
                if (mapInformation.get(yIndex)[xIndex].getHeight() == currentHeight.getNextHeightToSearch()) {
                    foundFields.add(mapInformation.get(yIndex)[xIndex]);
                }
            }
        }
        return foundFields;
    }

    private boolean isIndexInBoundsX(int xIndex) {
        return xIndex >= boundsX[0] && xIndex < boundsX[1];
    }

    private boolean isIndexInBoundsY(int yIndex) {
        return yIndex >= boundsY[0] && yIndex < boundsY[1];
    }

    public int getNumberOfWorkingPaths() {
        return numberOfWorkingPaths;
    }
}
