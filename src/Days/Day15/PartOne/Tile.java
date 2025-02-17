package Days.Day15.PartOne;

import Days.Day15.PartOne.Enums.tileState;

public class Tile {

    private int[] coordinates;
    private tileState state;

    public Tile(int[] coordinates, char tileSymbol) {
        this.coordinates = coordinates;
        state = evaluateState(tileSymbol);
    }

    private tileState evaluateState(char tileSymbol) {
        return switch (tileSymbol) {
            case '#' -> tileState.BLOCKING;
            case '.' -> tileState.FREE;
            case 'O' -> tileState.MOVEABLE;
            default -> tileState.PLAYER;
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
}
