package KW.CH07;

import java.util.Arrays;
import static KW.CH07.BitSequence.index;
import static KW.CH07.BitSequence.mask;

/**
 * Builder for the BitString.  A BitString is a string of bits packed
 * in an array of ints. The BitString builder is like the StringBuilder
 * supporting appending single bits or other bit sequences.
 * @author Koffman & Wolfgang
 */
public class BitStringBuilder implements BitSequence {
    /** Array of bytes to hold the data */
    private int[] theData;
    /** Current capacity (size of the byte array) */
    private int capacity;
    /** Current number of meaningful bits */
    private int size;

    /**
     * Construct an empty BitStringBuilder with the specified
     * initial capacity
     * @param capacity The initial capacity
     */
    public BitStringBuilder(int capacity) {
        theData = new int[capacity];
        size = 0;
        this.capacity = capacity;
    }

    /**
     * Construct an empty BitString with the initial
     * capacity of 1.
     */
    public BitStringBuilder() {
        this(1);
    }

    /**
     * Construct a BitStringBuilder that is a copy of an
     * existing BitString.
     * @param original The source BitString
     */
    public BitStringBuilder(BitString original) {
        size = original.size;
        capacity = original.theData.length;
        theData = Arrays.copyOf(original.theData, capacity);
    }
    
    /**
     * Construct a BitStringBuilder that is a copy of an
     * existing BitStringBuilder.
     * @param original The source BitString
     */
    public BitStringBuilder(BitStringBuilder original) {
        size = original.size;
        capacity = original.theData.length;
        theData = Arrays.copyOf(original.theData, capacity);
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
     * Set a selected bit to a one
     * @param i The index of the selected bit
     * @throws IndexOutOfBoundsException if the index
     *         is less than 0.
     */
    private void set(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i >= size) {
            if (i / 32 >= capacity) {
                ensureCapacity(i / 32);
            }
            size = i + 1;
        }
        theData[index(i)] |= mask(i);
    }

    /**
     * Reset a selected bit to a zero
     * @param i The index of the selected bit
     * @throws IndexOutOfBoundsException if the index
     *         is less than 0
     */
    private void clear(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i >= size) {
            if (i / 32 >= capacity) {
                ensureCapacity(i / 32);
            }
            size = i + 1;
        }
        theData[index(i)] &= ~mask(i);
    }

    /**
     * Ensure that the capacity is at least as large
     * as the specified value. If the desired capacity
     * is larger than the current capacity, but smaller
     * than twice the current capacity, then the current
     * capacity is doubled
     * @param c The desired capacity
     */
    private void ensureCapacity(int c) {
        int newCapacity = Math.max(c, Math.max(2 * capacity, 1));
        theData = Arrays.copyOf(theData, newCapacity);
        capacity = newCapacity;
    }

    /**
     * Construct a BitString that is the contents of this BitStringBuilder
     * @return A BitString that has the contents of this BitStringBuilder
     */
    public BitString toBitString() {
        int newCapacity = Math.max((size + 31) / 32, 1);
        int[] data = Arrays.copyOf(theData, newCapacity);
        return new BitString(data, size);
    }

    /**
     * Append a value to the end of the string
     * @param newBit The value to append true for 1 and
     *        false for zero
     * @return The resulting BitString
     */
    public BitStringBuilder append(boolean newBit) {
        if (newBit) {
            this.set(this.size);
        } else {
            this.clear(this.size);
        }
        return this;
    }

    /**
     * A method to append a BitSequence to this BitStringBuilder
     * @param right The BitSequence to be appended
     * @post The left BitStringBuilder is modified to the
     *       result
     * @return The modified BitStringBuilder
     */
    public BitStringBuilder append(BitSequence right) {
        for (int i = 0; i < right.size(); i++) {
            if (right.get(i)) {
                this.set(this.size);
            } else {
                this.clear(this.size);
            }
        }
        return this;
    }
    
    /**
     * A method to append a char as a bit sequence to this BitStringBuilder
     * @param theChar The char to be appended
     * @return The modified BitStringBuilder
     */
    public BitStringBuilder append(char theChar) {
        for (int i = 0; i < 16; i++) {
            this.append((theChar & 0x8000) == 0x8000);
            theChar <<= 1;
        }
        return this;
    }

    /**
     * Return the size of this BitStringBuilder
     * @return The size of this BitStringBuilder
     */
    @Override
    public int size() {
        return size;
    }
    
}
