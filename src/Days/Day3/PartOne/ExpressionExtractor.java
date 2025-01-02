package Days.Day3.PartOne;

import Days.Day3.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ExpressionExtractor {

    private final InputManipulatable<String> manipulatedInput;
    private final String regularExpression;
    private final List<Integer[]> foundPermittedExpressions;

    public ExpressionExtractor(String regex) throws IOException {
        this.manipulatedInput = new InputFormatter();
        this.regularExpression = regex;
        this.foundPermittedExpressions = findPermittedExpressions();
    }

    private List<Integer[]> findPermittedExpressions() {
        Pattern regexPattern = Pattern.compile(regularExpression);
        Matcher regexMatcher = regexPattern.matcher(manipulatedInput.getConvertedInput());

        List<Integer[]> foundPermittedExpressions = new ArrayList<>();

        // Looking for Matches
        while (regexMatcher.find()) {
            // Extracting Numbers
            int firstNumber = Integer.parseInt(regexMatcher.group(1));
            int secondNumber = Integer.parseInt(regexMatcher.group(2));
            foundPermittedExpressions.add(new Integer[]{firstNumber, secondNumber});
        }
        return foundPermittedExpressions;
    }

    public List<Integer[]> getExpressions() {
        return this.foundPermittedExpressions;
    }

    public static void main(String[] args) throws IOException {
        ExpressionExtractor test = new ExpressionExtractor("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        for (Integer[] expression : test.getExpressions()) {
            for (int number : expression) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }
}
