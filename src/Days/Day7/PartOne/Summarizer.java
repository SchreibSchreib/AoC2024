package Days.Day7.PartOne;

import java.io.IOException;
import java.util.List;

public class Summarizer {

    private final List<Long> expressionEvaluator;
    private final Long result;

    public Summarizer() throws IOException {
        expressionEvaluator = new ExpressionEvaluator().getFoundEquatations();
        result = sumExpressions();
    }

    private Long sumExpressions() {
        Long sum = 0L;

        for (Long expression : expressionEvaluator) {
            sum += expression;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        Summarizer testSummarizer = new Summarizer();
        System.out.println(testSummarizer.result);
        //2941973819040
    }
}
