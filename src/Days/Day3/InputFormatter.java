package Days.Day3;

import Interfaces.InputManipulatable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputFormatter implements InputManipulatable<String> {

    private final String input;
    private int size;

    public InputFormatter() throws IOException {
        input = readFile();
    }

    private String readFile() throws IOException {
        String data = Files.readString(Paths.get("src/Days/Day3/Input.txt"));
        setSize(data.length());
        return data;
    }

    @Override
    public String getConvertedInput() {
        return input;
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

            String input = testFormat.getConvertedInput();
            System.out.println(testFormat.getSize());
            System.out.println("Formattierter Input:");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
