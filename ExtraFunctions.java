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
                if (!(prev_char.equals("+")) && !(prev_char.equals("-")) && !(prev_char.equals("*")) && !(prev_char.equals("/")) && !(prev_char.equals("^")) && !(prev_char.equals("("))){
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

    public String updateNegatives(String expression){
        String updatedString = "";
        for (int i = 1; i < expression.length(); i++){
            if (expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i - 1))){
                updatedString = "";
                updatedString += expression.substring(0, i);
                updatedString += "+";
                updatedString += expression.substring(i);
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
                System.out.println(paraCount);
                if (tempExpression.charAt(i) == ')'){
                    System.out.println("hi");
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
                System.out.println("final index:" + finalIndex);
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
                System.out.println(paraCount);
                if (tempExpression.charAt(i) == ')'){
                    System.out.println("hi");
                    paraCount -= 1;
                }
                if (paraDetect){
                    if (paraCount == 0){
                        finalIndex = i;
                    }
                }
                System.out.println("final index:" + finalIndex);
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
        Translator translator = new Translator(expression);
        Engine engine=new Engine();
        while (expression.contains("sqrt")){
            String curLog = expression.substring(expression.indexOf("l"), expression.indexOf(")") + 1);
            String innerExpression = curLog.substring(curLog.indexOf("("), curLog.indexOf(")") + 1);
            String finalResult = "";
            while (!innerExpression.matches("-?\\d+(\\.\\d+)?")) {
                //expression = extra.updateNegatives(expression);
                ArrayList<String> components = translator.parse(innerExpression);
                //System.out.println(components.toString());
                engine.setExpression(components.get(0), Double.parseDouble(components.get(1)), Double.parseDouble(components.get(2)));
                double result=engine.compute();
                innerExpression=innerExpression.replace(components.get(3), String.format("%.2f", result));
                //steps.add(innerExpression);
            }
            finalResult = Double.toString(Math.log(Double.parseDouble(innerExpression)));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

}




