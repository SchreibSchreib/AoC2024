package Days.Day8;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputFormatter implements InputManipulatable<Map<Character, List<Integer[]>>> {

    private final List<String> input;
    private final Map<Character, List<Integer[]>> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        this.input = readFile();
        this.formattedInput = handleInput();
    }

    private Map<Character, List<Integer[]>> handleInput() {
        Map<Character, List<Integer[]>> mappedInput = new HashMap<>();
        for (int y = 0; y < this.input.size(); y++) {
            for (int x = 0; x < this.input.get(y).length(); x++) {
                if (this.input.get(y).charAt(x) == '.') {
                    continue;
                }
                char foundChar = this.input.get(y).charAt(x);
                if (mappedInput.containsKey(foundChar)) {
                    mappedInput.get(foundChar).add(new Integer[]{y, x});
                    continue;
                }
                List<Integer[]> line = new ArrayList<>();
                line.add(new Integer[]{y, x});
                mappedInput.put(foundChar, line);
            }

        }
        return mappedInput;
    }


    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day8/Input.txt"));
        setSize(data.size());
        return data;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public Map<Character, List<Integer[]>> getConvertedInput() {
        return this.formattedInput;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();
            System.out.println();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
