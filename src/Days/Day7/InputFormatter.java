package Days.Day7;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFormatter implements InputManipulatable<List<Integer[]>> {

    private final List<String> input;
    private final List<Integer[]> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        this.input = readFile();
        this.formattedInput = handleInput();
    }

    private List<Integer[]> handleInput() {
        List<Integer[]> inputAsCharArray = new ArrayList<>();
        for (String line : this.input) {
            inputAsCharArray.add(splitLine(line));
        }
        return inputAsCharArray;
    }

    private Integer[] splitLine(String line) {
        String[] lineArray = line.replace(":", "").trim().split(" ");
        Integer[] numbers = new Integer[lineArray.length];
        for (int i = 0; i < lineArray.length; i++) {
            numbers[i] = Integer.parseInt(lineArray[i]);
        }
        return numbers;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day7/TestInput.txt"));
        setSize(data.size());
        return data;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public List<Integer[]> getConvertedInput() {
        return this.formattedInput;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();
            for (Integer[] line : inputConverter.getConvertedInput()) {
                for (int c : line) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
