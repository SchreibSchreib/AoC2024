package Days.Day6.PartOne.Tiles;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Enum.Direction;

public class PlayerTile extends Tile {

    private int visitedTiles = 1;
    private Direction currentDirection;

    public PlayerTile(int yIndex, int xIndex, char tileSymbol) {
        super(yIndex, xIndex, tileSymbol);
        this.currentDirection = setCurrentDirection();
        this.isVisited = true;
    }

    public PlayerTile(PlayerTile playerTileToCopy) {
        super(playerTileToCopy.getYIndex(), playerTileToCopy.getXIndex(), playerTileToCopy.getTileSymbol());
        this.visitedTiles = playerTileToCopy.getVisitedTiles();
        this.currentDirection = playerTileToCopy.getCurrentDirection();
    }

    private Direction setCurrentDirection() {
        return switch (this.tileSymbol) {
            case '>' -> Direction.XPOSITIVE;
            case '<' -> Direction.XNEGATIVE;
            case 'v' -> Direction.YPOSITIVE;
            default -> Direction.YNEGATIVE;
        };
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
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
        this.visitedTiles++;
    }

    public void rotateTileSymbol() {
        char[] directions = {'^', '>', 'v', '<'};
        int indexNextDirection = new String(directions).indexOf(this.tileSymbol);
        this.tileSymbol = directions[(indexNextDirection + 1) % directions.length];
        this.currentDirection = setCurrentDirection();
    }

    public void setCoordinates() {
        switch (this.currentDirection) {
            case XPOSITIVE -> this.xIndex++;
            case XNEGATIVE -> this.xIndex--;
            case YPOSITIVE -> this.yIndex++;
            case YNEGATIVE -> this.yIndex--;
        }
    }

    public void setYIndex(int yIndex) {
        this.yIndex = yIndex;
    }

    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public int getVisitedTiles() {
        return this.visitedTiles;
    }
}
