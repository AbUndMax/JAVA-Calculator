package Calculator.Handlers;

import Calculator.Windows.CalculatorFrame;

// This is just for deciding which Handler should be used, based on the input of the Action nListener!
// That allows us to use Key and Button Listeners on the same Methods!

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

    private static Boolean isExtendedFunction(String command) {
        for (String extendedFunction : CalculatorFrame.extendedFunctions) {
            if (command.equals(extendedFunction)) {
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

}
