package Calculator.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Calculator.Handlers.CallHandler.callHandler;

public class ButtonListener implements ActionListener {

    private JFrame frame;

    public ButtonListener(JFrame frame){
        this.frame = frame;
    }

    // button is pressed -> actionCommand is parsed to callHandler
    @Override
    public void actionPerformed(ActionEvent e) {
        callHandler(e.getActionCommand());
        frame.requestFocus();
    }
}