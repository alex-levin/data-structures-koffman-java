package KW.AXA;

/**
 * Solution to Exercise A.8.3
 * @author Koffman & Wolfgang
 */
public class ExerciseA_8_3 {

    private static char[][] letters;

    private static void insertNext(int i, String next) {
// Insert solution to programming exercise 3, section 8, chapter A here
    }

    public static void main(String[] args) {
        String s = "The quick brown fox jumps over the lazy dog";
        String[] words = s.split("\\s+");
        letters = new char[words.length][];
        for (int i = 0; i < words.length; i++) {
            insertNext(i, words[i]);
        }
        for (char[] row : letters) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
