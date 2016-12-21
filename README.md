# Graphing-Calculator

The Graphing Functions Program

Created by Robert Fox

Introduction

This program is designed to graph and find solutions to many different mathematical systems including linear, quadratic, cubic, exponential, power, rational, and trigonometric (sine, cosine, tangent, cosecant, secant, and cotangent) functions as well as their inverses.

The files in the “Graphing” folder include Graph.java, GraphDriver.java, (written and designed by Robert Fox), and MathEval.java, a class file created by Lawrence PC Dol. and Carlos Gomez put out for public use.

Basic Layout & Functionality

The public Graph() is the content displayed when the driver file is run, and contains the coordinate plane in which the points are to be plotted, as well as tools that will graph the entered equation, find a certain y-value for the function, display the function’s inverse, and clear the plane. The visible JFrame that appears when the driver is run contains several GUI components including the JPanels holding the CLEAR, SHOW INVERSE, GRAPH, and FIND JButtons. 

When the GRAPH button is clicked, it stores the entered value in the text field above it and uses a loop to store values x-values from -10 to 10 in an array that will then displayed in the coordinate plane. Thanks to the MathEval class, the basic mathematical functions used to calculate the values to be displayed are known and can easily and accurately return the solution to a wide range of expression. The FIND button stores the entered value from the text field above it and simply plugs this value in for x in the entered expression the same way the GRAPH button does and is able to return its solution and even pinpoint the point if it is within the bounds of the frame. The CLEAR button simply paints over the current equations displayed, clears the array storing the values of the previously plotted function, and resets both text fields and the JLabel displaying the found y-value. The SHOW INVERSE button switches the x and y values of the function stored in the arrays and displays these points reflected of the line y=x in a different color.

Java Files

MathEval.java

This class file was created by Lawrence PC Dol. with assistance from Carlos Gomez, and is put up for public use at the page http://www.softwaremonkey.org/Code/MathEval. The MathEval object has several features (taken directly from the link above):

  •	Basic math operators, with inferred precedence (^ * × • / ÷ % + -).

  •	Explicit precedence with parenthesis.

  •	Implicit multiplication of bracketed subexpressions.

  •	Correct right-associativity of exponentials (power operator).

  •	Direct support for hexadecimal numbers prefixed by 0x.

  •	Constants and variables.

  •	Extensible operators.

  •	Extensible functions.

  •	Tiny 20 KiB footprint.

The methods that were used in Graph.java that are found the MathEval class are setVariable and evaluate. The MathEval setVariable(String nam, double val) method, which is used when the GRAPH and FIND buttons are clicked, accepts a String object (the variable name that will now be known) and stores the accepted double value under the given variable name. In addition, a similar MathEval setConstant(String nam, double val) method does a related task, and is used at the beginning of MathEval.java to set final values including the variables E (2.718…) and PI (3.141…), as well as other mathematical constants. The double evaluate(String exp) method is what performs the brute force that calculates the simplified expression, using knowledge of operators, exponents (denoted by ‘^’), order of operations, and other essential mathematical devices that is implemented throughout multiple methods in MathEval.java. This method is also called when the GRAPH and FIND buttons are clicked and directly adds the returned double values to the arrays holding the x and y-values.

Graph.java

This file creates the Graph() object and uses the Graphics class to display the functions on the frame. The javax.swing.*, java.awt.*, and java.awt.event.* packages are imported in order to use the selected GUI components and the corresponding ActionListeners of the JButtons. 

The void paintComponent(Graphics g) holds all the various lines and points that are to be displayed. The x and y-axes are centered with the origin at (300,250) and extend out 200 pixels in each direction. A for-loop is used to fill circles indicating every integral point from -10 to 10 on both axes. The Graphics component also draws circles at each integral point of the function drawn from the arrays holding values after the GRAPH button is clicked (arrays start empty so nothing appears on graph before an expression is entered), and actually connects every point on 0.5 step to make the function as accurate as possible without increasing runtime filling the arrays with more values. When the boolean showInv and showPoint are true (both initialized to be false), the inverse graph and pinpointed ordered pair that is found will respectively appear on the frame. 

