import java.util.ArrayList;

import javafx.util.Pair;

// Translator is responsible for breaking down the expression into its components and using PEMDAS to decide the next sub-expression to evaluate

public class Translator {

    private ArrayList<String> _calculations;
    private String _currentExpression;


    public Translator(String userInput) {
        
        // Assign values to private fields

        this._calculations=new ArrayList<String>();
        this._currentExpression=userInput;
        
    }

    // Getter methods to access private fields

    public ArrayList<String> getCalculations() {

        // Returns the current list of calculations to eventually be sent to formatter in main Calculator class

        return this._calculations;
    }

    public String getCurrentExpression() {

        // Gets current expression in order to access the private field from the outside Calculator object

        return this._currentExpression;
    }

    public ArrayList<String> parse(){

        // parse will convert expression string into list of components

        ArrayList<String> components= new ArrayList<String>();
        
        // Method stuff goes here
        

        return components;
    }

    // PEMDAS Stuff

    public Pair<Boolean, String> checkParanthesis() {

        // checkParanthesis will check for paranthesis in the current expression and return an array with a boolean and string (result)
        // If there is a paranthesis, the result will return [True, <the portion of the expression containing the paranthesis>]
        // If there isn't, the result will return [False, ""]

        Pair<Boolean, String> result = new Pair<>(false, "");

        //  Check for parenthesis here

        return result;

    }

    public Pair<Boolean, String> checkExponents() {

        // checkExponents will check for exponents in the current expression and return an array with a boolean and string (result)
        // If there is an exponent, the result will return [True, <the portion of the expression containing the exponent>]
        // If there isn't, the result will return [False, ""]

        Pair<Boolean, String> result = new Pair<>(false, "");

        //  Check for exponents here

        return result;

    }

    public Pair<Boolean, String> checkMultiplication() {

        // checkMultiplication will check for multiplication in the current expression and return an array with a boolean and string (result)
        // If there is multiplication, the result will return [True, <the portion of the expression containing the multiplication>]
        // If there isn't, the result will return [False, ""]

        Pair<Boolean, String> result = new Pair<>(false, "");

        //  Check for multiplication here

        return result;

    }

    public Pair<Boolean, String> checkDivision() {

        // checkDivision will check for division in the current expression and return an array with a boolean and string (result)
        // If there is division, the result will return [True, <the portion of the expression containing the division>]
        // If there isn't, the result will return [False, ""]

        Pair<Boolean, String> result = new Pair<>(false, "");

        //  Check for division here

        return result;

    }

    public Pair<Boolean, String> checkAddition() {

        // checkAddition will check for addition in the current expression and return an array with a boolean and string (result)
        // If there is addition, the result will return [True, <the portion of the expression containing the addition>]
        // If there isn't, the result will return [False, ""]

        Pair<Boolean, String> result = new Pair<>(false, "");

        //  Check for addition here

        return result;

    }

    public Pair<Boolean, String> checkSubtraction() {

        // checkSubtraction will check for subtraction in the current expression and return an array with a boolean and string (result)
        // If there is subtraction, the result will return [True, <the portion of the expression containing the subtraction>]
        // If there isn't, the result will return [False, ""]

        Pair<Boolean, String> result = new Pair<>(false, "");

        //  Check for subtraction here

        return result;

    }

}