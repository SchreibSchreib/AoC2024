package Days.Day2;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InputFormatter implements InputManipulatable<Integer[][]> {

    private final List<String> input;
    private final Integer[][] formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
        formattedInput = handleInput();
    }

    private Integer[][] handleInput() {
        Integer[][] newInput = new Integer[size][];
        for (int i = 0; i < input.size(); i++) {
            Integer[] row = convertStringToIntArray(input.get(i));
            newInput[i] = row;
        }
        return newInput;
    }

    private Integer[] convertStringToIntArray(String numberString) {
        String[] splittedNumbers = numberString.split(" ");
        return Arrays.stream(splittedNumbers).map(Integer::valueOf).toArray(Integer[]::new);
    }


    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day2/Input.txt"));
        setSize(data.size());
        return data;
    }

    @Override
    public Integer[][] getConvertedInput() {
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

            Integer[][] input = testFormat.getConvertedInput();
            System.out.println("Formattierter Input:");

            for (Integer[] pair : input) {
                for (Integer integer : pair) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
