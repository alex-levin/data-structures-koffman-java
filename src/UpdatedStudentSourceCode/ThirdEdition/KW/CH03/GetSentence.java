package KW.CH03;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Class to demonstrate use of debugger
 * @author Koffman & Wolfgang
 */
public class GetSentence {
// Insert solution to programming exercise 1, section 7, chapter 3 here
    /*<listing chapter="3" number="2">*/
    /**
     * Return the individual words entered by the user.
     * The user can enter the sentinel *** to terminate data entry.
     * @return A string with a maximum of 10 words
     */
    public static String getSentence() {
        Scanner in = new Scanner(System.in);
        StringJoiner stb = new StringJoiner(" ");
        int count = 0;
        while (count < 10) {
            System.out.println("Enter a word or *** to quit");
            String word = in.next();
// Insert solution to programming exercise 1, section 7, chapter 3 here
            if (word == "***") break;
            stb.add(word);
            count++;
        }
        return stb.toString();
    }
    /*</listing>*/
    
    public static void main(String[] args) {
        String sentence = getSentence();
        System.out.println("The sentence is " + sentence);
    }
    
}
