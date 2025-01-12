package Days.Day6.PartTwo;

import Days.Day6.PartOne.Abstract.Tile;
import Days.Day6.PartOne.Tiles.ObstacleTile;
import Days.Day6.PartOne.Tiles.PlayerTile;

import java.util.*;

public class ObstaclePlacer {


    private List<Tile[]> copiedMap;
    private PlayerTile copiedPlayer;
    private boolean wasAlreadyHere = false;
    private boolean causesObstacleLoop;
    private boolean reachedEnd = false;
    private static List<String> fieldsWereObstaclesArePlaced = new ArrayList<>();

    public ObstaclePlacer(List<Tile[]> copiedMap, PlayerTile copiedPlayer, int nextFieldY, int nextFieldX) {
        this.copiedMap = copiedMap;
        placeObstacle(nextFieldY, nextFieldX);
        this.copiedPlayer = copiedPlayer;
        if (!wasAlreadyHere) {
            this.causesObstacleLoop = findLoop(nextFieldY, nextFieldX);
        }
    }

    private void placeObstacle(int nextFieldY, int nextFieldX) {
        Tile nextTile = copiedMap.get(nextFieldY)[nextFieldX];
        if (!nextTile.isVisited()) {
            this.copiedMap.get(nextFieldY)[nextFieldX] = new ObstacleTile(nextFieldY, nextFieldX, '#');
            return;
        }
        this.wasAlreadyHere = true;
    }

    private boolean findLoop(int nextFieldY, int nextFieldX) {
        while (!reachedEnd) {
            movePlayer();
            if (causesObstacleLoop) {
                if (!fieldsWereObstaclesArePlaced.contains(nextFieldY + " " + nextFieldX)) {
                    fieldsWereObstaclesArePlaced.add(nextFieldY + " " + nextFieldX);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private void movePlayer() {
        Tile currentTile = copiedMap.get(copiedPlayer.getYIndex())[copiedPlayer.getXIndex()];
        copiedPlayer.setCoordinates();
        Tile nextTile;
        try {
            nextTile = copiedMap.get(copiedPlayer.getYIndex())[copiedPlayer.getXIndex()];
        } catch (IndexOutOfBoundsException e) {
            reachedEnd = true;
            return;
        }

        currentTile.increaseNumberOfTimesTheGuardStepsOnIt();

        if (nextTile instanceof ObstacleTile) {
            copiedPlayer.rotateTileSymbol();
            copiedPlayer.decreaseNumberOfTimesTheGuardStepsOnIt();
            copiedPlayer.setYIndex(currentTile.getYIndex());
            copiedPlayer.setXIndex(currentTile.getXIndex());
            return;
        }

        if (nextTile.getNumberOfTimesTheGuardStepsOnIt() > 4) {
            this.causesObstacleLoop = true;
            printMap();
        }

    }

    private void printMap() {
        for (Tile[] row : copiedMap) {
            for (Tile tile : row) {
                System.out.print(tile.getTileSymbol());
            }
            System.out.println();
        }
    }

    public static List<String> getFieldsWereObstaclesArePlaced() {
        return fieldsWereObstaclesArePlaced;
    }
}
