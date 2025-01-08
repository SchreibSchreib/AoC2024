package Days.Day6.PartOne;

import Days.Day6.InputFormatter;
import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Tiles.FreeTile;
import Days.Day6.PartOne.Tiles.ObstacleTile;
import Days.Day6.PartOne.Tiles.PlayerTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileLoader {

    List<Tile[]> loadedTiles;

    public TileLoader() throws IOException {
        InputFormatter input = new InputFormatter();
        this.loadedTiles = convertCharsToTiles(input.getConvertedInput());
    }

    private List<Tile[]> convertCharsToTiles(List<char[]> convertedInput) {
        List<Tile[]> tiles = new ArrayList<>();
        Tile[] arrayOfTiles = new Tile[convertedInput.getFirst().length];

        for (int y = 0; y < convertedInput.size(); y++) {
            for (int x = 0; x < arrayOfTiles.length; x++) {
                switch (convertedInput.get(y)[x]) {
                    case '#' -> arrayOfTiles[x] = new ObstacleTile(y, x, '#');
                    case '^' -> arrayOfTiles[x] = new PlayerTile(y, x, '^');
                    case '>' -> arrayOfTiles[x] = new PlayerTile(y, x, '>');
                    case '<' -> arrayOfTiles[x] = new PlayerTile(y, x, '<');
                    case 'v' -> arrayOfTiles[x] = new PlayerTile(y, x, 'v');
                    default -> arrayOfTiles[x] = new FreeTile(y, x, '.');
                }
            }
            tiles.add(arrayOfTiles);
        }
        return tiles;
    }

    public List<Tile[]> getLoadedTiles() {
        return this.loadedTiles;
    }

    public static void main(String[] args) throws IOException {
        TileLoader testLoader = new TileLoader();
        List<Tile[]> tiles = testLoader.getLoadedTiles();

    }
}

