package Calculator.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

public class AdditionalListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonInput = e.getActionCommand();

        switch(buttonInput){
            case "C":
                MathFunctions.calculation.clear();
                break;
            case "±":
        }

        // reprint calculation on display
        CalculatorFrame.display.setText(String.valueOf(MathFunctions.calculation));
    }
}
