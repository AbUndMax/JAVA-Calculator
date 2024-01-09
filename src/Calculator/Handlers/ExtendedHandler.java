package Calculator.Handlers;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtendedHandler {

    public static void extendedHandler(String command) {

        switch (command) {
            case "√":
                MathFunctions.calculation.add("√");

        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
