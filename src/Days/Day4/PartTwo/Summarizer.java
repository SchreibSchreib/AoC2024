package Days.Day4.PartTwo;

import java.io.IOException;
import java.util.Collection;

public class Summarizer {
    private final int result;
    private ExtractorAnalyzer analyzer;

    public Summarizer() throws IOException {
        analyzer = new ExtractorAnalyzer();
        result = summazize(analyzer.getValues());
    }

    private int summazize(Collection<Integer> values) {
        int result = 0;
        for (Integer value : values) {
            result += value;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Summarizer summarizer = new Summarizer();
        System.out.println(summarizer.result);
    }
}
