package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SpecialListeners implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        switch (buttonInput) {
            case "(":
                if (Arrays.binarySearch(CalculatorFrame.basicOperators, MathFunctions.calculation.getLast()) >= 0) {
                    MathFunctions.calculation.add(buttonInput);
                } else if (Arrays.binarySearch(CalculatorFrame.numbers, MathFunctions.calculation.getLast()) >= 0) {
                    MathFunctions.calculation.add("×");
                    MathFunctions.calculation.add("(");
                }
                break;
            case ".":
                MathFunctions.calculation.set(MathFunctions.calculation.size() - 1, MathFunctions.calculation.getLast() + buttonInput);
                break;

        }

        // reprint calculation on display
        CalculatorFrame.display.setText(String.valueOf(MathFunctions.calculation));
    }
}
