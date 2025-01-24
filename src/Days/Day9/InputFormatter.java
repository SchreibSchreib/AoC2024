package Days.Day9;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFormatter implements InputManipulatable<List<Integer>> {

    private final List<String> input;
    private final List<Integer> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        this.input = readFile();
        this.formattedInput = handleInput();
    }

    private List<Integer> handleInput() {
        List<Integer> inputAsIntegerList = new ArrayList<>(this.input.getFirst().length());

        for (char c : this.input.getFirst().toCharArray()) {
            inputAsIntegerList.add(Integer.parseInt(String.valueOf(c)));
        }

        return inputAsIntegerList;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day9/Input.txt"));
        setSize(data.getFirst().length());
        return data;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public List<Integer> getConvertedInput() {
        return this.formattedInput;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();
            for (Integer i : inputConverter.getConvertedInput()) {
                System.out.println(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
