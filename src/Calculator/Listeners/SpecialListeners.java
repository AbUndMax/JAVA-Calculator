package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Listeners.AdditionalListeners.isLastEntryNumber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SpecialListeners implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        switch (buttonInput) {
            case "(":
                //if the last entry is a operator, "(" will be added to calculation
                // otherwise if the last entry is a number, a multiplication symbol will be added in between
                if (Arrays.binarySearch(CalculatorFrame.basicOperators, calculation.getLast()) >= 0) {
                    calculation.add("(");
                } else if (isLastEntryNumber()) {
                    calculation.add("×");
                    calculation.add("(");
                }
                break;

            case ".":
                // if the last entry in the calculation list is a number and it does not already contain a . the a
                // "." will be concatenated to the last string in calculation list.
                if (isLastEntryNumber()) {
                    if (!calculation.getLast().contains(".")) {
                        calculation.set(calculation.size() - 1, calculation.getLast() + buttonInput);
                    }
                }
                break;

            case "=":
                // if last entry is a number, the result gets shown on the display, the calculation is cleared and
                // the new entry is the last result, so we can calculate with our last result.
                if (isLastEntryNumber()) {
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
