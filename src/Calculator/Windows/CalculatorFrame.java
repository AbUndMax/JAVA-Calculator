package Calculator.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import Calculator.Calculations.MathFunctions;
import Calculator.Listeners.*;

public class CalculatorFrame extends JFrame {

    private static AdditionalListeners additionalListener = new AdditionalListeners();
    private static NumberListeners numberListener = new NumberListeners();
    private static BasicOperatorListeners basicListener = new BasicOperatorListeners();
    private static SpecialListeners specialListener = new SpecialListeners();

    private static GridBagConstraints gbc = new GridBagConstraints();

    public static JLabel display = new JLabel();

    public static final String[] basicOperators = {"+", "-", "×", "÷"};
    public static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] additionalFunctions = {"C" , "±", "%"};
    public static final String[] specialButtons = {"(", ")", "=", "."};
    public static final String[] extendedFunctions = {"√", "^", "n!", "log"};

    public CalculatorFrame() {
        setTitle("Calculator");
        setLayout(new GridBagLayout());
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // set Display Layout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        // create Display
        display.setFont(new Font("Sans Serif", Font.BOLD, 24));
        display.setOpaque(true);
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        display.setPreferredSize(new Dimension(380, 50));
        display.setText(String.valueOf(MathFunctions.calculation));
        // add Display
        add(display, gbc);


        // set Layout for all Buttons
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        // add Buttons via own methods on Frame
        createMultipleButtons(additionalFunctions, additionalListener, 1, 1, 0, 3, false);
        createMultipleButtons(basicOperators, basicListener, 1, 4, 3, 1, false);
        createMultipleButtons(numbers, numberListener, 2, 3, 0, 3, true);

        // add special buttons with special layout
        gbc.gridx = 1;
        gbc.gridy = 5;
        createButton(specialButtons[3], specialListener);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        createButton(specialButtons[2], specialListener, new Color(255, 229, 204));
    }

    private void createButton(String name, ActionListener listener){
        JButton button = new JButton();
        Font currentFont = button.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.BOLD, 20);
        button.setText(name);
        button.addActionListener(listener);
        button.setActionCommand(name);
        button.setFont(newFont);
        add(button, gbc);
    }

    private void createButton(String name, ActionListener listener, Color backgroundColor){
        JButton button = new JButton();
        Font currentFont = button.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.BOLD, 20);
        button.setText(name);
        button.addActionListener(listener);
        button.setActionCommand(name);
        button.setFont(newFont);
        button.setBackground(backgroundColor);
        button.setBorderPainted(false); // Vorsicht, das schnediet den eigentlichen Button ab!
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        add(button, gbc);
    }

    private void createMultipleButtons(String[] buttonArray, ActionListener listener, int row, int numberRows, int column, int numberColumns, Boolean byRow) {
        int counter = 0;

        if (byRow) {
            for (String buttonName : buttonArray) {
                gbc.gridy = counter / numberRows + row;
                gbc.gridx = counter % numberColumns + column;
                createButton(buttonName, listener);
                counter++;
            }
        }
        else {
            for (String buttonName : buttonArray) {
                gbc.gridy = counter % numberRows + row;
                gbc.gridx = counter % numberColumns + column;
                createButton(buttonName, listener);
                counter++;
            }
        }
    }
}
