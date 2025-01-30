package Days.Day9.PartOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Summarizer {

    private List<Integer> fileToCalculate;
    private Long sumOfFile;

    public Summarizer() throws IOException {
        fileToCalculate = new FileBuilder().getCorrectOrderedFile();
        sumOfFile = calculate();
    }

    private Long calculate() {
        Long result = 0L;

        for (int iterator = 0; iterator < fileToCalculate.size(); iterator++) {
            result += (long) iterator * fileToCalculate.get(iterator);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Summarizer testSummarizer = new Summarizer();
        System.out.println(testSummarizer.sumOfFile);
    }
}
