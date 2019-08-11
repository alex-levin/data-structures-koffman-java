/*<listing chapter="7" section="5">*/
package KW.CH07;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;

/**
 * A hash set for storing set elements using open addressing. This version
 * is an adapter of a HashMap and extends the AbstractMap.
 * @param <K> The key type
 * @author Koffman and Wolfgang
 **/
public class HashSetOpen<K> extends AbstractSet<K> {

    private final Map<K, K> setMap = new HashtableOpen<>();

    /**
     * Adapter method contains.
     * @param key The key being sought
     * @return true if the key is found in setMap
     */
    @Override
    public boolean contains(Object key) {
        // HashtableOpen.get returns null if the key is not found.
        return (setMap.get((K)key) != null);
    }

    /**
     * Adapter method add.
     * @post Adds a new Entry object (key, key)
     * if key is not a duplicate.
     * @param key The key being inserted
     * @return true if the key is not a duplicate
     */
    @Override
    public boolean add(K key) {
        /* HashtableOpen.put returns null if the
        key is not a duplicate. */
        return (setMap.put(key, key) == null);
    }

    /**
     * Adapter method remove.
     * @post Removes the key-value pair (key, key).
     * @param key The key being removed
     * @return true if the key is found and removed
     */
    @Override
    public boolean remove(Object key) {
        // HashtableOpen.remove returns null if the
        // key is not removed. */
        return (setMap.remove(key) != null);
    }

    /**
     * Adapter method size.
     * @return the size of the set
     */
    @Override
    public int size() {
        return setMap.size();
    }

    /**
     *  Adapter class for the Iterator
     */
    private static class Itr<K> implements Iterator<K> {

        Iterator<Map.Entry<K, K>> itr;

        public Itr(Iterator<Map.Entry<K, K>> itr) {
            this.itr = itr;
        }

        @Override
        public boolean hasNext() {
            return itr.hasNext();
        }

        @Override
        public K next() {
            return itr.next().getKey();
        }

        @Override
        public void remove() {
            itr.remove();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Itr<K>(((HashtableOpen) setMap).entrySet().iterator());
    }

// Insert solution to programming exercise 2, section 1, chapter 7 here
}
