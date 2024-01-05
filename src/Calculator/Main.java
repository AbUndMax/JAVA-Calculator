package Calculator;

import Calculator.Calculations.MathFunctions;
import Calculator.Windows.CalculatorFrame;

public class Main {
    public static void main(String[] args){

        MathFunctions.calculation.add("1");
        MathFunctions.calculation.add("×");
        MathFunctions.calculation.add("2");
        MathFunctions.calculation.add("×");
        MathFunctions.calculation.add("(");
        MathFunctions.calculation.add("2");
        MathFunctions.calculation.add("×");
        MathFunctions.calculation.add("(");
        MathFunctions.calculation.add("1");
        MathFunctions.calculation.add("+");
        MathFunctions.calculation.add("3");
        MathFunctions.calculation.add(")");
        MathFunctions.calculation.add(")");
        MathFunctions.calculation.add("+");
        MathFunctions.calculation.add("2");
        System.out.println(MathFunctions.calculation);
        System.out.println(MathFunctions.calculateCalculation());
        System.out.println(MathFunctions.calculation);
    }
}
