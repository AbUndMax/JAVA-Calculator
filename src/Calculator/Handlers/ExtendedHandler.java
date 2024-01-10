package Calculator.Handlers;


import Calculator.Windows.CalculatorFrame;
import java.util.Arrays;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.CallHandler.isLastEntryNumber;
import static Calculator.Handlers.CallHandler.replaceLast;

public class ExtendedHandler {

    public static void extendedHandler(String command) {

        String lastEntry = calculation.getLast();

        switch (command) {
            case "(":
                //if the last entry is a operator, "(" will be added to calculation
                // otherwise if the last entry is a number, a multiplication symbol will be added in between
                if (Arrays.binarySearch(CalculatorFrame.basicOperators, lastEntry) >= 0 | lastEntry.equals("^")) {
                    calculation.add("(");
                }
                else if (lastEntry.equals("0")) {
                    replaceLast("(");
                }
                else if (isLastEntryNumber()) {
                    calculation.add("×");
                    calculation.add("(");
                }
                break;

            case ")":
                calculation.add(")");
                break;

            case "√":
                if (isLastEntryNumber() & !lastEntry.equals("0")) {
                    calculation.add("×");
                    calculation.add("√");
                    calculation.add("(");
                } else if (lastEntry.equals("0")){
                    replaceLast("√");
                    calculation.add("(");
                } else {
                    calculation.add("√");
                    calculation.add("(");
                }
                break;

            case "^":
                if (isLastEntryNumber() | lastEntry.equals(")")) {
                calculation.add("^");
            }


        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
