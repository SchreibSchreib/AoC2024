package Days.Day10;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InputFormatter implements InputManipulatable<List<Integer[]>> {

    private final List<String> input;
    private final List<Integer[]> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
        formattedInput = handleInput();
    }

    private List<Integer[]> handleInput() {
        List<Integer[]> inputAsIntegerArrayList = new LinkedList<>();

        for (String line : input) {
            Integer[] lineAsIntegerArray = new Integer[line.length()];
            char[] chars = line.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                lineAsIntegerArray[i] = Integer.parseInt(String.valueOf(chars[i]));
            }
            inputAsIntegerArrayList.add(lineAsIntegerArray);
        }

        return inputAsIntegerArrayList;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day10/Input.txt"));
        setSize(data.getFirst().length());
        return data;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public List<Integer[]> getConvertedInput() {
        return formattedInput;
    }

    @Override
    public int getSize() {
        return size;
    }

    public static void main(String[] args) throws IOException {
        InputFormatter testFormatter = new InputFormatter();
    }
}
