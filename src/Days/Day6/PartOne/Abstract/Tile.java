package Days.Day6.PartOne.Abstract;

public abstract class Tile {

    protected boolean isVisited = false;
    protected int yIndex;
    protected int xIndex;
    protected char tileSymbol;
    protected int numberOfTimesTheGuardStepsOnIt;

    public Tile(int yIndex, int xIndex, char tileSymbol) {
        this.yIndex = yIndex;
        this.xIndex = xIndex;
        this.tileSymbol = tileSymbol;
        this.numberOfTimesTheGuardStepsOnIt = 0;
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

    public void increaseNumberOfTimesTheGuardStepsOnIt() {
        this.numberOfTimesTheGuardStepsOnIt++;
    }

    public void decreaseNumberOfTimesTheGuardStepsOnIt() {
        this.numberOfTimesTheGuardStepsOnIt--;
    }

    public int getNumberOfTimesTheGuardStepsOnIt() {
        return this.numberOfTimesTheGuardStepsOnIt;
    }
}
