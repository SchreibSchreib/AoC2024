package Days.Day15.PartTwo;

import Days.Day15.InputFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WareHouseAndMovementMapper {

    private final List<char[]> input;
    private final Map<Integer, List<Tile>> tileMap;
    private final List<char[]> listOfMovements;
    private int indexOfEmptyEntry;
    private int[] indexOfStartingPosition;

    public WareHouseAndMovementMapper() throws IOException {
        input = new InputFormatter().getConvertedInput();
        tileMap = createTileMap();
        listOfMovements = createListOfMovements();
    }

    private List<char[]> createListOfMovements() {
        return input.subList(indexOfEmptyEntry + 1, input.size());
    }

    private Map<Integer, List<Tile>> createTileMap() {
        Map<Integer, List<Tile>> mappedTiles = new HashMap<>();
        int iterationMultiplier = 0;

        for (int yIndex = 0; yIndex < input.size(); yIndex++) {
            if (input.get(yIndex).length < 1) {
                indexOfEmptyEntry = yIndex;
                break;
            }
            iterationMultiplier++;
            mappedTiles.put(yIndex, new ArrayList<>());
            for (int xIndex = 0; xIndex < input.getFirst().length; xIndex++) {
                char tileSymbol = input.get(yIndex)[xIndex];
                if (tileSymbol == '@') {
                    indexOfStartingPosition = new int[]{yIndex, xIndex};
                }
                Tile[] convertedTiles = convertToBiggerTile(tileSymbol, yIndex, xIndex, iterationMultiplier);
                mappedTiles.get(yIndex).add(convertedTiles[0]);
                mappedTiles.get(yIndex).add(convertedTiles[1]);
            }
        }
        return mappedTiles;
    }

    private Tile[] convertToBiggerTile(char tileSymbol, int yIndex, int xIndex, int iterationMultiplier) {
        int[] xIndices = {xIndex * iterationMultiplier, xIndex * iterationMultiplier + 1};
        return switch (tileSymbol) {
            case '#' ->
                    new Tile[]{new Tile(new int[]{yIndex, xIndices[0]}, tileSymbol), new Tile(new int[]{yIndex, xIndices[1]}, '#')};
            case 'O' ->
                    new Tile[]{new Tile(new int[]{yIndex, xIndices[0]}, '['), new Tile(new int[]{yIndex, xIndices[1]}, ']')};
            default ->
                    new Tile[]{new Tile(new int[]{yIndex, xIndices[0]}, tileSymbol), new Tile(new int[]{yIndex, xIndices[1]}, '.')};

        };
    }


    public Map<Integer, List<Tile>> getTileMap() {
        return tileMap;
    }

    public List<char[]> getMovementList() {
        return listOfMovements;
    }

    public int[] getIndexOfStartingPosition() {
        return indexOfStartingPosition;
    }

    public static void main(String[] args) throws IOException {
        WareHouseAndMovementMapper testMapper = new WareHouseAndMovementMapper();

    }
}
