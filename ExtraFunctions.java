import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtraFunctions {

    //Updating universal constants

    public String replacePi(String expression){
        expression = expression.replaceAll("π", Double.toString(Math.PI));
        expression = expression.replaceAll("pi", Double.toString(Math.PI));
        return expression;
    }

    public String replaceE(String expression){
        expression = expression.replaceAll("e", Double.toString(Math.exp(1.0)));
        return expression;
    }

    //Implied Multiplication & Negatives

    public String impliedMulti(String expression){
        String updatedString = "";
        for (int i = 1; i < expression.length();  i ++){
            if (expression.charAt(i) == '('){
                String prev_char = expression.charAt(i - 1) + "";
                if (!(prev_char.equals("+")) && !(prev_char.equals("-")) && !(prev_char.equals("*")) && !(prev_char.equals("/")) && !(prev_char.equals("^"))){
                    updatedString = "";
                    updatedString += expression.substring(0, i);
                    updatedString += "*";
                    updatedString += expression.substring(i);
                    expression = updatedString;
                }
            }
        } 
        return expression;   
    }



}




