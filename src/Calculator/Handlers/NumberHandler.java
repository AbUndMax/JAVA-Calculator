package Calculator.Handlers;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.CallHandler.isLastEntryNumber;
import static Calculator.Handlers.CallHandler.replaceLast;

public class NumberHandler {

    public static void numberHandler(String command) {

        String lastEntry = calculation.getLast();



        // if the last entry has an . the next number gets appended
        if (lastEntry.contains(".")) {
            replaceLast(lastEntry + command);
        }

        // if the first number is a zero, we don't need this zero i.e. 023 = 23
        else if (lastEntry.charAt(0) == '0'){
            replaceLast(command);
        }

        // if there is already a number, and the input is another number, both numbers gets concatenated
        // i.e. "2" with new input: "3" -> "23"
        else if (isLastEntryNumber()) {
            replaceLast(lastEntry + command);
        }

        // otherwise the number gets just added to the calculation list
        else calculation.add(command);

    }

}
