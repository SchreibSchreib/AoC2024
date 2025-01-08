package Days.Day6.PartOne.Tiles;

import Days.Day6.PartOne.Abstract.Tile;

public class FreeTile extends Tile {

    public FreeTile(int yIndex, int xIndex, char tileSymbol) {
        super(yIndex, xIndex, tileSymbol);
    }

    @Override
    public void process() {
        System.out.println("Way is free...moving forward!");
    }
}
