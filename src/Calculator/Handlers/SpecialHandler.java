package Calculator.Handlers;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.AdditionalHandler.isLastEntryNumber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SpecialHandler {

    public static void specialHandler(String command) {

        switch (command) {

            case ".":
                // if the last entry in the calculation list is a number and it does not already contain a . the a
                // "." will be concatenated to the last string in calculation list.
                if (isLastEntryNumber()) {
                    if (!calculation.getLast().contains(".")) {
                        calculation.set(calculation.size() - 1, calculation.getLast() + command);
                    }
                }
                break;

            case "=":
                // if last entry is a number, the result gets shown on the display, the calculation is cleared and
                // the new entry is the last result, so we can calculate with our last result.
                if (isLastEntryNumber() | calculation.getLast().equals(")")) {
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
        }

        //reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
