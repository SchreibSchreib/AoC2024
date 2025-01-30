package Days.Day8.PartTwo;

import Days.Day8.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AntinodeCalculator {

    private final InputManipulatable<Map<Character, List<Integer[]>>> antennaInformations;
    private final List<Integer[]> foundLocations = new ArrayList<>();
    private final int[] boundsY = new int[]{0, 49};
    private final int[] boundsX = new int[]{0, 49};
    private final int numberOfAntinodes;

    public AntinodeCalculator() throws IOException {
        antennaInformations = new InputFormatter();
        numberOfAntinodes = calculateAntinodes();
    }

    private int calculateAntinodes() {
        int numberOfAntinodes = 0;

        for (Map.Entry<Character, List<Integer[]>> entry : antennaInformations.getConvertedInput().entrySet()){
            List<Integer[]> list = entry.getValue();
            foundLocations.addAll(list);
        }

        for (Map.Entry<Character, List<Integer[]>> entry : antennaInformations.getConvertedInput().entrySet()) {
            List<Integer[]> list = entry.getValue();
            numberOfAntinodes += list.size();
            numberOfAntinodes += findAntinodesForThisList(list);
        }
        return numberOfAntinodes;
    }

    private int findAntinodesForThisList(List<Integer[]> list) {
        int antinodes = 0;

        for (int i = 0; i < list.size(); i++) {
            Integer[] firstAntenna = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Integer[] secondAntenna = list.get(j);
                antinodes += calculatePositionsRecursive(firstAntenna, secondAntenna, Integer.compare(firstAntenna[1] - secondAntenna[1], 0), true);
                antinodes += calculatePositionsRecursive(firstAntenna, secondAntenna, Integer.compare(firstAntenna[1] - secondAntenna[1], 0), false);
            }
        }
        return antinodes;
    }

    private int calculatePositionsRecursive(Integer[] firstAntenna, Integer[] secondAntenna, int direction, boolean isFirstIteration) {
        int validPositions = 0;
        int distanceY = Math.abs(firstAntenna[0] - secondAntenna[0]);
        int distanceX = Math.abs(firstAntenna[1] - secondAntenna[1]);
        Integer[] firstPosition;
        Integer[] secondPosition;

        if (direction == 1) {
            firstPosition = new Integer[]{firstAntenna[0] - distanceY, firstAntenna[1] + distanceX};
            secondPosition = new Integer[]{secondAntenna[0] + distanceY, secondAntenna[1] - distanceX};

        } else {
            firstPosition = new Integer[]{firstAntenna[0] - distanceY, firstAntenna[1] - distanceX};
            secondPosition = new Integer[]{secondAntenna[0] + distanceY, secondAntenna[1] + distanceX};
        }

        if (isFirstIteration && isInBounds(firstPosition)) {
            if (isNotFound(firstPosition)) {
                validPositions++;
                foundLocations.add(firstPosition);
            }
            validPositions += calculatePositionsRecursive(firstPosition, firstAntenna, direction, isFirstIteration);
        }
        if (!isFirstIteration && isInBounds(secondPosition)) {
            if (isNotFound(secondPosition)) {
                validPositions++;
                foundLocations.add(secondPosition);
            }
            validPositions += calculatePositionsRecursive(secondAntenna, secondPosition, direction, isFirstIteration);
        }
        return validPositions;
    }

    private boolean isNotFound(Integer[] position) {
        for (Integer[] location : foundLocations) {
            if (Objects.equals(position[0], location[0]) && Objects.equals(position[1], location[1])) {
                return false;
            }
        }
        return true;
    }

    private boolean isInBounds(Integer[] position) {
        return (position[0] >= boundsY[0] && position[1] >= boundsX[0]) && (position[0] <= boundsY[1] && position[1] <= boundsX[1]);
    }


    public static void main(String[] args) throws IOException {
        AntinodeCalculator testCalculator = new AntinodeCalculator();
        System.out.println(testCalculator.numberOfAntinodes);
        //809
    }

}
