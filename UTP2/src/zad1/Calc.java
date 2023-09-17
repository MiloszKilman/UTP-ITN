/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad1;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class Calc {
    Map<String, Calculation> m;
    private String result;
    private double a ,b;


    public String doCalc(String cmd) {
        m.put("+", new AddCalculation());
        m.put("-", new SubtractCalculation());
        m.put("/", new DivideCalculation());
        m.put("*", new MultiplyCalculation());
        String [] arguments=cmd.split("\\s+");
//        a=Double.parseDouble(arguments[0]);
//        b=Double.parseDouble(arguments[2]);
//        BigDecimal a1 = BigDecimal.valueOf(a);
//        BigDecimal b1 = BigDecimal.valueOf(b);
//        try {
//            Arrays.stream(arguments).equals("*");
//            result= String.valueOf(opPlus(a1,b1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return result;
    }

}


class AddCalculation implements Calculation {
    public BigDecimal calculate(BigDecimal op1, BigDecimal op2) {
        return op1.add(op2);
    }
}
class DivideCalculation implements Calculation {
    public BigDecimal calculate(BigDecimal op1, BigDecimal op2) {
        return op1.divide(op2);
    }
}

class MultiplyCalculation implements Calculation {
    public BigDecimal calculate(BigDecimal op1, BigDecimal op2) {
        return op1.multiply(op2);
    }
}
class SubtractCalculation implements Calculation {
    public BigDecimal calculate(BigDecimal op1, BigDecimal op2) {
        return op1.subtract(op2);
    }
}

