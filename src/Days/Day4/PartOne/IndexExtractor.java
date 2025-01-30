package Days.Day4.PartOne;

import Days.Day4.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class IndexExtractor {

    private final InputManipulatable<List<char[]>> manipulatedInput;
    private int lengthY;
    private int lengthX;
    private final List<List<Integer[]>> foundIndices;

    public IndexExtractor() throws IOException {
        manipulatedInput = new InputFormatter();
        lengthY = manipulatedInput.getSize();
        lengthX = getLengthOfArrays();
        foundIndices = findPermittedExpressions();
    }

    private int getLengthOfArrays() {
        return manipulatedInput.getConvertedInput().getFirst().length;
    }

    private List<List<Integer[]>> findPermittedExpressions() {
        List<List<Integer[]>> allIndices = new ArrayList<>();

        for (int charToFind = 0; charToFind < 4; charToFind++) {
            List<Integer[]> indicesOfChar = new ArrayList<>();
            switch (charToFind) {
                case 0:
                    indicesOfChar = findAllIndicesForChar(manipulatedInput.getConvertedInput(), 'X');
                    break;
                case 1:
                    indicesOfChar = findAllIndicesForChar(manipulatedInput.getConvertedInput(), 'M');
                    break;
                case 2:
                    indicesOfChar = findAllIndicesForChar(manipulatedInput.getConvertedInput(), 'A');
                    break;
                default:
                    indicesOfChar = findAllIndicesForChar(manipulatedInput.getConvertedInput(), 'S');
                    break;

            }
            allIndices.add(indicesOfChar);
        }
        return allIndices;
    }

    private List<Integer[]> findAllIndicesForChar(List<char[]> convertedInput, char charToFind) {
        List<Integer[]> indices = new ArrayList<>();

        for (int row = 0; row < convertedInput.size(); row++) {
            for (int col = 0; col < convertedInput.get(row).length; col++) {
                if (convertedInput.get(row)[col] == charToFind) {
                    indices.add(new Integer[]{row, col});
                }
            }
        }
        return indices;
    }

    public List<List<Integer[]>> getExpressions() {
        return foundIndices;
    }

    public int getLengthY() {
        return lengthY;
    }

    public int getLengthX() {
        return lengthX;
    }

    public static void main(String[] args) throws IOException {
        IndexExtractor extractor = new IndexExtractor();
        for (List<Integer[]> indices : extractor.getExpressions()) {
            System.out.println("Next Entry");
            for (Integer[] indice : indices) {
                System.out.println(indice[0] + " " + indice[1]);
            }
        }
    }
}

