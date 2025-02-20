package Days.Day15.PartTwo;

import Days.Day15.PartOne.Enums.tileState;

public class Tile {

    private int[] coordinates;
    private char tileSymbol;
    private tileState state;

    public Tile(int[] coordinates, char tileSymbol) {
        this.coordinates = coordinates;
        this.tileSymbol = tileSymbol;
        state = evaluateState(tileSymbol);
    }

    private tileState evaluateState(char tileSymbol) {
        return switch (tileSymbol) {
            case '#' -> tileState.BLOCKING;
            case '.' -> tileState.FREE;
            case '[', ']' -> tileState.MOVEABLE;
            default -> tileState.ROBOT;
        };
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public tileState getState() {
        return state;
    }

    public char getTileSymbol() {
        return tileSymbol;
    }
}
