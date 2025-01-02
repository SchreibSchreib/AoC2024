package Days.Day4.PartOne;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractorAnalyzer {

    private final IndexExtractor extractor;
    private final Map<Integer[], Integer> analyzedFunctionalPhrases;
    private final char[] charsToLookFor = new char[]{'X', 'M', 'A', 'S'};
    private final int firstX = 0;
    private final int firstY = 0;
    private final int lastX;
    private final int lastY;

    public ExtractorAnalyzer() throws IOException {
        this.extractor = new IndexExtractor();
        this.lastX = extractor.getLengthX();
        this.lastY = extractor.getLengthY();
        this.analyzedFunctionalPhrases = analyzeExtractedData();
    }

    private Map<Integer[], Integer> analyzeExtractedData() {
        List<Integer[]> data = extractor.getExpressions().getFirst();
        Map<Integer[], Integer> map = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            map.put(data.get(i), validatePhrase(data.get(i)[0], data.get(i)[1]));
        }
        return map;
    }

    private Integer validatePhrase(Integer xIndex, Integer yIndex) {
        Integer validWords = 0;
        for (int i = 1; i <= extractor.getExpressions().size(); i++) {
            if (isFieldSearchCorrect(i, xIndex, yIndex)) {
                validWords++;
            }
        }
        return validWords;
    }

    private boolean isFieldSearchCorrect(int iteratorForCharIndex, Integer xIndex, Integer yIndex) {
        if (iteratorForCharIndex == charsToLookFor.length) {
            return true;
        }
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                int fieldToCheckX = xIndex + x;
                int fieldToCheckY = yIndex + y;

                if (fieldToCheckX < firstX || fieldToCheckX > lastX || fieldToCheckY < firstY || fieldToCheckY > lastY) {
                    continue;
                }
                if (isCharToLookFor(iteratorForCharIndex, fieldToCheckX, fieldToCheckY)) {
                    if (isFieldSearchCorrect(iteratorForCharIndex + 1, fieldToCheckX, fieldToCheckY)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private boolean isCharToLookFor(int iteratorForCharIndex, int fieldToCheckX, int fieldToCheckY) {
        for (Integer[] indexArray : extractor.getExpressions().get(iteratorForCharIndex)) {
            if (fieldToCheckX == indexArray[0] && fieldToCheckY == indexArray[1]) {
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


    public static void main(String[] args) throws IOException {
        ExtractorAnalyzer analyzer = new ExtractorAnalyzer();
    }
}
