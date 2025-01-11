package Days.Day6.PartOne.Tiles;

import Days.Day6.PartOne.Abstract.Tile;

public class FreeTile extends Tile {

    public FreeTile(int yIndex, int xIndex, char tileSymbol) {
        super(yIndex, xIndex, tileSymbol);
    }

    public FreeTile(int yIndex, int xIndex, char tileSymbol, boolean convertedTile) {
        super(yIndex,xIndex,tileSymbol);
        this.isVisited = convertedTile;
    }

    public FreeTile(FreeTile freeTileToCopy) {
        super(freeTileToCopy.getYIndex(), freeTileToCopy.getXIndex(), freeTileToCopy.getTileSymbol());
        this.isVisited = freeTileToCopy.isVisited;
    }

    @Override
    public void process() {
        System.out.println("Way is free...moving forward!");
    }

    @Override
    public Tile copy() {
        return new FreeTile(this);
    }
}
