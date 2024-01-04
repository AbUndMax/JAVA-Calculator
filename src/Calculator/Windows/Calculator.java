package Calculator.Windows;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private static GridBagLayout gbl = new GridBagLayout();
    private static GridBagConstraints gbc = new GridBagConstraints();

    public Calculator() {
        setTitle("Calculator");
        setLayout(gbl);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);





    }
}
