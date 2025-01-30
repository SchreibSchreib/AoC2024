package Days.Day6.PartOne.Tiles;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Enum.Direction;

public class PlayerTile extends Tile {

    private int visitedTiles = 1;
    private Direction currentDirection;

    public PlayerTile(int yIndex, int xIndex, char tileSymbol) {
        super(yIndex, xIndex, tileSymbol);
        currentDirection = setCurrentDirection();
        isVisited = true;
    }

    public PlayerTile(PlayerTile playerTileToCopy) {
        super(playerTileToCopy.getYIndex(), playerTileToCopy.getXIndex(), playerTileToCopy.getTileSymbol());
        visitedTiles = playerTileToCopy.getVisitedTiles();
        currentDirection = playerTileToCopy.getCurrentDirection();
    }

    private Direction setCurrentDirection() {
        return switch (tileSymbol) {
            case '>' -> Direction.XPOSITIVE;
            case '<' -> Direction.XNEGATIVE;
            case 'v' -> Direction.YPOSITIVE;
            default -> Direction.YNEGATIVE;
        };
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void process() {
        System.out.println("Checking next tile: ");
    }

    @Override
    public Tile copy() {
        return new PlayerTile(this);
    }

    public void increaseVisitedTiles() {
        visitedTiles++;
    }

    public void rotateTileSymbol() {
        char[] directions = {'^', '>', 'v', '<'};
        int indexNextDirection = new String(directions).indexOf(tileSymbol);
        tileSymbol = directions[(indexNextDirection + 1) % directions.length];
        currentDirection = setCurrentDirection();
    }

    public void setCoordinates() {
        switch (currentDirection) {
            case XPOSITIVE -> xIndex++;
            case XNEGATIVE -> xIndex--;
            case YPOSITIVE -> yIndex++;
            case YNEGATIVE -> yIndex--;
        }
    }

    public void setYIndex(int yIndex) {
        this.yIndex = yIndex;
    }

    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public int getVisitedTiles() {
        return visitedTiles;
    }
}
