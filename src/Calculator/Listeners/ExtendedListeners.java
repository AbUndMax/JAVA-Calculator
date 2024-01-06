package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtendedListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        switch (buttonInput) {
            case "√":
                MathFunctions.calculation.add("√");

        }

        // reprint calculation on display
        CalculatorFrame.display.setText(String.valueOf(MathFunctions.calculation));
    }
}
