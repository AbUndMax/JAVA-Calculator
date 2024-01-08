package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;
import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Listeners.AdditionalListeners.isLastEntryNumber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NumberListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // if there is already a number, put booth number together -> "2" with new input: "3" -> "23"
        // first check if array has already an element, because otherwise .getLast() will throw an error.
        if (!calculation.isEmpty()) {

            // if there is already a number, and the input is another number, both numbers gets appended
            if (isLastEntryNumber()) {
                calculation.set(calculation.size() - 1, calculation.getLast() + e.getActionCommand());
            }


            else if (calculation.getLast().contains(".") & countOccurrencesOfDot(calculation.getLast()) == 1) {
                calculation.set(calculation.size() - 1, calculation.getLast() + e.getActionCommand());
            }

            // if the last entry has an . the next number gets appended
            else if (calculation.getLast().contains(".")) {
                calculation.set(calculation.size() - 1, calculation.getLast() + e.getActionCommand());
            }

            // otherwise the number gets just added to the calculation list
            else calculation.add(e.getActionCommand());
        }
        else calculation.add(e.getActionCommand());

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }

    private static int countOccurrencesOfDot(String text) {
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.') count++;
        }

        return count;
    }
}
