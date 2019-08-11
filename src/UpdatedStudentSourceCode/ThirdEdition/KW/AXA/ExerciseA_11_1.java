package KW.AXA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to Exercise A.11.1
 * @author Koffman & Wolfgang
 */
public class ExerciseA_11_1 {

    public static void main(String[] args) {
        int total = 1000;
        int num;
        int average = 0;
        String numStr;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            numStr = in.readLine();
            num = Integer.parseInt(numStr);
            average = total / num;
// Insert solution to programming exercise 1, section 11, chapter A here
    }
}
