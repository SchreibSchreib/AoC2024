package Days.Day9.PartTwo;

import java.io.IOException;
import java.util.List;

public class Summarizer {

    private List<Integer> fileToCalculate;
    private Long sumOfFile;

    public Summarizer() throws IOException {
        this.fileToCalculate = new FileFragmenter().getCorrectFragmentedFile();
        this.sumOfFile = calculate();
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
        //6326952672104
    }
}
