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

        System.out.println("Enter an expression to compute or type 'q' to quit: ");
        String expression=scanner.nextLine();

        String ans = "";

        //remove all whitespace form input string
        expression.replace("\\s", "");

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
