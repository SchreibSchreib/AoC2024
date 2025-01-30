package Days.Day8.PartOne;

import Days.Day8.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AntinodeCalculator {

    private final InputManipulatable<Map<Character, List<Integer[]>>> antennaInformations;
    private List<int[]> foundLocations = new ArrayList<>();
    private final int[] boundsY = new int[]{0, 49};
    private final int[] boundsX = new int[]{0, 49};
    private final int numberOfAntinodes;

    public AntinodeCalculator() throws IOException {
        antennaInformations = new InputFormatter();
        numberOfAntinodes = calculateAntinodes();
    }

    private int calculateAntinodes() {
        int numberOfAntinodes = 0;
        for (Map.Entry<Character, List<Integer[]>> entry : antennaInformations.getConvertedInput().entrySet()) {
            List<Integer[]> list = entry.getValue();

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
                antinodes += calculatePositions(firstAntenna, secondAntenna, Integer.compare(firstAntenna[1] - secondAntenna[1], 0));
            }
        }
        return antinodes;
    }

    private int calculatePositions(Integer[] firstAntenna, Integer[] secondAntenna, int direction) {
        int validPositions = 0;
        int distanceY = Math.abs(firstAntenna[0] - secondAntenna[0]);
        int distanceX = Math.abs(firstAntenna[1] - secondAntenna[1]);
        int[] firstPosition;
        int[] secondPosition;

        if (direction == 1) {
            firstPosition = new int[]{firstAntenna[0] - distanceY, firstAntenna[1] + distanceX};
            secondPosition = new int[]{secondAntenna[0] + distanceY, secondAntenna[1] - distanceX};

        } else {
            firstPosition = new int[]{firstAntenna[0] - distanceY, firstAntenna[1] - distanceX};
            secondPosition = new int[]{secondAntenna[0] + distanceY, secondAntenna[1] + distanceX};
        }

        if (isInBounds(firstPosition) && !isAlreadyFound(firstPosition)) {
            validPositions++;
        }
        if (isInBounds(secondPosition) && !isAlreadyFound(secondPosition)) {
            validPositions++;
        }
        foundLocations.add(firstPosition);
        foundLocations.add(secondPosition);
        return validPositions;
    }

    private boolean isAlreadyFound(int[] position) {
        for (int[] location : foundLocations) {
            if (position[0] == location[0] && position[1] == location[1]) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBounds(int[] position) {
        return (position[0] >= boundsY[0] && position[1] >= boundsX[0]) && (position[0] <= boundsY[1] && position[1] <= boundsX[1]);
    }


    public static void main(String[] args) throws IOException {
        AntinodeCalculator testCalculator = new AntinodeCalculator();
        System.out.println(testCalculator.numberOfAntinodes);
        //214
    }

}
