package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NumberListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // if there is already a number, put booth number together -> "2" with new input: "3" -> "23"
        // first check if array has already an element, because otherwise .getLast() will throw an error.
        if (MathFunctions.calculation.size() > 0) {

            // if there is already a number, and the input is another number, both numbers gets appended
            if (Arrays.binarySearch(CalculatorFrame.numbers, String.valueOf(MathFunctions.calculation.getLast().charAt(0))) >= 0) {
                MathFunctions.calculation.set(MathFunctions.calculation.size() - 1, MathFunctions.calculation.getLast() + e.getActionCommand());
            }

            // if the last entry has an . the next number gets appended
            else if (MathFunctions.calculation.getLast().contains(".")) {
                MathFunctions.calculation.set(MathFunctions.calculation.size() - 1, MathFunctions.calculation.getLast() + e.getActionCommand());
            }

            // otherwise the number gets just added to the calculation list
            else {
                MathFunctions.calculation.add(e.getActionCommand());
            }
        }
        else {
            MathFunctions.calculation.add(e.getActionCommand());
        }

        CalculatorFrame.display.setText(String.valueOf(MathFunctions.calculation));
    }
}
