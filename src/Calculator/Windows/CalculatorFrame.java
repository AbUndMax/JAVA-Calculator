package Calculator.Windows;

import javax.swing.*;
import java.awt.*;
import Calculator.Listeners.*;

public class CalculatorFrame extends JFrame {

    private static FunctionListeners functionListener = new FunctionListeners();

    private static GridBagConstraints gbc = new GridBagConstraints();

    private JLabel display = new JLabel();

    public static final String[] basicOperators = {"+", "-", "×", "÷", "="};
    public static final String[] additionalFunctions = {"%", "±", "C", "."};

    public CalculatorFrame() {
        setTitle("Calculator");
        setLayout(new GridBagLayout());
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);





    }

    private static JButton createButton(String name){
        JButton button = new JButton();
        button.setText(name);
        button.addActionListener(functionListener);
        button.setActionCommand(name);
        return button;
    }
}
