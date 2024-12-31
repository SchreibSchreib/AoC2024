package Days.Day3.PartTwo;

import java.io.IOException;

public class Summarizer {

    private ExpressionExtractor stringPurifier;
    private int sum;

    public Summarizer() throws IOException {
        this.stringPurifier = new ExpressionExtractor("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        this.sum = calculate();
    }

    private int calculate() {
        int sum = 0;
        for (Integer[] expression : this.stringPurifier.getExpressions()) {
            sum += expression[0] * expression[1];
        }
        return sum;
    }

    public int getSum() {
        return this.sum;
    }

    public static void main(String[] args) throws IOException {
        Summarizer summarizer = new Summarizer();
        System.out.println(summarizer.getSum());
    }
}
