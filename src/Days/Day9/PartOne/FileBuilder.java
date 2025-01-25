package Days.Day9.PartOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileBuilder {

    private final FileLoader loadedFile;
    private final List<Integer> correctOrderedFile;

    public FileBuilder() throws IOException {
        this.loadedFile = new FileLoader();
        this.correctOrderedFile = buildFile();
    }

    private List<Integer> buildFile() {
        Map<Integer, Integer[]> mappedNumbersWithLength = this.loadedFile.getMappedNumbersWithLength();
        List<Integer[]> listedPositionsOfDots = this.loadedFile.getListedPositionsOfDots();

        List<Integer> builtFile = build(mappedNumbersWithLength, listedPositionsOfDots);

        return builtFile;
    }

    private List<Integer> build(Map<Integer, Integer[]> mappedNumbersWithLength, List<Integer[]> listedPositionsOfDots) {
        List<Integer> fileToBuild = new LinkedList<>();

        for (int index = 0; index < mappedNumbersWithLength.size(); index++) {
            for (int numberOfPlacements = mappedNumbersWithLength.get(index)[1]; numberOfPlacements > 0; numberOfPlacements--) {
                fileToBuild.add(index);
            }
        }

        rearrangeBuild(fileToBuild, listedPositionsOfDots);

        return fileToBuild;
    }

    private void rearrangeBuild(List<Integer> fileToBuild, List<Integer[]> listedPositionsOfDots) {
        int lastIndex = 0;
        int lastNumberToFind = 0;

        for (int indexForDotIteration = 0; indexForDotIteration < listedPositionsOfDots.size(); indexForDotIteration++) {
            int indexToInsert = calculateIndex(fileToBuild, lastIndex, lastNumberToFind);

            if (indexToInsert == -1) {
                break;
            }

            Integer[] takeIntegersToSwap = loadArrayWithIntegers(fileToBuild, listedPositionsOfDots.get(indexForDotIteration)[1]);
            for (int indexForSwappingNumbers = listedPositionsOfDots.get(indexForDotIteration)[1]; indexForSwappingNumbers > 0; indexForSwappingNumbers--) {
                fileToBuild.add(indexToInsert, takeIntegersToSwap[takeIntegersToSwap.length - indexForSwappingNumbers]);
            }
            lastIndex = indexToInsert + takeIntegersToSwap.length;
            lastNumberToFind++;
            deleteEntries(fileToBuild, takeIntegersToSwap.length);
        }
    }

    private void deleteEntries(List<Integer> fileToBuild, int length) {
        for (; length > 0; length--) {
            fileToBuild.removeLast();
        }
    }

    private int calculateIndex(List<Integer> fileToBuild, int lastIndex, int lastNumberToFind) {
        for (; lastIndex < fileToBuild.size(); lastIndex++) {
            if (fileToBuild.get(lastIndex) == lastNumberToFind + 1) {
                return lastIndex;
            }
        }
        return -1;
    }

    private Integer[] loadArrayWithIntegers(List<Integer> fileToBuild, Integer integer) {
        Integer[] integersToLoad = new Integer[integer];
        for (int index = 0; index < integer; index++) {
            integersToLoad[index] = fileToBuild.get(fileToBuild.size() - integer + index);
        }
        return integersToLoad;
    }

    public List<Integer> getCorrectOrderedFile() {
        return this.correctOrderedFile;
    }

    public static void main(String[] args) throws IOException {
        FileBuilder testBuilder = new FileBuilder();
    }
}
