package Days.Day12.PartOne;

public class GardenPlot {

    private final int sides;
    private final int[] coordinates;
    private final char plotSymbol;

    public GardenPlot(int yCoordinate, int xCoordinate, char plotSymbol, int sides) {
        this.sides = sides;
        this.plotSymbol = plotSymbol;
        coordinates = new int[]{yCoordinate, xCoordinate};
    }

    public int getSides() {
        return sides;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public char getPlotSymbol() {
        return plotSymbol;
    }
}
