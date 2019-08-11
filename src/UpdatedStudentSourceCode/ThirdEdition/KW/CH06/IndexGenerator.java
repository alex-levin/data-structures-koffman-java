/*<listing chapter="6" number="8">*/
package KW.CH06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Class to build an index.
 *
 * @author Koffman and Wolfgang
 *
 */
public class IndexGenerator {

    // Data Fields
    /**
     * Tree for storing the index.
     */
    private final TreeSet<String> index;
    /**
     * Pattern for extracting words from a line A word is a string of one or
     * more letters or numbers or ' characters
     */
    private static final String PATTERN = "[\\p{L}\\p{N}']+";

    // Methods
    public IndexGenerator() {
        index = new TreeSet<>();
    }

    /**
     * Reads each word in file referenced by Scanner scan and stores it in 
     * search tree along with its line number.
     *
     * @post Lowercase form of each word with line number stored in index.
     * @param scan A Scanner object that contains the input text
     */
    public void buildIndex(Scanner scan) {
        int lineNum = 0; // Line number
        // Keep reading lines until done.
        while (scan.hasNextLine()) {
            lineNum++;
            // Extract each token and store it in index
            String token;
            while ((token = scan.findInLine(PATTERN)) != null) {
                token = token.toLowerCase();
                index.add(String.format("%s, %3d", token, lineNum));
            }
            scan.nextLine();
        }
    }

    /**
     * Displays the index, one word per line.
     */
    public void showIndex() {
        // Use an iterator to access and display tree data.
        index.forEach(next -> System.out.println(next));
    }

// Insert solution to programming exercise 4, section 5, chapter 6 here
}
/*</listing>*/
