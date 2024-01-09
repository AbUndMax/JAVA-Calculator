package Calculator.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Calculator.Calculations.MathFunctions.calculateCalculation;
import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.CallHandler.callHandler;

public class CalculatorFrame extends JFrame {
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
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '*') {
                    callHandler("×");
                }
                if (e.getKeyChar() == '/') {
                    callHandler("÷");
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    callHandler("=");
                }
                callHandler(Character.toString(e.getKeyChar()));
            }
        });


        // set Display Layout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.insets = new Insets(3, 3, 1, 3);
        // create Display
        display.setFont(new Font("Sans Serif", Font.BOLD, 24));
        display.setOpaque(true);
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        display.setPreferredSize(new Dimension(100, 50));
        // add Display
        add(display, gbc);
        displayCalculation();


        // set Layout for all Buttons
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        // add Buttons via own methods on Frame
        createMultipleButtons(additionalFunctions, 1, 1, 0, 3, false);
        createMultipleButtons(basicOperators, 1, 4, 3, 1, false);
        createMultipleButtons(numbers, 2, 3, 0, 3, true);

        // add special buttons with special layout
        gbc.gridx = 1;
        gbc.gridy = 5;
        createButton(specialButtons[3]);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        createButton(specialButtons[2]);
    }

    private void createButton(String name){
        JButton button = new JButton();
        Font currentFont = button.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.BOLD, 20);
        button.setText(name);
        button.addActionListener(e -> {callHandler(e.getActionCommand());
            requestFocus();});
        button.setActionCommand(name);
        button.setFont(newFont);
        add(button, gbc);
    }

    private void createMultipleButtons(String[] buttonArray, int row, int numberRows, int column, int numberColumns, Boolean byRow) {
        int counter = 0;

        if (byRow) {
            for (String buttonName : buttonArray) {
                gbc.gridy = counter / numberRows + row;
                gbc.gridx = counter % numberColumns + column;
                createButton(buttonName);
                counter++;
            }
        }
        else {
            for (String buttonName : buttonArray) {
                gbc.gridy = counter % numberRows + row;
                gbc.gridx = counter % numberColumns + column;
                createButton(buttonName);
                counter++;
            }
        }
    }

    public static void displayCalculation() {
        String calcString = "";

        for (String value : calculation) {
            calcString += value;
        }
        display.setText(calcString);
    }

    public static void displayResult() {
        Double result = calculateCalculation();
        if (result == Math.floor(result)) {
            display.setText(String.valueOf(result.intValue()));
        }
        else {
            display.setText(String.valueOf(result));
        }
    }
}
