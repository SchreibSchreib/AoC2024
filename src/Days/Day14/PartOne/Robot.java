package Days.Day14.PartOne;

public class Robot {

    private static int numberOfRobotsWithFourNeighbours = 0;
    private int positionY;
    private int positionX;
    private final int velocityY;
    private final int velocityX;

    public Robot(int positionY, int positionX, int velocityY, int velocityX) {
        this.positionY = positionY;
        this.positionX = positionX;
        this.velocityY = velocityY;
        this.velocityX = velocityX;
    }

    public void setPositionY(int moves, int bathroomSizeY) {
        positionY = (positionY + (velocityY * moves)) % bathroomSizeY;
        if (positionY < 0) {
            positionY += bathroomSizeY;
        }
    }

    public void setPositionX(int moves, int bathroomSizeX) {
        positionX = (positionX + (velocityX * moves)) % bathroomSizeX;
        if (positionX < 0) {
            positionX += bathroomSizeX;
        }
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public static void incrementNumberOfRobotsWithFourNeighbours() {
        numberOfRobotsWithFourNeighbours++;
    }

    public static void resetNumberOfRobotsWithFourNeighbours() {
        numberOfRobotsWithFourNeighbours = 0;
    }

    public static int getNumberOfRobotsWithFourNeighbours() {
        return numberOfRobotsWithFourNeighbours;
    }
}
