package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;
import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Listeners.AdditionalListeners.isLastEntryNumber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class BasicOperatorListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        // if the last entry in calculation is a number, the operator is inserted into the calculation
        // else, the last operator will be overwritten by the new one.
        if (isLastEntryNumber()) {
            calculation.add(buttonInput);
        }

        else {
            calculation.set(calculation.size() - 1, buttonInput);
        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }

}
