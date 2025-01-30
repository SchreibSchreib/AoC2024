package Days.Day7;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFormatter implements InputManipulatable<List<Long[]>> {

    private final List<String> input;
    private final List<Long[]> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
        formattedInput = handleInput();
    }

    private List<Long[]> handleInput() {
        List<Long[]> inputAsCharArray = new ArrayList<>();
        for (String line : input) {
            inputAsCharArray.add(splitLine(line));
        }
        return inputAsCharArray;
    }

    private Long[] splitLine(String line) {
        String[] lineArray = line.replace(":", "").trim().split(" ");
        Long[] numbers = new Long[lineArray.length];
        for (int i = 0; i < lineArray.length; i++) {
            numbers[i] = Long.parseLong(lineArray[i]);
        }
        return numbers;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day7/Input.txt"));
        setSize(data.size());
        return data;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public List<Long[]> getConvertedInput() {
        return formattedInput;
    }

    @Override
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();
            for (Long[] line : inputConverter.getConvertedInput()) {
                for (Long c : line) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
