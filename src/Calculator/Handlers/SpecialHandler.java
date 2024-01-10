package Calculator.Handlers;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.CallHandler.isLastEntryNumber;
import static Calculator.Handlers.CallHandler.replaceLast;

public class SpecialHandler {

    public static void specialHandler(String command) {

        String lastEntry = calculation.getLast();

        switch (command) {

            case ".":
                // if the last entry in the calculation list is a number and it does not already contain a . the a
                // "." will be concatenated to the last string in calculation list.
                if (isLastEntryNumber()) {

                    if (!lastEntry.contains(".")) {
                        replaceLast(lastEntry + command);
                    }
                }
                break;

            case "=":
                // if last entry is a number, the result gets shown on the display, the calculation is cleared and
                // the new entry is the last result, so we can calculate with our last result.
                if (isLastEntryNumber() | lastEntry.equals(")")) {
                    CalculatorFrame.displayResult();

                    Double result = MathFunctions.calculateCalculation();
                    String resultString;
                    if (result == Math.floor(result)) {
                        resultString = String.valueOf(result.intValue());
                    } else {
                        resultString = String.valueOf(result);
                    }
                    calculation.clear();
                    calculation.add(resultString);
                }

                return;

            case "⌫":

                // if the last string is only one Character, the string gets removed from calculation
                // if this will lead to an empty calculation -> "0" is inserted
                if (lastEntry.length() == 1) {
                    calculation.removeLast();

                    if (calculation.isEmpty()) {
                        calculation.add("0");
                    }
                }
                // elsewise the last Character in the last String in calculation will get removed "123" -> "12"
                else {
                    replaceLast(lastEntry.substring(0, lastEntry.length() - 1));
                }
        }

        //reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
