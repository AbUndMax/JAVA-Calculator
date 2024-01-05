package Calculator.Windows;

import javax.swing.*;
import java.awt.*;

import Calculator.Calculations.MathFunctions;
import Calculator.Listeners.*;

public class CalculatorFrame extends JFrame {

    private static FunctionListeners functionListener = new FunctionListeners();
    private static NumberListeners numberListener = new NumberListeners();

    private static GridBagConstraints gbc = new GridBagConstraints();

    public static JLabel display = new JLabel();

    public static final String[] basicOperators = {"+", "-", " ", "÷", "="};
    public static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] additionalFunctions = {"%", "±", "C", "."};
    public static final String[] parenthesisFunctions = {"(", ")"};

    public CalculatorFrame() {
        setTitle("Calculator");
        setLayout(new GridBagLayout());
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        display.setFont(new Font("Sans Serif", Font.BOLD, 24));
        display.setOpaque(true);
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        display.setPreferredSize(new Dimension(380, 50));
        display.setText(String.valueOf(MathFunctions.calculation));

        int yPos = 0;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = yPos++;
        add(display, gbc);




        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;

        int counter = 0;

        for (int i = 0; i < numbers.length-1; i++) {
            JButton button = createNumberButton(numbers[i]);
            gbc.gridy = counter / 3 + yPos;
            gbc.gridx = counter % 3 + yPos;
            add(button, gbc);
            counter++;
        }




    }

    private static JButton createNumberButton(String name){
        JButton button = new JButton();
        button.setText(name);
        button.addActionListener(numberListener);
        button.setActionCommand(name);
        return button;
    }

    private static JButton createFunctionButton(String name){
        JButton button = new JButton();
        button.setText(name);
        button.addActionListener(functionListener);
        button.setActionCommand(name);
        return button;
    }
}
