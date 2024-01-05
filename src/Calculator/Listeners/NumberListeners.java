package Calculator.Listeners;

import Calculator.Calculations.MathFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberListeners implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MathFunctions.calculation.add(e.getActionCommand());
    }
}
