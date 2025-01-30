package Days.Day6.PartTwo;

import Days.Day6.InputFormatter;
import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Tiles.FreeTile;
import Days.Day6.PartOne.Tiles.ObstacleTile;
import Days.Day6.PartOne.Tiles.PlayerTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileLoader {

    private final List<Tile[]> loadedTiles;
    private final PlayerTile playerTile;

    public TileLoader() throws IOException {
        InputFormatter input = new InputFormatter();
        loadedTiles = convertCharsToTiles(input.getConvertedInput());
        playerTile = findPlayer();
        replacePlayerWithFreeTile();
    }

    private void replacePlayerWithFreeTile() {
        int playerY = playerTile.getYIndex();
        int playerX = playerTile.getXIndex();
        loadedTiles.get(playerY)[playerX] = new FreeTile(playerY, playerX, '.', true);
    }


    private PlayerTile findPlayer() {
        for (Tile[] tiles : loadedTiles) {
            for (Tile tile : tiles) {
                if (tile instanceof PlayerTile) {
                    return (PlayerTile) tile;
                }
            }
        }
        throw new IllegalStateException("No PlayerTile found on the map!");
    }

    private List<Tile[]> convertCharsToTiles(List<char[]> convertedInput) {
        List<Tile[]> tiles = new ArrayList<>();

        for (int y = 0; y < convertedInput.size(); y++) {
            Tile[] arrayOfTiles = new Tile[convertedInput.getFirst().length];
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
        return loadedTiles;
    }

    public PlayerTile getPlayerTile() {
        return playerTile;
    }

    public static void main(String[] args) throws IOException {
        TileLoader testLoader = new TileLoader();
        List<Tile[]> tiles = testLoader.getLoadedTiles();

    }
}

