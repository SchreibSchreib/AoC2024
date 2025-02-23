package Days.Day16.PartOne;

import Days.Day16.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {

    private List<char[]> input;
    private List<Tile[]> tileMap;

    public MapBuilder() throws IOException {
        InputManipulatable<List<char[]>> input = new InputFormatter();
        this.input = input.getConvertedInput();
        tileMap = convertInput();
    }

    private List<Tile[]> convertInput() {
        List<Tile[]> result = new ArrayList<>();
        Tile[] row = new Tile[input.getFirst().length];

        for (int yIndex = 0; yIndex < input.size(); yIndex++) {
            result.add(convertCharsToTiles(input.get(yIndex), yIndex));
        }

        return result;
    }

    private Tile[] convertCharsToTiles(char[] chars, int yIndex) {
        Tile[] result = new Tile[chars.length];

        for (int xIndex = 0; xIndex < chars.length; xIndex++) {
            if (chars[xIndex] == '.') {
                result[xIndex] = new Tile(new int[]{yIndex, xIndex}, checkForIntersection(yIndex, xIndex));
            }
        }
        return result;
    }

    private boolean checkForIntersection(int yIndex, int xIndex) {
        int pathsFromHere = 0;
        if (input.get(yIndex)[xIndex - 1] == '.') {
            pathsFromHere++;
        }
        if (input.get(yIndex)[xIndex + 1] == '.') {
            pathsFromHere++;
        }
        if (input.get(yIndex + 1)[xIndex] == '.') {
            pathsFromHere++;
        }
        if (input.get(yIndex - 1)[xIndex - 1] == '.') {
            pathsFromHere++;
        }
        return pathsFromHere >= 3;
    }

    public static void main(String[] args) throws IOException {
        MapBuilder testBuilder = new MapBuilder();

    }
}
