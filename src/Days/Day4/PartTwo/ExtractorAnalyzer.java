package Days.Day4.PartTwo;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractorAnalyzer {

    private final IndexExtractor extractor;
    private final Map<Integer[], Integer> analyzedFunctionalPhrases;
    private final Collection<Integer> values;
    private final char[] charsToLookFor = new char[]{'A', 'M', 'S'};
    private final Map<Integer, Integer[]> coordinatesToCheck;
    private final int firstX = 0;
    private final int firstY = 0;
    private final int lastX;
    private final int lastY;

    public ExtractorAnalyzer() throws IOException {
        extractor = new IndexExtractor();
        lastX = extractor.getLengthX() - 1;
        lastY = extractor.getLengthY() - 1;
        coordinatesToCheck = getCoordinates();
        analyzedFunctionalPhrases = analyzeExtractedData();
        values = analyzedFunctionalPhrases.values();
    }

    private Map<Integer, Integer[]> getCoordinates() {
        Map<Integer, Integer[]> map = new HashMap<>();
        map.put(1, new Integer[]{2, 0});
        map.put(2, new Integer[]{0, -2});
        map.put(3, new Integer[]{2, -2});
        map.put(4, new Integer[]{-2, 0});
        map.put(5, new Integer[]{0, 2});
        map.put(6, new Integer[]{-2, 2});
        return map;
    }

    private Map<Integer[], Integer> analyzeExtractedData() {
        List<Integer[]> data = extractor.getExpressions().get(2);
        Map<Integer[], Integer> map = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            map.put(data.get(i), validatePhrase(data.get(i)[0], data.get(i)[1]));
        }
        return map;
    }

    private Integer validatePhrase(Integer yIndex, Integer xIndex) {
        int fieldToCheckX = xIndex - 1;
        int fieldToCheckY = yIndex + 1;

        if (isWithinBounds(fieldToCheckX, fieldToCheckY) && isWithinBounds(fieldToCheckX + 2, fieldToCheckY - 2)) {
            if (isFieldSearchCorrect(1, fieldToCheckX, fieldToCheckY)) {
                return 1;

            }
        }
        return 0;
    }

    private boolean isFieldSearchCorrect(int iteratorForCharIndex, Integer xIndex, Integer yIndex) {

        if (isCharToLookFor(iteratorForCharIndex, xIndex, yIndex)) {
            return startFieldSearch(iteratorForCharIndex, xIndex, yIndex, coordinatesToCheck.get(1), coordinatesToCheck.get(2), coordinatesToCheck.get(3));
        }
        if (!isCharToLookFor(iteratorForCharIndex, xIndex, yIndex)) {

            int nextFieldToCheckX = xIndex + 2;
            int nextFieldToCheckY = yIndex - 2;

            if (isCharToLookFor(iteratorForCharIndex, nextFieldToCheckX, nextFieldToCheckY)) {
                return startFieldSearch(iteratorForCharIndex, nextFieldToCheckX, nextFieldToCheckY, coordinatesToCheck.get(4), coordinatesToCheck.get(5), coordinatesToCheck.get(6));
            }
        }
        return false;
    }

    private boolean startFieldSearch(int iteratorForCharIndex, Integer xIndex, Integer yIndex, Integer[] firstCoordinates, Integer[] secondCoordinates, Integer[] thirdCoordinates) {
        int fieldToCheckX = xIndex + firstCoordinates[0];
        int fieldToCheckY = yIndex + firstCoordinates[1];

        if (isCharToLookFor(iteratorForCharIndex, fieldToCheckX, fieldToCheckY)) {

            return isCharToLookFor(iteratorForCharIndex + 2, xIndex + secondCoordinates[0], yIndex + secondCoordinates[1])
                    && isCharToLookFor(iteratorForCharIndex + 2, xIndex + thirdCoordinates[0], yIndex + thirdCoordinates[1]);
        }

        fieldToCheckX = xIndex + secondCoordinates[0];
        fieldToCheckY = yIndex + secondCoordinates[1];

        if (isCharToLookFor(iteratorForCharIndex, fieldToCheckX, fieldToCheckY)) {

            return isCharToLookFor(iteratorForCharIndex + 2, xIndex + firstCoordinates[0], yIndex + firstCoordinates[1])
                    && isCharToLookFor(iteratorForCharIndex + 2, xIndex + thirdCoordinates[0], yIndex + thirdCoordinates[1]);
        }
        return false;
    }

    private boolean isWithinBounds(int xIndex, int yIndex) {
        return xIndex >= firstX && xIndex <= lastX && yIndex >= firstY && yIndex <= lastY;
    }

    private boolean isCharToLookFor(int iteratorForCharIndex, int fieldToCheckX, int fieldToCheckY) {
        for (Integer[] indexArray : extractor.getExpressions().get(iteratorForCharIndex)) {
            if (indexArray[0] > fieldToCheckY) {
                return false;
            }
            if (fieldToCheckY == indexArray[0] && fieldToCheckX == indexArray[1]) {
                return true;
            }
        }
        return false;
    }


    private Map<Integer[], Integer> loadIndices(int listToSearch) {
        Map<Integer[], Integer> map = new HashMap<>();
        for (Integer[] entry : extractor.getExpressions().get(listToSearch)) {
            map.put(entry, 0);
        }
        return map;
    }

    public Collection<Integer> getValues() {
        return values;
    }


    public static void main(String[] args) throws IOException {
        ExtractorAnalyzer analyzer = new ExtractorAnalyzer();
    }
}
