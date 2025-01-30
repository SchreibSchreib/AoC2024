package Days.Day10.PartTwo;

import java.io.IOException;
import java.util.Deque;
import java.util.List;

public class HikingTrailEvaluator {

    private final List<Height[]> mapInformation;
    private final List<Height> startingPoints;
    private int foundAvailableHikingRoutes;

    public HikingTrailEvaluator() throws IOException {
        TopographicMapConverter topographicMapConverter = new TopographicMapConverter();
        mapInformation = topographicMapConverter.getTopographicMap();
        startingPoints = topographicMapConverter.getStartingPoints();
        foundAvailableHikingRoutes = evaluateRoutes();
    }

    private int evaluateRoutes() {
        int hikingRoutes = 0;

        for (Height startingPoint : startingPoints) {
            RouteAnalyzer routeAnalyzer = new RouteAnalyzer(startingPoint, mapInformation);
            hikingRoutes += routeAnalyzer.getNumberOfWorkingPaths();
        }
        return hikingRoutes;
    }



    public static void main(String[] args) throws IOException {
        HikingTrailEvaluator evaluator = new HikingTrailEvaluator();
        //1686
    }
}
