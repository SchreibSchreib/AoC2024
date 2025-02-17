package Days.Day15.PartOne;

import Days.Day15.InputFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WareHouseAndMovementMapper {

    private final List<char[]> input;
    private Map<Integer, List<Tile>> tileMap;
    private List<Character> listOfMovements = new ArrayList<>();
    private int indexOfEmptyEntry;

    public WareHouseAndMovementMapper() throws IOException {
        input = new InputFormatter().getConvertedInput();
        tileMap = createTileMap();
        listOfMovements = createListOfMovements();
    }

    private List<Character> createListOfMovements() {

    }

    private Map<Integer, List<Tile>> createTileMap() {
        Map<Integer, List<Tile>> mappedTiles = new HashMap<>();

        for (int yIndex = 0; yIndex < input.size(); yIndex++) {
            mappedTiles.put(yIndex, new ArrayList<>());
            for (int xIndex = 0; xIndex < input.getFirst().length; xIndex++) {
                mappedTiles.get(yIndex).add(new Tile(new int[]{yIndex, xIndex}, input.get(yIndex)[xIndex]));
            }
        }
        return mappedTiles;
    }
}
