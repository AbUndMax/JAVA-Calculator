package Calculator.Handlers;

import Calculator.Windows.CalculatorFrame;
import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.AdditionalHandler.isLastEntryNumber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicOperatorHandler {

    public static void basicOperatorHandler(String command) {

        // if the last entry in calculation is a number, the operator is inserted into the calculation
        // else, the last operator will be overwritten by the new one.
        if (isLastEntryNumber()) {
            calculation.add(command);
        }

        else {
            calculation.set(calculation.size() - 1, command);
        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }

}