Within the Graph(), all the JPanels and subpanels are instantiated to hold the various GUI components. Each of the subpanels found in toolPanel (BorderLayout.SOUTH in JPanel panel) hold their own individual components in order to keep an appealing format using BoxLayout. 

The leftmost subpanel in toolPanel holds the CLEAR and SHOW INVERSE buttons. The CLEAR button, when clicked, uses the ActionListener ClearListener, which will empty the arrays holding the x and y-values thus removing the graphed function from the coordinate plane, clear the text fields and JLabel printY, set both showInv and showPoint to be false, and call the repaint() method to make all of this occur. The SHOW INVERSE button will toggle the boolean value showInv between true and false, and by calling the repaint() method will display the inverse graph back in the paint component. When showInv is true, the x and y arrays will switch, and the new points will graphed and formatted accordingly to reflect across the line y=x. 

The center subpanel contains the text field in which the expression is to be entered and the GRAPH button. The GRAPH button uses the ActionListener GraphListener() . The GraphListener first sets showInv and showPoint to be false, removing any previous graphics on the graph. Using a for-loop, the index of the character ‘x’ is found, and a following series of if-statements will place a multiplication symbol (‘*’) if it needs it (the MathEval class does not recognize, for example, the expression 2x to be the same as 2*x, so a ‘*’ is inserted accordingly). This modified expression becomes the new String value that is evaluated using the evaluate method in MathEval. From there, a for-loop running from -10 to 10 with a 0.5 step inserts this value as x into the expression, stores this value into the x-value array, and stores the evaluated result into the y-value array at the corresponding index. The actual value is needed to be modified in order to later be displayed at the correct pixel coordinates on the frame (the paint component directly draws circles at the array values), for example, value (0,0) is needed to actually be stored as (300,250) in order to appear at the corresponding pixel value. Back in  the paint component, one for-loop runs through every other value in each array, so each integer value on the graph is displayed with a circle, and another for-loop traverses the entirety of both arrays connecting lines from each point. 

The FIND button in the right subpanel uses the ActionListener FindListener(). FindListener() plugs in the double value entered in the above text field into the expression of the function, and similar to GraphListener, will find the corresponding y-value using the evaluate method and display the value rounded to five decimal places below the button. In addition, if both the x and y-value of this found point are within the bounds of the frame, it will pinpoint this ordered pair and surround it with a box. By setting showPoint to true and calling repaint(), this box will appear.

GraphDriver.java

GraphDriver.java contains the main method, and the JFrame frame will display the components in Graph() using setContentPane(new Graph()). The size is set at 600x700 and is not resizable in order to preserve the layout and graphics. 

Encountered Bugs

  •	When graphing the trigonometric functions tan(x), csc(x) (expressed as 1/sin(x)), sec(x) (expressed as 1/cos(x)), and cot(x) (expressed as 1/tan(x)); rational functions (i.e. 1/x), or any functions that contain vertical asymptotes, it is difficult to display the accuracy of the asymptotes. The values for each pixel (every 1/10 on the graph), can be found in order to define the lines more accurately as they approach their asymptote, but because the step for holding each x and y-value is stored, it really connects point to point across five pixels. When one of these graphs is displayed, a line runs across each asymptote because it is connected to the points directly before and after the asymptote.

  •	 When entering points into the text field above the FIND button, it can only parse double values. For example, the value 9/2 would need to be given as 4.5 in order for it to read it correctly, or the value 3*PI would need to be given as the exact double value of this expression to be read correctly.

  •	In graphs where the lines curve, it is difficult to preserve the accuracy of each spot on the line, especially from point to point. Because lines are only connected using a 0.5 step, any value within 0.5 of a correctly shown point could be inaccurate, especially if there is a drastic change between two points. To illustrate this, the function x^2 connects the points (0,0) and (0.5, 0.25) and is able to find each x-value between 0 and 0.5, but a line is drawn from 0 to 0.5, which may actually show a false point for the value 0.1, for example. The FIND button, however, will still be able to display and pinpoint the correct value 0.1 if it is entered even if where it falls on the line is slightly off.
