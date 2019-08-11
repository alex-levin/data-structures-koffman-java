package KW.CH04;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringJoiner;

public class Exercise_4_2_1 {

// Insert solution to programming exercise 1, section 2, chapter 4 here

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter line to be reversed");
            if (in.hasNextLine()) {
                line = in.nextLine();
                System.out.println(reverseWords(line));
            } else {
                break;
            }
        }
    }
}
