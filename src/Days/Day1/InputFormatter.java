package Days.Day1;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InputFormatter implements InputManipulatable<Integer[][]> {

    private List<String> input;
    private int[] leftNumbers;
    private int[] rightNumbers;
    private int size;

    public InputFormatter() throws IOException {
        this.input = readFile();
        leftNumbers = new int[input.size()];
        rightNumbers = new int[input.size()];
        splitAndSortNumbers();
    }

    private void splitAndSortNumbers() {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String[] splittedLine = line.split("\\s{3}");

            leftNumbers[i] = Integer.parseInt(splittedLine[0]);
            rightNumbers[i] = Integer.parseInt(splittedLine[1]);
        }
        Arrays.sort(leftNumbers);
        Arrays.sort(rightNumbers);
    }

    private List<String> readFile() throws IOException {
        return Files.readAllLines(Paths.get("src/Days/Day1/Input.txt"));
    }

    @Override
    public Integer[][] getFormattedInput() {
        Integer[][] formattedPairs = new Integer[input.size()][2];
        for (int i = 0; i < input.size(); i++) {
            formattedPairs[i][0] = leftNumbers[i];
            formattedPairs[i][1] = rightNumbers[i];
        }
        setSize(input.size());
        return formattedPairs;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter testFormat = new InputFormatter();

            Integer[][] input = testFormat.getFormattedInput();
            System.out.println("Formattierter Input:");

            for (Integer[] pair : input) {
                System.out.println(pair[0] + " " + pair[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
