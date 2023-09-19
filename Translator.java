import java.util.ArrayList;

public class Translator {

    private ArrayList<String> calculations;
    private String currentExpression;


    public Translator(String userInput) {
        
        // Assign values to private fields

        this.calculations=new ArrayList<String>();
        this.currentExpression=userInput;
        
    }

    // Initializing getter methods for future debugging

    public ArrayList<String> getCalculations() {

        // Returns the current list of calculations to eventually be sent to formatter in main Calculator class

        return this.calculations;
    }

    public String getCurrentExpression() {

        // Gets current expression in order to access the private field from the outside Calculator object

        return this.currentExpression;
    }

    public ArrayList<String> parser(){

        // Parser will convert expression string into list of components

        ArrayList<String> components= new ArrayList<String>();
        
        // Method stuff goes here

        return components;
    }

    // PEMDAS Stuff

    public Object[] checkParanthesis() {

        // checkParanthesis will check for paranthesis in the current expression and return an array with a boolean and string (result)
        // If there is a paranthesis, the result will return [True, <the portion of the expression containing the paranthesis>]
        // If there isn't, the result will return [False, ""]

        Object[] result = new Object[2];

        //  Check for parenthesis here

        return result;

    }

    public Object[] checkExponents() {

        // checkExponents will check for exponents in the current expression and return an array with a boolean and string (result)
        // If there is an exponent, the result will return [True, <the portion of the expression containing the exponent>]
        // If there isn't, the result will return [False, ""]

        Object[] result = new Object[2];

        //  Check for exponents here

        return result;

    }

    public Object[] checkMultiplication() {

        // checkMultiplication will check for multiplication in the current expression and return an array with a boolean and string (result)
        // If there is multiplication, the result will return [True, <the portion of the expression containing the multiplication>]
        // If there isn't, the result will return [False, ""]

        Object[] result = new Object[2];

        //  Check for multiplication here

        return result;

    }

    public Object[] checkDivision() {

        // checkDivision will check for division in the current expression and return an array with a boolean and string (result)
        // If there is division, the result will return [True, <the portion of the expression containing the division>]
        // If there isn't, the result will return [False, ""]

        Object[] result = new Object[2];

        //  Check for division here

        return result;

    }

    public Object[] checkAddition() {

        // checkAddition will check for addition in the current expression and return an array with a boolean and string (result)
        // If there is addition, the result will return [True, <the portion of the expression containing the addition>]
        // If there isn't, the result will return [False, ""]

        Object[] result = new Object[2];

        //  Check for addition here

        return result;

    }

    public Object[] checkSubtraction() {

        // checkSubtraction will check for subtraction in the current expression and return an array with a boolean and string (result)
        // If there is subtraction, the result will return [True, <the portion of the expression containing the subtraction>]
        // If there isn't, the result will return [False, ""]

        Object[] result = new Object[2];

        //  Check for subtraction here

        return result;

    }

}