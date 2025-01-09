package Days.Day6.PartOne.Tiles;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Enum.Direction;

public class PlayerTile extends Tile {

    private int visitedTiles = 1;

    public PlayerTile(int yIndex, int xIndex, char tileSymbol) {
        super(yIndex, xIndex, tileSymbol);
    }

    @Override
    public void process() {
        System.out.print("Checking next tile: ");
    }

    public void increaseVisitedTiles() {
        this.visitedTiles++;
    }

    public void rotateTileSymbol() {
        char[] directions = {'^', '>', 'v', '<'};
        int indexNextDirection = new String(directions).indexOf(this.tileSymbol);
        this.tileSymbol = directions[indexNextDirection + 1 % directions.length];
    }

    public void setCoordinates(Direction direction) {
        switch (direction) {
            case XPOSITIVE -> this.xIndex++;
            case XNEGATIVE -> this.xIndex--;
            case YPOSITIVE -> this.yIndex++;
            case YNEGATIVE -> this.yIndex--;
        }
    }
}
