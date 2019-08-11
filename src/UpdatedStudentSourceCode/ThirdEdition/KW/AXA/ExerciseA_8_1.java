package KW.AXA;

/**
 * Solution to Exercise A.8.1
 * @author Paul Wolfgang
 */
public class ExerciseA_8_1 {

// Insert solution to programming exercise 1, section 8, chapter A here

    public static void main(String[] args) {
        int[] a = {121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = {11, 121, 144, 19, 161, 19, 144, 19};
        if (sameElements(a, b)) {
            System.out.println("Test 1 passed");
        } else {
            System.out.println("Test 1 failed");
        }
        int[] c = {11, 121, 144, 18, 161, 19, 144, 19};
        if (sameElements(a, c)) {
            System.out.println("Test 2 failed");
        } else {
            System.out.println("Test 2 passed");
        }

    }
}
