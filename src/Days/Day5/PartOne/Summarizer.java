package Days.Day5.PartOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Summarizer {
    private List<Integer> numbersToSumUp;
    private int sum;

    public Summarizer() throws IOException {
        this.numbersToSumUp = new NumberExtractor().getListOfExtractedNumbers();
        this.sum = sumNumbers();
    }

    private int sumNumbers() {
        int sum = 0;
        for (Integer number : numbersToSumUp) {
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        Summarizer summarizer = new Summarizer();
        System.out.println(summarizer.sum);
    }
}
