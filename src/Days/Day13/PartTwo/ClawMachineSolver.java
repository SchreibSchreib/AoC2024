package Days.Day13.PartTwo;

public class ClawMachineSolver {
    private final long resultAPresses;
    private final long resultBPresses;


    public ClawMachineSolver(Button buttonA, Button buttonB, int resultX, int resultY) {
        long longResultX = 10000000000000L + resultX;
        long longResultY = 10000000000000L + resultY;
        int determinant = calculateDeterminantOfMatrix(buttonA.getIncrementX(), buttonB.getIncrementY(), buttonA.getIncrementY(), buttonB.getIncrementX());
        long determinantA = calculateDeterminantOfMatrixA(longResultX, buttonB.getIncrementY(), longResultY, buttonB.getIncrementX());
        long determinantB = calculateDeterminantOfMatrixB(buttonA.getIncrementX(), longResultY, longResultX, buttonA.getIncrementY());
        resultAPresses = calculateResult(determinantA, determinant);
        resultBPresses = calculateResult(determinantB, determinant);
    }

    private long calculateDeterminantOfMatrixA(long longResultX, int incrementY, long longResultY, int incrementX) {
        return longResultX * incrementY - longResultY * incrementX;
    }

    private long calculateDeterminantOfMatrixB(int incrementX, long longResultY, long longResultX, int incrementY) {
        return incrementX * longResultY - longResultX * incrementY;
    }

    private long calculateResult(long determinantButton, int determinant) {
        if (determinantButton % determinant != 0) {
            return 0;
        }
        return determinantButton / determinant;
    }

    private int calculateDeterminantOfMatrix(int a, int b, int c, int d) {
        return a * b - c * d;
    }

    public long getResultAPresses() {
        return resultAPresses;
    }

    public long getResultBPresses() {
        return resultBPresses;
    }

    public static void main(String[] args) {
        ClawMachineSolver testMachineA = new ClawMachineSolver(new Button(94, 34), new Button(22, 67), 8400, 5400);
        ClawMachineSolver testMachineB = new ClawMachineSolver(new Button(26, 66), new Button(67, 21), 12748, 12176);

    }
}
