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
        //used for handling implied multiplication
        String updatedString = "";
        

        Boolean continue_loop = true;
        Boolean modified = false;

        //remove all whitespace form input string
        
        while (continue_loop) {            
            System.out.println("Enter an expression to compute or type '#' to quit: ");
            String expression=scanner.nextLine();

            expression.replace("\\s", "");
            for (int i = 1; i < expression.length();  i ++){
                if (expression.charAt(i) == '('){
                    String prev_char = expression.charAt(i - 1) + "";
                    if (!(prev_char.equals("+")) || !(prev_char.equals("-")) || !(prev_char.equals("*")) || !(prev_char.equals("/")) || !(prev_char.equals("^"))){
                        updatedString = "";
                        updatedString += expression.substring(0, i);
                        updatedString += "*";
                        updatedString += expression.substring(i);
                        System.out.println(updatedString);
                        modified = true;
                    }
                }

                else if (!modified){
                    updatedString = expression;
                }

                
            }

            //System.out.print(expression);

            if (expression.equals("#")){
                continue_loop = false;
            }

            else{
                String currentExpression=updatedString;
                Translator translator = new Translator(expression);

                steps.add(expression);

                while (!currentExpression.matches("-?\\d+(\\.\\d+)?")) {

                    ArrayList<String> components = translator.parse(currentExpression);
                    System.out.println(components.toString());
                    engine.setExpression(components.get(0), Double.parseDouble(components.get(1)), Double.parseDouble(components.get(2)));
                    double result=engine.compute();
                    currentExpression=currentExpression.replace(components.get(3), Double.toString(result));
                    System.out.println(currentExpression);
                    steps.add(currentExpression);

                }

                ans = steps.get(steps.size() - 1);
                formatter.formatAndDisplay(steps);
                steps.clear();
            }
        }
       
    }
    
    
}
