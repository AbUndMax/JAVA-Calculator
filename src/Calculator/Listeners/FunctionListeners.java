package Calculator.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

public class FunctionListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals(CalculatorFrame.basicOperators[0])){
            int i = 0;
        }
    }
}
