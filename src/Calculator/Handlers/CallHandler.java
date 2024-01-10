package Calculator.Handlers;

import Calculator.Windows.CalculatorFrame;

import java.util.Arrays;
import java.util.List;

import static Calculator.Calculations.MathFunctions.calculation;

// This class is the "bridge" between the listeners and handlers. This allows us to use the same handlers for the key and
// button listeners likewise!!

// there are multiple handler classes, simply for organisation reasons and improve code readability!

public class CallHandler {

    public static void callHandler(String command) {

        // use NumberHandler if input is number
        if (isAdditionalFunction(command)) {
            AdditionalHandler.additionalHandler(command);
        }

        if (isBasicOperator(command)) {
            BasicOperatorHandler.basicOperatorHandler(command);
        }

        if (isNumber(command)) {
            NumberHandler.numberHandler(command);
        }

        if (isSpecialFunction(command)) {
            SpecialHandler.specialHandler(command);
        }

        if (isExtendedFunction(command)) {
            ExtendedHandler.extendedHandler(command);
        }
    }

    // the following functions are all simply for checking to which handler the input has to be parsed.

    private static Boolean isAdditionalFunction(String command) {
        for (String additionalFunction : CalculatorFrame.additionalFunctions) {
            if (command.equals(additionalFunction)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isBasicOperator(String command) {
        for (String basicOperator : CalculatorFrame.basicOperators) {
            if (command.equals(basicOperator)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isNumber(String command) {
        for (String number : CalculatorFrame.numbers) {
            if (command.equals(number)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isSpecialFunction(String command) {
        for (String specialFunction : CalculatorFrame.specialButtons) {
            if (command.equals(specialFunction)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isExtendedFunction(String command) {
        for (String extendedFunction : CalculatorFrame.extendedFunctions) {
            if (command.equals(extendedFunction)) {
                return true;
            }
        }
        return false;
    }

    // this function are for all the other Handlers
    public static final Boolean isLastEntryNumber() {
        String lastEntry = calculation.getLast();
        List<String> numbersList = Arrays.asList(CalculatorFrame.numbers);

        return numbersList.contains(String.valueOf(lastEntry.charAt(lastEntry.length() - 1)));
    }

    public static final void replaceLast(String replacement) {
        calculation.set(calculation.size() - 1, replacement);
    }
}
