package Days.Day8.PartOne;

import Days.Day8.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AntinodeCalculator {

    private final InputManipulatable<Map<Character, List<Integer[]>>> antennaInformations;
    private final int[] boundsY = new int[]{0, 49};
    private final int[] boundsX = new int[]{0, 49};
    private final int numberOfAntinodes;

    public AntinodeCalculator() throws IOException {
        this.antennaInformations = new InputFormatter();
        this.numberOfAntinodes = calculateAntinodes();
    }

    private int calculateAntinodes() {
        int numberOfAntinodes = 0;
        for (Map.Entry<Character, List<Integer[]>> entry : this.antennaInformations.getConvertedInput().entrySet()) {
            List<Integer[]> list = entry.getValue();

            numberOfAntinodes += findAntinodesForThisList(list);
        }
        return numberOfAntinodes;
    }

    private int findAntinodesForThisList(List<Integer[]> list) {
        int antinodes = 0;

        for (int i = 0; i < list.size(); i++) {
            Integer[] entry = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Integer[] next = list.get(j);
                int stepsInY = Math.abs(entry[0] - next[0]);
                int stepsInX = Math.abs(entry[1] - next[1]);
                System.out.println(stepsInY + " " + stepsInX);
            }
        }
        return antinodes;
    }

    public static void main(String[] args) throws IOException {
        new AntinodeCalculator();
    }

}
