import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {

    // Calculator will serve as a main class of sorts. This is where we will create our objects, call methods, etc.

    // Implement Calculator functionality here

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter an expression to compute: ");
        String expression=scanner.nextLine();
        Translator translator = new Translator(expression);
        
        // Testing out Translator PEMDAS functions here

        ArrayList<Object> checkPar = translator.checkParanthesis(expression);
        System.out.println("Check Parenthesis: " + checkPar.toString() + "\n");

        ArrayList<Object> checkExp = translator.checkExponents(expression);
        System.out.println("Check Exponents: " + checkExp.toString() + "\n");
        
        ArrayList<Object> checkMult = translator.checkMultiplication(expression);
        System.out.println("Check Multiplication: " + checkMult.toString() + "\n");

        ArrayList<Object> checkDiv = translator.checkDivision(expression);
        System.out.println("Check Division: " + checkDiv.toString() + "\n");

        ArrayList<Object> checkAdd = translator.checkAddition(expression);
        System.out.println("Check Addition: " + checkAdd.toString() + "\n");

        ArrayList<Object> checkSub = translator.checkSubtraction(expression);
        System.out.println("Check Subtraction: " + checkSub.toString() + "\n");

    }
    
    
}
