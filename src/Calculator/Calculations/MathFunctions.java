package Calculator.Calculations;

import Calculator.Windows.CalculatorFrame;

import java.util.*;

public class MathFunctions {

    public static ArrayList<String> calculation = new ArrayList<>(Arrays.asList("0"));

    public static Double calculateCalculation(){
        return calculateArrayList(calculation);
    }

    // this function will calculate the value based on an ArrayList<String> where each value is a single String that
    // contains the calculation information in the form of {"1", "+", "1"}
    private static Double calculateArrayList(ArrayList<String> list){

        //System.out.println("\n#######################");
        //System.out.println("new list" + list);

        ArrayList<String> listCC = new ArrayList<>(list);

        ListIterator<String> parenthesisIterator = list.listIterator();

         //System.out.println("\n check for parenthesis ###");
        while(parenthesisIterator.hasNext()) {

            String currentVal = parenthesisIterator.next();
            //System.out.println("currentVal:" + currentVal);

            //If an open parenthesis is found the function calls itself again with the calculation inside the parenthesis.
            if (currentVal.equals("(")) {

                //System.out.println("parenthesis found");
                ListIterator<String> backwardIterator = list.listIterator(list.size());

                while (backwardIterator.hasPrevious()) {
                    if (backwardIterator.previous().equals(")")) {
                        //System.out.println("back parenthesis found");

                        ArrayList<String> operationsBetweenParenthesis = new ArrayList<String>(list.subList(parenthesisIterator.nextIndex(),
                                                                                                            backwardIterator.previousIndex() + 1));

                        // calculate value between the parenthesis
                        Double insertion = calculateArrayList(operationsBetweenParenthesis);

                        //System.out.println("back to first loop");
                        //System.out.println(backwardIterator.previousIndex());
                        //System.out.println(listCC.size());
                        //System.out.println(listCC.subList(parenthesisIterator.nextIndex()-1, backwardIterator.nextIndex()));

                        // delete parenthesis and all elements between them, add calculated value inside the parenthesis to the index of the opening parenthesis
                        listCC.subList(parenthesisIterator.nextIndex()-1, backwardIterator.previousIndex()+2).clear();

                        // add value between parenthesis to listCopy
                        listCC.add(parenthesisIterator.nextIndex()-1, String.valueOf(insertion));

                        return calculateArrayList(listCC);
                    }
                }
            }
        }

        ListIterator<String> multiDivIterator = list.listIterator();

        // System.out.println("\n check for * or / ###");
        while(multiDivIterator.hasNext()) {
            String currentVal = multiDivIterator.next();

            if (currentVal.equals("×")) {

                //System.out.println("current Index:" + (multiDivIterator.nextIndex()-2));
                Double number1 = Double.parseDouble(listCC.remove(multiDivIterator.nextIndex()-2));
                listCC.remove(multiDivIterator.nextIndex()-2);
                Double number2 = Double.parseDouble(listCC.remove(multiDivIterator.nextIndex()-2));

                listCC.add(multiDivIterator.nextIndex()-2, String.valueOf(number1 * number2));
                return calculateArrayList(listCC);
            }

            else if (currentVal.equals("÷")) {
                Double number1 = Double.parseDouble(listCC.remove(multiDivIterator.nextIndex()-2));
                listCC.remove(multiDivIterator.nextIndex()-2);
                Double number2 = Double.parseDouble(listCC.remove(multiDivIterator.nextIndex()-2));

                listCC.add(multiDivIterator.nextIndex()-2, String.valueOf(number1 / number2));
                return calculateArrayList(listCC);
            }
        }

        ListIterator<String> addSubIterator = list.listIterator();

        //System.out.println("\n check for + or - ###");
        while(addSubIterator.hasNext()) {
            String currentVal = addSubIterator.next();

            if (currentVal.equals("+")) {
                Double number1 = Double.parseDouble(listCC.remove(addSubIterator.nextIndex()-2));
                listCC.remove(addSubIterator.nextIndex()-2);
                Double number2 = Double.parseDouble(listCC.remove(addSubIterator.nextIndex()-2));

                listCC.add(addSubIterator.nextIndex()-2, String.valueOf(number1 + number2));
                return calculateArrayList(listCC);
            }

            else if (currentVal.equals("-")) {
                Double number1 = Double.parseDouble(listCC.remove(addSubIterator.nextIndex()-2));
                listCC.remove(addSubIterator.nextIndex()-2);
                Double number2 = Double.parseDouble(listCC.remove(addSubIterator.nextIndex()-2));

                listCC.add(addSubIterator.nextIndex()-2, String.valueOf(number1 - number2));
                return calculateArrayList(listCC);
            }
        }

        return Double.parseDouble(listCC.getFirst());
    }

}