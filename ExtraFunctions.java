import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ExtraFunctions {    


    //Updating universal constants

    public String replacePi(String expression){
        for (int i = 1; i < expression.length(); i++){
            if (expression.charAt(i) == 'π'){
                String prev_char = expression.charAt(i - 1) + "";
                String updatedString = "";
                if (!(prev_char.equals("+")) && !(prev_char.equals("-")) && !(prev_char.equals("*")) && !(prev_char.equals("/")) && !(prev_char.equals("^")) && !(prev_char.equals("("))){
                    updatedString = "";
                    updatedString += expression.substring(0, i);
                    updatedString += "*";
                    updatedString += expression.substring(i);   
                    expression = updatedString; 
                }       
            }
        }
        expression = expression.replaceAll("π", String.format("%.2f", Math.PI));
        expression = expression.replaceAll("pi", String.format("%.2f", Math.PI));
        return expression;
    }

    public String replaceE(String expression){
        for (int i = 1; i < expression.length(); i++){
            if (expression.charAt(i) == 'e'){
                String prev_char = expression.charAt(i - 1) + "";
                String updatedString = "";
                if (!(prev_char.equals("+")) && !(prev_char.equals("-")) && !(prev_char.equals("*")) && !(prev_char.equals("/")) && !(prev_char.equals("^")) && !(prev_char.equals("("))){
                    updatedString = "";
                    updatedString += expression.substring(0, i);
                    updatedString += "*";
                    updatedString += expression.substring(i);   
                    expression = updatedString; 
                }       
            }
        }
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
            if ((expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i - 1)) || (expression.charAt(i) == '-' && expression.charAt(i-1) == ')'))){
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
    public String simplifyLog(String expression, Boolean graphing){
        while (expression.contains("log")){
            String tempExpression = expression.substring(expression.indexOf("l"), expression.lastIndexOf(")") + 1);;
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
            ArrayList <String> result = Calculator.compute(innerExpression, graphing);
            finalResult = Double.toString(Math.log10(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifyLn(String expression, Boolean graphing){
        while (expression.contains("ln")){
            String tempExpression = expression.substring(expression.indexOf("l"), expression.lastIndexOf(")") + 1);
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
            ArrayList <String> result = Calculator.compute(innerExpression, graphing);
            finalResult = Double.toString(Math.log(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifySqrt(String expression, Boolean graphing){
        while (expression.contains("sqrt")){
            String tempExpression = expression.substring(expression.indexOf("s"), expression.lastIndexOf(")") + 1);
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
            System.out.println(curLog);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            System.out.println(innerExpression);
            String finalResult = "";
            ArrayList <String> result = Calculator.compute(innerExpression, graphing);
            finalResult = Double.toString(Math.sqrt(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    //trig
    public String simplifySin(String expression, Boolean graphing){
        while (expression.contains("sin")){
            String tempExpression = expression.substring(expression.indexOf("s"), expression.lastIndexOf(")") + 1);
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
            ArrayList <String> result = Calculator.compute(innerExpression, graphing);
            finalResult = Double.toString(Math.sin(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifyCos(String expression, Boolean graphing){
        while (expression.contains("cos")){
            String tempExpression = expression.substring(expression.indexOf("c"), expression.lastIndexOf(")") + 1);
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
            ArrayList <String> result = Calculator.compute(innerExpression, graphing);
            finalResult = Double.toString(Math.cos(Double.parseDouble(result.get(result.size() - 1))));
            expression = expression.replace(curLog, finalResult);
        }
        
        return expression;
    }

    public String simplifyTan(String expression, Boolean graphing){
    while (expression.contains("tan")){
        String tempExpression = expression.substring(expression.indexOf("t"), expression.lastIndexOf(")") + 1);
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
        ArrayList <String> result = Calculator.compute(innerExpression, graphing);
        finalResult = Double.toString(Math.tan(Double.parseDouble(result.get(result.size() - 1))));
        expression = expression.replace(curLog, finalResult);
    }
    
    return expression;
    }

    public String randNum(String expression){
        while (expression.contains("rand")){
            String tempExpression = expression.substring(expression.indexOf("r"), expression.lastIndexOf(")") + 1);
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
            
            String curLog = expression.substring(expression.indexOf("r"), finalIndex + 1);
            String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
            Double min = Double.parseDouble(innerExpression.substring(0, innerExpression.indexOf(',')));
            Double max = Double.parseDouble(innerExpression.substring(innerExpression.indexOf(',') + 1));
            String finalResult = Double.toString((Math.random() * (max - min)) + min);
            expression = expression.replace(curLog, finalResult);
        }
    
        return expression;
    }

    public String absValue(String expression, Boolean graphing){
        while (expression.contains("abs")){
            String tempExpression = expression.substring(expression.indexOf("a"), expression.lastIndexOf(")") + 1);
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
            
        String curLog = expression.substring(expression.indexOf("a"), finalIndex + 1);
        String innerExpression = curLog.substring(curLog.indexOf("(") + 1, curLog.lastIndexOf(")"));
        String finalResult = "";
        ArrayList <String> result = Calculator.compute(innerExpression, graphing);
        finalResult = Double.toString(Math.abs(Double.parseDouble(result.get(result.size() - 1))));
        expression = expression.replace(curLog, finalResult);
        }
    
        return expression;
    }

    // Statistics
    public ArrayList listOne(String expression){
        ArrayList<Double> l1 = new ArrayList<Double>();
        Double curNum = 0.0;
        if (expression.contains("l1")){
            expression = expression.substring(expression.indexOf("("), expression.indexOf(")") + 1);
            System.out.println(expression);
            while (!(expression.substring(expression.indexOf("(") + 1, expression.indexOf(")")).equals(""))){
                if (expression.contains(",")){
                    curNum = Double.parseDouble(expression.substring(1, expression.indexOf(",")));
                    expression = expression.replace(Double.toString(curNum) + ",", "");
                }
                else{
                    curNum = Double.parseDouble(expression.substring(1, expression.indexOf(")")));
                    expression = expression.replace(Double.toString(curNum), "");
                }
                l1.add(curNum);
            }
        }

        return l1;
    }

    public Double calculateMedian(ArrayList<Double> data) {
        Collections.sort(data);
        int size = data.size();
        if (size % 2 == 0) {
            int mid1 = size / 2;
            int mid2 = mid1 - 1;
            return (data.get(mid1) + data.get(mid2)) / 2.0;
        } else {
            return data.get(size / 2);
        }
    }

    public Double calculateQ1(ArrayList<Double> data) {
        Collections.sort(data);
        int size = data.size();
        int middle = size / 2;
        if (size % 2 == 0) {
            return calculateMedian(new ArrayList<>(data.subList(0, middle)));
        } else {
            return calculateMedian(new ArrayList<>(data.subList(0, middle + 1)));
        }
    }

    public Double calculateQ3(ArrayList<Double> data) {
        Collections.sort(data);
        int size = data.size();
        int middle = size / 2;
        if (size % 2 == 0) {
            return calculateMedian(new ArrayList<>(data.subList(middle, size)));
        } else {
            return calculateMedian(new ArrayList<>(data.subList(middle + 1, size)));
        }
    }

    public Double calculateIQR(ArrayList<Double> data) {
        double q1 = calculateQ1(data);
        double q3 = calculateQ3(data);
        return q3 - q1;
    }

}




