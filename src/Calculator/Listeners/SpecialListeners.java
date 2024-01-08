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
                if (Arrays.binarySearch(CalculatorFrame.basicOperators, calculation.getLast()) >= 0) {
                    calculation.add("(");
                } else if (isLastEntryNumber()) {
                    calculation.add("×");
                    calculation.add("(");
                }
                break;

            case ".":
                if (!calculation.isEmpty()) {
                    if (!calculation.getLast().contains(".")) {
                        calculation.set(calculation.size() - 1, calculation.getLast() + buttonInput);
                    }
                }
                break;

            case "=":
                if (isLastEntryNumber())
                    CalculatorFrame.displayResult();
                return;
        }

        //reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
