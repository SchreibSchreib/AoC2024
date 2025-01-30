package Days.Day10.PartOne;

import Days.Day10.InputFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopographicMapConverter {

    private final List<Integer[]> mapInformation;
    private final List<Height[]> topographicMap;
    private final List<Height> foundStartingPoints = new ArrayList<>();


    public TopographicMapConverter() throws IOException {
        mapInformation = new InputFormatter().getConvertedInput();
        topographicMap = convertToHeightObjects();
    }

    private List<Height[]> convertToHeightObjects() {
        List<Height[]> topographicMap = new ArrayList<>(mapInformation.size());

        for (int yIndex = 0; yIndex < mapInformation.size(); yIndex++) {
            Height[] topographicInformation = new Height[mapInformation.get(yIndex).length];
            for (int xIndex = 0; xIndex < topographicInformation.length; xIndex++) {
                int foundNumber = mapInformation.get(yIndex)[xIndex];
                topographicInformation[xIndex] = new Height(foundNumber, new int[]{yIndex, xIndex});
                if (foundNumber == 0){
                    foundStartingPoints.add(new Height(foundNumber, new int[]{yIndex, xIndex}));
                }
            }
            topographicMap.add(topographicInformation);
        }
        return topographicMap;
    }

    public List<Height[]> getTopographicMap() {
        return topographicMap;
    }

    public List<Height> getStartingPoints() {
        return foundStartingPoints;
    }

    public static void main(String[] args) throws IOException {
        TopographicMapConverter converter = new TopographicMapConverter();

    }
}
