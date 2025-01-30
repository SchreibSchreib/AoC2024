package Days.Day4.PartOne;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractorAnalyzer {

    private final IndexExtractor extractor;
    private final Map<Integer[], Integer> analyzedFunctionalPhrases;
    private final Collection<Integer> values;
    private final char[] charsToLookFor = new char[]{'X', 'M', 'A', 'S'};
    private final int firstX = 0;
    private final int firstY = 0;
    private final int lastX;
    private final int lastY;

    public ExtractorAnalyzer() throws IOException {
        extractor = new IndexExtractor();
        lastX = extractor.getLengthX();
        lastY = extractor.getLengthY();
        analyzedFunctionalPhrases = analyzeExtractedData();
        values = analyzedFunctionalPhrases.values();
    }

    private Map<Integer[], Integer> analyzeExtractedData() {
        List<Integer[]> data = extractor.getExpressions().getFirst();
        Map<Integer[], Integer> map = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            map.put(data.get(i), validatePhrase(data.get(i)[0], data.get(i)[1]));
        }
        return map;
    }

    private Integer validatePhrase(Integer yIndex, Integer xIndex) {
        Integer validWords = 0;
        for (int xDirection = -1; xDirection <= 1; xDirection++) {
            for (int yDirection = -1; yDirection <= 1; yDirection++) {

                int fieldToCheckX = xIndex + xDirection;
                int fieldToCheckY = yIndex + yDirection;

                if (isWithinBounds(fieldToCheckX, fieldToCheckY)) {
                    if (isFieldSearchCorrect(1, xIndex, yIndex, xDirection, yDirection)) {
                        validWords++;
                    }
                }
            }
        }
        return validWords;
    }

    private boolean isFieldSearchCorrect(int iteratorForCharIndex, Integer xIndex, Integer yIndex, Integer xDirection, Integer yDirection) {
        if (iteratorForCharIndex == charsToLookFor.length) {
            return true;
        }

        int fieldToCheckX = xIndex + xDirection;
        int fieldToCheckY = yIndex + yDirection;

        if (isWithinBounds(fieldToCheckX, fieldToCheckY)
                && isCharToLookFor(iteratorForCharIndex, fieldToCheckX, fieldToCheckY)) {
            return isFieldSearchCorrect(iteratorForCharIndex + 1, fieldToCheckX, fieldToCheckY, xDirection, yDirection);
        }
        return false;
    }

    private boolean isWithinBounds(int xIndex, int yIndex) {
        return xIndex >= firstX && xIndex <= lastX && yIndex >= firstY && yIndex <= lastY;
    }

    private boolean isCharToLookFor(int iteratorForCharIndex, int fieldToCheckX, int fieldToCheckY) {
        for (Integer[] indexArray : extractor.getExpressions().get(iteratorForCharIndex)) {
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
        System.out.println();
    }
}
