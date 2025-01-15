package Days.Day7.PartOne;

import Days.Day7.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionEvaluator {

    private final InputManipulatable<List<Integer[]>> input;
    private final char[] allowedExpressions = {'+', '*'};
    private final List<Integer> foundEquatations;

    public ExpressionEvaluator() throws IOException {
        this.input = new InputFormatter();
        this.foundEquatations = evaluateExpression(input.getConvertedInput());
    }

    private List<Integer> evaluateExpression(List<Integer[]> convertedInput) {
        List<Integer> foundMatchingExpressions = new ArrayList<>();

        for (Integer[] input : convertedInput) {
            if (expressionMatches(input)) {
                foundMatchingExpressions.add(input[0]);
            }
        }

        return foundMatchingExpressions;
    }

    private boolean expressionMatches(Integer[] input) {
        int result = input[0];

        if (iterateExpressions(result, input)) {
            return true;
        }
        return false;
    }

    private boolean iterateExpressions(int result, Integer[] input) {
        Integer[] numbersToCalculate = extractNumbers(input);

        for (int i = numbersToCalculate.length - 1; i >= 0; i--) {
            System.out.println(i);

        }

        return true;
    }

    private Integer[] extractNumbers(Integer[] input) {
        Integer[] numbers = new Integer[input.length - 1];

        for (int i = 0; i < input.length - 1; i++) {
            numbers[i] = input[i + 1];
        }
        return numbers;
    }

    public static void main(String[] args) throws IOException {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

    }
}
