package Days.Day4.PartOne;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtractorAnalyzer {

    private final IndexExtractor extractor;
    private final Map<Integer[], Integer> analyzedXOccourences;
    private final int firstX = 0;
    private final int firstY = 0;
    private final int lastX;
    private final int lastY;

    public ExtractorAnalyzer() throws IOException {
        this.extractor = new IndexExtractor();
        this.lastX = extractor.getLengthX();
        this.lastY = extractor.getLengthY();
        this.analyzedXOccourences = analyzeExtractedData();
    }

    private Map<Integer[], Integer> analyzeExtractedData() {
        Map<Integer[], Integer> map = loadXIndices();
        return map;
    }

    private Map<Integer[], Integer> loadXIndices() {
        Map<Integer[], Integer> map = new HashMap<>();
        for (Integer[] entry : extractor.getExpressions().getFirst()) {
            map.put(entry, 0);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        ExtractorAnalyzer indexPurifier = new ExtractorAnalyzer();
    }
}
