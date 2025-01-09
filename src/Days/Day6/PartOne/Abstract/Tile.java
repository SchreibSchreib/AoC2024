package Days.Day6.PartOne.Abstract;

public abstract class Tile {

    protected int yIndex;
    protected int xIndex;
    protected char tileSymbol;

    public Tile(int yIndex, int xIndex, char tileSymbol) {
        this.yIndex = yIndex;
        this.xIndex = xIndex;
        this.tileSymbol = tileSymbol;

    }

    public int getYIndex() {
        return this.yIndex;
    }

    public int getXIndex() {
        return this.xIndex;
    }

    public char getTileSymbol() {
        return this.tileSymbol;
    }

    public abstract void process();
}
