package Days.Day9.PartTwo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class FileFragmenter {

    private final FileLoader fileLoader;
    private final Map<Integer, Integer[]> mappedNumbers;
    private final Map<Integer, Integer[]> mappedDots;
    private List<Integer> fragmentedNumbers;

    public FileFragmenter() throws IOException {
        fileLoader = new FileLoader();
        mappedNumbers = fileLoader.getMappedNumbersWithLength();
        mappedDots = fileLoader.getMappedPositionsOfDots();
        fragmentedNumbers = loadFragmentedList();
    }

    private List<Integer> loadFragmentedList() {
        Map<Integer, Integer[]> fragmentedMap = fragmentData();
        return loadList(fragmentedMap);
    }

    private Map<Integer, Integer[]> fragmentData() {
        Map<Integer, Integer[]> fragmentedMap = new HashMap<>(mappedNumbers);
        int numberIndex = (mappedNumbers.size() - 1) * 2;

        for (; numberIndex >= 2; numberIndex -= 2) {
            for (int dotIndex = 1; dotIndex < numberIndex; dotIndex += 2) {
                if (fragmentedMap.get(numberIndex)[1] <= mappedDots.get(dotIndex)[1]) {
                    fragmentMap(fragmentedMap, dotIndex, numberIndex);
                    break;
                }
            }
        }
        return fragmentedMap;

    }

    private void fragmentMap(Map<Integer, Integer[]> fragmentedMap, int dotIndex, int numberIndex) {
        if (fragmentedMap.containsKey(dotIndex)) {
            fragmentedMap.put(dotIndex, createNewInteger(fragmentedMap.get(dotIndex), fragmentedMap.get(numberIndex)));
        } else {
            fragmentedMap.put(dotIndex, fragmentedMap.get(numberIndex));
        }
        mappedDots.get(dotIndex)[1] -= fragmentedMap.get(numberIndex)[1];
        mappedDots.get(numberIndex - 1)[1] += fragmentedMap.get(numberIndex)[1];
        fragmentedMap.remove(numberIndex);
    }

    private Integer[] createNewInteger(Integer[] existingArray, Integer[] arrayToConcat) {
        return Stream.concat(Arrays.stream(existingArray), Arrays.stream(arrayToConcat))
                .toArray(Integer[]::new);
    }


    private List<Integer> loadList(Map<Integer, Integer[]> fragmentedMap) {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < fileLoader.getHighestIndex(); i++) {
            if (fragmentedMap.containsKey(i)) {
                for (int j = 0; j < fragmentedMap.get(i).length; j += 2) {
                    for (int k = 0; k < fragmentedMap.get(i)[j + 1]; k++) {
                        data.add(fragmentedMap.get(i)[j]);
                    }
                }

            }
            if (mappedDots.containsKey(i)) {
                for (int k = 0; k < mappedDots.get(i)[1]; k++) {
                    data.add(0);
                }
            }
        }
        return data;
    }

    public List<Integer> getCorrectFragmentedFile() {
        return fragmentedNumbers;
    }

    public static void main(String[] args) throws IOException {
        FileFragmenter fileFragmenter = new FileFragmenter();

    }
}
