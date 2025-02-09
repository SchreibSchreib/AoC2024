package Days.Day13;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputFormatter implements InputManipulatable<Map<Integer, List<Integer[]>>> {

    private final List<String> input;
    private final Map<Integer, List<Integer[]>> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
        formattedInput = handleInput();
    }

    private Map<Integer, List<Integer[]>> handleInput() {
        Map<Integer, List<Integer[]>> mappedInput = new HashMap<>();
        for (int index = 0; index < input.size(); index++) {

            mappedInput.putIfAbsent(index / 3, new ArrayList<>());
            mappedInput.get(index / 3).add(splitInput(input.get(index)));
        }
        return mappedInput;
    }

    private Integer[] splitInput(String inputLine) {
        Integer[] splittedAndConvertedInput = new Integer[2];

        if (inputLine.contains("Button")) {
            String[] splittedInput = inputLine.split("\\+");
            splittedAndConvertedInput[0] = Integer.parseInt(splittedInput[1].substring(0, splittedInput[1].indexOf(',')));
            splittedAndConvertedInput[1] = Integer.parseInt(splittedInput[2]);
        } else {
            String[] splittedInput = inputLine.split("=");
            splittedAndConvertedInput[0] = Integer.parseInt(splittedInput[1].substring(0, splittedInput[1].indexOf(',')));
            splittedAndConvertedInput[1] = Integer.parseInt(splittedInput[2]);
        }


        return splittedAndConvertedInput;
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day13/TestInput.txt"));
        List<String> filteredData = new ArrayList<>();
        for (String line : data) {
            if (line.length() == 0) {
                continue;
            }
            filteredData.add(line);
        }
        setSize(filteredData.size());
        return filteredData;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public Map<Integer, List<Integer[]>> getConvertedInput() {
        return formattedInput;
    }

    @Override
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();
            for (Map.Entry<Integer, List<Integer[]>> entry : inputConverter.getConvertedInput().entrySet()) {
                System.out.println(entry.getKey());
                for (Integer[] line : entry.getValue()) {
                    for (Integer integer : line) {
                        System.out.print(integer + " ");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
