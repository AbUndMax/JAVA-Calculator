package Calculator.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import static Calculator.Calculations.MathFunctions.calculation;

public class AdditionalListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        switch(buttonInput){
            // clear calculation
            case "C":
                calculation.clear();
                break;

            // changes the last number to negative if positive and to positive if negative
            case "±":
                if (isLastEntryNumber()) {
                    if (Double.parseDouble(calculation.getLast()) > 0)
                        calculation.set(calculation.size() - 1, "-" + calculation.getLast());

                    else if (Double.parseDouble(calculation.getLast()) < 0)
                        calculation.set(calculation.size() - 1, calculation.getLast().substring(1));
                }

                break;

            case "%":
                if (isLastEntryNumber()) {
                    calculation.set(calculation.size() - 1, String.valueOf(Double.parseDouble(calculation.getLast()) * 0.01));
                }
                break;
        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }

    public static final Boolean isLastEntryNumber() {
        if (!calculation.isEmpty()) {
            return Arrays.binarySearch(CalculatorFrame.numbers, String.valueOf(calculation.getLast().charAt(calculation.getLast().length() - 1))) >= 0;
        } else return false;
    }
}
