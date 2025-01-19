package Days.Day7.PartTwo;

import Days.Day7.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpressionEvaluator {

    private final InputManipulatable<List<Long[]>> input;
    private final List<Long> foundEquatations;

    public ExpressionEvaluator() throws IOException {
        this.input = new InputFormatter();
        this.foundEquatations = evaluateExpression(input.getConvertedInput());
    }

    private List<Long> evaluateExpression(List<Long[]> convertedInput) {
        List<Long> foundMatchingExpressions = new ArrayList<>();

        for (Long[] input : convertedInput) {
            if (expressionMatches(input)) {
                foundMatchingExpressions.add(input[0]);
            }
        }

        return foundMatchingExpressions;
    }

    private boolean expressionMatches(Long[] input) {
        Long result = input[0];

        return iterateExpressions(result, input);
    }

    private boolean iterateExpressions(Long result, Long[] input) {
        Long[] numbersToCalculate = extractNumbers(input);

        return recursiveEvaluation(numbersToCalculate, 1, numbersToCalculate[0], result);
    }

    private boolean recursiveEvaluation(Long[] numbersToCalculate, int index, Long currentResult, Long result) {
        if (index >= numbersToCalculate.length) {
            return Objects.equals(currentResult, result);
        }

        Long nextNumber = numbersToCalculate[index];
        String concatNumber =  currentResult.toString().concat(nextNumber.toString());

        return recursiveEvaluation(numbersToCalculate, index + 1, currentResult + nextNumber, result) ||
                recursiveEvaluation(numbersToCalculate, index + 1, currentResult * nextNumber, result) ||
                recursiveEvaluation(numbersToCalculate, index + 1, Long.parseLong(concatNumber), result);
    }

    private Long[] extractNumbers(Long[] input) {
        Long[] numbers = new Long[input.length - 1];

        for (int i = 0; i < input.length - 1; i++) {
            numbers[i] = input[i + 1];
        }
        return numbers;
    }

    public List<Long> getFoundEquatations() {
        return this.foundEquatations;
    }

    public static void main(String[] args) throws IOException {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
    }
}
