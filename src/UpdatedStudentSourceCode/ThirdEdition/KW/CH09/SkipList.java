/*<listing chapter="9" section="6">*/
package KW.CH09;

import KW.CH06.SearchTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A Skip List is an alternative to a binary tree that provides for
 * approximately logarithmic searching, insertion, and deletion. Skip Lists were
 * developed by William Pugh and first described in "Skip Lists: A Probabilistic
 * Alternative to Balanced Trees", CACM 13:8 (June 1990) pp 668-676
 *
 * @param <E> The element type
 * @author Koffman & Wolfgang
 */
public class SkipList<E extends Comparable<E>> implements SearchTree<E> {

    /**
     * Static class to contain the data and the links
     * @param <E> The element type
     */
    protected static class SLNode<E> {

        public SLNode<E>[] links;
        public E data;

        /**
         * Create a node of level n
         * @param n The level of this node
         * @param data The data to be stored in this node
         */
        @SuppressWarnings("unchecked")
        public SLNode(int n, E data) {
            links = (SLNode<E>[]) new SLNode[n];
            this.data = data;
        }
    }
    /**
     * Maximum level
     */
    int maxLevel = 2;
    /**
     * Nominal maximum capacity
     */
    int maxCap = computeMaxCap(maxLevel);
    /**
     * Natural Log of 2
     */
    static final double LOG2 = Math.log(2.0);
    /**
     * A random number generator
     */
    final static Random rand = new Random();
    /**
     * The current size of the skipList
     */
    int size;

    /**
     * Method to compute the maximum capacity, given the maximum level. It
     * computes Math.pow(2, maxLevel) - 1, using shift.
     *
     * @return Math.pow(2, maxLevel+1) - 1;
     */
    private static int computeMaxCap(int maxLevel) {
        return ~(~0 << maxLevel);
    }

    /**
     * Method to generate a logarithmic distributed integer between 1 and
     * maxLevel. I.E. 1/2 values returned are 1, 1/4 are 2, 1/8 are 3, 1/16 are
     * 4, etc.
     *
     * @return a random logarithmic distributed integer between 1 and maxLevel
     */
    private int logRandom() {
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }
    /**
     * The head node is a dummy node, it contains no data
     */
    protected SLNode<E> head = new SLNode<>(maxLevel, null);

    /*<listing chapter="9" number="7">*/
    @SuppressWarnings("unchecked")
    /**
     * Search for an item in the list
     *
     * @param item The item being sought
     * @return A SLNode array which references the nodes preceeding the sought
     * item at each level.
     */
    private SLNode<E>[] search(E item) {
        SLNode<E>[] result = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null
                    && current.links[i].data.compareTo(item) < 0) {
                current = current.links[i];
            }
            result[i] = current;
        }
        return result;
    }

    /**
     * Find an object in the skip list
     *
     * @param target The item being sought
     * @return A reference to the object in the skip list that compares equal as
     * determined by compareTo to the target. If not found null is returned.
     */
    @Override
    public E find(E target) {
        SLNode<E>[] update = search(target);
        if (update[0].links[0] != null
                && update[0].links[0].data.compareTo(target) == 0) {
            return update[0].links[0].data;
        } else {
            return null;
        }
    }
    /*</listing>/

// Insert solution to programming exercise 1, section 6, chapter 9 here

    /**
     * Determine if an item is in the tree
     *
     * @param target Item being sought in tree
     * @return true If the item is in the tree, false otherwise
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

// Insert solution to programming exercise 2, section 6, chapter 9 here

    /**
     * Remove all data from the tree
     */
    public void clear() {
        for (int i = 0; i < maxLevel; i++) {
            head.links[i] = null;
        }
        size = 0;
    }
    
    /**
     * Return a list containing the contents of the search tree in ascending order.
     * @return a list containing the contents of the search tree in ascending order.
     */
    @Override
    public List<E> toList() {
        List<E> list = new ArrayList<>();
            SLNode<E> current = head.links[0];
            while (current != null) {
                list.add(current.data);
                current = current.links[0];
            }
        return list;
    }
    
}
/*</listing>*/
