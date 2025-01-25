package Days.Day9.PartTwo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileFragmenter {

    private final FileLoader fileLoader;
    private final Map<Integer, Integer[]> mappedNumbers;
    private final Map<Integer, Integer> mappedDots;
    private List<Integer> fragmentedNumbers;

    public FileFragmenter() throws IOException {
        this.fileLoader = new FileLoader();
        this.mappedNumbers = this.fileLoader.getMappedNumbersWithLength();
        this.mappedDots = this.fileLoader.getMappedPositionsOfDots();
        this.fragmentedNumbers = fragmentData();
    }

    private List<Integer> fragmentData() {
        List<Integer> data = loadList();
        return data;
    }

    private List<Integer> loadList() {
        List<Integer> data = new LinkedList<>();

        for (int i = 0; i < this.mappedNumbers.size() + this.mappedDots.size(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < this.mappedNumbers.get(i)[1]; j++) {
                    data.add(mappedNumbers.get(i)[0]);
                }
            } else {
                for (int j = 0; j < this.mappedDots.get(i); j++) {
                    data.add(null);
                }
            }
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
        FileFragmenter fileFragmenter = new FileFragmenter();

    }
}
