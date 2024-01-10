package Calculator.Handlers;

import Calculator.Windows.CalculatorFrame;
import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.CallHandler.isLastEntryNumber;
import static Calculator.Handlers.CallHandler.replaceLast;

public class NumberHandler {

    public static void numberHandler(String command) {

        String lastEntry = calculation.getLast();

        // if there is already a number, put booth number together -> "2" with new input: "3" -> "23"

        // if the first number is a zero, we don't need this zero i.e. 023 = 23
        if (lastEntry.charAt(0) == '0'){
            replaceLast(command);
        }

        // if there is already a number, and the input is another number, both numbers gets concatenated
        else if (isLastEntryNumber()) {
            replaceLast(lastEntry + command);
        }

        // if the last entry has an . the next number gets appended
        else if (lastEntry.contains(".")) {
            replaceLast(lastEntry + command);
        }

        // otherwise the number gets just added to the calculation list
        else calculation.add(command);

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
