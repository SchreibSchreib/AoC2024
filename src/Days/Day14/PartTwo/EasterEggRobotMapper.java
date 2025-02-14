package Days.Day14.PartTwo;

import Days.Day14.InputFormatter;
import Days.Day14.PartOne.Robot;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.List;

public class EasterEggRobotMapper {

    private List<Robot> robots;
    private boolean christmastreeFound = false;
    private Bathroom bathroom = new Bathroom();

    public EasterEggRobotMapper() throws IOException {
        InputManipulatable<List<Robot>> inputManipulatable = new InputFormatter();
        robots = inputManipulatable.getConvertedInput();
        letRobotsWalk();

    }

    private void letRobotsWalk() {
        int iterator = 1;

        while (!christmastreeFound) {
            step(iterator++);
            christmastreeFound = checkAllRobotNeighbours();
        }
    }

    private boolean checkAllRobotNeighbours() {
        //hier weiter machen (Broadsearch um Nachbarn zu checken)
    }

    private void step(int numberOfSteps) {
        for (Robot robot : robots) {
            robot.setPositionY(numberOfSteps, bathroom.getBathroomLengthY());
            robot.setPositionX(numberOfSteps, bathroom.getBathroomLengthX());
        }
    }
}
