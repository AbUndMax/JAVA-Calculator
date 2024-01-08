package Calculator.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

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
                calculation.add("0");
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
                // simply adds a 0.01 multiplication
                if (isLastEntryNumber()) {
                    calculation.set(calculation.size() - 1, String.valueOf(Double.parseDouble(calculation.getLast()) * 0.01));
                }
                break;
        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }

    // this function checks, rather the last entry in calculation is a number or not!
    public static final Boolean isLastEntryNumber() {
        List<String> numbersList = Arrays.asList(CalculatorFrame.numbers);
        return numbersList.contains(String.valueOf(calculation.getLast().charAt(calculation.getLast().length() - 1)));
    }
}
