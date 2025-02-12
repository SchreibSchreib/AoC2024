package Days.Day13.PartOne;

public class ClawMachineSolver {
    private final int resultAPresses;
    private final int resultBPresses;


    public ClawMachineSolver(Button buttonA, Button buttonB, int resultX, int resultY) {
        int determinant = calculateDeterminantOfMatrix(buttonA.getIncrementX(), buttonB.getIncrementY(), buttonA.getIncrementY(), buttonB.getIncrementX());
        int determinantA = calculateDeterminantOfMatrix(resultX, buttonB.getIncrementY(), resultY, buttonB.getIncrementX());
        int determinantB = calculateDeterminantOfMatrix(buttonA.getIncrementX(), resultY, resultX, buttonA.getIncrementY());
        resultAPresses = calulateResult(determinantA, determinant);
        resultBPresses = calulateResult(determinantB, determinant);
    }

    private int calulateResult(int determinantButton, int determinant) {
        return determinantButton / determinant;
    }

    private int calculateDeterminantOfMatrix(int a, int b, int c, int d) {
        return a * b - c * d;
    }

    public int getResultAPresses() {
        return resultAPresses;
    }

    public int getResultBPresses() {
        return resultBPresses;
    }

    public static void main(String[] args) {
        ClawMachineSolver testMachine = new ClawMachineSolver(new Button(94,34),new Button(22,67),8400,5400);

    }
}
