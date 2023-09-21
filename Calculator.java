import java.util.Scanner;

public class Calculator {

    // Calculator will serve as a main class of sorts. This is where we will create our objects, call methods, etc.

    // Implement Calculator functionality here

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter an expression to compute: ");
        String expression=scanner.nextLine();

        Translator translator = new Translator(expression);
        

    }
    
    
}
