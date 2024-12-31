package Days.Day3.PartTwo;

import Days.Day3.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ExpressionExtractor {

    private final InputManipulatable<String> manipulatedInput;
    private final String regularExpression;
    private int lastFoundDoIndex = 0;
    private final Map<Integer, Integer> disabledAreas;
    private final List<Integer[]> foundPermittedExpressions;

    public ExpressionExtractor(String regex) throws IOException {
        this.manipulatedInput = new InputFormatter();
        this.regularExpression = regex;
        this.disabledAreas = locateDisabledAreas();
        this.foundPermittedExpressions = findPermittedExpressions();
    }

    private Map<Integer, Integer> locateDisabledAreas() {
        List<Integer> indicesDo = getIndices("do\\(\\)");
        List<Integer> indicesDont = getIndices("don['’]t");
        Map<Integer, Integer> disabledAreas = calculateDisabledAreas(indicesDo, indicesDont);

        return disabledAreas;
    }

    private Map<Integer, Integer> calculateDisabledAreas(List<Integer> indicesDo, List<Integer> indicesDont) {
        Map<Integer, Integer> disabledAreas = new HashMap<>();
        for (Integer index : indicesDont) {
            for (; lastFoundDoIndex < indicesDo.size(); lastFoundDoIndex++) {
                if (indicesDo.get(lastFoundDoIndex) > index) {
                    disabledAreas.put(index, indicesDo.get(lastFoundDoIndex));
                }
            }
        }
        return disabledAreas;
    }

    private List<Integer> getIndices(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher regexMatcher = pattern.matcher(manipulatedInput.getFormattedInput());

        List<Integer> indices = new ArrayList<>();

        while (regexMatcher.find()) {
            indices.add(regexMatcher.end());
        }
        return indices;
    }

    private List<Integer[]> findPermittedExpressions() {
        Pattern regexPattern = Pattern.compile(regularExpression);
        Matcher regexMatcher = regexPattern.matcher(manipulatedInput.getFormattedInput());

        List<Integer[]> foundPermittedExpressions = new ArrayList<>();

        // Looking for Matches
        while (regexMatcher.find()) {
            if (!IndexIsInForbiddenRange(regexMatcher.end())) {
                // Extracting Numbers
                int firstNumber = Integer.parseInt(regexMatcher.group(1));
                int secondNumber = Integer.parseInt(regexMatcher.group(2));
                foundPermittedExpressions.add(new Integer[]{firstNumber, secondNumber});
            }
        }
        return foundPermittedExpressions;
    }

    private boolean IndexIsInForbiddenRange(int index) {
        for (Map.Entry<Integer, Integer> entry : disabledAreas.entrySet()) {
            int areaStart = entry.getKey();
            int areaEnd = entry.getValue();

            if (index > areaStart && index < areaEnd) {
                return true;
            }
        }
        return false;
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
