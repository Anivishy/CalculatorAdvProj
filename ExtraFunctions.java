import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExtraFunctions {    


    //Updating universal constants

    public String replacePi(String expression){
        //String piValue = String.format("%.2f", Double.toString(Math.PI));
        //System.out.print(piValue);
        expression = expression.replaceAll("π", String.format("%.2f", Math.PI));
        expression = expression.replaceAll("pi", String.format("%.2f", Math.PI));
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
                if (!(prev_char.equals("+")) && !(prev_char.equals("-")) && !(prev_char.equals("*")) && !(prev_char.equals("/")) && !(prev_char.equals("^")) && !(prev_char.equals("("))){
                    updatedString = "";
                    updatedString += expression.substring(0, i);
                    updatedString += "*";
                    updatedString += expression.substring(i);
                    System.out.println(updatedString);
                    expression = updatedString;
                }
            }
        }
        return expression;   
    }

    public String updateNegatives(String expression){
        String updatedString = "";
        for (int i = 1; i < expression.length(); i++){
            if ((expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i - 1)) || (expression.charAt(i) == '-' && expression.charAt(i-1) == ')'))){
                System.out.println(expression.charAt(i) == '-');
                updatedString = "";
                updatedString += expression.substring(0, i);
                updatedString += "+";
                updatedString += expression.substring(i); 
                System.out.println(updatedString);
                expression = updatedString;

            }
        }
        return expression;
    }

    //log, ln, and square root
    public String simplifyLog(String expression){
        while (expression.contains("log")){
            String tempExpression = expression.substring(expression.indexOf("l"), expression.lastIndexOf(")") + 1);
            System.out.print(tempExpression);
            Integer finalIndex = 0;
            Integer paraCount = 0;
            Boolean paraDetect = false;
            for (int i = 0; i < tempExpression.length(); i++){
                if (tempExpression.charAt(i) == '('){
                    paraCount += 1;
                    paraDetect = true;
                }
                if (tempExpression.charAt(i) == ')'){
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
            }
            
            String curLog = expression.substring(expression.indexOf("l"), finalIndex + 1);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            String finalResult = "";
            ArrayList <String> result = Calculator.compute(innerExpression);
            finalResult = Double.toString(Math.log10(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifyLn(String expression){
        while (expression.contains("ln")){
            String tempExpression = expression.substring(expression.indexOf("l"), expression.lastIndexOf(")") + 1);
            System.out.print(tempExpression);
            Integer finalIndex = 0;
            Integer paraCount = 0;
            Boolean paraDetect = false;
            for (int i = 0; i < tempExpression.length(); i++){
                if (tempExpression.charAt(i) == '('){
                    paraCount += 1;
                    paraDetect = true;
                }
                if (tempExpression.charAt(i) == ')'){
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
            }
            
            String curLog = expression.substring(expression.indexOf("l"), finalIndex + 1);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            String finalResult = "";
            ArrayList <String> result = Calculator.compute(innerExpression);
            finalResult = Double.toString(Math.log(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifySqrt(String expression){
        while (expression.contains("sqrt")){
            String tempExpression = expression.substring(expression.indexOf("s"), expression.lastIndexOf(")") + 1);
            System.out.print(tempExpression);
            Integer finalIndex = 0;
            Integer paraCount = 0;
            Boolean paraDetect = false;
            for (int i = 0; i < tempExpression.length(); i++){
                if (tempExpression.charAt(i) == '('){
                    paraCount += 1;
                    paraDetect = true;
                }
                if (tempExpression.charAt(i) == ')'){
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
            }
            
            String curLog = expression.substring(expression.indexOf("s"), finalIndex + 1);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            String finalResult = "";
            ArrayList <String> result = Calculator.compute(innerExpression);
            finalResult = Double.toString(Math.sqrt(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    //trig
    public String simplifySin(String expression){
        while (expression.contains("sin")){
            String tempExpression = expression.substring(expression.indexOf("s"), expression.lastIndexOf(")") + 1);
            System.out.print(tempExpression);
            Integer finalIndex = 0;
            Integer paraCount = 0;
            Boolean paraDetect = false;
            for (int i = 0; i < tempExpression.length(); i++){
                if (tempExpression.charAt(i) == '('){
                    paraCount += 1;
                    paraDetect = true;
                }
                if (tempExpression.charAt(i) == ')'){
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
            }
            
            String curLog = expression.substring(expression.indexOf("s"), finalIndex + 1);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            String finalResult = "";
            ArrayList <String> result = Calculator.compute(innerExpression);
            finalResult = Double.toString(Math.sin(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifyCos(String expression){
        while (expression.contains("cos")){
            String tempExpression = expression.substring(expression.indexOf("c"), expression.lastIndexOf(")") + 1);
            System.out.print(tempExpression);
            Integer finalIndex = 0;
            Integer paraCount = 0;
            Boolean paraDetect = false;
            for (int i = 0; i < tempExpression.length(); i++){
                if (tempExpression.charAt(i) == '('){
                    paraCount += 1;
                    paraDetect = true;
                }
                if (tempExpression.charAt(i) == ')'){
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
            }
            
            String curLog = expression.substring(expression.indexOf("c"), finalIndex + 1);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            String finalResult = "";
            ArrayList <String> result = Calculator.compute(innerExpression);
            finalResult = Double.toString(Math.cos(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifyTan(String expression){
    while (expression.contains("tan")){
        String tempExpression = expression.substring(expression.indexOf("t"), expression.lastIndexOf(")") + 1);
        System.out.print(tempExpression);
        Integer finalIndex = 0;
        Integer paraCount = 0;
        Boolean paraDetect = false;
        for (int i = 0; i < tempExpression.length(); i++){
            if (tempExpression.charAt(i) == '('){
                paraCount += 1;
                paraDetect = true;
            }
            if (tempExpression.charAt(i) == ')'){
                paraCount -= 1;
            }
            if (paraDetect){
                if (paraCount == 0){
                    finalIndex = i;
                }
            }
        }
        
        String curLog = expression.substring(expression.indexOf("t"), finalIndex + 1);
        String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
        String finalResult = "";
        ArrayList <String> result = Calculator.compute(innerExpression);
        finalResult = Double.toString(Math.tan(Double.parseDouble(result.get(result.size() - 1))));
        expression = expression.replace(curLog, finalResult);
    }
    
    return expression;
    }
}




