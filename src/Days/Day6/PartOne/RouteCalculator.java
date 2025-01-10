package Days.Day6.PartOne;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Enum.Direction;
import Days.Day6.PartOne.Tiles.ObstacleTile;
import Days.Day6.PartOne.Tiles.PlayerTile;

import java.io.IOException;
import java.util.List;

public class RouteCalculator {

    private Boolean reachedEnd = false;
    private final TileLoader tileLoader;
    private final List<Tile[]> tileMap;
    private final PlayerTile playerTile;
    private int currentPosY;
    private int currentPosX;
    private final int walkedSteps;


    public RouteCalculator() throws IOException {
        this.tileLoader = new TileLoader();
        this.tileMap = tileLoader.getLoadedTiles();
        this.playerTile = tileLoader.getPlayerTile();
        this.currentPosY = playerTile.getYIndex();
        this.currentPosX = playerTile.getXIndex();
        this.walkedSteps = calculateRoute();
    }

    private int calculateRoute() {
        while (!reachedEnd) {
            movePlayer();
        }
        return playerTile.getVisitedTiles();
    }

    private void movePlayer() {
        playerTile.process();
        playerTile.setCoordinates();
        int newY = playerTile.getYIndex();
        int newX = playerTile.getXIndex();

        if (isOutOfBounds(newY, newX)) {
            reachedEnd = true;
            return;
        }

        Tile nextTile = tileMap.get(newY)[newX];

        processMove(nextTile, newY, newX);
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
        System.out.println(testCalc.walkedSteps);
    }
}
