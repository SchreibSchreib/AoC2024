package Days.Day16.PartOne;

public class Tile {

    private final int[] coordinates;
    private int pointsToGetHere = 0;
    private int pathPoints;
    private int pointsToReachGoalFromHere = 0;
    private boolean hasReachedGoal = false;
    private final boolean isIntersection;

    public Tile(int[] coordinates, boolean isIntersection) {
        this.coordinates = coordinates;
        this.isIntersection = isIntersection;
    }

    public int getPointsToGetHere() {
        return pointsToGetHere;
    }

    public void setPointsToGetHere(int pointsToGetHere) {
        this.pointsToGetHere = pointsToGetHere;
    }

    public void setPathPoints(int pathPoints) {
        this.pathPoints = pathPoints;
    }

    public int getPointsToReachGoalFromHere() {
        return pointsToReachGoalFromHere;
    }

    public void setPointsToReachGoalFromHere() {
        if (hasReachedGoal) {
            pointsToReachGoalFromHere = pathPoints - pointsToGetHere;
        }
    }

    public void setHasReachedGoal() {
        hasReachedGoal = true;
    }

    public boolean isIntersection() {
        return isIntersection;
    }
}
