package Days.Day14.PartTwo;

import Days.Day14.InputFormatter;
import Days.Day14.PartOne.Robot;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasterEggRobotMapper {

    private final List<Robot> robots;
    private boolean christmastreeFound = false;
    private final Bathroom bathroom = new Bathroom();

    public EasterEggRobotMapper() throws IOException {
        InputManipulatable<List<Robot>> inputManipulatable = new InputFormatter();
        robots = inputManipulatable.getConvertedInput();
        letRobotsWalk();

    }

    private void letRobotsWalk() {
        int iteration = 0
                ;
        while (!christmastreeFound) {
            iteration++;
            step();
            Map<Integer, List<Integer>> coordinatesMap = createMapFromRobots();
            checkAllRobotNeighbours(coordinatesMap);
            christmastreeFound = Robot.getNumberOfRobotsWithFourNeighbours() > 150;
            Robot.resetNumberOfRobotsWithFourNeighbours();
        }
        System.out.println("Found christmastree at : " + iteration);
    }

    private Map<Integer, List<Integer>> createMapFromRobots() {
        Map<Integer, List<Integer>> coordinatesMap = new HashMap<>();

        for (Robot robot : robots) {
            coordinatesMap.putIfAbsent(robot.getPositionY(), new ArrayList<>());
            coordinatesMap.get(robot.getPositionY()).add(robot.getPositionX());
        }

        return coordinatesMap;
    }

    private void checkAllRobotNeighbours(Map<Integer, List<Integer>> coordinatesMap) {
        for (Robot robot : robots) {
            if (broadsearchForNeighbours(robot, coordinatesMap)){
                Robot.incrementNumberOfRobotsWithFourNeighbours();
            }
        }
    }

    private boolean broadsearchForNeighbours(Robot robot, Map<Integer, List<Integer>> coordinatesMap) {
        int yPosition = robot.getPositionY();
        int xPosition = robot.getPositionX();

        return hasValidYNeighbours(yPosition, xPosition, coordinatesMap) && hasValidXNeighbours(yPosition, xPosition, coordinatesMap);
    }

    private boolean hasValidYNeighbours(int yPosition, int xPosition, Map<Integer, List<Integer>> coordinatesMap) {
        return coordinatesMap.containsKey(yPosition - 1) && coordinatesMap.containsKey(yPosition + 1) && coordinatesMap.containsKey(xPosition);
    }

    private boolean hasValidXNeighbours(int yPosition, int xPosition, Map<Integer, List<Integer>> coordinatesMap) {
        return coordinatesMap.get(yPosition).contains(xPosition - 1) && coordinatesMap.get(yPosition).contains(xPosition + 1);
    }


    private void step() {
        for (Robot robot : robots) {
            robot.setPositionY(1, bathroom.getBathroomLengthY());
            robot.setPositionX(1, bathroom.getBathroomLengthX());
        }
    }

    public static void main(String[] args) throws IOException {
        EasterEggRobotMapper testMapper = new EasterEggRobotMapper();
        //6876
    }
}
