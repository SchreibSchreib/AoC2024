package Days.Day3.PartTwo;

import Days.Day3.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;
import java.util.*;
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

        return calculateDisabledAreas(indicesDo, indicesDont);
    }

    private Map<Integer, Integer> calculateDisabledAreas(List<Integer> indicesDo, List<Integer> indicesDont) {
        TreeMap<Integer, Integer> disabledAreas = new TreeMap<>();
        for (Integer index : indicesDont) {
            if (isNewKeyHigherThenLastValue(disabledAreas, index)) {
                for (; lastFoundDoIndex < indicesDo.size(); lastFoundDoIndex++) {
                    if (indicesDo.get(lastFoundDoIndex) > index && !isValueAlreadyGiven(disabledAreas, indicesDo.get(lastFoundDoIndex))) {
                        disabledAreas.put(index, indicesDo.get(lastFoundDoIndex));
                        break;
                    }
                }
            }
        }
        return disabledAreas;
    }

    private boolean isNewKeyHigherThenLastValue(TreeMap<Integer, Integer> disabledAreas, Integer index) {
        if (disabledAreas.isEmpty()) {
            return true;
        }
        return disabledAreas.lastEntry().getValue() < index;
    }

    private boolean isValueAlreadyGiven(Map<Integer, Integer> disabledAreas, Integer value) {
        for (Map.Entry<Integer, Integer> entry : disabledAreas.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                return true;
            }
        }
        return false;
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
            if (!indexIsInForbiddenRange(regexMatcher.end())) {
                // Extracting Numbers
                int firstNumber = Integer.parseInt(regexMatcher.group(1));
                int secondNumber = Integer.parseInt(regexMatcher.group(2));
                foundPermittedExpressions.add(new Integer[]{firstNumber, secondNumber});
            }
        }
        return foundPermittedExpressions;
    }

    private boolean indexIsInForbiddenRange(int index) {
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
