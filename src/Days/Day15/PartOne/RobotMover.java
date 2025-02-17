package Days.Day15.PartOne;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotMover {

    private final Map<Integer, List<Tile>> warehouseMap;
    private final List<char[]> moveSet;
    private int[] startingPosition;

    public RobotMover() throws IOException {
        WareHouseAndMovementMapper warehouseInformation = new WareHouseAndMovementMapper();
        warehouseMap = warehouseInformation.getTileMap();
        moveSet = warehouseInformation.getMovementList();
        startingPosition = warehouseInformation.getIndexOfStartingposition();
        executeRobotMovement();
    }

    private void executeRobotMovement() {

    }


}
