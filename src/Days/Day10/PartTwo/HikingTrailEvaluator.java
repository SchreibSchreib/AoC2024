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
        this.mapInformation = topographicMapConverter.getTopographicMap();
        this.startingPoints = topographicMapConverter.getStartingPoints();
        this.foundAvailableHikingRoutes = evaluateRoutes();
    }

    private int evaluateRoutes() {
        int hikingRoutes = 0;

        for (Height startingPoint : this.startingPoints) {
            RouteAnalyzer routeAnalyzer = new RouteAnalyzer(startingPoint, this.mapInformation);
            hikingRoutes += routeAnalyzer.getNumberOfWorkingPaths();
        }
        return hikingRoutes;
    }



    public static void main(String[] args) throws IOException {
        HikingTrailEvaluator evaluator = new HikingTrailEvaluator();
        //1686
    }
}
