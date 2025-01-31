package Days.Day11;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFormatter implements InputManipulatable<List<Double>> {

    private final String input;
    private final List<Double> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
        formattedInput = handleInput();
    }

    private List<Double> handleInput() {
        List<Double> newInput = new ArrayList();
        for (String line : input.split(" ")) {
            newInput.add(Double.parseDouble(line));
        }
        setSize(newInput.size());
        return newInput;
    }

    private String readFile() throws IOException {
        return Files.readString(Paths.get("src/Days/Day11/Input.txt"));
    }

    @Override
    public List<Double> getConvertedInput() {
        return formattedInput;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter testFormat = new InputFormatter();

            List<Double> input = testFormat.getConvertedInput();
            System.out.println("Formattierter Input:");

            for (Double pair : input) {
                System.out.print(pair + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
