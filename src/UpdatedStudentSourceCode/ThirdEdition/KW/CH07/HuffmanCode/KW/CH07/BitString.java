package KW.CH07;

import java.util.Arrays;
import static KW.CH07.BitSequence.index;
import static KW.CH07.BitSequence.mask;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *  A class to represent a string of bits. This class is like
 *  the String except that the contents is a sequence of 0's and 1's. 
 *  BitSrings are are varying length and are immutable.
 *  @author Koffman and Wolfgang
 */
public class BitString implements BitSequence {

    /** Array of bytes to hold the data */
    final int[] theData;
    /** Current number of meaningful bits */
    final int size;
    
    /**
     * Construct a BitSring with specified contents and size.
     * Note that this is a package private constructor.
     * @param data The data
     * @param size The size
     */
    BitString(int[] data, int size) {
        this.theData = data;
        this.size = size;
    }

    /**
     * Construct a BitString that is a copy of an
     * existing BitString.
     * @param original the original BitString
     */
    public BitString(BitString original) {
        size = original.size;
        theData = Arrays.copyOf(original.theData, original.theData.length);
    }

    /**
     * Access a selected bit
     * @param i The index of the selected bit
     * @return true if the selected bit is a 1
     */
    @Override
    public boolean get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (theData[index(i)] & mask(i)) != 0;
    }

    /**
     * Return a String representation of this BitString
     * where each 1 is a '1' and each 0 is a '0'
     * @return A String representation of this BitString.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (get(i)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    /**
     * Determine if two bitStrings are equal
     * @param other The other BitString
     * @return true If other is a BitString with the same
     *         contents as this BitString
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof BitString) {
            BitString otherBitString =
                    (BitString) other;
            if (theData.length != otherBitString.theData.length) return false;
            for (int i = 0; i < otherBitString.theData.length; i++) {
                if (theData[i] != otherBitString.theData[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method to append one BitString onto another
     * @param left BitString
     * @param right The right BitString
     * @return The modified BitString
     */
    public static BitString append(BitString left, BitString right) {
        BitStringBuilder result = new BitStringBuilder(left);
        result.append(right);
        return result.toBitString();
    }

    /**
     * Return a hashCode for this BitString
     * @return The hashCode for this BitString
     */
    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < theData.length; i++) {
            result = (result << 1) | (result >> 31);
            result ^= theData[i];
        }
        return result;
    }

    /**
     * Return the size of this BitString
     * @return The size of this BitString
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Write this BitString to a DataOutputStream.
     * @param out The destination DataOutputStream.
     * @throws java.io.IOException If an IO error occurs
     */
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(size);
        for (int i = 0; i < theData.length; i++) {
            out.writeInt(theData[i]);
        }
        out.flush();
    }
    
    /**
     * Read a BitString from a DataInputStream.
     * @param in The source DataInputStream
     * @return The BitString that was read
     * @throws java.io.IOException If an IO error occurs
     */
    public static BitString read(DataInputStream in) throws IOException {
        int size = in.readInt();
        int capacity = (size + 31)/32;
        int[] theData = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            theData[i] = in.readInt();
        }
        return new BitString(theData, size);
    }
    
    /**
     * Class to iterate through a BitString.
     */
    public class BitStringIterator {
        private int index;
        private final int size;
        private final BitString bitString;
        
        private BitStringIterator(BitString bitString) {
            this.bitString = bitString;
            this.size = bitString.size();
            this.index = 0;
        }
        
        public boolean hasNext() {
            return index < size;
        }
        
        public boolean next() {
            return bitString.get(index++);
        }
    }
    
    /**
     * Method to return a BitSringIterator to this BitString;
     * @return A BitStringIterator
     */
    public BitStringIterator iterator() {
        return new BitStringIterator(this);
    }
}
