package Days.Day9.PartTwo;

import Days.Day9.InputFormatter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileLoader {

    private final List<Integer> manipulatedInput;
    private final Map<Integer, Integer[]> mappedNumbersWithLength = new HashMap<>();
    private final Map<Integer, Integer[]> mappedPositionsOfDots = new HashMap<>();
    private final int highestIndex;

    public FileLoader() throws IOException {
        manipulatedInput = new InputFormatter().getConvertedInput();
        highestIndex = manipulatedInput.size();
        loadLists();
    }

    private void loadLists() {
        for (int index = 0; index < manipulatedInput.size(); index++) {
            if (index % 2 == 0) {
                mappedNumbersWithLength.put(index, new Integer[]{index / 2, manipulatedInput.get(index)});
            } else {
                mappedPositionsOfDots.put(index, new Integer[]{index / 2, manipulatedInput.get(index)});
            }
        }
    }

    public int getHighestIndex() {
        return highestIndex;
    }

    public Map<Integer, Integer[]> getMappedNumbersWithLength() {
        return mappedNumbersWithLength;
    }

    public Map<Integer, Integer[]> getMappedPositionsOfDots() {
        return mappedPositionsOfDots;
    }

    public static void main(String[] args) throws IOException {
        FileLoader fileLoader = new FileLoader();
    }
}


