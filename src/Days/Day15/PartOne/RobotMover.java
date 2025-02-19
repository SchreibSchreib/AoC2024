package Days.Day15.PartOne;

import Days.Day15.PartOne.Enums.tileState;

import java.io.IOException;
import java.util.*;

public class RobotMover {

    private final Map<Integer, List<Tile>> warehouseMap;
    private final List<char[]> moveSet;
    private int[] startingPosition;

    public RobotMover() throws IOException {
        WareHouseAndMovementMapper warehouseInformation = new WareHouseAndMovementMapper();
        warehouseMap = warehouseInformation.getTileMap();
        moveSet = warehouseInformation.getMovementList();
        startingPosition = warehouseInformation.getIndexOfStartingposition();
        evaluateRobotMovement();
    }

    private void evaluateRobotMovement() {
        for (char[] movePattern : moveSet) {
            for (char move : movePattern) {
                int[] stepDirection = analyzeMove(move);
                Tile robotTile = warehouseMap.get(startingPosition[0]).get(startingPosition[1]);
                Tile nextTile = warehouseMap.get(startingPosition[0] + stepDirection[0]).get(startingPosition[1] + stepDirection[1]);
                List<Tile> affectedTilesInCurrentMoveList = new ArrayList<>(Collections.singletonList(robotTile));

                executeMoveFactory(stepDirection, nextTile, affectedTilesInCurrentMoveList);
            }
        }
    }

    private int[] analyzeMove(char move) {
        return switch (move) {
            case '^' -> new int[]{-1, 0};
            case 'v' -> new int[]{1, 0};
            case '<' -> new int[]{0, -1};
            default -> new int[]{0, 1};
        };
    }

    private void executeMoveFactory(int[] stepDirection, Tile nextTile, List<Tile> affectedTilesInCurrentMoveList) {
        affectedTilesInCurrentMoveList.add(nextTile);

        if (nextTile.getState() == tileState.FREE) {
            executeMove(stepDirection, affectedTilesInCurrentMoveList);
        }
        if (nextTile.getState() == tileState.MOVEABLE) {
            nextTile = warehouseMap.get(nextTile.getCoordinates()[0] + stepDirection[0]).get(nextTile.getCoordinates()[1] + stepDirection[1]);
            executeMoveFactory(stepDirection, nextTile, affectedTilesInCurrentMoveList);
        }
    }

    private void executeMove(int[] stepDirection, List<Tile> affectedTilesInCurrentMoveList) {
        int[] initialStartingPosition = affectedTilesInCurrentMoveList.getFirst().getCoordinates();

        for (Tile affectedTile : affectedTilesInCurrentMoveList) {
            if (affectedTile.getState() == tileState.FREE) {
                affectedTile.setCoordinates(initialStartingPosition);
                adjustMap(affectedTile);
            } else {
                int nextYCoordinates = affectedTile.getCoordinates()[0] + stepDirection[0];
                int nextXCoordinates = affectedTile.getCoordinates()[1] + stepDirection[1];
                affectedTile.setCoordinates(new int[]{nextYCoordinates, nextXCoordinates});
                adjustMap(affectedTile);
            }
        }
    }

    private void adjustMap(Tile affectedTile) {
        if (affectedTile.getState() == tileState.ROBOT) {
            startingPosition = affectedTile.getCoordinates();
        }
        warehouseMap.get(affectedTile.getCoordinates()[0]).set(affectedTile.getCoordinates()[1], affectedTile);
    }

    public Map<Integer, List<Tile>> getEvaluatedWarehouseMap() {
        return warehouseMap;
    }

    public static void main(String[] args) throws IOException {
        RobotMover testMover = new RobotMover();

        for (Map.Entry<Integer, List<Tile>> entry : testMover.warehouseMap.entrySet()) {
            for (Tile affectedTile : entry.getValue()) {
                System.out.print(affectedTile.getTileSymbol());
            }
            System.out.println();
        }
    }
}
