package Calculator.Handlers;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.AdditionalHandler.isLastEntryNumber;

public class ExtendedHandler {

    public static void extendedHandler(String command) {

        switch (command) {
            case "(":
                //if the last entry is a operator, "(" will be added to calculation
                // otherwise if the last entry is a number, a multiplication symbol will be added in between
                if (Arrays.binarySearch(CalculatorFrame.basicOperators, calculation.getLast()) >= 0 | calculation.getLast().equals("^")) {
                    calculation.add("(");
                }
                else if (calculation.getLast().equals("0")) {
                    calculation.set(calculation.size() - 1, "(");
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
                if (isLastEntryNumber() & !calculation.getLast().equals("0")) {
                    calculation.add("×");
                    calculation.add("√");
                    calculation.add("(");
                } else if (calculation.getLast().equals("0")){
                    calculation.set(calculation.size() - 1, "√");
                    calculation.add("(");
                } else {
                    calculation.add("√");
                    calculation.add("(");
                }
                break;

            case "^":
                if (isLastEntryNumber() | calculation.getLast().equals(")")) {
                calculation.add("^");
            }


        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
