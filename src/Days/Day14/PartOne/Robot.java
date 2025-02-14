package Days.Day14.PartOne;

public class Robot {

    private int positionY;
    private int positionX;
    private final int velocityY;
    private final int velocityX;
    private boolean hasFourNeighbours = false;

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

    public boolean hasFourNeighbours() {
        return hasFourNeighbours;
    }

    public void setHasFourNeighbours(boolean hasFourNeighbours) {
        this.hasFourNeighbours = hasFourNeighbours;
    }
}
