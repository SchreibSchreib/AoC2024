package Days.Day10.PartOne;

import java.io.IOException;
import java.util.List;

public class HikingTrailEvaluator {

    private final List<Height[]> mapInformation;
    private final List<Height> startingPoints;
    private int foundAvailableHikingRoutes;

    public HikingTrailEvaluator() throws IOException {
        TopographicMapConverter topographicMapConverter = new TopographicMapConverter();
        this.mapInformation = topographicMapConverter.getTopographicMap();
        this.startingPoints = topographicMapConverter.getStartingPoints();
        this.foundAvailableHikingRoutes = evaluateRoutes();
    }

    private int evaluateRoutes() {
        int hikingRoutes = 0;

        for (Height startingPoint : startingPoints) {
            RouteAnalyzer routeAnalyzer = new RouteAnalyzer(startingPoint, this.mapInformation);
            hikingRoutes += routeAnalyzer.getNumberOfWorkingPaths();
            resetFields();
        }
        return hikingRoutes;
    }

    private void resetFields() {
        for (Height[] heights : mapInformation) {
            for (Height height : heights) {
                height.resetSteppedOn();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HikingTrailEvaluator evaluator = new HikingTrailEvaluator();
        //717
    }
}
