import java.util.Stack;
import java.util.ArrayList;
// Formatter will format the result and steps, manage the history, and display everything to the screen/command-line

public class Formatter {
    
    private Stack<String> _history;

    public Formatter() {

        // Assign values to private fields

        this._history=new Stack<String>();
    }

     // Getter methods to access private fields

     public Stack<String> getHistory() {
        return this._history;
    }
    
    public void formatAndDisplay(ArrayList<String> steps) {        
        // formatAndDisplay will run through the steps and display them on the screen

        System.out.println("Input: " + steps.get(0));
        for (int i = 1; i < steps.size() - 1; i++){
            System.out.println("Step #" + i + ": " + steps.get(i));
        } 
        System.out.println("Answer: " + steps.get(steps.size() - 1));

    }

    public void addToHistory(String expression) {

        // addToHistory will add an expression to the history stack

        this._history.push(expression);
    }
}
