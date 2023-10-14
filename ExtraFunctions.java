import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtraFunctions {

    public String replacePi(String expression){
        expression = expression.replaceAll("π", Double.toString(Math.PI));
        expression = expression.replaceAll("pi", Double.toString(Math.PI));
        return expression;
    }

    
}




