package Calculator.Windows;

import Calculator.Listeners.ButtonListener;
import Calculator.Listeners.RadioListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyListener;

import static Calculator.Calculations.MathFunctions.calculateCalculation;
import static Calculator.Calculations.MathFunctions.calculation;

public class CalculatorFrame extends JFrame {

    private static KeyListener keyListener = new Calculator.Listeners.KeyListener();
    private ButtonListener buttonListener = new ButtonListener(this);
    private RadioListener radioListener = new RadioListener(this);

    private static GridBagConstraints gbc = new GridBagConstraints();
    public static final JTextPane display = new JTextPane();
    private static final JScrollPane scrollPane = new JScrollPane(display);
    public static final JRadioButton radio1 = new JRadioButton("normal view");
    public static final JRadioButton radio2 = new JRadioButton("extended view");
    private static final JPanel extendedButtonsPane = new JPanel(new GridLayout(1, 4));


    public static final String[] basicOperators = {"+", "-", "×", "÷"};
    public static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] additionalFunctions = {"C" , "±", "%"};
    public static final String[] specialButtons = {".", "="};
    public static final String[] extendedFunctions = {"√", "^", "(", ")"};

    public CalculatorFrame() {
        setTitle("Calculator");
        setLayout(new GridBagLayout());
        setSize(350, 500);
        setMinimumSize(new Dimension(300, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(keyListener);


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
        display.setPreferredSize(new Dimension(100, 50));
        display.setEnabled(false);
        display.setDisabledTextColor(Color.BLACK);

        // make calculation aligned to right side
        StyledDocument style = display.getStyledDocument();
        SimpleAttributeSet align= new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);
        style.setParagraphAttributes(0, style.getLength(), align, false);

        // add scrollbar for bigger calculations
        JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        horizontalScrollBar.setPreferredSize(new Dimension(0, 0));
        scrollPane.setHorizontalScrollBar(horizontalScrollBar);

        // add to this frame
        add(scrollPane, gbc);
        displayCalculation();


        // set Layout for all Buttons
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        // add Buttons via own methods on Frame
        createMultipleButtons(additionalFunctions, 2, 1, 0, 3, false);
        createMultipleButtons(basicOperators, 2, 4, 3, 1, false);
        createMultipleButtons(numbers, 3, 3, 0, 3, true);

        // add special buttons with special layout
        gbc.gridx = 1;
        gbc.gridy = 6;
        createButton(specialButtons[0]);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        createButton(specialButtons[1]);

        // add buttons to activate "extend" Mode
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weighty = 0.25;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        radio1.setActionCommand("normal");
        radio2.setActionCommand("extended");
        radio1.addActionListener(radioListener);
        radio2.addActionListener(radioListener);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);

        radio1.setSelected(true);

        add(radio1, gbc);

        gbc.gridx = 2;
        add(radio2, gbc);

        // add extended Functions
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 1;

        createExtendButton();

    }

    private void createButton(String name){
        JButton button = new JButton();
        Font currentFont = button.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.BOLD, 20);
        button.setText(name);
        button.addActionListener(buttonListener);
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

    private void createExtendButton(){

        for (String name : extendedFunctions) {
            JButton button = new JButton();
            Font currentFont = button.getFont();
            Font newFont = new Font(currentFont.getName(), currentFont.BOLD, 20);
            button.setText(name);
            button.addActionListener(buttonListener);
            button.setActionCommand(name);
            button.setFont(newFont);
            extendedButtonsPane.add(button);
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

    public void extendView() {

        add(extendedButtonsPane, gbc);

        revalidate();
        repaint();
    }

    public void normalView() {

        remove(extendedButtonsPane);

        revalidate();
        repaint();
    }
}
