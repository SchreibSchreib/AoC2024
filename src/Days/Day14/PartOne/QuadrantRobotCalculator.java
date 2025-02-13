package Days.Day14.PartOne;

import Days.Day14.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuadrantRobotCalculator {

    private final Bathroom bathroom = new Bathroom();
    private final int secondsToPass = 100;
    private final List<Robot> robots;
    private Map<Integer, List<Robot>> robotMapAfterWalking;

    public QuadrantRobotCalculator() throws IOException {
        InputManipulatable<List<Robot>> inputManipulatable = new InputFormatter();
        robots = inputManipulatable.getConvertedInput();
        robotMapAfterWalking = calculateMoves();
    }

    private Map<Integer, List<Robot>> calculateMoves() {
        Map<Integer, List<Robot>> positionsOfRobots = new HashMap<>();

        for (Robot robot : robots) {
            robot.setPositionY(secondsToPass, bathroom.getBathroomLengthY());
            robot.setPositionX(secondsToPass, bathroom.getBathroomLengthX());
            loadRobotToMap(positionsOfRobots, robot);
        }

        return positionsOfRobots;
    }

    private void loadRobotToMap(Map<Integer, List<Robot>> positionsOfRobots, Robot robot) {
        int positionOfRobotY = robot.getPositionY();
        int positionOfRobotX = robot.getPositionX();

        if (positionOfRobotY < bathroom.getBathroomMiddleY()) {
            if (positionOfRobotX < bathroom.getBathroomMiddleX()) {
                positionsOfRobots.putIfAbsent(0, new ArrayList<>());
                positionsOfRobots.get(0).add(robot);
            } else if (positionOfRobotX > bathroom.getBathroomMiddleX()) {
                positionsOfRobots.putIfAbsent(1, new ArrayList<>());
                positionsOfRobots.get(1).add(robot);
            }
        }
        if (positionOfRobotY > bathroom.getBathroomMiddleY()) {
            if (positionOfRobotX < bathroom.getBathroomMiddleX()) {
                positionsOfRobots.putIfAbsent(2, new ArrayList<>());
                positionsOfRobots.get(2).add(robot);
            } else if (positionOfRobotX > bathroom.getBathroomMiddleX()) {
                positionsOfRobots.putIfAbsent(3, new ArrayList<>());
                positionsOfRobots.get(3).add(robot);
            }
        }
    }

    public Map<Integer, List<Robot>> getPositionsOfRobots() {
        return robotMapAfterWalking;
    }
}
