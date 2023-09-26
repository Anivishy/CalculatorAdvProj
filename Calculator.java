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

        //Testing parse function

        ArrayList<String> testParse = translator.parse(expression);
        for (int i = 0; i < testParse.size(); i++){
            System.out.print("#" + i + " ");
            System.out.println(testParse.get(i));
        }        

        // Testing out Translator PEMDAS functions here

        ArrayList<String> checkPar = translator.checkParenthesis(expression);
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


    }
    
    
}
