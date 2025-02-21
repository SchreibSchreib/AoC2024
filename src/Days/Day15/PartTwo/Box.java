package Days.Day15.PartTwo;

public class Box {

    public int yCoordinate;
    public int[] xCoordinates;

    public Box(char boxSide, int yCoordinate, int xCoordinate) {
        this.yCoordinate = yCoordinate;
        xCoordinates = calculateCoordinates(boxSide, xCoordinate);
    }

    private int[] calculateCoordinates(char boxSide, int xCoordinate) {
        if (boxSide == '[') {
            return new int[]{xCoordinate, xCoordinate + 1};
        }
        return new int[]{xCoordinate - 1, xCoordinate};
    }

    public int getLeftBoxSide() {
        return xCoordinates[0];
    }

    public int getRightBoxSide() {
        return xCoordinates[1];
    }
}
