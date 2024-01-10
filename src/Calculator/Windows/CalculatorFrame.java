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

    //Listeners
    private static final KeyListener keyListener = new Calculator.Listeners.KeyListener();
    private ButtonListener buttonListener = new ButtonListener(this);
    private RadioListener radioListener = new RadioListener(this);

    // GUI-Components
    private static GridBagConstraints gbc = new GridBagConstraints();
    public static final JTextPane display = new JTextPane();
    private static final JScrollPane scrollPane = new JScrollPane(display);
    public static final JRadioButton radio1 = new JRadioButton("normal view");
    public static final JRadioButton radio2 = new JRadioButton("extended view");
    private static final JPanel extendedButtonsPane = new JPanel(new GridLayout(1, 4));
    private static final JPanel displayPanel = new JPanel(new BorderLayout());

    // Button Name Arrays
    public static final String[] basicOperators = {"+", "-", "×", "÷"};
    public static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] additionalFunctions = {"C" , "±", "%"};
    public static final String[] specialButtons = {".", "=", "⌫"};
    public static final String[] extendedFunctions = {"√", "^", "(", ")"};


    public CalculatorFrame() {

        // Frame settings
        setTitle("Calculator");
        setLayout(new GridBagLayout());
        setSize(350, 500);
        setMinimumSize(new Dimension(200, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(keyListener);


        // set Display Layout
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

        // add scrollbar for bigger calculations ( the right side is always visible)
        // make the scrollBars invisible!
        JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        horizontalScrollBar.setPreferredSize(new Dimension(0, 0));
        scrollPane.setHorizontalScrollBar(horizontalScrollBar);

        JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        verticalScrollBar.setPreferredSize(new Dimension(0, 0));
        scrollPane.setVerticalScrollBar(verticalScrollBar);

        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // create a Button which allows the user to redo their input
        JButton delButton = createButton("⌫");
        delButton.setPreferredSize(new Dimension(40, 50));
        displayPanel.add(delButton, BorderLayout.EAST);


        // add displayPanel to frame
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(3, 3, 1, 3);

        // add to this frame
        add(displayPanel, gbc);
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
        add(createButton(specialButtons[0]), gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(createButton(specialButtons[1]), gbc);


        // add radio buttons to activate "extend" Mode
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

        // normal mode is default
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

        // create all extended buttons
        for (String name : extendedFunctions) {
            extendedButtonsPane.add(createButton(name));
        }

        // put them on a JPanel
        add(extendedButtonsPane, gbc);

        // make Panel invisible, because Normal mode doesn't show them
        extendedButtonsPane.setVisible(false);

    }

    // a simple function for creating buttons so all buttons have the same Font etc... (prevents code repetition)
    private JButton createButton(String name){
        JButton button = new JButton();
        Font currentFont = button.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.BOLD, 20);
        button.setText(name);
        button.addActionListener(buttonListener);
        button.setActionCommand(name);
        button.setFont(newFont);

        return button;
    }

    // special case for multiple button creation based on the button Arrays. (prevents Code repetition)
    private void createMultipleButtons(String[] buttonArray, int row, int numberRows, int column, int numberColumns, Boolean byRow) {
        int counter = 0;

        // there is an if / else for deciding on the insertion order by row or by col

        if (byRow) {
            for (String buttonName : buttonArray) {
                gbc.gridy = counter / numberRows + row;
                gbc.gridx = counter % numberColumns + column;
                add(createButton(buttonName), gbc);

                counter++;
            }
        }
        else {
            for (String buttonName : buttonArray) {
                gbc.gridy = counter % numberRows + row;
                gbc.gridx = counter % numberColumns + column;
                add(createButton(buttonName), gbc);
                counter++;
            }
        }
    }

    // The calculation is shown by simply concatenate all Strings in calculation ArrayList
    public static void displayCalculation() {
        String calcString = "";

        for (String value : calculation) {
            calcString += value;
        }
        display.setText(calcString);
    }

    // The result is drawn by first checking if it's an integer and therefore drawn without a .0
    // or with the full .xxxxxxx numbers of the result if it is an double value!
    public static void displayResult() {
        Double result = calculateCalculation();
        if (result == Math.floor(result)) {
            display.setText(String.valueOf(result.intValue()));
        }
        else {
            display.setText(String.valueOf(result));
        }
    }

    // Method for expanding the extended Buttons Panel
    public void extendView() {

        extendedButtonsPane.setVisible(true);

        revalidate();
        repaint();
    }

    // Method for hiding extended Button Panel
    public void normalView() {

        extendedButtonsPane.setVisible(false);

        revalidate();
        repaint();
    }
}
