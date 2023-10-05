import java.util.Scanner;
import java.util.ArrayList;


public class Calculator {

    // Calculator will serve as a main class of sorts. This is where we will create our objects, call methods, etc.

    // Implement Calculator functionality here


    public static void main(String[] args) {
        ArrayList<String> steps = new ArrayList<String>();

        Scanner scanner=new Scanner(System.in);
        Engine engine=new Engine();
        Formatter formatter=new Formatter();
        String ans = "";

        

        Boolean continue_loop = true;

        //remove all whitespace form input string
        
        while (continue_loop) {            
            System.out.println("Enter an expression to compute or type '#' to quit: ");
            String expression=scanner.nextLine();

            expression.replace("\\s", "");
            for (int i = 1; i < expression.length();  i ++){
                if (expression.charAt(i) == '(' || expression.charAt(i) == ')'){
                    if (expression.charAt(i - 1) != '+'){
                        break;
                    }
                }
            }

            System.out.print(expression);

            if (expression .equals("#")){
                continue_loop = false;
            }

            else{
                String currentExpression=expression;
                Translator translator = new Translator(expression);

                steps.add(expression);

                while (!currentExpression.matches("-?\\d+(\\.\\d+)?")) {

                    ArrayList<String> components = translator.parse(currentExpression);
                    System.out.println(components.toString());
                    engine.setExpression(components.get(0), Double.parseDouble(components.get(1)), Double.parseDouble(components.get(2)));
                    double result=engine.compute();
                    currentExpression=currentExpression.replace(components.get(3), Double.toString(result));
                    steps.add(currentExpression);

                }

                ans = steps.get(steps.size() - 1);
                formatter.formatAndDisplay(steps);
                steps.clear();

                // Testing out Translator PEMDAS functions here

                /** 
                ArrayList<Object> checkPar = translator.checkParenthesis(expression);
                System.out.println("Check Parenthesis: " + checkPar.toString() + "\n");

                ArrayList<String> checkExp = translator.checkExponents(expression);
                System.out.println("Check Exponents: " + checkExp.toString() + "\n");
                
                ArrayList<String> checkMult = translator.checkMultiplication(expression);
                System.out.println("Check Multiplication: " + checkMult.toString() + "\n");

                ArrayList<String> checkDiv = translator.checkDivision(expression);
                System.out.println("Check Division: " + checkDiv.toString() + "\n");

                ArrayList<String> checkAdd = translator.checkAddition(expression);
                System.out.println("Check Addition: " + checkAdd.toString() + "\n");

                ArrayList<String> checkSub = translator.checkSubtraction(expression);
                System.out.println("Check Subtraction: " + checkSub.toString() + "\n");
                **/
                
            }
        }
       
    }
    
    
}
