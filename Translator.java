import java.util.ArrayList;

// Translator is responsible for breaking down the expression into its components and using PEMDAS to decide the next sub-expression to evaluate

public class Translator {

    private ArrayList<String> _calculations;
    private String _currentExpression;


    public Translator(String userInput) {
        
        // Assign values to private fields

        this._calculations=new ArrayList<String>();
        this._currentExpression=userInput.replace("\\s", "");
        
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

    public Integer countOperators(String expression){
        int count = 0;
        for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) + "" == "+" || expression.charAt(i) + "" == "-" || expression.charAt(i) + "" == "*" || 
            expression.charAt(i) + "" == "/" || expression.charAt(i) + "" == "^"){
                count ++;
            }
        }

        return count;
    }

    public ArrayList<String> parse(String cur_expression){

        // parse will convert expression string into list of components
        while (true) {
            // Check for parentheses
            ArrayList<String> parenthesisResult = getParenthesis(cur_expression);
            if (parenthesisResult.get(0).equals("true")) {
                cur_expression = parenthesisResult.get(1);
            }

            // Check for exponentiation
            ArrayList<String> exponentResult = getExponent(cur_expression);
            if (exponentResult.get(0).equals("true")) {
                return exponentResult.get(1);
            }

            // Check for multiplication and division
            ArrayList<String> multiplicationResult = getMultiplication(cur_expression);
            ArrayList<String> divisionResult = getDivision(cur_expression);
            
            if (multiplicationResult.get(0).equals("true")) {
                cur_expression = multiplicationResult.get(1);
            } else if (divisionResult.get(0).equals("true")) {
                cur_expression = divisionResult.get(1);
            }

            // Check for addition and subtraction
            ArrayList<String> additionResult = getAddition(cur_expression);
            ArrayList<String> subtractionResult = getSubtraction(cur_expression);

            if (additionResult.get(0).equals("true")) {
                cur_expression = additionResult.get(1);
            } else if (subtractionResult.get(0).equals("true")) {
                cur_expression = subtractionResult.get(1);
            }

            // No more operations to perform
            if (!parenthesisResult.get(0).equals("true") &&
                !exponentResult.get(0).equals("true") &&
                !multiplicationResult.get(0).equals("true") &&
                !divisionResult.get(0).equals("true") &&
                !additionResult.get(0).equals("true") &&
                !subtractionResult.get(0).equals("true")) {
                break;
            }
        }

        // If no operations were found, return an empty string
        return "";
    
    }

    // PEMDAS Stuff

    public ArrayList<Object> checkParanthesis() {

        // checkParanthesis will check for paranthesis in the current expression and return an array with a boolean and string (result)
        // If there is a paranthesis, the result will return [True, <the portion of the expression containing the paranthesis>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!getCurrentExpression().contains("(")) {
            return result;
        }
        else {
            String chunk=getCurrentExpression();
            chunk=chunk.substring(chunk.lastIndexOf("("), chunk.indexOf(")")+1);
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }
        
    }

    public ArrayList<Object> checkExponents() {

        // checkExponents will check for exponents in the current expression and return an array with a boolean and string (result)
        // If there is an exponent, the result will return [True, <the portion of the expression containing the exponent>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();

        //  Check for exponents here

        return result;

    }

    public ArrayList<Object> checkMultiplication() {

        // checkMultiplication will check for multiplication in the current expression and return an array with a boolean and string (result)
        // If there is multiplication, the result will return [True, <the portion of the expression containing the multiplication>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();

        //  Check for multiplication here

        return result;

    }

    public ArrayList<Object> checkDivision() {

        // checkDivision will check for division in the current expression and return an array with a boolean and string (result)
        // If there is division, the result will return [True, <the portion of the expression containing the division>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();

        //  Check for division here

        return result;

    }

    public ArrayList<Object> checkAddition() {

        // checkAddition will check for addition in the current expression and return an array with a boolean and string (result)
        // If there is addition, the result will return [True, <the portion of the expression containing the addition>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();

        //  Check for addition here

        return result;

    }

    public ArrayList<Object> checkSubtraction() {

        // checkSubtraction will check for subtraction in the current expression and return an array with a boolean and string (result)
        // If there is subtraction, the result will return [True, <the portion of the expression containing the subtraction>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();

        //  Check for subtraction here

        return result;

    }

}