# CalculatorAdvProj
Advanced Software Projects 
Project #2 Design Doc

Authors: Akshai Srinivasan, Anikait Vishwanathan

_**Updated Documentation**_

The core functionality of this calculator is for use as a scientific calculator while braking down steps for simplyfying input expressions. 

**Key Features**

1: PEMDAS | This calculator follows PEMDAS and will simplify expressions in the correct order. It also interprets impluied multiplication and universal constants such as pi and e. 

- In additioan to solving expresisons using PEMDAS, the calculator is also able to simplify expressions and break down the steps for the user as it goes through each function. The steps are displayed in a seperate column so that the user is able to follow along

2: Graphing | The second primary feature of this calculator is graphing, where the user can enter in an expression in terms of x to be plotted on a graph. 
- Ex: x ^ 2 + 1 or 2x - 5
- The calculator will then plot the graph of the expression, utalizing thousands of calculated points from the provided expression

3: Extended Mode | In addition to the normal functions provided on the home screen of the calculator, the extended button provides the user with additional options including...
- Absolute Value
- Square Root
- Random Number

4: Stat Button | Enables the user to enter a list of doubles, seperated by commas
- Calculates the min, max, range, sum, average, IQR, Q1, Q2, and median of the input set of data.

5: Settings/Customization | Allows the user to selecte between 4 different color themes, two in dark mode and two in light mode. 

Project Overview:
	The first elements considered in the design of this project are focused on the minimum requirements in order to have a functional calculator. In order to accomplish this the project was broken down into three major components.
1.	Engine:
  -	Core of the calculator
  -	Responsible for handling all calculations given input of an operation and operands.
    
2.	Translator: Responsible for accepting the user’s input and passing it into the engine to run calculations
  -	Parse: This method contains the main loop that parses through the user's input. Parse begins with a loop, iterating through the user’s input. It will then make calls to PEMDAS functions, checking the users input in mathematically correct order. After identifying a specific portion of PEMDAS, the code will then return the the expression of what needs to be calculated in an array list in the form of operator, operand1, operand2. 
  -	getParenthesis, getExponent, getMultiplication, getDivision, getAddition, getSubtraction: Returns a string expression for each of the specific operations.
    
3.	Format: Responsible for processing the list of steps to perform the calculation and displaying the information to the console. Will also be responsible for displaying and rendering information to the GUI in future updates.
- This file was later remmoved, as its functionality was implemented within the GUI file itself.

5.	Calculator: Core calculator file. Will contain the user input loop when run as a console application and will the initialization of the GUI when updated

7.	GUI: This file was used for implementing all of the buttons for the GUI and their functionality. This file feeds all of the GUI information to the calculator class as a string to be further parsed

9.	Config: Used for global constants and allowing the use of the "ANS" key.
   
Special Features:

1: Display steps for calculations
  -	Will be implemented along with the core calculator in our initial development.
    
2: GUI Implementation
  -	Calculator will first be developed to be run in the terminal but will then be updated to run in a GUI using the Swing java library.
  -	Overall goal to think through the user experience to make the application as easy to use as possible while ensuring an elegant design.
    
3: Graphing Calculator
  -	Works with the GUI to display graphs
o	Potential to implement graph analysis features such as finding intercepts, tracing values along the graph, and calculus-based calculations such as integrals and derivatives.



Citations:
- https://www.geeksforgeeks.org/
- https://www.baeldung.com/
- https://stackoverflow.com/
- https://docs.oracle.com/javase/8/docs/technotes/guides/swing/index.html




