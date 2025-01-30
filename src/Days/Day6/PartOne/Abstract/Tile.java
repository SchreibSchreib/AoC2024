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
        numberOfTimesTheGuardStepsOnIt = 0;
    }

    public int getYIndex() {
        return yIndex;
    }

    public int getXIndex() {
        return xIndex;
    }

    public char getTileSymbol() {
        return tileSymbol;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setTileSymbol(char tileSymbol) {
        this.tileSymbol = tileSymbol;
    }

    public void toggleVisitStatus() {
        isVisited = !isVisited;
    }

    public abstract void process();

    public abstract Tile copy();

    public void increaseNumberOfTimesTheGuardStepsOnIt() {
        numberOfTimesTheGuardStepsOnIt++;
    }

    public void decreaseNumberOfTimesTheGuardStepsOnIt() {
        numberOfTimesTheGuardStepsOnIt--;
    }

    public int getNumberOfTimesTheGuardStepsOnIt() {
        return numberOfTimesTheGuardStepsOnIt;
    }
}
