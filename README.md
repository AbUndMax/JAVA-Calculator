# Project Documentation

## Calculation

The Idea of this calculator is as follows:

We have an ArrayList<String> which is called calculation and found in
`Calculator.Calculations.MathFunctions#calculation`
This ArrayList stores each Number as a String i.e. "1" "3784".
It also stores each operation as a String i.e. "+", "(", "^" etc...

This will result in an Array List like this: (Example!)  
```["12.45", "+", "35", "*", "(", "2", "-", "5", ")", "+", "6"]```

which translates to:
12.45+35*(2-5)+6

the big Function
`Calculator.Calculations.MathFunctions#calculateArrayList`
inside this class provides the Programm for Calculating with these Strings
It is capable of calculating with basic operators +, -, *, /
and extended operations like (), root, exponents

**the logic of this function is as follows:**  
It basically searches the calculation ArrayList for the operators
in order of their priority (root > parenthesis > exponents > multiplication & division > addition & substraction)

- **square-root:**
for the root it will search from the left starting of calculation for a root symbol
if it found one, it will proceed like with parenthesis but the returning value gets rooted (square root)
 

- **parenthesis:**
th function searches from the left for an opening "(" bracket. if it found one, it searches with a new iterator for
an closing ")" bracket. It then will take EVERYTHING inside those booth brackets to a substring (which will basically
end up as the calculation for the most outer brackets of the actual calculation) and call the calculateArrayList
function recursive.


- **for all the other operations it is straight forward:**
The Algorithm searches for an operator i.e. ^ * / + -
if it found one. It will take the String to the left and to the right of this operator, transfer them to an Double and
use the corresponding math operator on these numbers. all three string "number1" "operator" "number2" are getting deleted
in the calculation ArrayList and therefore on the same position replaced with the result of the operation.

One trick was:
I couldn't change the ArrayList as long as an Iterator was activated on it. These means I had to copy the calculation to a
new ArrayList I called listCC (Carbon Copy). So the Iterator moves on the list the method received and the actual operation,
insertion, etc. is done on the listCC. Everytime listCC got changed, the new calculateArrayList is called on listCC.
This leads to a recursive calculation:

call calculateArrayList() on our example:    
```["12.45", "+", "35", "*", "(", "2", "-", "5", ")", "+", "6"]```  
first (normal) call:  
```["12.45", "+", "35", "*", "(", "2", "-", "5", ")", "+", "6"]```  
first recursive call: (value inside parentheses)  
```["2", "-", "5"]```  
second recursive call:  
```["12.45", "+", "35", "*", "-3" "+", "6"]```  
third:  
```["12.45", "+", "-105" "+", "6"]```  
fourth:  
```["-92.55" "+", "6"]```  
last call:  
```["-86.55"]```

Each operation which is needed to correctly insert the input value from the buttons (or keys) is handled in the handlers.
the logic is described there.

## GUI

For the Calculaot GUI I chosed the GridBagLayout to have better control on where to place the buttons.
The display is put on a ScrollPane so if the calculation would get to long, one is still able to scrolll through the calculation.

Display and delete button are booth placed on a JPanel with BorderLayout so I can resize the Button to a fixed value.

The extended function buttons i.e. √, ^, ( and ) are placed on a JPanel which is activatet or deactivated based on the
radio buttons

for the other buttons I used loops for easally add them onto the frame and still fulfill "Don't repeat yourself"

## Listeners & Handlers

For the Listeners I chosed a way to channel both inputs from key and buttons to the same handlers.
Therefore, I didn't had to copy all the code from the button handlers to the key handlers.

This is done by a "connector" class called
`Calculator.Handlers.CallHandler`
which will receive the input from the listeners and reroute them to the corresponding handler:

```
                                            |---> NumberHandler (number input)
                                            |
                                            |---> BasidOperatorHandler (+ - * / operator input)
ButtonListenner ---|                        |
                   |------> CallHandler ----|---> SpecialHandler (special functions like . or = or del)
KeyListener -------|                        |
                                            |---> AdditionalHandler (basic additional functions like ±, % or Clear)
                                            |   
                                            |---> ExtendedHandler (functions which fulfill the "extend your calc." task 1e
```
