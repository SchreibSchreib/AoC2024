package Days.Day6.PartOne.Abstract;

public abstract class Tile {

    protected boolean isVisited = false;
    protected int yIndex;
    protected int xIndex;
    protected char tileSymbol;

    public Tile(int yIndex, int xIndex, char tileSymbol) {
        this.yIndex = yIndex;
        this.xIndex = xIndex;
        this.tileSymbol = tileSymbol;
    }

    public Tile(Tile tileToClone){
        this.yIndex = tileToClone.getYIndex();
        this.xIndex = tileToClone.getXIndex();
        this.tileSymbol = tileToClone.getTileSymbol();
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

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setTileSymbol(char tileSymbol) {
        this.tileSymbol = tileSymbol;
    }

    public void toggleVisitStatus() {
        this.isVisited = !this.isVisited;
    }

    public abstract void process();

    public abstract Tile copy();
}
