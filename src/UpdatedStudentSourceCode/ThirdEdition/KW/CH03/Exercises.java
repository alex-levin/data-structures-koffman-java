package KW.CH03;

/**
 * Class to contain programming exercise solutions for Chapter 3
 *
 * @author Koffman & Wolfgang
 */
public class Exercises {

// Insert solution to programming exercise 1, section 2, chapter 3 here
    
// Insert solution to programming exercise 1, section 3, chapter 3 here

// Insert solution to programming exercise 2, section 3, chapter 3 here
    
// Insert solution to programming exercise 4, section 3, chapter 3 here
    
    /** Finds the largest value in array elements x[start] through x[last].
     * @param x array whose largest value is found
     * @param start first subscript in range
     * @param last last subscript in range
     * @return the largest value of x[start] through x[last]
     * @pre first <= last
     */
    public static int findMax(int[] x, int start, int last) {
        if (start > last)
            throw new IllegalArgumentException("Empty range");
        int maxSoFar = 0;
        for (int i = start; i < last; i++) {
// Insert solution to programming exercise 2, section 7, chapter 3 here
            if (x[i] > maxSoFar)
                maxSoFar = i;
        }
        return maxSoFar;
    }
    
    
}
