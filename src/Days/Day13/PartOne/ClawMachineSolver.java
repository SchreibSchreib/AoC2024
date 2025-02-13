package Days.Day13.PartOne;

public class ClawMachineSolver {
    private final int resultAPresses;
    private final int resultBPresses;


    public ClawMachineSolver(Button buttonA, Button buttonB, int resultX, int resultY) {
        int determinant = calculateDeterminantOfMatrix(buttonA.getIncrementX(), buttonB.getIncrementY(), buttonA.getIncrementY(), buttonB.getIncrementX());
        int determinantA = calculateDeterminantOfMatrix(resultX, buttonB.getIncrementY(), resultY, buttonB.getIncrementX());
        int determinantB = calculateDeterminantOfMatrix(buttonA.getIncrementX(), resultY, resultX, buttonA.getIncrementY());
        resultAPresses = calculateResult(determinantA, determinant);
        resultBPresses = calculateResult(determinantB, determinant);
    }

    private int calculateResult(int determinantButton, int determinant) {
        if (determinantButton % determinant != 0) {
            return 0;
        }
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
        ClawMachineSolver testMachine = new ClawMachineSolver(new Button(26,66),new Button(67,21),12748,12176);

    }
}
