package Calculator.Handlers;

import Calculator.Windows.CalculatorFrame;

import static Calculator.Calculations.MathFunctions.calculation;
import static Calculator.Handlers.CallHandler.replaceLast;
import static Calculator.Handlers.CallHandler.isLastEntryNumber;

public class AdditionalHandler {


    public static void additionalHandler(String eventString) {
        
        String lastEntry = calculation.getLast();

        switch(eventString){
            // clear calculation
            case "C":
                calculation.clear();
                calculation.add("0");
                break;

            // changes the last number to negative if positive and to positive if negative
            case "±":
                if (isLastEntryNumber()) {
                    if (Double.parseDouble(lastEntry) > 0)
                        replaceLast("-" + lastEntry);

                    else if (Double.parseDouble(lastEntry) < 0)
                        replaceLast(lastEntry.substring(1));
                }
                break;

            case "%":
                // simply adds a 0.01 multiplication
                if (isLastEntryNumber()) {
                    replaceLast(String.valueOf(Double.parseDouble(lastEntry) * 0.01));
                }
                break;
        }

        // reprint calculation on display
        CalculatorFrame.displayCalculation();
    }

}
