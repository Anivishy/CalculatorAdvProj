import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtraFunctions {

    public String replacePi(String expression){
        expression = expression.replaceAll("π", Double.toString(Math.PI));
        expression = expression.replaceAll("pi", Double.toString(Math.PI));
        return expression;
    }

    public String replaceE(String expression){
        expression = expression.replaceAll("e", Double.toString(Math.exp(1.0)));
        return expression;
    }

    
}




