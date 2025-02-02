package Days.Day12.PartOne;

public class GardenPlot {

    private int sides = 4;
    private final int[] coordinates;
    private final char plotSymbol;
    private boolean isAlreadyInGroup = false;

    public GardenPlot(int yCoordinate, int xCoordinate, char plotSymbol) {
        this.plotSymbol = plotSymbol;
        coordinates = new int[]{yCoordinate, xCoordinate};
    }

    public void calculateSides(int sides) {
        this.sides += sides;
    }

    public int getPerimeter() {
        return sides;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public char getPlotSymbol() {
        return plotSymbol;
    }

    public void setAlreadyInGroup() {
        isAlreadyInGroup = true;
    }

    public boolean isAlreadyInGroup() {
        return isAlreadyInGroup;
    }
}
