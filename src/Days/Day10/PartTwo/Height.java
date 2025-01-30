package Days.Day10.PartTwo;

public class Height {

    private final int height;
    private final int[] index;
    private final int nextNumberToSearch;
    private boolean leadsThisHeightToGoal = false;

    public Height(int height, int[] index) {
        this.height = height;
        this.index = index;
        nextNumberToSearch = calculateNumber();
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
}
