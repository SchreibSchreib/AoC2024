package Days.Day13.PartTwo;

public class GreatestCommonDivisorCalculator {

    private final int greatestCommonDivisor;

    public GreatestCommonDivisorCalculator(int biggerNumber, int smallerNumber) {
        greatestCommonDivisor = executeEuclideanAlgorithm(biggerNumber, smallerNumber);
    }

    private int executeEuclideanAlgorithm(int biggerNumber, int smallerNumber) {
        if (biggerNumber % smallerNumber == 0) {
            return smallerNumber;
        }
        return executeEuclideanAlgorithm(smallerNumber, biggerNumber % smallerNumber);
    }

    public int getGreatestCommonDivisor() {
        return greatestCommonDivisor;
    }

    public static void main(String[] args) {
        GreatestCommonDivisorCalculator calculator = new GreatestCommonDivisorCalculator(30, 36);
    }
}
