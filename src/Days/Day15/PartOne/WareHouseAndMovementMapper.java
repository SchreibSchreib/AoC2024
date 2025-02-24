package Days.Day15.PartOne;

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
    private int[] indexOfStartingposition;

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

        for (int yIndex = 0; yIndex < input.size(); yIndex++) {
            if (input.get(yIndex).length < 1) {
                indexOfEmptyEntry = yIndex;
                break;
            }
            mappedTiles.put(yIndex, new ArrayList<>());
            for (int xIndex = 0; xIndex < input.getFirst().length; xIndex++) {
                char tileSymbol = input.get(yIndex)[xIndex];
                if (tileSymbol == '@') {
                    indexOfStartingposition = new int[]{yIndex, xIndex};
                }
                mappedTiles.get(yIndex).add(new Tile(new int[]{yIndex, xIndex}, tileSymbol));
            }
        }
        return mappedTiles;
    }

    public Map<Integer, List<Tile>> getTileMap() {
        return tileMap;
    }

    public List<char[]> getMovementList() {
        return listOfMovements;
    }

    public int[] getIndexOfStartingposition() {
        return indexOfStartingposition;
    }

    public static void main(String[] args) throws IOException {
        WareHouseAndMovementMapper testMapper = new WareHouseAndMovementMapper();

    }
}
