package Calculator.Listeners;

import Calculator.Windows.CalculatorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Calculator.Windows.CalculatorFrame.radio1;
import static Calculator.Windows.CalculatorFrame.radio2;

public class RadioListener implements ActionListener {

    private CalculatorFrame frame;

    public RadioListener(CalculatorFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();

        if (source == radio1) {
            frame.normalView();
        }

        if (source == radio2) {
            frame.extendView();
        }
    }
}
