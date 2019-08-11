package KW.CH07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

/**
 * Alternate solution to programming exercises 7.2.1 and 7.2.2
 *
 * @author Koffman & Wolfgang
 */
public class WordCount2 {

// Insert solution to programming exercise 1, section 2, chapter 7 here

// Insert solution to programming exercise 2, section 2, chapter 7 here
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            Map<String, Long> counts = buildWordCounts(br);
            counts.forEach((k, v) -> System.out.printf("%-15s%5d%n", k, v));
        } catch (IOException ioex) {
            System.err.println("Problem opening or closing " + args[0]);
        }
    }
}
