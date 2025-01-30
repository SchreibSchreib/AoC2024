package Days.Day6.PartTwo;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Tiles.FreeTile;
import Days.Day6.PartOne.Tiles.ObstacleTile;
import Days.Day6.PartOne.Tiles.PlayerTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteCalculator {

    private Boolean reachedEnd = false;
    private final TileLoader tileLoader;
    private final List<Tile[]> tileMap;
    private final PlayerTile playerTile;
    private int currentPosY;
    private int currentPosX;
    private int validPlacedObjects = 0;


    public RouteCalculator() throws IOException {
        tileLoader = new TileLoader();
        tileMap = tileLoader.getLoadedTiles();
        playerTile = tileLoader.getPlayerTile();
        currentPosY = playerTile.getYIndex();
        currentPosX = playerTile.getXIndex();
        validPlacedObjects = calculateRoute();
    }

    private int calculateRoute() {
        while (!reachedEnd) {
            movePlayer();
        }
        return ObstaclePlacer.getFieldsWereObstaclesArePlaced().size();
    }

    private void movePlayer() {
        playerTile.process();
        tileMap.get(playerTile.getYIndex())[playerTile.getXIndex()].setTileSymbol(playerTile.getTileSymbol());
        PlayerTile deepCopyPlayer = new PlayerTile(playerTile);
        playerTile.setCoordinates();
        int newY = playerTile.getYIndex();
        int newX = playerTile.getXIndex();

        if (isOutOfBounds(newY, newX)) {
            reachedEnd = true;
            return;
        }

        Tile nextTile = tileMap.get(newY)[newX];

        checkForLoop(tileMap, deepCopyPlayer, newY, newX);

        processMove(nextTile, newY, newX);
    }

    private void checkForLoop(List<Tile[]> tileMap, PlayerTile deepCopyPlayer, int newY, int newX) {
        List<Tile[]> deepCopyTiles = copyTiles(tileMap);
        new ObstaclePlacer(deepCopyTiles, deepCopyPlayer, newY, newX);
    }

    private List<Tile[]> copyTiles(List<Tile[]> tileMap) {
        List<Tile[]> tiles = new ArrayList<>();

        for (Tile[] row : tileMap) {
            Tile[] copiedRow = new Tile[row.length];
            for (int i = 0; i < row.length; i++) {
                copiedRow[i] = row[i].copy();
            }
            tiles.add(copiedRow);
        }

        return tiles;
    }

    private void processMove(Tile nextTile, int newY, int newX) {
        if (!(nextTile instanceof ObstacleTile)) {
            nextTile.process();
            currentPosY = newY;
            currentPosX = newX;
            if (!nextTile.isVisited()) {
                playerTile.increaseVisitedTiles();
                nextTile.toggleVisitStatus();
            }
        } else {
            nextTile.process();
            playerTile.rotateTileSymbol();
            playerTile.setYIndex(currentPosY);
            playerTile.setXIndex(currentPosX);
        }
    }

    private boolean isOutOfBounds(int newY, int newX) {
        return (newY > tileMap.size() - 1 || newY < 0) || (newX > tileMap.get(newY).length - 1 || newX < 0);
    }

    public static void main(String[] args) throws IOException {
        RouteCalculator testCalc = new RouteCalculator();
        System.out.println(testCalc.validPlacedObjects);
        System.out.println(testCalc.playerTile.getVisitedTiles());
        for (Tile[] tiles : testCalc.tileMap) {
            for (Tile tile : tiles) {
                System.out.print(tile.getTileSymbol());
            }
            System.out.println();
        }
    }
}
