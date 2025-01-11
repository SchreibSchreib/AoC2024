package Days.Day6.PartTwo;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Tiles.ObstacleTile;
import Days.Day6.PartOne.Tiles.PlayerTile;

import java.util.*;

public class ObstaclePlacer {

    private static int numberOfValidObjects;
    private static int numberOfVisitedTilesMoved;
    private static List<String> fieldsWhereObjectsArePlaced = new ArrayList<>();
    private boolean reachedEnd = false;
    private final List<Tile[]> tileMap;
    private final PlayerTile playerTile;
    private int currentPosY;
    private int currentPosX;
    private boolean foundLoop = false;

    public ObstaclePlacer(List<Tile[]> newMap, PlayerTile playerTile, int yIndexObstacle, int xIndexObstacle) {
        this.tileMap = loadObstacle(newMap, yIndexObstacle, xIndexObstacle);
        this.playerTile = playerTile;
        this.currentPosY = playerTile.getYIndex();
        this.currentPosX = playerTile.getXIndex();
        checkForLoop(fieldsWhereObjectsArePlaced, currentPosY, currentPosX);
    }

    private List<Tile[]> loadObstacle(List<Tile[]> newMap, int yIndexObstacle, int xIndexObstacle) {
        newMap.get(yIndexObstacle)[xIndexObstacle] = new ObstacleTile(yIndexObstacle, xIndexObstacle, '#');
        return newMap;
    }

    private void checkForLoop(List<String> fieldsWhereObjectsArePlaced, int yIndexObject, int xIndexObject) {
        boolean isLoop = calculateRoute();
        if (isLoop) {
            if (fieldsWhereObjectsArePlaced.contains(yIndexObject + " " + xIndexObject)) {
                return;
            }
            fieldsWhereObjectsArePlaced.add(yIndexObject + " " + xIndexObject);
            increaseNumberOfValidObjects();
        }
    }

    private void printMap() {
        for (Tile[] tiles : tileMap) {
            for (Tile tile : tiles) {
                System.out.print(tile.getTileSymbol());
            }
            System.out.println();
        }
    }

    private boolean calculateRoute() {
        System.out.print("Checking for Loop...");
        while (!reachedEnd) {
            movePlayer();
            if (foundLoop) {
                System.out.println("Found loop!");
                return true;
            }
        }
        System.out.println("Did not find loop!");
        return false;
    }

    private void movePlayer() {
        tileMap.get(playerTile.getYIndex())[playerTile.getXIndex()].setTileSymbol(playerTile.getTileSymbol());
        playerTile.setCoordinates();
        int newY = playerTile.getYIndex();
        int newX = playerTile.getXIndex();

        if (isOutOfBounds(newY, newX)) {
            reachedEnd = true;
            return;
        }

        Tile nextTile = tileMap.get(newY)[newX];

        if (nextTile.isVisited()) {
            numberOfVisitedTilesMoved++;
        }

        if (numberOfVisitedTilesMoved > 20000) {
            foundLoop = true;
            numberOfVisitedTilesMoved = 0;
            return;
        }

        processMove(nextTile, newY, newX);
    }

    private void processMove(Tile nextTile, int newY, int newX) {
        if (!(nextTile instanceof ObstacleTile)) {
            currentPosY = newY;
            currentPosX = newX;
            if (!nextTile.isVisited()) {
                playerTile.increaseVisitedTiles();
                nextTile.toggleVisitStatus();
            }
        } else {
            playerTile.rotateTileSymbol();
            playerTile.setYIndex(currentPosY);
            playerTile.setXIndex(currentPosX);
        }
    }

    private boolean isOutOfBounds(int newY, int newX) {
        return (newY > tileMap.size() - 1 || newY < 0) || (newX > tileMap.get(newY).length - 1 || newX < 0);
    }

    public static int getNumberOfValidObjects() {
        return numberOfValidObjects;
    }

    public void increaseNumberOfValidObjects() {
        numberOfValidObjects++;
    }
}
