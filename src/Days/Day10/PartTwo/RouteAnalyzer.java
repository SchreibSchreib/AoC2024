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
        this.boundsY = new int[]{0, mapInformation.size()};
        this.boundsX = new int[]{0, mapInformation.getFirst().length};
        processRoutes();

    }

    private void processRoutes() {
        analyzeRoute();
        while (!crossRoads.isEmpty()) {
            RouteAnalyzer newRoute = new RouteAnalyzer(crossRoads.pop(), this.mapInformation);
            numberOfWorkingPaths += newRoute.getNumberOfWorkingPaths();
        }
    }

    private void analyzeRoute() {
        while (true) {
            if (this.currentHeight.getHeight() == 9) {
                this.numberOfWorkingPaths++;
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
                this.numberOfWorkingPaths++;
                break;
            }

            this.currentHeight = nextHeight;
        }
    }

    private void loadHeightsToDeque(List<Height> foundFields) {
        crossRoads.addAll(foundFields.subList(1, foundFields.size()));
    }


    private List<Height> searchPattern() {
        List<Height> foundFields = new ArrayList<>();

        for (int y = -1; y <= 1; y += 2) {
            int yIndex = this.currentHeight.getIndex()[0] + y;
            int xIndex = this.currentHeight.getIndex()[1];
            if (isIndexInBoundsY(yIndex)) {
                if (this.mapInformation.get(yIndex)[xIndex].getHeight() == this.currentHeight.getNextHeightToSearch()) {
                    foundFields.add(this.mapInformation.get(yIndex)[xIndex]);
                }
            }
        }

        for (int x = -1; x <= 1; x += 2) {
            int yIndex = this.currentHeight.getIndex()[0];
            int xIndex = this.currentHeight.getIndex()[1] + x;
            if (isIndexInBoundsX(xIndex)) {
                if (this.mapInformation.get(yIndex)[xIndex].getHeight() == this.currentHeight.getNextHeightToSearch()) {
                    foundFields.add(this.mapInformation.get(yIndex)[xIndex]);
                }
            }
        }
        return foundFields;
    }

    private boolean isIndexInBoundsX(int xIndex) {
        return xIndex >= this.boundsX[0] && xIndex < this.boundsX[1];
    }

    private boolean isIndexInBoundsY(int yIndex) {
        return yIndex >= this.boundsY[0] && yIndex < this.boundsY[1];
    }

    public int getNumberOfWorkingPaths() {
        return this.numberOfWorkingPaths;
    }
}
