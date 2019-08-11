/*<listing chapter="6" section="3">*/
package KW.CH06;

import java.util.Scanner;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.function.BiConsumer;

/**
 * Class for a binary tree that stores type E objects.
 *
 * @param <E> The element type
 * @author Koffman and Wolfgang
 *
 */
public class BinaryTree<E> implements Serializable {

    /*<listing chapter="6" number="1">*/
    /**
     * Class to encapsulate a tree node.
     *
     * @param <E> The element type
     */
    protected static class Node<E> implements Serializable {
        // Data Fields

        /**
         * The information stored in this node.
         */
        public E data;
        /**
         * Reference to the left child.
         */
        public Node<E> left;
        /**
         * Reference to the right child.
         */
        public Node<E> right;

        // Constructors
        /**
         * Construct a node with given data and no children.
         *
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Returns a string representation of the node.
         *
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }

    }
    /*</listing>*/
    // Data Field
    /**
     * The root of the binary tree
     */
    protected Node<E> root;

    /**
     * Construct an empty BinaryTree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a BinaryTree with a specified root. Should only be used by
     * subclasses.
     *
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree as its left
     * subtree and rightTree as its right subtree.
     *
     * @param data The data item to store in the root
     * @param leftTree the left child
     * @param rightTree the right child
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
            BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     *
     * @return The left subtree or null if either the root or the left subtree
     * is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     *
     * @return the right sub-tree or null if either the root or the right
     * subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     *
     * @return the data field of the root or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        preOrderTraverse((e, d) -> {
            for (int i = 1; i < d; i++) {
                sb.append("  ");
            }
            sb.append(e);
            sb.append("\n");
        });
        return sb.toString();
    }

    /*<listing chapter="6" section="4">
    /**
     * Starter method for preorder traversal
     * @param consumer an object that instantiates the BiConsumer interface.
     *        Its method implements the abstract method apply.
     */
    public void preOrderTraverse(BiConsumer<E, Integer> consumer) {
        preOrderTraverse(root, 1, consumer);
    }

    /**
     * Performs a recursive pre-order traversal of the tree,
     * applying the action specified in the consumer object.
     * @param node The local root
     * @param depth The depth
     * @param consumer object whose accept method specifies the action 
     * to be performed on each node.
     */
    private void preOrderTraverse(Node<E> node, int depth, 
            BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth + 1, consumer);
            preOrderTraverse(node.right, depth + 1, consumer);
        }
    }
    /*</listing>*/

// Insert solution to programming exercise 4, section 4, chapter 6 here

// Insert solution to programming exercise 5, section 4, chapter 6 here

    /*<listing chapter="6" number="2">*/
    /**
     * Method to read a binary tree.
     *
     * @pre The input consists of a pre-order traversal of the binary tree. The
     * line "null" indicates a null tree.
     * @param scan the Scanner attached to the input file
     * @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }
    /*</listing>*/

// Insert solution to programming exercise 1, section 3, chapter 6 here

// Insert solution to programming exercise 2, section 3, chapter 6 here

// Insert solution to programming exercise 3, section 3, chapter 6 here

}
/*</listing>*/
