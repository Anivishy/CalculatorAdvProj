# CalculatorAdvProj
Advanced Software Projects 
Project #2 Design Doc

Authors: Akshai Srinivasan, Anikait Vishwanathan
Project Overview:
	The first elements considered in the design of this project are focused on the minimum requirements in order to have a functional calculator. In order to accomplish this the project was broken down into three major components.
1.	Engine:
  -	Core of the calculator
  -	Responsible for handling all calculations given input of an operation and operands.
2.	Translator: Responsible for accepting the user’s input and passing it into the engine to run calculations
  -	Parse: This method contains the main loop that parses through the user's input. Parse begins with a loop, iterating through the user’s input. It will then make calls to PEMDAS functions, checking the users input in mathematically correct order. After identifying a specific portion of PEMDAS, the code will then return the the expression of what needs to be calculated in an array list in the form of operator, operand1, operand2. 
  -	getParenthesis, getExponent, getMultiplication, getDivision, getAddition, getSubtraction: Returns a string expression for each of the specific operations. 
3.	Format: Responsible for processing the list of steps to perform the calculation and displaying the information to the console. Will also be responsible for displaying and rendering information to the GUI in future updates.
4.	Calculator: Core calculator file. Will contain the user input loop when run as a console application and will the initialization of the GUI when updated
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




