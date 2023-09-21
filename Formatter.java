import java.util.Stack;

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
    
    public void formatAndDisplay(String[] steps) {        
        // formatAndDisplay will run through the steps and display them on the screen
        // Implement format here
        System.out.println("Input: " + steps[0]);
        for (int i = 1; i < steps.length - 2; i++){
            System.out.println("Step #" + i + ": " + steps[i]);
        } 
        System.out.println("Answer: " + steps[steps.length - 1]);

    }

    public void addToHistory(String expression) {

        // addToHistory will add an expression to the history stack

        // Implement addToHistory here

    }
}
