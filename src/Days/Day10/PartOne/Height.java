package Days.Day10.PartOne;

public class Height {

    private final int height;
    private final int[] index;
    private final int nextNumberToSearch;
    private boolean alreadySteppedOn = false;

    public Height(int height, int[] index) {
        this.height = height;
        this.index = index;
        this.nextNumberToSearch = calculateNumber();
    }

    private int calculateNumber() {
        return height + 1;
    }

    public int getHeight() {
        return height;
    }

    public int[] getIndex() {
        return index;
    }

    public int getNextHeightToSearch() {
        return nextNumberToSearch;
    }

    public boolean isAlreadySteppedOn() {
        return alreadySteppedOn;
    }

    public void setSteppedOn() {
        this.alreadySteppedOn = !alreadySteppedOn;
    }

    public void resetSteppedOn() {
        this.alreadySteppedOn = false;
    }
}
