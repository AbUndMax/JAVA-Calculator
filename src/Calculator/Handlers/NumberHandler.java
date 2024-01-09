package Calculator.Handlers;

import Calculator.Windows.CalculatorFrame;
import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.AdditionalHandler.isLastEntryNumber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberHandler {

    public static void numberHandler(String command) {
        // if there is already a number, put booth number together -> "2" with new input: "3" -> "23"

        // if the first number is a zero, we don't need this zero i.e. 023 = 23
        if (calculation.getLast().charAt(0) == '0'){
            calculation.set(calculation.size() - 1, command);
        }

        // if there is already a number, and the input is another number, both numbers gets concatenated
        else if (isLastEntryNumber()) {
            calculation.set(calculation.size() - 1, calculation.getLast() + command);
        }

        // if the last entry has an . the next number gets appended
        else if (calculation.getLast().contains(".")) {
            calculation.set(calculation.size() - 1, calculation.getLast() + command);
        }

        // otherwise the number gets just added to the calculation list
        else calculation.add(command);

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }
}
