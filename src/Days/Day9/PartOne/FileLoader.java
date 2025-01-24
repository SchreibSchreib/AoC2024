package Days.Day9.PartOne;

import Days.Day9.InputFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileLoader {

    private final List<Integer> manipulatedInput;
    private final Map<Integer,Integer[]> mappedNumbersWithLength = new HashMap<>();
    private final List<Integer[]> listedPositionsOfDots = new ArrayList<>();

    public FileLoader() throws IOException {
        this.manipulatedInput = new InputFormatter().getConvertedInput();
        loadLists();
    }

    private void loadLists() {
        for (int index = 0; index < manipulatedInput.size(); index++) {
            if (index % 2 == 0) {
                mappedNumbersWithLength.put(index / 2, new Integer[] {index, manipulatedInput.get(index)});
            } else {
                listedPositionsOfDots.add(new Integer[] {index, manipulatedInput.get(index)});
            }
        }
    }

    public Map<Integer, Integer[]> getMappedNumbersWithLength() {
        return mappedNumbersWithLength;
    }

    public List<Integer[]> getListedPositionsOfDots() {
        return listedPositionsOfDots;
    }

    public static void main(String[] args) throws IOException {
        FileLoader fileLoader = new FileLoader();
    }
}


