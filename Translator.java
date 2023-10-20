import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        //expression.charAt(1);
        for (int i = 0; i < expression.length(); i++){
            if ((expression.charAt(i) + "").equals("+") || (expression.charAt(i) + "").equals("/") || 
            (expression.charAt(i) + "").equals("*") || (expression.charAt(i) + "").equals("^")){
                count ++;
            }
        }

        return count;
    }

    public ArrayList<String> parse(String cur_expression){

        // parse will convert expression string into list of components
        while (true) {
            // Check for parentheses
            ArrayList<String> parenthesisResult = checkParenthesis(cur_expression);
            Boolean hasPara = false;
            //System.out.print(parenthesisResult);
            if (parenthesisResult.get(0).equals("true")) {
                cur_expression = (String) parenthesisResult.get(1);
                if (countOperators(cur_expression) > 1)
                    hasPara = false;
                else{
                    hasPara = true;
                }                
            }

            //System.out.println(hasPara);

            // Check for exponentiation
            //ArrayList<String> exponentResult = checkExponents(cur_expression);
            ArrayList<String> exponentResult = checkEMDAS(cur_expression, "^");
            if (exponentResult.get(0).equals("true")) {
                String resultString = (String) exponentResult.get(1);
                int operator_index = resultString.indexOf("^");
                System.out.print(resultString);
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                result.add(operator);
                result.add(num1);
                result.add(num2);
                if (hasPara){
                    resultString = "(" + resultString + ")";
                    result.add(resultString);
                }
                else{
                    result.add(resultString);
                }
                return result;
            }

            // Check for multiplication and division
            //ArrayList<String> multiplicationResult = checkMultiplication(cur_expression);
            //ArrayList<String> divisionResult = checkDivision(cur_expression);

            ArrayList<String> multiplicationResult = checkEMDAS(cur_expression, "*");
            ArrayList<String> divisionResult = checkEMDAS(cur_expression, "/");
            
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
                if (hasPara){
                    resultString = "(" + resultString + ")";
                    result.add(resultString);
                }
                else{
                    result.add(resultString);
                }
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
                if (hasPara){
                    resultString = "(" + resultString + ")";
                    result.add(resultString);
                }
                else{
                    result.add(resultString);
                }
                return result;
            }

            // Check for addition and subtraction
            //ArrayList<String> additionResult = checkAddition(cur_expression);
            //ArrayList<String> subtractionResult = checkSubtraction(cur_expression);

            ArrayList<String> additionResult = checkEMDAS(cur_expression, "+");
            ArrayList<String> subtractionResult = checkEMDAS(cur_expression, "-");

            if (additionResult.get(0).equals("true")) {
                String resultString = (String) additionResult.get(1);
                int operator_index = resultString.indexOf("+");
                ArrayList<String> result = new ArrayList<String>();
                String num1 = resultString.substring(0, operator_index);
                String num2 = resultString.substring(operator_index + 1, resultString.length());
                String operator = resultString.charAt(operator_index) + "";
                //System.out.println("Num1" + num1 + " num2" + num2 + " operator" + operator);
                result.add(operator);
                result.add(num1);
                result.add(num2);
                if (hasPara){
                    resultString = "(" + resultString + ")";
                    result.add(resultString);
                }
                else{
                    result.add(resultString);
                }
                return result;
            } 
          

            else if (hasPara){
                System.out.println("TEST");
                ArrayList<String> result = new ArrayList<String>();
                result.add("+");
                result.add((String) parenthesisResult.get(1));
                result.add("0");
                result.add("(" + (String) parenthesisResult.get(1) + ")");
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

    public ArrayList<String> checkParenthesis(String currentExpresion) {

        // checkParanthesis will check for paranthesis in the current expression and return an array with a boolean and string (result)
        // If there is a paranthesis, the result will return [True, <the portion of the expression containing the paranthesis>]
        // If there isn't, the result will return [False, ""]

        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpresion.contains("(")) {
            return result;
        }

        else {
            String chunk=currentExpresion;
            String ending=chunk.substring(chunk.lastIndexOf("(")+1);
            chunk=chunk.substring(chunk.lastIndexOf("(")+1, ending.indexOf(")")+chunk.lastIndexOf("(")+1);
            // if (!(chunk.contains("+")) && !(chunk.contains("/")) && !(chunk.contains("*")) && !(chunk.contains("("))){
            //     chunk += "+ 0";
            // }
            result.set(0, "true");
            result.set(1,chunk);
            System.out.println("Chunk: " + chunk);
            return result;
        }
        
    }

    public ArrayList<String> checkEMDAS(String currentExpression, String key) {
        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpression.contains(key)) {
            return result;
        }
        else {
            String chunk=currentExpression;            
            String[] operations={"+", "*", "/", "^", ")", "("};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf(key)-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i)+"")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf(key)+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf(key)+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();
            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, "true");
            result.set(1,chunk);
            return result;
        }
    }

    /** 
    public ArrayList<String> checkExponents(String currentExpression) {

        // checkExponents will check for exponents in the current expression and return an array with a boolean and string (result)
        // If there is an exponent, the result will return [True, <the portion of the expression containing the exponent>]
        // If there isn't, the result will return [False, ""]

        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpression.contains("^")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "*", "/", "^", "(", ")"};
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
            result.set(0, "true");
            result.set(1,chunk);
            return result;
        }

    }

    public ArrayList<String> checkMultiplication(String currentExpression) {

        // checkMultiplication will check for multiplication in the current expression and return an array with a boolean and string (result)
        // If there is multiplication, the result will return [True, <the portion of the expression containing the multiplication>]
        // If there isn't, the result will return [False, ""]

        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpression.contains("*")) {
            return result;
        }
        else {
            String chunk=currentExpression;            
            String[] operations={"+", "*", "/", "^", ")", "("};
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
            result.set(0, "true");
            result.set(1,chunk);
            return result;
        }
    }

    public ArrayList<String> checkDivision(String currentExpression) {

        // checkDivision will check for division in the current expression and return an array with a boolean and string (result)
        // If there is division, the result will return [True, <the portion of the expression containing the division>]
        // If there isn't, the result will return [False, ""]

        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpression.contains("/")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "*", "/", "^", ")", "("};
            List operationsList = Arrays.asList(operations);
            int frontIndex=0;
            int backIndex=0;

            for (int i=chunk.indexOf("/")-1; i>=0; i--) {
                if (operationsList.contains(chunk.charAt(i)+"")) {
                    frontIndex=i+1;
                    break;
                }
            }

            for (String i:operations) {
                if (chunk.substring(chunk.indexOf("/")+1).contains(i)) {
                    backIndex=chunk.indexOf(i, chunk.indexOf("/")+1);
                    break;
                }
            }
            if (backIndex==0) {
                backIndex=chunk.length();
            }
            
            chunk=chunk.substring(frontIndex, backIndex);
            result.set(0, "true");
            result.set(1,chunk);
            return result;
        }

    }

    public ArrayList<String> checkAddition(String currentExpression) {

        // checkAddition will check for addition in the current expression and   return an array with a boolean and string (result)
        // If there is addition, the result will return [True, <the portion of the expression containing the addition>]
        // If there isn't, the result will return [False, ""]

        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpression.contains("+")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "*", "/", "^", ")", "("};
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
            result.set(0, "true");
            result.set(1,chunk);
            return result;
        }

    }

    public ArrayList<String> checkSubtraction(String currentExpression) {

        // checkSubtraction will check for subtraction in the current expression and return an array with a boolean and string (result)
        // If there is subtraction, the result will return [True, <the portion of the expression containing the subtraction>]
        // If there isn't, the result will return [False, ""]

        ArrayList<String> result = new ArrayList<String>();
        result.add("false");
        result.add("");

        if (!currentExpression.contains("-")) {
            return result;
        }
        else {
            String chunk=currentExpression;
            
            String[] operations={"+", "-", "*", "/", "^", ")", "("};
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
            result.set(0, "true");
            result.set(1,chunk);
            return result;
        }
        

    }
    **/

}