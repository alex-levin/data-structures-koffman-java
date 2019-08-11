/*<listing chapter="4" number="5">*/
package KW.CH04;

import java.util.NoSuchElementException;

/** Class to implement interface StackInt as a linked list.
 *  @param <E> The type of elements in the stack
 *  @author Koffman & Wolfgang
 **/
public class LinkedStack<E> implements StackInt<E> {

    // Insert inner class Node<E> here (see Listing 2.1)
    /** A Node is the building block for a single-linked list. */
    private static class Node<E> {
        // Data Fields

        /** The reference to the data. */
        private final E data;
        /** The reference to the next node. */
        private final Node<E> next;

        // Constructors
        /**
         * Creates a new node with a null next field.
         * @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        /**
         * Creates a new node that references another node.
         * @param dataItem The data stored
         * @param nodeRef The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }
    //End of inserted class
    // Data Fields
    /** The reference to the first stack node. */
    private Node<E> topOfStackRef = null;

    /**
     * Insert a new item on top of the stack.
     * @post The new item is the top item on the stack.
     *       All other items are one position lower.
     * @param obj The item to be inserted
     * @return The item that was inserted
     */
    @Override
    public E push(E obj) {
        topOfStackRef = new Node<>(obj, topOfStackRef);
        return obj;
    }

    /**
     * Remove and return the top item on the stack.
     * @pre The stack is not empty.
     * @post The top item on the stack has been
     *       removed and the stack is one item smaller.
     * @return The top item on the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            E result = topOfStackRef.data;
            topOfStackRef = topOfStackRef.next;
            return result;
        }
    }

    /**
     * Return the top item on the stack.
     * @pre The stack is not empty.
     * @post The stack remains unchanged.
     * @return The top item on the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return topOfStackRef.data;
        }
    }

    /**
     * See whether the stack is empty.
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return topOfStackRef == null;
    }

// Insert solution to programming exercise 1, section 4, chapter 4 here
}
/*</listing>*/
