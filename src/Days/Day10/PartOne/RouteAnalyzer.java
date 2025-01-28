package Days.Day10.PartOne;

import java.util.ArrayList;
import java.util.List;

public class RouteAnalyzer {

    private Height currentHeight;
    private boolean haveReachedEnd = false;
    private final List<Height> crossRoads = new ArrayList<>();
    private final List<Height[]> mapInformation;
    private final List<Height> walkedFields = new ArrayList<>();
    private final int[] boundsY;
    private final int[] boundsX;
    private int numberOfWorkingPaths = 0;

    public RouteAnalyzer(Height currentHeight, List<Height[]> mapInformation) {
        this.currentHeight = currentHeight;
        this.mapInformation = mapInformation;
        this.boundsY = new int[]{0, mapInformation.size()};
        this.boundsX = new int[]{0, mapInformation.getFirst().length};
        analyzeRoute();
        for (Height crossRoad : this.crossRoads) {
            RouteAnalyzer newRoute = new RouteAnalyzer(crossRoad, this.mapInformation);
            numberOfWorkingPaths += newRoute.getNumberOfWorkingPaths();
        }
    }

    private void analyzeRoute() {
        while (!this.haveReachedEnd) {
            if (isPathContinuing()) {
                if (this.currentHeight.getHeight() == 9) {
                    this.numberOfWorkingPaths++;
                    this.haveReachedEnd = true;
                }
                continue;
            } else {
                this.haveReachedEnd = true;
            }
        }
    }

    private boolean isPathContinuing() {
        List<Height> foundPaths = broadSearch();

        if (foundCrossroad(foundPaths)) {
            crossRoads.add(this.currentHeight);
        }
        this.walkedFields.add(this.currentHeight);

        if (!foundPaths.isEmpty()) {
            this.currentHeight = foundPaths.getFirst();
            this.currentHeight.setSteppedOn();
            return true;
        }

        return false;
    }

    private boolean foundCrossroad(List<Height> foundPaths) {
        return foundPaths.size() > 1;
    }

    private List<Height> broadSearch() {
        List<Height> foundPaths = new ArrayList<>();

        for (int y = -1; y <= 1; y += 2) {
            int yIndex = this.currentHeight.getIndex()[0] + y;
            int xIndex = this.currentHeight.getIndex()[1];
            if (isIndexInBoundsY(yIndex)) {
                Height nextHeight = this.mapInformation.get(yIndex)[xIndex];
                if (nextHeight.getHeight() == this.currentHeight.getNextHeightToSearch() && !nextHeight.isAlreadySteppedOn()) {
                    foundPaths.add(nextHeight);
                }
            }
        }

        for (int x = -1; x <= 1; x += 2) {
            int yIndex = this.currentHeight.getIndex()[0];
            int xIndex = this.currentHeight.getIndex()[1] + x;
            if (isIndexInBoundsX(xIndex)) {
                Height nextHeight = this.mapInformation.get(yIndex)[xIndex];
                if (nextHeight.getHeight() == this.currentHeight.getNextHeightToSearch() && !nextHeight.isAlreadySteppedOn()) {
                    foundPaths.add(nextHeight);
                }
            }
        }

        return foundPaths;
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
