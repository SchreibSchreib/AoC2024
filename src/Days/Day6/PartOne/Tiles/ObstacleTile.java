package Days.Day6.PartOne.Tiles;

import Days.Day6.PartOne.Abstract.Tile;

public class ObstacleTile extends Tile {

    public ObstacleTile(int yIndex, int xIndex, char tileSymbol) {
        super(yIndex, xIndex, tileSymbol);
    }

    @Override
    public void process() {
        System.out.println("Way is blocked...turning by 90 degrees");
    }
}
