package Days.Day5;

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
            if (line.length() == 0) {
                continue;
            }
            if (line.contains("|")) {
                inputAsCharArray.add(splitToIntegerArray(line, "\\|"));
            } else {
                inputAsCharArray.add(splitToIntegerArray(line, ","));
            }
        }
        return inputAsCharArray;
    }

    private Integer[] splitToIntegerArray(String line, String Separator) {
        String[] splitLine = line.split(Separator);
        Integer[] integerArray = new Integer[splitLine.length];

        for (int i = 0; i < splitLine.length; i++) {
            integerArray[i] = Integer.parseInt(splitLine[i]);
        }
        return integerArray;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day5/Input.txt"));
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
                for (Integer c : line) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
