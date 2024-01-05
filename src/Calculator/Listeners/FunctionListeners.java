package Calculator.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

public class FunctionListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        if (buttonInput.equals(CalculatorFrame.basicOperators[0])){
            int i = 0;
        }

        // parenthesis open "("
        if (buttonInput.equals("(")) {
            if (Arrays.binarySearch(CalculatorFrame.basicOperators, MathFunctions.calculation.getLast()) >= 0) {
                MathFunctions.calculation.add(buttonInput);
            } else if (Arrays.binarySearch(CalculatorFrame.numbers, MathFunctions.calculation.getLast()) >= 0) {
                MathFunctions.calculation.add("×");
                MathFunctions.calculation.add("(");
            }
        }

        if (buttonInput.equals(".")) {
            MathFunctions.calculation.set(MathFunctions.calculation.size() - 1, MathFunctions.calculation.getLast() + buttonInput);
        }
    }
}
