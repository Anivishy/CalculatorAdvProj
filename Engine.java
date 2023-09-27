// Engine is responsible for computing all the sub-expressions

public class Engine {

    private String _operator;
    private double _num1;
    private double _num2;



    // Getter methods to access private fields

    public String getOperator() {
        return this._operator;
    }

    public double getNum1() {
        return this._num1;
    }

    public double getNum2() {
        return this._num2;
    }

    public void setExpression(String operator, double num1, double num2) {
        this._operator=operator;
        this._num1=num1;
        this._num2=num2;
    }

    public double compute() {

        // Compute will call the appropriate method based on the operation and return the result

        double result=0.0;

        if (getOperator().equals("+")) {
            result=addNums();
        }

        else if (getOperator().equals("-")) {
            result=subtractNums();
        }

        else if (getOperator().equals("*")) {
            result=multiplyNums();
        }

        else if (getOperator().equals("/")) {
            result=divideNums();
        }

        else if (getOperator().equals("^")) {
            result=powerNums();
        }

        return result;
    }

    // Methods for computing basic operations

    public double addNums() {
        return getNum1() + getNum2();
    }

    public double subtractNums() {
        return getNum1() - getNum2();
    }

    public double multiplyNums() {
        return getNum1() * getNum2();
    }

    public double divideNums() {
        return getNum1() / getNum2();
    }

    public double powerNums() {
        return Math.pow(getNum1(), getNum2());
    }

}
