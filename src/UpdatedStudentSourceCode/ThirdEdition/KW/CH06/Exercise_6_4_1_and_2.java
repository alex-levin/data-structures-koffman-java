package KW.CH06;

import java.util.Scanner;
import java.util.function.BiFunction;

public class Exercise_6_4_1_and_2 {
    
    /*<exericse chapter="6" section="4" type="programming" number="1">*/
    static BiFunction<Double, Integer, Double> f1 = (x, n) -> Math.pow(x, n);
    static BiFunction<Double, Integer, Double> f2 = (x, n) -> {
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    };
    /*</exercise>*/
    
// Insert solution to programming exercise 2, section 4, chapter 6 here
    

}
