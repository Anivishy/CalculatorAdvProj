import java.util.ArrayList;

// Engine is responsible for computing all the sub-expressions

public class Engine {

    private String _operator;
    private ArrayList<Double> _nums;
    

    public Engine(String operator, ArrayList<Double> nums) {

        // Assign values to private fields

        this._operator=operator;
        this._nums=nums;
     
    }

    // Getter methods to access private fields

    public String getOperator() {
        return this._operator;
    }

    public ArrayList<Double> getNums() {
        return this._nums;
    }


    public double compute() {

        // Compute will call the appropriate method based on the operation and return the result

        double result=0.0;

        // Insert compute logic here

        return result;
    }

    // Methods for computing basic operations

    public double addNums() {
        double result=0;
        for (double i:getNums()) {
            result+=i;
        }
        return result;
    }

    public double subtractNums() {
        double result=getNums().get(0);
        for (int i=1; i<getNums().size(); i++) {
            result-=i;
        }
        return result;
    }

    public double multiplyNums() {
        double result=1;
        for (double i:getNums()) {
            result*=i;
        }
        return result;
    }

    public double divideNums() {
        double result=getNums().get(0);
        for (int i=1; i<getNums().size(); i++) {
            result/=i;
        }
        return result;
    }

    public double powerNums() {
        return Math.pow(getNums().get(0), getNums().get(1));
    }

}
