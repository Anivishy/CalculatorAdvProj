import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Integer;

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
            ArrayList<Object> parenthesisResult = checkParenthesis(cur_expression);
            if (parenthesisResult.get(0).equals("true")) {
                cur_expression = (String) parenthesisResult.get(1);
            }

            // Check for exponentiation
            ArrayList<Object> exponentResult = checkExponents(cur_expression);
            if (exponentResult.get(0).equals("true")) {
                String resultString = (String) exponentResult.get(1);
                int operator_index = resultString.indexOf("^");
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                result.add(operator);
                result.add(num1);
                result.add(num2);
                result.add(resultString);
                return result;
            }

            // Check for multiplication and division
            ArrayList<Object> multiplicationResult = checkMultiplication(cur_expression);
            ArrayList<Object> divisionResult = checkDivision(cur_expression);
            
            if (multiplicationResult.get(0).equals("true")) {
                String resultString = (String) multiplicationResult.get(1);
                int operator_index = resultString.indexOf("*");
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                result.add(operator);
                result.add(num1);
                result.add(num2);
                result.add(resultString);
                return result;
            } else if (divisionResult.get(0).equals("true")) {
                String resultString = (String) divisionResult.get(1);
                int operator_index = resultString.indexOf("/");
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                result.add(operator);
                result.add(num1);
                result.add(num2);
                result.add(resultString);
                return result;
            }

            // Check for addition and subtraction
            ArrayList<Object> additionResult = checkAddition(cur_expression);
            ArrayList<Object> subtractionResult = checkSubtraction(cur_expression);

            if (additionResult.get(0).equals("true")) {
                String resultString = (String) additionResult.get(1);
                int operator_index = resultString.indexOf("");
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                result.add(operator);
                result.add(num1);
                result.add(num2);
                result.add(resultString);
                return result;
            } else if (subtractionResult.get(0).equals("true")) {
                String resultString = (String) subtractionResult.get(1);
                int operator_index = resultString.indexOf("");
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                result.add(operator);
                result.add(num1);
                result.add(num2);
                result.add(resultString);
                return result;
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
        ArrayList<String> blank = new ArrayList <String>();
        blank.add("");
        blank.add("");
        blank.add("");
        blank.add("");
        return blank;
    
    }

    // PEMDAS Stuff

    public ArrayList<Object> checkParenthesis(String currentExpresion) {

        // checkParanthesis will check for paranthesis in the current expression and return an array with a boolean and string (result)
        // If there is a paranthesis, the result will return [True, <the portion of the expression containing the paranthesis>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!currentExpresion.contains("(")) {
            return result;
        }
        else {
            String chunk=currentExpresion;
            chunk=chunk.substring(chunk.lastIndexOf("(")+1, chunk.indexOf(")"));
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }
        
    }

    public ArrayList<Object> checkExponents(String currentExpression) {

        // checkExponents will check for exponents in the current expression and return an array with a boolean and string (result)
        // If there is an exponent, the result will return [True, <the portion of the expression containing the exponent>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!currentExpression.contains("^")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "-", "*", "/", "^", ")"};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf("^")-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i) + "")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf("^")+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf("^")+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();
            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }

    }

    public ArrayList<Object> checkMultiplication(String currentExpression) {

        // checkMultiplication will check for multiplication in the current expression and return an array with a boolean and string (result)
        // If there is multiplication, the result will return [True, <the portion of the expression containing the multiplication>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!currentExpression.contains("*")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "-", "*", "/", "^", ")"};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf("*")-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i)+"")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf("*")+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf("*")+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();
            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }
    }

    public ArrayList<Object> checkDivision(String currentExpression) {

        // checkDivision will check for division in the current expression and return an array with a boolean and string (result)
        // If there is division, the result will return [True, <the portion of the expression containing the division>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!currentExpression.contains("/")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "-", "*", "/", "^", ")"};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf("*")-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i)+"")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf("*")+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf("*")+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();
            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }

    }

    public ArrayList<Object> checkAddition(String currentExpression) {

        // checkAddition will check for addition in the current expression and   return an array with a boolean and string (result)
        // If there is addition, the result will return [True, <the portion of the expression containing the addition>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!currentExpression.contains("+")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "-", "*", "/", "^", ")"};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf("+")-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i)+"")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf("+")+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf("+")+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();

            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }

    }

    public ArrayList<Object> checkSubtraction(String currentExpression) {

        // checkSubtraction will check for subtraction in the current expression and return an array with a boolean and string (result)
        // If there is subtraction, the result will return [True, <the portion of the expression containing the subtraction>]
        // If there isn't, the result will return [False, ""]

        ArrayList<Object> result = new ArrayList<Object>();
        result.add(false);
        result.add("");

        if (!currentExpression.contains("-")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "-", "*", "/", "^", ")"};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf("-")-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i)+"")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf("-")+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf("-")+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();
            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, true);
            result.set(1,chunk);
            return result;
        }

    }

}