package Days.Day4;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFormatter implements InputManipulatable<List<char[]>> {

    private final List<String> input;
    private final List<char[]> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        this.input = readFile();
        this.formattedInput = handleInput();
    }

    private List<char[]> handleInput() {
        List<char[]> inputAsCharArray = new ArrayList<>();
        for (String line : this.input) {
            inputAsCharArray.add(line.toCharArray());
        }
        return inputAsCharArray;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day4/TestInput.txt"));
        setSize(data.size());
        return data;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public List<char[]> getConvertedInput() {
        return this.formattedInput;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();
            for (char[] line : inputConverter.getConvertedInput()) {
                for (char c : line) {
                    System.out.print(c);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
