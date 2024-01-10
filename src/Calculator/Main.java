package Calculator;

import Calculator.Windows.CalculatorFrame;


// Main class with main function -> just calls CalculatorFrame and set it to visible

public class Main {

    public static void main(String[] args){

        CalculatorFrame cf = new CalculatorFrame();
        cf.setVisible(true);

    }
}
