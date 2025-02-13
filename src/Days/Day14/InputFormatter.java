package Days.Day14;

import Days.Day14.PartOne.Robot;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputFormatter implements InputManipulatable<List<Robot>> {

    private final List<String> input;
    private final List<Robot> formattedInput;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
        formattedInput = handleInput();
    }

    private List<Robot> handleInput() {
        List<Robot> listedInput = new ArrayList<>();
        for (int index = 0; index < input.size(); index++) {
            listedInput.add(splitInput(input.get(index)));
        }
        return listedInput;
    }

    private Robot splitInput(String inputLine) {
        String position = inputLine.substring(2, inputLine.indexOf(' '));
        String velocity = inputLine.substring(inputLine.indexOf(' ') + 3);
        int positionY = Integer.parseInt(position.split(",")[1]);
        int positionX = Integer.parseInt(position.split(",")[0]);
        int velocityY = Integer.parseInt(velocity.split(",")[1]);
        int velocityX = Integer.parseInt(velocity.split(",")[0]);

        return new Robot(positionY, positionX, velocityY, velocityX);
    }

    private List<String> readFile() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("src/Days/Day14/Input.txt"));
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
    public List<Robot> getConvertedInput() {
        return formattedInput;
    }

    @Override
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        try {
            InputFormatter inputConverter = new InputFormatter();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
