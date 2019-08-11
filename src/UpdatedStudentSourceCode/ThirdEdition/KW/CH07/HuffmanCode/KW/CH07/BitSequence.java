package KW.CH07;

/**
 * A BitSequence is a sequence of 0's and 1's represented as boolean values.
 * @author Paul
 */
public interface BitSequence {
    
    /**
     * Get the ith bit in the sequence
     * @param i the index
     * @return true if the bit is a 1 and false if the bit is a zero.
     */
    boolean get(int i);
    
    /**
     * Return the size of this BitSequence
     * @return the number of bits in this BitSequence
     */
    int size();
    
    /**
     * Create a mask for the selected bit
     * @param i The index of the selected bit
     * @return A mask with a 1 bit set in the
     *         appropriate bit
     */
    static int mask(int i) {
        int r = i % 32;
        return 0x80000000 >>> r;
    }

    /**
     * Determine the index of the byte that contains
     * the selected bit
     * @param i The index of the selected bit
     * @return The index of the byte that contains the
     *         selected bit.
     */
    static int index(int i) {
        return i / 32;
    }
    
}
