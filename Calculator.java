import java.util.*;


public class Calculator {

    // Calculator will serve as a main class of sorts for the CLI.

    public static ArrayList<String> compute(String expression, Boolean graphing) {

        ArrayList<String> steps = new ArrayList<String>();

        Translator translator = new Translator(expression);
        Engine engine=new Engine();
        ExtraFunctions extra = new ExtraFunctions();
   
        //used for handling implied multiplication
        String updatedString = "";

        //Statistics Lists
        ArrayList<Double> l1 = new ArrayList<Double>();
        
        //Boolean modified = false;

        //remove all whitespace form input string
        
 
        updatedString = "";
        //Remove Spacing
        expression = expression.replace("\\s", "");

        //Remove special chars
        expression = extra.replacePi(expression);
        expression = extra.replaceE(expression);

        //simplify logs, ln sqrt, etc.
        expression = extra.simplifyLog(expression, graphing);
        expression = extra.simplifyLn(expression, graphing);        

        //trig
        expression = extra.simplifySin(expression, graphing);
        expression = extra.simplifyCos(expression, graphing);
        expression = extra.simplifyTan(expression, graphing);

        //Extra
        expression = extra.randNum(expression);
        expression = extra.absValue(expression, graphing);
        expression = extra.simplifySqrt(expression, graphing);

        //stat
        l1 = extra.listOne(expression);

        if (!(l1.isEmpty())){
            String min = "";
            String max = "";
            String range = "";
            Double temp = 0.0;
            String totalSum = "";
            String mean = "";
            String result = "";
            String iqr = Double.toString(extra.calculateIQR(l1));
            String q1 = Double.toString(extra.calculateQ1(l1));
            String q2 = Double.toString(extra.calculateQ3(l1));
            String median = Double.toString(extra.calculateMedian(l1));
            Collections.sort(l1);

            min = Double.toString(l1.get(0));
            max = Double.toString(l1.get(l1.size() - 1));
            range = Double.toString(l1.get(l1.size() - 1) - l1.get(0));
            for (int i = 0; i < l1.size(); i++){
                temp += l1.get(i);
            }
            totalSum = Double.toString(temp);
            mean = Double.toString(temp / l1.size());

            result = "Minimum Number: " + min + "\n" + 
                     "Maximum Number: " + max + "\n" + 
                     "Range: " + range + "\n" + 
                     "Sum: " + totalSum + "\n" +
                     "Average: " + mean + "\n" +
                     "Median: " + median + "\n" +
                     "Q1: " + q1 + "\n" +
                     "Q2: " + q2 + "\n" +
                     "IQR: " + iqr;

            steps.add(result);
            return steps;
        }

        else {
            //implied multiplication
            expression = extra.impliedMulti(expression);

            

            //negative 
            expression = extra.updateNegatives(expression);


            String currentExpression=expression;

            steps.add(expression);

        while (!currentExpression.matches("-?\\d+(\\.\\d+)?")) {
            
            ArrayList<String> components = translator.parse(currentExpression);
            
            engine.setExpression(components.get(0), Double.parseDouble(components.get(1)), Double.parseDouble(components.get(2)));
            double result=engine.compute();
            if (!(graphing)){
                currentExpression=currentExpression.replace(components.get(3), String.format("%.2f", result));
            }
            else{
                currentExpression=currentExpression.replace(components.get(3), String.format("%.20f", result));
            }
            steps.add(currentExpression);
        }

            Config.ans = steps.get(steps.size() - 1);
            return steps;
        }
        
        
    }

}

    //********************OLD CONSOLE IMPLEMENTATION*************************
//     public static void main(String[] args) {
//         ArrayList<String> steps = new ArrayList<String>();

//         Scanner scanner=new Scanner(System.in);
//         Engine engine=new Engine();
//         Formatter formatter=new Formatter();
//         String ans = "";
//         //used for handling implied multiplication
//         String updatedString = "";
        

//         Boolean continue_loop = true;
//         Boolean modified = false;

//         //remove all whitespace form input string

        
        
//         while (continue_loop) {            
//             System.out.println("Enter an expression to compute or type '#' to quit: ");
//             String expression=scanner.nextLine();
//             modified = false;
//             updatedString = "";

//             // while(expression.contains("(")){
//             //     String checkString = expression.substring(expression.indexOf("("), expression.indexOf(")"));
//             //     if (checkString.contains("+") || checkString.contains("*") || checkString.contains("/") || checkString.contains("^")){
//             //         ;
//             //     }
                
//             // }

//             expression = expression.replace("\\s", "");
//             for (int i = 1; i < expression.length();  i ++){
//                 if (expression.charAt(i) == '('){
//                     String prev_char = expression.charAt(i - 1) + "";
//                     if (!(prev_char.equals("+")) && !(prev_char.equals("-")) && !(prev_char.equals("*")) && !(prev_char.equals("/")) && !(prev_char.equals("^"))){
//                         updatedString = "";
//                         updatedString += expression.substring(0, i);
//                         updatedString += "*";
//                         updatedString += expression.substring(i);
//                         expression = updatedString;
//                         modified = true;
//                     }
//                 }

//                 else if (!modified){
//                     //updatedString = expression;
//                     expression = expression;
//                 }                
//             }

//             for (int i = 1; i < expression.length(); i++){
//                 if (expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i - 1))){
//                     updatedString = "";
//                     updatedString += expression.substring(0, i);
//                     updatedString += "+";
//                     updatedString += expression.substring(i);
//                     expression = updatedString;

//                 }
//             }

//             //System.out.print(expression);

//             if (expression.equals("#")){
//                 continue_loop = false;
//             }

//             else{
//                 String currentExpression=expression;
//                 Translator translator = new Translator(expression);

//                 steps.add(expression);

//                 while (!currentExpression.matches("-?\\d+(\\.\\d+)?")) {


//                     ArrayList<String> components = translator.parse(currentExpression);
//                     //System.out.println(components.toString());
//                     engine.setExpression(components.get(0), Double.parseDouble(components.get(1)), Double.parseDouble(components.get(2)));
//                     double result=engine.compute();
//                     currentExpression=currentExpression.replace(components.get(3), String.format("%.2f", result));
//                     steps.add(currentExpression);

//                 }

//                 ans = steps.get(steps.size() - 1);
//                 formatter.formatAndDisplay(steps);
//                 steps.clear();
//             }
//         }
       
//     }
    
    
// }
