package Days.Day3.PartOne;

import Days.Day3.InputFormatter;
import Interfaces.InputManipulatable;

import java.io.IOException;

class StringPurifier {

    private InputManipulatable manipulatedInput;
    String regularExpression;
    String[] foundPermittedExpressions;

    private StringPurifier() throws IOException {
        manipulatedInput = new InputFormatter();

    }
}
